package com.filmbooking.controller.customer.account;

import com.filmbooking.hibernate.HibernateSessionProvider;
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
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/facebook/login")
public class FacebookLoginController extends HttpServlet {
    private static final String GRAPH_API_URL = "https://graph.facebook.com/v12.0/me?fields=email,name";
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

        }

    }

    private String getToken(final String code)throws IOException{
        String response = Request.Post(propertiesUtils.getProperty("link_get_token")).bodyForm(Form.form().add("client_id", propertiesUtils.getProperty("client_id"))
                        .add("client_secret", propertiesUtils.getProperty("client_secret"))
                        .add("redirect_uri",propertiesUtils.getProperty("redirect_uri")).add("code", code)
                        .add("grant_type", propertiesUtils.getProperty("grant_type")).build())
                .execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        return jobj.get("access_token").toString().replaceAll("\"", "");
    }
    private JsonObject getUserInfo(String accessToken) {
        try {
            // Tạo URL cho yêu cầu API Facebook với accessToken và các trường cần lấy thông tin (email, name)
            var url = new URL(GRAPH_API_URL + "&access_token=" + accessToken);
            // Mở kết nối HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Thiết lập phương thức là GET
            connection.setRequestMethod("GET");

            // Đọc dữ liệu trả về từ API Facebook
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Chuyển đổi chuỗi JSON thành đối tượng JsonObject bằng Gson
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(response.toString()).getAsJsonObject();

            // Trả về thông tin người dùng dưới dạng JsonObject
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
