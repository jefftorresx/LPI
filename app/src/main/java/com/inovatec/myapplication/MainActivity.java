package com.inovatec.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.bumptech.glide.Glide;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView tvResult;
    private ImageView imageViewProduct;
    private TextToSpeech textToSpeech;
    private static final String API_URL = "https://world.openfoodfacts.org/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnScan = findViewById(R.id.btnScan);
        tvResult = findViewById(R.id.tvResult);
        imageViewProduct = findViewById(R.id.imageViewProduct); // Inicializando o ImageView

        // Inicializa o TextToSpeech
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.getDefault());
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("MainActivity", "Idioma não suportado");
                    }
                } else {
                    Log.e("MainActivity", "Falha na inicialização do TextToSpeech");
                }
            }
        });

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(MainActivity.this).initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("MainActivity", "Cancelado");
                tvResult.setText("Cancelado");
            } else {
                Log.d("MainActivity", "Lido: " + result.getContents());
                tvResult.setText(result.getContents());
                fetchProductInfo(result.getContents());
            }
        }
    }

    private void fetchProductInfo(String barcode) {
        Log.d("MainActivity", "Buscando informações para o código: " + barcode);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApiService apiService = retrofit.create(MyApiService.class);
        Call<ProductInfo> call = apiService.getProductInfo(barcode);
        call.enqueue(new Callback<ProductInfo>() {
            @Override
            public void onResponse(Call<ProductInfo> call, Response<ProductInfo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProductInfo productInfo = response.body();
                    StringBuilder resultText = new StringBuilder();
                    String imageUrl = null;

                    if (productInfo.getProduct() != null) {
                        Product product = productInfo.getProduct();
                        resultText.append("Nome: ").append(product.getProductName()).append("\n");
                        resultText.append("Marca: ").append(product.getBrand()).append("\n");
                        resultText.append("Quantidade: ").append(product.getQuantity()).append("\n");
                        resultText.append("Calorias: ").append(product.getNutriments().getCalories()).append(" kcal\n");

                        if (product.getImageUrl() != null) {
                            imageUrl = product.getImageUrl();
                        }

                        // Enviar dados para a ResultActivity
                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        intent.putExtra("RESULT_TEXT", resultText.toString());
                        intent.putExtra("IMAGE_URL", imageUrl);
                        startActivity(intent);

                        // Falar o resultado do produto
                        speak(resultText.toString());

                    } else {
                        Log.d("MainActivity", "Produto não encontrado na resposta da API.");
                        tvResult.setText("Produto não encontrado.");
                    }
                } else {
                    Log.e("MainActivity", "Resposta não bem-sucedida: " + response.code());
                    tvResult.setText("Produto não encontrado.");
                }
            }

            @Override
            public void onFailure(Call<ProductInfo> call, Throwable t) {
                Log.e("MainActivity", "Erro: " + t.getMessage());
                tvResult.setText("Erro ao acessar a API.");
            }
        });
    }


    private void loadImage(String imageUrl) {
        Glide.with(this)
                .load(imageUrl)
                .into(imageViewProduct); // Carregar a imagem no ImageView
    }

    private void speak(String text) {
        if (textToSpeech != null) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
