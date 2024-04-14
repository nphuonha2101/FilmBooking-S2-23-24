package com.filmbooking.controller.admin.read;

import com.filmbooking.controller.apis.LogAPI;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.LogModel;
import com.filmbooking.services.impls.LogModelServicesImpl;
import com.filmbooking.utils.gsonUtils.GSONUtils;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "logManagement", value = "/admin/management/log")
public class LogManagementController extends HttpServlet {
    LogModelServicesImpl logModelServices;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        logModelServices = new LogModelServicesImpl(sessionProvider);
        Gson gson = GSONUtils.getGson();
        String jsonResp = "{\"data\": [";
        List<LogModel> list = logModelServices.getAll().getMultipleResults();

        for(LogModel logModel : list){
            jsonResp += gson.toJson(logModel);
            if (list.indexOf(logModel) != list.size() - 1) {
                jsonResp += ",";
            }
        }
        jsonResp += "]}";

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResp);

    }
}
