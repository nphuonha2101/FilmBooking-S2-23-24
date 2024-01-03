package com.filmbooking.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class RedirectPageUtils {
    /**
     * Redirect to a page and save the current page to session for method {@link RedirectPageUtils#redirectPreviousPageIfExist(HttpServletRequest, HttpServletResponse)} 
     *
     * @param toPage              page to redirect to
     * @param currentQueryString current query string
     * @param req                 request used to get session
     * @param resp                response used to redirect
     * @throws IOException if redirect fails
     */
    public static void redirectPage(String toPage, String currentQueryString, HttpServletRequest req,
                                    HttpServletResponse resp) throws IOException {
        HttpSession userSession = req.getSession(false);
        String previousPageURI = PathUtils.getURLWithContextPath(req, req.getRequestURI());
        if (currentQueryString != null)
            previousPageURI += "?" + currentQueryString;
        System.out.println("previous page: " + previousPageURI);
        userSession.setAttribute("previousPage", previousPageURI);

        resp.sendRedirect(toPage);
    }

    /**
     * Redirect to previous page if existed, else redirect to home page
     *
     * @param req  request used to get session
     * @param resp response used to redirect
     * @throws IOException if redirect fails
     */
    public static void redirectPreviousPageIfExist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession userSession = req.getSession(false);
        String previousPage = (String) userSession.getAttribute("previousPage");
        System.out.println("previous page: " + previousPage);
        if (previousPage != null) {
            resp.sendRedirect(previousPage);
            return;
        }
        resp.sendRedirect(PathUtils.getURLWithContextPath(req, "/home"));
    }
}

