package com.filmbooking.controller.customer.account;

import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.model.TokenModel;
import com.filmbooking.services.impls.TokenServicesImpl;
import com.filmbooking.services.serviceResult.ServiceResult;
import com.filmbooking.utils.WebAppPathUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */

@WebServlet("/token/verify")
public class VerifyUserToken extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        String username = req.getParameter("username");
        String tokenType = req.getParameter("token-type");

        TokenServicesImpl tokenServices = new TokenServicesImpl();

        // verify token
        TokenModel tokenModel = tokenServices.getToken(token, username, tokenType);
        ServiceResult serviceResult = tokenServices.verifyToken(tokenModel);

        if (serviceResult.getStatus().equals(StatusCodeEnum.TOKEN_NOT_FOUND)) {
            req.setAttribute("statusCodeErr", StatusCodeEnum.TOKEN_NOT_FOUND.getStatusCode());
            req.setAttribute("verifyStatus","token-not-found" );
            req.getRequestDispatcher(WebAppPathUtils.getURLWithContextPath(req, resp, "/reset-password"))
                    .forward(req, resp);
            return;
        }


        // case token type is password reset
        if (tokenType.equals("PASSWORD_RESET")) {
            if (serviceResult.getStatus().equals(StatusCodeEnum.TOKEN_VERIFIED)) {
                req.setAttribute("verifyStatus", "token-verified");
                req.setAttribute("username", username);
                // send token to session
                req.getSession(false).setAttribute("token", tokenModel);
                req.getRequestDispatcher(WebAppPathUtils.getURLWithContextPath(req, resp, "/reset-password"))
                        .forward(req, resp);
                return;
            }
            if (serviceResult.getStatus().equals(StatusCodeEnum.TOKEN_EXPIRED)) {
                req.setAttribute("verifyStatus", "token-expired");
                req.setAttribute("statusCodeErr", StatusCodeEnum.TOKEN_EXPIRED.getStatusCode());
                req.getRequestDispatcher(WebAppPathUtils.getURLWithContextPath(req, resp, "/reset-password"))
                        .forward(req, resp);
                // remove token from session if token is expired
                req.getSession(false).removeAttribute("token");
                return;
            }
        }
    }
}
