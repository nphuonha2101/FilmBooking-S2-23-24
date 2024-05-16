package com.filmbooking.controller.customer.account;

import com.filmbooking.enumsAndConstants.enums.AccountRoleEnum;
import com.filmbooking.enumsAndConstants.enums.AccountTypeEnum;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.utils.WebAppPathUtils;
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

    private final PropertiesUtils propertiesUtils = PropertiesUtils.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = req.getParameter("error");
        String code = req.getParameter("code");
        if (error != null && error.equals("access_denied")) {
            resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/login"));
        }else {
            HibernateSessionProvider hibernateSessionProvider = new HibernateSessionProvider();
            UserServicesImpl userServices = new UserServicesImpl(hibernateSessionProvider);
            String accessToken = getToken(code);
            GoogleUserInfo googleUserInfo = getUserInfo(accessToken);
            String id = googleUserInfo.getId();
            System.out.println(id);
            String userEmail = googleUserInfo.getEmail();
            String userFullName = googleUserInfo.getName();
            System.out.println(userEmail);
            User loginUser = userServices.getByEmail(userEmail);
            if (loginUser == null) {
                loginUser = new User(id, userFullName, userEmail, null, AccountRoleEnum.CUSTOMER, AccountTypeEnum.GOOGLE.getAccountType(),1);
                userServices.save(loginUser);
            }
            HttpSession session = req.getSession();
            session.setAttribute("loginUser", loginUser);
            FilmBooking filmBooking = new FilmBooking();
            filmBooking.setUser(loginUser);
            session.setAttribute("filmBooking", filmBooking);

            resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/home"));
        }

    }

    private String getToken(final String code) throws IOException {
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
