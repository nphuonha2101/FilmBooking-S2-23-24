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


}
