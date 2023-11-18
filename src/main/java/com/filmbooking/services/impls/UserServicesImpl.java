package com.filmbooking.services.impls;

import com.filmbooking.dao.IDAO;
import com.filmbooking.dao.UserDAOImpl;
import com.filmbooking.model.User;
import com.filmbooking.services.IUserServices;
import com.filmbooking.services.serviceResult.ServiceResult;
import com.filmbooking.statusEnums.StatusEnum;
import com.filmbooking.utils.StringUtils;
import com.filmbooking.utils.mailUtils.SendEmail;
import com.filmbooking.utils.validateUtils.Regex;
import com.filmbooking.utils.validateUtils.UserRegex;

import java.util.List;

public class UserServicesImpl implements IUserServices {
    private final IDAO<User> userDAO;

    public UserServicesImpl() {
        userDAO = new UserDAOImpl();
//        getAll();
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User getByUsername(String id) {
        return userDAO.getByID(id);
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
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    public ServiceResult userAuthentication(String usernameOrEmail, String password) {
        ServiceResult serviceResult = null;
        // hash password
        password = StringUtils.generateSHA256String(password);
        // determine login method
        boolean isEmail = Regex.validate(UserRegex.USER_EMAIL, usernameOrEmail);
        boolean isUsername = Regex.validate(UserRegex.USERNAME, usernameOrEmail);

        User loginUser = null;
        // login by email
        if (isEmail)
            loginUser = getByEmail(usernameOrEmail);
        // login by username
        if (isUsername)
            loginUser = getByUsername(usernameOrEmail);
        // if input is not email or username
        if (!(isEmail || isUsername)) {
            serviceResult = new ServiceResult(StatusEnum.NOT_VALID_INPUT);
            return serviceResult;
        }

        // validate user
        if (loginUser == null) {
            serviceResult = new ServiceResult(StatusEnum.USER_NOT_FOUND);
            return serviceResult;
        } else {
            // verify password
            if (!loginUser.getUserPassword().equals(password)) {
                serviceResult = new ServiceResult(StatusEnum.PASSWORD_NOT_MATCH);
                return serviceResult;
            }
        }

        serviceResult = new ServiceResult(StatusEnum.FOUND_USER, loginUser);
        return serviceResult;
    }

    @Override
    public ServiceResult userForgotPassword(String username, String email) {
        ServiceResult result = null;

        User forgotPassUser = getByUsername(username);

        // if user not exist
        if (forgotPassUser == null) {
            result = new ServiceResult(StatusEnum.USER_NOT_FOUND);
            return result;
        } else {
            // if user exist and email not match
            if (!forgotPassUser.getUserEmail().equalsIgnoreCase(email)) {
                result = new ServiceResult(StatusEnum.EMAIL_NOT_MATCH);
                return result;
            } else {
                String newPassword = StringUtils.createRandomStringUtil(9);
                forgotPassUser.setUserPassword(StringUtils.generateSHA256String(newPassword));
                update(forgotPassUser);

                SendEmail sendEmail = SendEmail.getInstance();
                sendEmail.sendEmailToUser(forgotPassUser.getUserEmail(),
                        "Mật khẩu mới của bạn",
                        sendEmail.createResetPasswordEmail(newPassword));

                result = new ServiceResult(StatusEnum.SUCCESSFUL);
                return result;

            }
        }
    }
}
