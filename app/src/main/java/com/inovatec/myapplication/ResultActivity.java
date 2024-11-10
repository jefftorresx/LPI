package com.inovatec.myapplication;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ResultActivity extends AppCompatActivity {
    private TextView tvResult;
    private ImageView imageViewProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResult = findViewById(R.id.tvResult);
        imageViewProduct = findViewById(R.id.imageViewProduct);

        // Recupera os dados da Intent
        String resultText = getIntent().getStringExtra("RESULT_TEXT");
        String imageUrl = getIntent().getStringExtra("IMAGE_URL");

        tvResult.setText(resultText);

        if (imageUrl != null) {
            Glide.with(this).load(imageUrl).into(imageViewProduct);
        }
    }
}
