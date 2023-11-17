package com.filmbooking.controller.client;

import com.filmbooking.model.User;
import com.filmbooking.services.IShowtimeViewServices;
import com.filmbooking.services.impls.FilmGenreServicesImpl;
import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.services.impls.ShowtimeViewServicesImpl;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
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

        filmServices = new FilmServicesImpl();

//            req.setAttribute("navigationComponent", ContextPathUtils.getComponentPagesPath("navigation-bar.jsp"));
        req.setAttribute("title", "Phim mới ra rạp");
        req.setAttribute("filmsData", filmServices.getAll());

//        RenderViewUtils.updateView(req, resp, ContextPathUtils.getLayoutPath("master.jsp"));
        req.setAttribute("pageTitle", "homeTitle");
        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getClientPagesPath("home.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));


    }

    @Override
    public void destroy() {
        filmServices = null;
    }
}
