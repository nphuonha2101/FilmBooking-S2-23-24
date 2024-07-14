package com.filmbooking.controller.apis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.filmbooking.controller.apis.apiResponse.APIJSONResponse;
import com.filmbooking.controller.apis.apiResponse.RespCodeEnum;
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
import jakarta.servlet.http.HttpSession;

@MultipartConfig
@WebServlet(urlPatterns = {"/api/v1/users/*", "/api/v1/users"})
public class UserAPI extends HttpServlet {
    UserServicesImpl userServicesImpl;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userServicesImpl = new UserServicesImpl();

        APIUtils<User> apiUtils = new APIUtils<>(userServicesImpl, req, resp);
        String command = req.getParameter("command");

        if (command.equals("loginUser")) {
            APIJSONResponse apiResponse = getApijsonResponse(req);
            apiUtils.setJsonResponse(apiResponse);
            apiUtils.writeResponse(null, 0);
            return;
        }

        apiUtils.processRequest(command);
        apiUtils.writeResponse(null, 0);
    }

    private APIJSONResponse getApijsonResponse(HttpServletRequest req) {
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        APIJSONResponse apiResponse = null;
        if (loginUser == null) {
            apiResponse = new APIJSONResponse(RespCodeEnum.UNAUTHORIZED.getCode(), RespCodeEnum.UNAUTHORIZED.getMessage(), "default", null);
        } else {
            apiResponse = new APIJSONResponse(RespCodeEnum.SUCCESS.getCode(), "Get login user successfully", "default", loginUser);
        }
        return apiResponse;
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
     *
     * @param req  request
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

        CRUDServicesLogProxy<User> userServicesLog = new CRUDServicesLogProxy<>(new UserServicesImpl(), req, User.class);

        User user = userServicesLog.select(username);
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
