package com.filmbooking.controller.apis;

import com.filmbooking.model.Film;
import com.filmbooking.model.Revenue;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.impls.RevenueServiecsImpl;
import com.filmbooking.utils.gsonUtils.GSONUtils;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/api/v1/revenues/*", "/api/v1/revenues"})
public class RevenueAPI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RevenueServiecsImpl revenueServiecs = new RevenueServiecsImpl();
        String command = req.getParameter("command");
        String jsonResp = "";
        if (command.equalsIgnoreCase("year")) {
            String year = req.getParameter("year");
            List<Revenue> result = new ArrayList<>();
            Revenue yearRevenue = revenueServiecs.calculateRevenueByYear(year);
            result.add(yearRevenue);
            for (int i = 1; i < 13; i++) {
                Revenue revenue = revenueServiecs.calculateRevenueByMonth(i + "",year );
                result.add(revenue);
            }
            jsonResp = createJsonResponse(200, "Success", result);

        } else if (command.equalsIgnoreCase("dates")) {
            String dateStart = req.getParameter("dateStart");
            dateStart = dateStart.replace("-", "/");
            String dateEnd = req.getParameter("dateEnd");
            dateEnd = dateEnd.replace("-", "/");
            List<Revenue> result = revenueServiecs.getByDates(dateStart, dateEnd);
            jsonResp = createJsonResponse(200, "Success", result);
        } else {
            jsonResp = createJsonResponse(400, "Invalid command", null);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResp);
    }

    private String createJsonResponse(int status, String message, List<Revenue> data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        response.put("data", data);
        Gson gson = new Gson();
        return gson.toJson(response);
    }

}
