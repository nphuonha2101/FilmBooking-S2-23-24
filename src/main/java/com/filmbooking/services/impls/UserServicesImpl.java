package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.TokenModel;
import com.filmbooking.model.User;
import com.filmbooking.services.AbstractServices;
import com.filmbooking.services.serviceResult.ServiceResult;
import com.filmbooking.enumsAndConstant.enums.StatusCodeEnum;
import com.filmbooking.utils.StringUtils;
import com.filmbooking.utils.validateUtils.Regex;
import com.filmbooking.utils.validateUtils.UserRegexEnum;

import java.util.List;
import java.util.Map;

public class UserServicesImpl extends AbstractServices<User> {

    public UserServicesImpl(HibernateSessionProvider sessionProvider) {
        super.decoratedDAO = new DataAccessObjects<>(User.class);
        super.setSessionProvider(sessionProvider);
    }

    @Override
    public User getBySlug(String slug) {
        throw new UnsupportedOperationException("This method is not supported for User");
    }

    @Override
    public User getByID(String id) {
        return super.decoratedDAO.getByID(id, false);
    }

    public User getByEmail(String email) {
        Map<String, Object> map = Map.of("userEmail", email);
        return this.getByPredicates(map).getMultipleResults().get(0);
    }

    /**
     * Authenticate user by username or email
     * <br>
     * Used for login
     * @param usernameOrEmail username or email
     * @param password password
     * @return ServiceResult with status code and user object
     */
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
            loginUser = getByID(usernameOrEmail);
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

    public ServiceResult userForgotPassword(String username, String email, String language) {
        ServiceResult result = null;

        User forgotPassUser = getByID(username);

        // if user not exist
        if (forgotPassUser == null) {
            result = new ServiceResult(StatusCodeEnum.USER_NOT_FOUND);
            return result;
        } else {
            // if user exist and email not match
            if (!forgotPassUser.getUserEmail().equalsIgnoreCase(email)) {
                result = new ServiceResult(StatusCodeEnum.EMAIL_NOT_MATCH);
            } else {
                // prepare Token
                TokenModel tokenModel = new TokenModel(forgotPassUser.getUserEmail(), forgotPassUser.getUsername());

                // send Email
                String emailSubject = language == null || language.equals("default") ? "Mật khẩu mới của bạn" : "Your new password";
//                AbstractSendEmail abstractSendEmail = AbstractSendEmail.getInstance();
//                abstractSendEmail.sendEmailToUser(forgotPassUser.getUserEmail(),
//                        emailSubject,
//                        abstractSendEmail.loadResetEmailFromHTML(forgotPassUser, tokenModel.getToken(), language));

                result = new ServiceResult(StatusCodeEnum.SUCCESSFUL);

            }
            return result;
        }
    }


    public ServiceResult userResetPassword(String token, String newPassword) {



        return null;
    }


    public ServiceResult userChangePassword(String username, String oldPassword, String newPassword) {
        return null;
    }
}
