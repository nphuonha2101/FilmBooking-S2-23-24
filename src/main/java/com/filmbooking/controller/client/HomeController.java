package com.filmbooking.controller.client;

import com.filmbooking.DAOservices.FilmDAOServicesImpl;
import com.filmbooking.DAOservices.IFilmDAOServices;
import com.filmbooking.model.Film;
import com.filmbooking.ultils.ContextPathUtils;
import com.filmbooking.ultils.RenderViewUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="home", value = "/home")
public class HomeController extends HttpServlet {
    private IFilmDAOServices filmDAOServices;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession userLoginSession = req.getSession();
        String loginUsername = (String) userLoginSession.getAttribute("username");
        filmDAOServices = new FilmDAOServicesImpl();
        if (loginUsername != null) {
//            req.setAttribute("navigationComponent", ContextPathUtils.getComponentPagesPath("already-login-nav.jsp"));
            req.setAttribute("title", "Phim mới ra rạp");
            req.setAttribute("filmsData", filmDAOServices.getAll());

            RenderViewUtils.updateView(req, resp, ContextPathUtils.getLayoutPath("master.jsp"));
            req.setAttribute("pageTitle", "Film Booking - Trang chủ");
            RenderViewUtils.renderViewToLayout(req, resp,
                    ContextPathUtils.getClientPagesPath("home.jsp"),
                    ContextPathUtils.getLayoutPath("master.jsp"));


        }
        else {
            resp.sendRedirect("login");

        }

    }
}
