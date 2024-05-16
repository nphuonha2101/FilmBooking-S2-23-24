package com.filmbooking.controller;

import com.filmbooking.recaptchaV3Verification.RecaptchaVerification;
import com.filmbooking.utils.MultipartsFormUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@WebServlet("/verify-recaptcha-v3")
@MultipartConfig
public class ReCaptchaVerifyController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> requestAction = List.of("action");
        MultipartsFormUtils formUtils = new MultipartsFormUtils(req);
        String action = formUtils.getFormFields(requestAction).get("action");

        Map<String, String> paramValues = null;
        String handleControllerPath = "";

        // verify captcha
        boolean captchaVerified = new RecaptchaVerification().verify(req, resp);
        System.out.println("Captcha verified (Recaptcha controller): " + captchaVerified);
        req.getSession().setAttribute("captchaVerified", captchaVerified);

        switch (action) {
            case "login":
                req.getRequestDispatcher("/login").forward(req, resp);
                break;
            default:
                // do something
                break;
        }

    }

    private void sendDataToController(HttpServletRequest req, String handleControllerPath, Map<String, String> paramValues, String method) throws IOException {
        String servletContextPath = req.getServletContext().getContextPath();
        String scheme = req.getScheme(); // http
        String serverName = req.getServerName(); // hostname.com
        int serverPort = req.getServerPort(); // 8080

        String redirectPath = scheme + "://" + serverName + ":" + serverPort + servletContextPath + handleControllerPath;

        System.out.println("Redirect path: " + redirectPath);

        URL url = new URL(redirectPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod(method);

        // prepare data to send
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String> param : paramValues.entrySet()) {
            if (!postData.isEmpty()) postData.append('&');

            postData.append(param.getKey());
            postData.append('=');
            postData.append(URLEncoder.encode(param.getValue(), StandardCharsets.UTF_8));
        }

        // send data
        try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
            writer.write(postData.toString());
            writer.flush();
        }

        // Get the response
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code : " + responseCode);


    }

}
