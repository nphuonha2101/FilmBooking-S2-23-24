package com.filmbooking.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class RedirectPageUtils {
    public static void redirectPage(String toPage, String currentQueryString, HttpServletRequest req,
                                    HttpServletResponse resp) throws IOException {
        HttpSession userSession = req.getSession(false);
        String previousPageURI = req.getRequestURI();
        if (currentQueryString != null)
            previousPageURI += "?" + currentQueryString;
        System.out.println("previous page: " + previousPageURI);
        userSession.setAttribute("previousPage", previousPageURI);

        resp.sendRedirect(toPage);
    }

    public static void redirectPreviousPageIfExist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession userSession = req.getSession(false);
        String previousPage = (String) userSession.getAttribute("previousPage");
        System.out.println("previous page: " + previousPage);
        if (previousPage != null) {
            resp.sendRedirect(previousPage);
            return;
        }
        resp.sendRedirect("home");
    }
}
