package com.filmbooking.controller.admin.read;


import com.filmbooking.services.impls.FilmServicesImpl;
import com.filmbooking.services.IFilmServices;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RenderViewUtils;
import com.filmbooking.hibernate.HibernateSessionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "filmManagement", value = "/film-management")
public class FilmManagementController extends HttpServlet {
    private IFilmServices filmServices;
    private HibernateSessionProvider hibernateSessionProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        filmServices= new FilmServicesImpl(hibernateSessionProvider);

        req.setAttribute("filmsData", filmServices.getAll());

        req.setAttribute("pageTitle", "filmManagementTitle");

        RenderViewUtils.renderViewToLayout(req, resp,
                ContextPathUtils.getAdminPagesPath("film-management.jsp"),
                ContextPathUtils.getLayoutPath("master.jsp"));

        hibernateSessionProvider.closeSession();

//        RenderViewUtils.updateView(req, resp,
//                ContextPathUtils.getLayoutPath("master.jsp"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void destroy() {
        filmServices = null;
        hibernateSessionProvider = null;
    }
}
