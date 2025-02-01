# Leitor de Produtos Inteligente 📱👁️

Aplicativo Android para identificar produtos via código de barras, reconhecimento de imagem e conversão de texto em fala, com foco em acessibilidade.

## 🚀 Funcionalidades Principais

- **Leitura de Código de Barras/QR Code**  
  Scanner integrado para identificar produtos usando a câmera do dispositivo.

- **Consulta à API OpenFoodFacts**  
  Busca detalhes como nome, marca, calorias e categoria do produto.

- **Carregamento de Imagens**  
  Exibe a imagem do produto a partir da URL retornada pela API.

- **Texto para Fala (TTS)**  
  Converte informações do produto em áudio para auxiliar usuários com deficiência visual.

## 🛠️ Tecnologias e Bibliotecas

| Biblioteca       | Versão  | Função                                     |
|-----------------|---------|--------------------------------------------|
| ZXing          | 4.3.0   | Leitura de códigos de barras e QR Codes.   |
| Retrofit       | 2.9.0   | Chamadas HTTP para a API OpenFoodFacts.    |
| Gson Converter | 2.9.0   | Conversão de JSON para objetos Java.       |
| Glide          | 4.13.2  | Carregamento dinâmico de imagens.          |
| TextToSpeech   | (SDK)   | Conversão de texto em voz.                 |

## 📦 Instalação e Configuração

### Clone o repositório:
```sh
git clone https://github.com/jefftorresx/LPI.git  
```

### Adicione permissões no `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.INTERNET" />
```

### Adicione as dependências no `build.gradle`:
```gradle
dependencies {
    implementation 'com.journeyapps:zxing-android-embedded:4.3.0'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.github.bumptech.glide:glide:4.13.2'
}
```

**Requisitos Mínimos:** Android 5.0 (API 21) ou superior.

## 🖥️ Como Usar

1. **Iniciar o Scanner:**  
   Toque no botão "Escanear" para ativar a câmera e ler um código de barras.

2. **Resultado da Consulta:**  
   As informações do produto (nome, marca, calorias) são exibidas e lidas em voz alta.

3. **Detalhes do Produto:**  
   Toque em "Ver Detalhes" para acessar a imagem do produto e informações nutricionais completas.

## 🔗 Integração com a API OpenFoodFacts

**Endpoint:**
```sh
https://world.openfoodfacts.org/api/v0/product/{barcode}.json
```
Substitua `{barcode}` pelo código escaneado.

**Exemplo de Resposta JSON:**
```json
{
  "product": {
    "product_name": "Nome do Produto",
    "brands": "Marca",
    "nutriments": { "energy-kcal_100g": 250 },
    "image_url": "https://exemplo.com/imagem.jpg"
  }
}
```

## 🧩 Estrutura do Projeto

- **MainActivity:** Controla o scanner e exibe informações básicas.
- **ResultActivity:** Mostra detalhes completos e imagem do produto.
- **MyApiService:** Configura o Retrofit para consultar a API.
- **Modelos de Dados:**
  - `ProductInfo`: Contém todos os dados do produto.
  - `Nutriments`: Armazena informações nutricionais.

## 🔮 Melhorias Futuras

- Modo offline com cache de produtos consultados.
- Suporte a comandos de voz para iniciar o scanner.
- Tradução automática das informações para outros idiomas.

## 🤝 Contribuição

Contribuições são bem-vindas! Siga os passos:

1. Abra uma issue descrevendo sua sugestão.
2. Faça um fork do projeto e envie um pull request.

## 📄 Licença

Distribuído sob a licença MIT. Veja [LICENSE](LICENSE) para detalhes.

## 🙌 Créditos

- **Dados de produtos:** OpenFoodFacts.  
- **Bibliotecas:** ZXing (scanner), Retrofit (API), Glide (imagens).  
- **Desenvolvido por:** Jeffrey 🚀  
📧 **Contato:** jeff.devcontato@gmail.com  
- **Ideia by:** Claudiane  


---


