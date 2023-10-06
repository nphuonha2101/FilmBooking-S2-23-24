package com.filmbooking.controller.client;

import com.filmbooking.services.FilmGenreServicesImpl;
import com.filmbooking.services.FilmServicesImpl;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.ultils.ContextPathUtils;
import com.filmbooking.ultils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "home", value = "/home")
public class HomeController extends HttpServlet {
    private IFilmServices filmServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("dfhjdhf" + new FilmGenreServicesImpl().getAll().size());


        HttpSession userLoginSession = req.getSession();
        String loginUsername = (String) userLoginSession.getAttribute("username");
        filmServices = new FilmServicesImpl();

//            req.setAttribute("navigationComponent", ContextPathUtils.getComponentPagesPath("navigation-bar.jsp"));
        req.setAttribute("title", "Phim mới ra rạp");
        req.setAttribute("filmsData", filmServices.getAll());


        RenderViewUtils.updateView(req, resp, ContextPathUtils.getLayoutPath("master.jsp"));
        req.setAttribute("pageTitle", "Film Booking - Trang chủ");
        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("home.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));


    }
}
