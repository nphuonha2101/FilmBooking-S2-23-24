package com.filmbooking.controller.client.account;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.services.serviceResult.ServiceResult;
import com.filmbooking.statusEnums.StatusCodeEnum;
import com.filmbooking.utils.ContextPathUtils;
import com.filmbooking.utils.RedirectPageUtils;
import com.filmbooking.utils.RenderViewUtils;
import com.filmbooking.utils.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet {
    private UserServicesImpl userServices;
    private HibernateSessionProvider hibernateSessionProvider;
    private final String VIEW_PATH = ContextPathUtils.getClientPagesPath("login.jsp");
    private final String LAYOUT_PATH = ContextPathUtils.getLayoutPath("master.jsp");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("username") != null)
            resp.sendRedirect("home");
        else {
            req.setAttribute("pageTitle", "loginTitle");
            RenderViewUtils.renderViewToLayout(req, resp, VIEW_PATH, LAYOUT_PATH);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hibernateSessionProvider = new HibernateSessionProvider();
        userServices = new UserServicesImpl(hibernateSessionProvider);

        String username = StringUtils.handlesInputString(req.getParameter("username"));
        String password = StringUtils.handlesInputString(req.getParameter("password"));

        User loginUser = null;


        ServiceResult serviceResult = userServices.userAuthentication(username, password);
        if (serviceResult.getStatus() != StatusCodeEnum.FOUND_USER) {
            req.setAttribute("pageTitle", "loginTitle");
            req.setAttribute("statusCodeErr", serviceResult.getStatus().getStatusCode());

            RenderViewUtils.renderViewToLayout(req, resp, VIEW_PATH, LAYOUT_PATH);
        } else {
            HttpSession session = req.getSession();
            loginUser = (User) serviceResult.getData();

            session.setAttribute("loginUser", loginUser);
            FilmBooking filmBooking = new FilmBooking();
            filmBooking.setUser(loginUser);
            session.setAttribute("filmBooking", filmBooking);

            /* return to previous page that was visited before login
             * if it has no previous page, return to home page
             */
            RedirectPageUtils.redirectPreviousPageIfExist(req, resp);

            hibernateSessionProvider.closeSession();
        }
    }

    @Override
    public void destroy() {
        userServices = null;
        hibernateSessionProvider = null;
    }
}