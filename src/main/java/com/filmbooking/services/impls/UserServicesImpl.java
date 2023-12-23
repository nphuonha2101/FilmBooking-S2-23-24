package com.filmbooking.services.impls;

import com.filmbooking.dao.GenericDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.User;
import com.filmbooking.services.IUserServices;
import com.filmbooking.services.serviceResult.ServiceResult;
import com.filmbooking.statusEnums.StatusCodeEnum;
import com.filmbooking.utils.SendEmailUtils;
import com.filmbooking.utils.StringUtils;
import com.filmbooking.utils.validateUtils.Regex;
import com.filmbooking.utils.validateUtils.UserRegexEnum;

import java.util.List;

public class UserServicesImpl implements IUserServices {
    private final IDAO<User> userDAO;

    public UserServicesImpl() {
        userDAO = new GenericDAOImpl<>(User.class);
    }

    public UserServicesImpl(HibernateSessionProvider sessionProvider) {
        userDAO = new GenericDAOImpl<>(User.class);
        setSessionProvider(sessionProvider);
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        userDAO.setSessionProvider(sessionProvider);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User getByUsername(String id) {
        return userDAO.getByID(id, false);
    }

    @Override
    public User getByEmail(String email) {
        for (User user : getAll()
        ) {
            if (user.getUserEmail().equalsIgnoreCase(email))
                return user;
        }
        return null;
    }

    @Override
    public boolean save(User user) {
        return userDAO.save(user);
    }

    @Override
    public boolean update(User user) {
        return userDAO.update(user);
    }

    @Override
    public boolean delete(User user) {
        return userDAO.delete(user);
    }

    @Override
    public ServiceResult userAuthentication(String usernameOrEmail, String password) {
        ServiceResult serviceResult = null;
        // hash password
        password = StringUtils.generateSHA256String(password);
        // determine login method
        boolean isEmail = Regex.validate(UserRegexEnum.USER_EMAIL, usernameOrEmail);
        boolean isUsername = Regex.validate(UserRegexEnum.USERNAME, usernameOrEmail);

        User loginUser = null;
        // login by email
        if (isEmail)
            loginUser = getByEmail(usernameOrEmail);
        // login by username
        if (isUsername)
            loginUser = getByUsername(usernameOrEmail);
        // if input is not email or username
        if (!(isEmail || isUsername)) {
            serviceResult = new ServiceResult(StatusCodeEnum.INVALID_INPUT);
            return serviceResult;
        }

        // validate user
        if (loginUser == null) {
            serviceResult = new ServiceResult(StatusCodeEnum.USER_NOT_FOUND);
            return serviceResult;
        } else {
            // verify password
            if (!loginUser.getUserPassword().equals(password)) {
                serviceResult = new ServiceResult(StatusCodeEnum.PASSWORD_NOT_MATCH);
                return serviceResult;
            }
        }

        serviceResult = new ServiceResult(StatusCodeEnum.FOUND_USER, loginUser);
        return serviceResult;
    }

    @Override
    public ServiceResult userForgotPassword(String username, String email, String language) {
        ServiceResult result = null;

        User forgotPassUser = getByUsername(username);

        // if user not exist
        if (forgotPassUser == null) {
            result = new ServiceResult(StatusCodeEnum.USER_NOT_FOUND);
            return result;
        } else {
            // if user exist and email not match
            if (!forgotPassUser.getUserEmail().equalsIgnoreCase(email)) {
                result = new ServiceResult(StatusCodeEnum.EMAIL_NOT_MATCH);
            } else {
                String newPassword = StringUtils.createRandomStringUtil(9);
                forgotPassUser.setUserPassword(StringUtils.generateSHA256String(newPassword));
                update(forgotPassUser);

                String emailSubject = language == null || language.equals("default") ? "Mật khẩu mới của bạn" : "Your new password";

                SendEmailUtils sendEmailUtils = SendEmailUtils.getInstance();
                sendEmailUtils.sendEmailToUser(forgotPassUser.getUserEmail(),
                        emailSubject,
                        sendEmailUtils.loadResetEmailFromHTML(forgotPassUser, newPassword, language));

                result = new ServiceResult(StatusCodeEnum.SUCCESSFUL);

            }
            return result;
        }
    }
}
