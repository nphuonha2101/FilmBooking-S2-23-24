package com.filmbooking.controller.apis;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Genre;
import com.filmbooking.model.LogModel;
import com.filmbooking.services.impls.LogModelServicesImpl;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import com.filmbooking.utils.GSONUtils;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/api/v1/logs/*", "/api/v1/logs" })
public class LogAPI extends HttpServlet{
    LogModelServicesImpl logModelServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        logModelServices = new LogModelServicesImpl(sessionProvider);
        Gson gson = GSONUtils.getGson();
        String jsonResp = "";
        String id = req.getParameter("log-id");
        if (id != null) {
            LogModel logModel = (LogModel) logModelServices.getByID(id);
            jsonResp = gson.toJson(logModel);
        } else {
            List<LogModel> logModelList = logModelServices.getAll().getMultipleResults();

            jsonResp = "[";

            for (LogModel logModel : logModelList) {
                jsonResp += gson.toJson(logModel);
                if (logModelList.indexOf(logModel) != logModelList.size() - 1) {
                    jsonResp += ",";
                }
            }
            jsonResp += "]";
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResp);
    }
}
