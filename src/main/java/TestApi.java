import yandex.cloud.api.ai.translate.v2.TranslationServiceGrpc;
import yandex.cloud.api.ai.translate.v2.TranslationServiceGrpc.TranslationServiceBlockingStub;
import yandex.cloud.api.ai.translate.v2.TranslationServiceOuterClass.TranslateRequest;
import yandex.cloud.api.ai.translate.v2.TranslationServiceOuterClass.TranslateResponse;
import yandex.cloud.sdk.ServiceFactory;
import yandex.cloud.sdk.auth.Auth;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class TestApi {

       /* HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://sa-translate.p.rapidapi.com/translate"))
                .header("content-type", "application/json")
                .header("x-rapidapi-key", "db1ecf38f4msh9ee2a1c0c517ff1p1eaa02jsn9c4e44007bf1")
                .header("x-rapidapi-host", "sa-translate.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\r\n    \"text\": \"Provide some text you would like to translate into another language\",\r\n    \"targetLanguage\": \"pt\"\r\n}"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());*/





    private static final String MY_YC_FOLDER_ID = "b1gd4v9h9o78f02ttbis";

    public static void main(String[] args) {
        // Configuration
        ServiceFactory factory = ServiceFactory.builder()
                .credentialProvider(Auth.oauthTokenBuilder().fromEnv("YC_OAUTH"))
                .requestTimeout(Duration.ofMinutes(1))
                .build();
        TranslationServiceBlockingStub translationService = factory.create(TranslationServiceBlockingStub.class, TranslationServiceGrpc::newBlockingStub);

        // Translate texts from English to Russian
        String text = "Привет брат";
        TranslateResponse response = translationService.translate(buildTranslateRequest(text));
        String translation = response.getTranslations(0).getText();
        System.out.println(String.format("%s -> %s", text, translation));
    }

    private static TranslateRequest buildTranslateRequest(String text) {
        return TranslateRequest.newBuilder()
                .setSourceLanguageCode("ru")
                .setTargetLanguageCode("en")
                .setFormat(TranslateRequest.Format.PLAIN_TEXT)
                .addTexts(text)
                .setFolderId(MY_YC_FOLDER_ID)
                .build();
    }




}
