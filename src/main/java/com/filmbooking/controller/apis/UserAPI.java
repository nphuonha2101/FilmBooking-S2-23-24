package com.filmbooking.controller.apis;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import com.filmbooking.controller.apis.apiResponse.APIJSONResponse;
import com.filmbooking.controller.apis.apiResponse.RespCodeEnum;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.User;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.services.logProxy.CRUDServicesLogProxy;
import com.filmbooking.utils.APIUtils;
import com.filmbooking.utils.MultipartsFormUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig
@WebServlet(urlPatterns = {"/api/v1/users/*", "/api/v1/users"})
public class UserAPI extends HttpServlet {
    UserServicesImpl userServicesImpl;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        userServicesImpl = new UserServicesImpl(sessionProvider);

        APIUtils<User> apiUtils = new APIUtils<>(userServicesImpl, req, resp);
        String command = req.getParameter("command");

        apiUtils.processRequest(command);
        apiUtils.writeResponse(null, 0);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("PATCH"))
            this.doPatch(req, resp);
        else
            super.service(req, resp);
    }

    /**
     * Handle PATCH request
     * @param req request
     * @param resp response
     * @throws IOException exception. It is thrown cause of PrintWriter
     */
    private void doPatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        MultipartsFormUtils formUtils = new MultipartsFormUtils(req);
        Map<String, String> formFields = formUtils.getFormFields(List.of("username", "full-name", "role"));

        String username = formFields.get("username");
        String fullName = formFields.get("full-name");
        String role = formFields.get("role");

        System.out.println("form fields: " + formFields);

        HibernateSessionProvider sessionProvider = new HibernateSessionProvider();
        CRUDServicesLogProxy<User> userServicesLog = new CRUDServicesLogProxy<User>(new UserServicesImpl(), req, sessionProvider);

        User user = userServicesLog.getByID(username);
        user.setUserFullName(fullName);
        user.setAccountRole(role);

        boolean isUpdateSuccess = userServicesLog.update(user);

        APIJSONResponse apiResponse = null;
        String language = "default"; // default value
        if (req.getSession().getAttribute("language") != null) {
            language = req.getSession().getAttribute("language").toString();
        }

        if (isUpdateSuccess) {
            apiResponse = new APIJSONResponse(RespCodeEnum.SUCCESS.getCode(), "Update user successfully", language, user);
        } else {
            apiResponse = new APIJSONResponse(RespCodeEnum.INTERNAL_SERVER_ERROR.getCode(), "Update user failed", language, null);
        }

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(apiResponse.getResponse());
    }
}
