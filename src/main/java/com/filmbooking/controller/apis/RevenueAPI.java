package com.filmbooking.controller.apis;

import com.filmbooking.model.Revenue;
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
import java.util.List;

@WebServlet(urlPatterns = {"/api/v1/revenues/*", "/api/v1/revenues"})
public class RevenueAPI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Revenue API");
        RevenueServiecsImpl revenueServiecs = new RevenueServiecsImpl();
        String command = req.getParameter("command");
            if (command.equalsIgnoreCase("year")) {
                String year = req.getParameter("year");
                List<Double> revenues = new ArrayList<>();
                System.out.println(year);
                double resultYear = revenueServiecs.calculateRevenueByYear(year);
                revenues.add(resultYear);
                System.out.println(resultYear);
                for (int i = 1; i < 13; i++) {
                    revenues.add(revenueServiecs.calculateRevenueByMonth(year, "0" + i));
                }
                Gson gson = GSONUtils.getGson();
                String jsonResp = gson.toJson(revenues);
                System.out.println(jsonResp);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(jsonResp);
            } else if (command.equalsIgnoreCase("dates")) {
                String dateStart = req.getParameter("dateStart");
                dateStart = dateStart.replace("-", "/");
                String dateEnd = req.getParameter("dateEnd");
                dateEnd = dateEnd.replace("-", "/");
                System.out.println(dateStart);
                System.out.println(dateEnd);
                double result = revenueServiecs.calculateRevenueByDate(dateStart, dateEnd);
                Gson gson = GSONUtils.getGson();
                String jsonResp = gson.toJson(result);
                System.out.println(jsonResp);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(jsonResp);
        }
    }
}
