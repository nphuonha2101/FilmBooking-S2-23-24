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
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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


        if (req.getSession().getAttribute("username") != null)
            resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/home"));
        else {
            String clientID = propertiesUtils.getProperty("client_id");
            String redirectURI = propertiesUtils.getProperty("redirect_uri");
            System.out.println(redirectURI);
            String google =
                    "https://accounts.google.com/o/oauth2/auth?scope=email%20profile" +
                    "&redirect_uri=" + redirectURI +
                    "&response_type=code" +
                    "&client_id=" + clientID +
                    "&approval_prompt=force";

            Page loginPage = new ClientPage(
                    "loginTitle",
                    "login",
                    "master",
                    Map.of("google", google)
            );
            loginPage.render(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> requestParams = new ArrayList<>();
        requestParams.add("username");
        requestParams.add("password");

        Map<String, String> paramsMap = new MultipartsFormUtils(req).getFormFields(requestParams);

        boolean captchaVerified = new RecaptchaVerification().verify(req, resp);
        System.out.println("Captcha verified: " + captchaVerified);

        if (captchaVerified) {

            hibernateSessionProvider = new HibernateSessionProvider();
            userServices = new UserServicesLogProxy<>(new UserServicesImpl(), req, hibernateSessionProvider);

            String username = StringUtils.handlesInputString(paramsMap.get("username"));
            String password = StringUtils.handlesInputString(paramsMap.get("password"));

            User loginUser = null;


            ServiceResult serviceResult = userServices.userAuthentication(username, password);
            if (serviceResult.getStatus() != StatusCodeEnum.FOUND_USER) {
                req.setAttribute("statusCodeErr", serviceResult.getStatus().getStatusCode());

                doGet(req, resp);
            } else {
                HttpSession session = req.getSession();
                loginUser = (User) serviceResult.getData();

                System.out.println(loginUser.getUserEmail() + " logged in");

                session.setAttribute("loginUser", loginUser);
                FilmBooking filmBooking = new FilmBooking();
                filmBooking.setUser(loginUser);
                session.setAttribute("filmBooking", filmBooking);

                /* return to previous page that was visited before login
                 * if it has no previous page, return to home page
                 */
                RedirectPageUtils.redirectPreviousPageIfExist(req, resp);


            }
        } else {
            req.setAttribute("statusCodeErr", StatusCodeEnum.RECAPTCHA_VERIFICATION_ERROR.getStatusCode());
            doGet(req, resp);
        }
        hibernateSessionProvider.closeSession();

    }

    @Override
    public void destroy() {
        userServices = null;
        hibernateSessionProvider = null;
    }
}