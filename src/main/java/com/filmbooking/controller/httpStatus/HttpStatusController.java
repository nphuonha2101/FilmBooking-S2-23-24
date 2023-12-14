package com.filmbooking.controller.httpStatus;

import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/http-status")
public class HttpStatusController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int httpStatusCode = resp.getStatus();

        req.setAttribute("errorImgName", "httpStatusImg.svg");

        req.setAttribute("httpErrorCode", httpStatusCode);
        req.setAttribute("httpErrorMessage", "http" + httpStatusCode);
        req.setAttribute("pageTitle", "http" + httpStatusCode + "Title");

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getErrorPagesPath("error.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));
    }
}
