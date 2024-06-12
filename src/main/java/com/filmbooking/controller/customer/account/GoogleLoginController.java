package com.filmbooking.controller.customer.account;

import com.filmbooking.enumsAndConstants.enums.AccountRoleEnum;
import com.filmbooking.enumsAndConstants.enums.AccountTypeEnum;
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
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/google/login")
public class GoogleLoginController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = req.getParameter("error");
        String code = req.getParameter("code");
        if (error != null && error.equals("access_denied")) {
            resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/login"));
        }else {
            UserServicesImpl userServices = new UserServicesImpl();

            String accessToken = null;
            try {
                accessToken = getToken(code);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            GoogleUserInfo googleUserInfo = null;
            try {
                googleUserInfo = getUserInfo(accessToken);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            String id = googleUserInfo.getId();
            System.out.println(id);
            String userEmail = googleUserInfo.getEmail();
            String userFullName = googleUserInfo.getName();
            System.out.println(userEmail);

            User loginUser = userServices.getByEmail(userEmail);
            if (loginUser == null) {
                loginUser = new User(id, userFullName, userEmail, null, AccountRoleEnum.CUSTOMER, AccountTypeEnum.GOOGLE.getAccountType(),1);
                userServices.insert(loginUser);

            }
            HttpSession session = req.getSession();
            session.setAttribute("loginUser", loginUser);
            FilmBooking filmBooking = new FilmBooking();
            filmBooking.setUser(loginUser);
            session.setAttribute("filmBooking", filmBooking);

            resp.sendRedirect(WebAppPathUtils.getURLWithContextPath(req, resp, "/home"));
        }

    }

    private String getToken(final String code) throws IOException, ParseException {
        HttpPost httpPost = new HttpPost(PropertiesUtils.getProperty("link_get_token"));
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("client_id", PropertiesUtils.getProperty("client_id")));
        params.add(new BasicNameValuePair("client_secret", PropertiesUtils.getProperty("client_secret")));
        params.add(new BasicNameValuePair("redirect_uri", PropertiesUtils.getProperty("redirect_uri")));
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("grant_type", PropertiesUtils.getProperty("grant_type")));
        httpPost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpPost);

        String responseString = EntityUtils.toString(response.getEntity());
        JsonObject jobj = new Gson().fromJson(responseString, JsonObject.class);
        return jobj.get("access_token").toString().replaceAll("\"", "");
    }

    private GoogleUserInfo getUserInfo(final String accessToken) throws IOException, ParseException {
        HttpGet httpGet = new HttpGet(PropertiesUtils.getProperty("link_get_user_info") + accessToken);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpGet);

        String responseString = EntityUtils.toString(response.getEntity());
        return new Gson().fromJson(responseString, GoogleUserInfo.class);
    }

}
