package com.filmbooking.controller.customer.account;

import com.filmbooking.enumsAndConstants.enums.AccountRoleEnum;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.services.serviceResult.ServiceResult;
import com.filmbooking.utils.RedirectPageUtils;
import com.filmbooking.utils.WebAppPathUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import com.filmbooking.model.GoogleUserInfo;
import com.filmbooking.utils.PropertiesUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

@WebServlet("/google/login")
public class GoogleLoginController extends HttpServlet {

    private UserServicesImpl userServices;
    private HibernateSessionProvider hibernateSessionProvider;

    private PropertiesUtils propertiesUtils = PropertiesUtils.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = req.getParameter("error");
        String code = req.getParameter("code");
        if (error != null && error.equals("access_denied")) {
            resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/login"));
        }else {
            hibernateSessionProvider = new HibernateSessionProvider();
            userServices = new UserServicesImpl(hibernateSessionProvider);
            String accessToken = getToken(code);
            GoogleUserInfo googleUserInfo = getUserInfo(accessToken);
            String id = googleUserInfo.getId();
            System.out.println(id);
            String userEmail = googleUserInfo.getEmail();
            String userFullName = googleUserInfo.getName();
            System.out.println(userEmail);
            if (userServices.getByEmail(userEmail) == null) {
                User newUser = new User(id, userFullName, userEmail, id, AccountRoleEnum.CUSTOMER);
                userServices.save(newUser);
                HttpSession session = req.getSession();
                User loginUser = userServices.getByEmail(userEmail);
                session.setAttribute("loginUser", loginUser);
                FilmBooking filmBooking = new FilmBooking();
                filmBooking.setUser(loginUser);
                session.setAttribute("filmBooking", filmBooking);
            } else {
                HttpSession session = req.getSession();
                User loginUser = userServices.getByEmail(userEmail);
                session.setAttribute("loginUser", loginUser);
                FilmBooking filmBooking = new FilmBooking();
                filmBooking.setUser(loginUser);
                session.setAttribute("filmBooking", filmBooking);
            }
            resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/home"));
        }

    }

    private String getToken(final String code) throws ClientProtocolException, IOException {
        String response = Request.Post(propertiesUtils.getProperty("link_get_token")).bodyForm(Form.form().add("client_id", propertiesUtils.getProperty("client_id"))
                        .add("client_secret", propertiesUtils.getProperty("client_secret"))
                        .add("redirect_uri",propertiesUtils.getProperty("redirect_uri")).add("code", code)
                        .add("grant_type", propertiesUtils.getProperty("grant_type")).build())
                .execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        return jobj.get("access_token").toString().replaceAll("\"", "");
    }
    private GoogleUserInfo getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = propertiesUtils.getProperty("link_get_user_info") + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        return new Gson().fromJson(response, GoogleUserInfo.class);
    }
}
