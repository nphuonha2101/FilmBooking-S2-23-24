package com.filmbooking.controller.customer.account;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.page.ClientPage;
import com.filmbooking.page.Page;
import com.filmbooking.recaptchaV3Verification.RecaptchaVerification;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.services.logProxy.UserServicesLogProxy;
import com.filmbooking.services.serviceResult.ServiceResult;
import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.utils.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "login", value = "/login")
@MultipartConfig
public class LoginController extends HttpServlet {
    private UserServicesLogProxy<User> userServices;
    private HibernateSessionProvider hibernateSessionProvider;
    private final PropertiesUtils propertiesUtils = PropertiesUtils.getInstance();
    private final String VIEW_PATH = WebAppPathUtils.getClientPagesPath("login.jsp");
    private final String LAYOUT_PATH = WebAppPathUtils.getLayoutPath("master.jsp");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if (req.getSession().getAttribute("loginUser") != null)
            resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/home"));
        else {
            String clientID = propertiesUtils.getProperty("client_id");
            String redirectURI = propertiesUtils.getProperty("redirect_uri");
            System.out.println(redirectURI);
            Page loginPage = getPage(redirectURI, clientID);
            loginPage.render(req, resp);
        }
    }

    private Page getPage(String redirectURI, String clientID) {
        String google =
                "https://accounts.google.com/o/oauth2/auth?scope=email%20profile" +
                        "&redirect_uri=" + redirectURI +
                        "&response_type=code" +
                        "&client_id=" + clientID +
                        "&approval_prompt=force";

        return new ClientPage(
                "loginTitle",
                "login",
                "master",
                Map.of("google", google)
        );
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Page loginPage = new ClientPage(
                "loginTitle",
                "login",
                "master"
        );
        boolean isCaptchaVerified = req.getSession().getAttribute("captchaVerified") != null && (boolean) req.getSession().getAttribute("captchaVerified");

        String username = StringUtils.handlesInputString(req.getParameter("usernameOrEmail"));
        String password = StringUtils.handlesInputString(req.getParameter("password"));

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        if (!isCaptchaVerified) {
            loginPage.putError(StatusCodeEnum.RECAPTCHA_VERIFICATION_ERROR.getStatusCode());
            getHtmlRespFromPage(req, resp, loginPage);
            return;
        }

        hibernateSessionProvider = new HibernateSessionProvider();
        userServices = new UserServicesLogProxy<>(new UserServicesImpl(), req, hibernateSessionProvider);


        User loginUser = null;

        // user authentication
        ServiceResult serviceResult = userServices.userAuthentication(username, password);
        // case user not found then send error to login page
        if (serviceResult.getStatus() != StatusCodeEnum.FOUND_USER) {
            loginPage.putError(serviceResult.getStatus().getStatusCode());

            getHtmlRespFromPage(req, resp, loginPage);

        } else {
            HttpSession session = req.getSession();
            loginUser = (User) serviceResult.getData();

            System.out.println(loginUser.getUserEmail() + " logged in");

            session.setAttribute("loginUser", loginUser);
            FilmBooking filmBooking = new FilmBooking();
            filmBooking.setUser(loginUser);
            session.setAttribute("filmBooking", filmBooking);

            System.out.println("Test login found user");

            /* return to previous page that was visited before login
             * if it has no previous page, return to home page
             */
//            RedirectPageUtils.redirectPreviousPageIfExist(req, resp);

            PrintWriter out = resp.getWriter();
            resp.setContentType("text/plain");
            out.println("/home");

        }


        hibernateSessionProvider.closeSession();

    }

    private void getHtmlRespFromPage(HttpServletRequest req, HttpServletResponse resp, Page page) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = page.render(req, resp);
        StringWriter stringWriter = new StringWriter();
        requestDispatcher.include(req, new HttpServletResponseWrapper(resp) {
            @Override
            public PrintWriter getWriter() {
                return new PrintWriter(stringWriter);
            }
        });
        resp.setContentType("text/html");
        resp.getWriter().write(stringWriter.toString());
    }

    @Override
    public void destroy() {
        userServices = null;
        hibernateSessionProvider = null;
    }
}