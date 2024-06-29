package com.filmbooking.services.logProxy;

import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.model.IModel;
import com.filmbooking.model.LogModel;
import com.filmbooking.model.User;
import com.filmbooking.services.AbstractService;
import com.filmbooking.services.IUserServices;
import com.filmbooking.services.impls.UserServicesImpl;
import com.filmbooking.services.serviceResult.ServiceResult;
import com.filmbooking.utils.validateUtils.Regex;
import com.filmbooking.utils.validateUtils.UserRegexEnum;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public class UserServicesLogProxy extends AbstractServicesLogProxy<User> implements IUserServices {
    private final UserServicesImpl userServices;

    public UserServicesLogProxy(UserServicesImpl userServices, HttpServletRequest req) {
        super(req, User.class);
        this.userServices = userServices;
    }

    @Override
    public ServiceResult userAuthentication(String usernameOrEmail, String password) {
        ServiceResult serviceResult = userServices.userAuthentication(usernameOrEmail, password);
        LogModel logModel = null;
        User user;

        if (serviceResult.getStatus() == StatusCodeEnum.FOUND_USER) {
            logModel = buildLogModel(LogModel.LOGIN_SERVICE, null, userServices, true);
            // set user for log model because current user not in session yet
            user = (User) serviceResult.getData();
        } else {
            logModel = buildLogModel(LogModel.LOGIN_SERVICE, null, userServices, false);
            // default log level is INFO, but when user login fail, we need to alert
            logModel.setLevel(LogModel.LOG_LVL_ALERT);
            // get user by username or email
            boolean isLoginWithUsername = Regex.validate(UserRegexEnum.USERNAME, usernameOrEmail);
            if (isLoginWithUsername)
                user = userServices.select(usernameOrEmail);
            else
                user = userServices.getByEmail(usernameOrEmail);
        }
        if (user != null){
            logModel.setUsername(user.getUsername());
        }else{
            logModel.setUsername(null);
        }
        logModelServices.insert(logModel);
        return serviceResult;
    }

    @Override
    public ServiceResult userForgotPassword(String username, String email, String language) {
        ServiceResult serviceResult = userServices.userForgotPassword(username, email, language);
        LogModel logModel = null;
        User user = null;

        if (serviceResult.getStatus() == StatusCodeEnum.SUCCESSFUL) {
            logModel = buildLogModel(LogModel.FORGOT_PASSWORD_SERVICE, null, userServices, true);
            user = (User) serviceResult.getData();
        } else {
            logModel = buildLogModel(LogModel.FORGOT_PASSWORD_SERVICE, null, userServices, false);
            // default log level is INFO, but when user forgot password fail, we need to alert
            logModel.setLevel(LogModel.LOG_LVL_ALERT);
            user = userServices.select(username);
        }

        logModel.setUsername(user.getUsername());
        logModelServices.insert(logModel);

        return serviceResult;
    }

    @Override
    public ServiceResult userChangePassword(String username, String oldPassword, String newPassword) {
        ServiceResult serviceResult = userServices.userChangePassword(username, oldPassword, newPassword);
        LogModel logModel = null;
        User user = null;

        if (serviceResult.getStatus() == StatusCodeEnum.PASSWORD_CHANGE_SUCCESSFUL) {
            logModel = buildLogModel(LogModel.CHANGE_PASSWORD_SERVICE, null, userServices, true);
            user = (User) serviceResult.getData();
        } else {
            logModel = buildLogModel(LogModel.CHANGE_PASSWORD_SERVICE,null, userServices, false);
            // default log level is INFO, but when user change password fail, we need to alert
            logModel.setLevel(LogModel.LOG_LVL_ALERT);
            user = userServices.select(username);
        }

        logModel.setUsername(user.getUsername());
        logModelServices.insert(logModel);
        return serviceResult;
    }
}
