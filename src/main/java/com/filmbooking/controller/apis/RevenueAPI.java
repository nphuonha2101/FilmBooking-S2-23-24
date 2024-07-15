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
import java.util.List;

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
            double total = 0;
            int count = 0;
            List<Revenue> revenues = revenueServiecs.calculateRevenueByYear(year);
            for (Revenue revenue : revenues) {
                total += revenue.getFilmRevenue();
                count += revenue.getTicketSold();
            }
            Revenue yearRevenue = new Revenue(year, count, total);
            result.add(yearRevenue);
            for (int i =1 ; i<13;i++){
                Revenue revenue = revenueServiecs.calculateRevenueByMonth(year, i+"");
                result.add(revenue);
            }
            Gson newGson = new Gson();
            jsonResp = newGson.toJson(result);

        } else if (command.equalsIgnoreCase("dates")) {
            String dateStart = req.getParameter("dateStart");
            dateStart = dateStart.replace("-", "/");
            String dateEnd = req.getParameter("dateEnd");
            dateEnd = dateEnd.replace("-", "/");
            List<Revenue> result = revenueServiecs.getByDates(dateStart, dateEnd);
            Gson newGson = new Gson();
            jsonResp = newGson.toJson(result);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResp);
    }
}
