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
        FilmServicesImpl filmServices = new FilmServicesImpl();


        String command = req.getParameter("command");
        String jsonResp = "";
        Gson gson = GSONUtils.getGson();
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
            jsonResp = gson.toJson(revenues);

        } else if (command.equalsIgnoreCase("dates")) {
            String dateStart = req.getParameter("dateStart");
            dateStart = dateStart.replace("-", "/");
            String dateEnd = req.getParameter("dateEnd");
            dateEnd = dateEnd.replace("-", "/");
            System.out.println(dateStart);
            System.out.println(dateEnd);
            double result = revenueServiecs.calculateRevenueByDate(dateStart, dateEnd);
            jsonResp = gson.toJson(result);
        }else if (command.equalsIgnoreCase("films")){
           List<Film> films = filmServices.selectAll();
           List<Revenue> revenues = new ArrayList<>();
                for (Film film : films) {
                    double revenue = revenueServiecs.calculateRevenueByFilm(film.getFilmID());
                    revenues.add(new Revenue(film.getFilmName(), revenue));
                }
                for (Revenue revenue : revenues) {
                    System.out.println(revenue.getFilmName());
                    System.out.println(revenue.getFilmRevenue());
                }
                Gson newGson = new Gson();
            jsonResp = newGson.toJson(revenues);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResp);
    }
}
