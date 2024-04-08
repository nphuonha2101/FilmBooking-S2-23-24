package com.filmbooking.controller.customer.account;

import com.filmbooking.enumsAndConstants.constants.PathConstant;
import com.filmbooking.enumsAndConstants.enums.AccountRoleEnum;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FacebookUserInfo;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.utils.PropertiesUtils;
import com.filmbooking.utils.WebAppPathUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static sun.rmi.transport.TransportConstants.Version;

@WebServlet("/facebook/login")
public class FacebookLoginController extends HttpServlet {
    private UserServicesImpl userServices;
    private HibernateSessionProvider sessionProvider;
    private final PropertiesUtils propertiesUtils = PropertiesUtils.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = req.getParameter("error");
        String code = req.getParameter("code");
        if (error != null && error.equals("access_denied")) {
            // TODO: handle error when login failed
            resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/login"));
        }else{
            sessionProvider = new HibernateSessionProvider();
            userServices = new UserServicesImpl(sessionProvider);
            String accessToken = getToken(code);
            FacebookUserInfo userInfo = getUserInfo(accessToken);
            String username = userInfo.getUsername();
            String email = userInfo.getEmail();
            if(userServices.getByEmail(email) == null){
                User newUser = new User(email,username,email,null, AccountRoleEnum.CUSTOMER);
                userServices.save(newUser);
                HttpSession session = req.getSession();
                User loginUser = userServices.getByEmail(email);
                session.setAttribute("loginUser", loginUser);
                FilmBooking filmBooking = new FilmBooking();
                session.setAttribute("filmBooking", filmBooking);
            }else{
                HttpSession session = req.getSession();
                User loginUser = userServices.getByEmail(email);
                session.setAttribute("loginUser", loginUser);
                FilmBooking filmBooking = new FilmBooking();
                filmBooking.setUser(loginUser);
                session.setAttribute("filmBooking", filmBooking);
            }

        }
        resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/home"));
    }
    public static String getToken(final String code) throws ClientProtocolException, IOException {
        String link = String.format(PathConstant.FACEBOOK_LINK_GET_TOKEN, PathConstant.FACEBOOK_APP_ID, PathConstant.FACEBOOK_APP_SECRET, PathConstant.FACEBOOK_REDIRECT_URL, code);
        String response = Request.Get(link).execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static FacebookUserInfo getUserInfo(String accessToken) throws ClientProtocolException, IOException{
        String url = "https://graph.facebook.com/me?fields=name,email&access_token=" + accessToken;
        String response = Request.Get(url).execute().returnContent().asString();
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        FacebookUserInfo userInfo = new FacebookUserInfo();
        userInfo.setUsername(jsonObject.get("name").getAsString());
        if (jsonObject.has("email")) {
            userInfo.setEmail(jsonObject.get("email").getAsString());
        } else {
            userInfo.setEmail(null);
        }
        return userInfo;
    }
}
