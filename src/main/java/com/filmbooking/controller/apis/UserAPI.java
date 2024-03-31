package com.filmbooking.controller.apis;

import java.io.IOException;
import java.util.List;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Room;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.utils.GSONUtils;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/api/v1/users/*", "/api/v1/users" })
public class UserAPI extends HttpServlet {
    UserServicesImpl userServicesImpl;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        userServicesImpl = new UserServicesImpl(sessionProvider);
        Gson gson = GSONUtils.getGson();
        String jsonResp = "";
        String id = req.getParameter("user-id");
        if (id != null) {
            User user = userServicesImpl.getByID(id);
            jsonResp += gson.toJson(user);
        } else {
            List<User> userList = userServicesImpl.getAll().getMultipleResults();
            jsonResp += "[";
            for (User user : userList) {
                jsonResp += gson.toJson(user);
                if (userList.indexOf(user) != userList.size() - 1) {
                    jsonResp += ",";
                }
            }
            jsonResp += "]";
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(jsonResp);
    }
}
