package com.filmbooking.recaptchaV3Verification;

import com.filmbooking.utils.MultipartsFormUtils;
import com.filmbooking.utils.PropertiesUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public class RecaptchaVerification {
    private final String secretKey;
    //    private final String siteKey;
    private final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public RecaptchaVerification() {

//        this.siteKey = propertiesUtils.getProperty("recaptcha.site_key");
        this.secretKey = PropertiesUtils.getProperty("recaptcha.secret_key");
    }

    public boolean verify(HttpServletRequest req, HttpServletResponse resp) {
        List<String> requestParams = List.of("g-recaptcha-response");
        Map<String, String> paramsMap = new MultipartsFormUtils(req).getFormFields(requestParams);

        String recaptchaResponse = paramsMap.get("g-recaptcha-response");
        System.out.println("Captcha: " + recaptchaResponse);

        // Verify reCAPTCHA token
        String verifyUrl = SITE_VERIFY_URL + "?secret=" + this.secretKey + "&response=" + recaptchaResponse;

        // Send request to Google reCAPTCHA verification
        try {
            URL url = new URL(verifyUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setFixedLengthStreamingMode(0);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseStringBuilder = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                responseStringBuilder.append(line);
            }
            reader.close();

            // Parse JSON response
            RecaptchaResp captchaResponse = new Gson().fromJson(responseStringBuilder.toString(), RecaptchaResp.class);

            System.out.println(captchaResponse);

            return captchaResponse.isSuccess() && captchaResponse.getScore() >= 0.5;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
