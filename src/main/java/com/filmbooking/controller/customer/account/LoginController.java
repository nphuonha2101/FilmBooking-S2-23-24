package com.filmbooking.controller.customer.account;

import com.filmbooking.email.AbstractSendEmail;
import com.filmbooking.email.SendFailLogin5TimesEmail;
import com.filmbooking.enumsAndConstants.enums.LanguageEnum;
import com.filmbooking.model.FailedLogin;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.page.ClientPage;
import com.filmbooking.page.Page;
import com.filmbooking.services.impls.FailedLoginServicesImpl;
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
import java.time.LocalDateTime;
import java.util.Map;

@WebServlet(name = "login", value = "/login")
@MultipartConfig
public class LoginController extends HttpServlet {
    private UserServicesLogProxy userServices;

    private UserServicesImpl userServicesimpl ;
    private final String VIEW_PATH = WebAppPathUtils.getClientPagesPath("login.jsp");
    private final String LAYOUT_PATH = WebAppPathUtils.getLayoutPath("master.jsp");
    int countFailedLogin = 0 ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("loginUser") != null)
            resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/home"));
        else {
            String clientID = PropertiesUtils.getProperty("client_id");
            String redirectURI = PropertiesUtils.getProperty("redirect_uri");
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

        // check if user login failed
        FailedLoginServicesImpl failedLoginServices = new FailedLoginServicesImpl();
        FailedLogin failedLogin = failedLoginServices.select(req.getRemoteAddr());


        if (failedLogin != null) {
            if (failedLogin.getLockTime().isAfter(LocalDateTime.now())) {
                System.out.println("Login after 5 minutes");
                loginPage.putError(StatusCodeEnum.LOGIN_AGAIN_AFTER_5_MINUTES.getStatusCode());
                getHtmlRespFromPage(req, resp, loginPage);
                return;
            }

        }

        // verify captcha
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

        userServices = new UserServicesLogProxy(new UserServicesImpl(), req);
        userServicesimpl = new UserServicesImpl();
        String currentLanguage = (String) req.getSession().getAttribute("lang");

        User loginUser = null;

        // user authentication
        ServiceResult serviceResult = userServices.userAuthentication(username, password);
        // case user not found then send error to login page
        if (serviceResult.getStatus() != StatusCodeEnum.FOUND_USER) {
            if (failedLogin == null) {
                failedLogin = new FailedLogin();
                failedLogin.setReqIp(req.getRemoteAddr());
                failedLogin.setLoginCount(1);
                failedLogin.setLockTime(LocalDateTime.now());
                failedLoginServices.insert(failedLogin);
            } else {
                failedLoginServices.update(failedLogin);
                if(failedLogin.getLoginCount() >= 5){
                    User user = userServicesimpl.getByUsername(username);
                    sendFailLoginEmail(username, failedLogin.getLockTime().toString(),failedLogin.getReqIp(), user.getUserEmail(), currentLanguage);
                }
            }
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

            failedLoginServices.delete(failedLogin);

            /* return to previous page that was visited before login
             * if it has no previous page, return to home page
             */
//            RedirectPageUtils.redirectPreviousPageIfExist(req, resp);

            PrintWriter out = resp.getWriter();
            resp.setContentType("text/plain");
            out.println("/home");

        }
    }

    private static void sendFailLoginEmail(String username, String lockTime, String reqIp, String email,String language) {
        LanguageEnum languageEnum = language == null || language.equals("default") ? LanguageEnum.VIETNAMESE
                : LanguageEnum.ENGLISH;
        AbstractSendEmail emailSender = new SendFailLogin5TimesEmail();
        emailSender
                .loadHTMLEmail(languageEnum)
                .putEmailInfo("username", username)
                .putEmailInfo("lockTime", lockTime)
                .putEmailInfo("reqIp", reqIp)
                .loadEmailContent()
                .sendEmailToUser(email, "Your account has been locked");
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
    }
}
