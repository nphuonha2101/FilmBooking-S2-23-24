package com.filmbooking.controller.httpStatus;

import com.filmbooking.page.ClientPage;
import com.filmbooking.page.Page;
import com.filmbooking.utils.WebAppPathUtils;
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

        Page httpStatusPage = new ClientPage(
                "http" + httpStatusCode + "Title",
                "http-status",
                "master"
        );

        httpStatusPage.putAttribute("httpErrorCode", httpStatusCode);
        httpStatusPage.putAttribute("httpErrorMessage", "http" + httpStatusCode);
        httpStatusPage.putAttribute("errorImgName", "httpStatusImg.svg");

        httpStatusPage.render(req, resp);
    }
}
