package com.filmbooking.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RenderViewUtils {
    /**
     * Render a view to a layout by setting dynamicContents as a viewPath to the layoutPath
     * @param request       used to set attribute and used to forward request
     * @param response      used to forward request
     * @param viewPath      a view path
     * @param layoutPath    a layout path
     */
    public static void renderViewToLayout(HttpServletRequest request, HttpServletResponse response, String viewPath, String layoutPath) {
        request.setAttribute("dynamicContents", viewPath);
        try {
            request.getRequestDispatcher(layoutPath).forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
