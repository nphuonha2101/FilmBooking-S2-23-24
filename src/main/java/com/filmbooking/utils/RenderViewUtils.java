package com.filmbooking.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RenderViewUtils {
    public static void renderViewToLayout(HttpServletRequest request, HttpServletResponse response, String viewPath, String layoutPath) {
        request.setAttribute("dynamicContents", viewPath);
        try {
            request.getRequestDispatcher(layoutPath).forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateView(HttpServletRequest request, HttpServletResponse response, String viewPath) {
        try {
            request.getRequestDispatcher(viewPath).include(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
