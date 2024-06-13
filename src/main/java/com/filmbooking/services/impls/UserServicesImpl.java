package com.filmbooking.services.impls;

import java.util.HashMap;
import java.util.Map;

import com.filmbooking.email.AbstractSendEmail;
import com.filmbooking.email.SendResetPasswordEmail;
import com.filmbooking.enumsAndConstants.enums.LanguageEnum;
import com.filmbooking.enumsAndConstants.enums.StatusCodeEnum;
import com.filmbooking.enumsAndConstants.enums.TokenTypeEnum;
import com.filmbooking.model.TokenModel;
import com.filmbooking.model.User;
import com.filmbooking.repository.UserRepository;
import com.filmbooking.services.AbstractService;
import com.filmbooking.services.IUserServices;
import com.filmbooking.services.serviceResult.ServiceResult;
import com.filmbooking.utils.PropertiesUtils;
import com.filmbooking.utils.StringUtils;
import com.filmbooking.utils.validateUtils.Regex;
import com.filmbooking.utils.validateUtils.UserRegexEnum;
import jakarta.persistence.NoResultException;

public class UserServicesImpl extends AbstractService<User> implements IUserServices {
    private final TokenServicesImpl tokenServices;

    public UserServicesImpl() {
        super(new UserRepository());
        this.tokenServices = new TokenServicesImpl();
    }

    public User getByEmail(String email) {
        try {
            Map<String, Object> filters = new HashMap<>();
            filters.put("user_email", email);
            return this.selectAll(filters).get(0);
        } catch (NoResultException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public User getByUsername(String username){
        try {
            Map<String, Object> filters = new HashMap<>();
            filters.put("username", username);
            return this.selectAll(filters).get(0);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Authenticate user by username or email
     * <br>
     * Used for login
     *
     * @param usernameOrEmail username or email
     * @param password        password
     * @return ServiceResult with status code and user object
     */
    public ServiceResult userAuthentication(String usernameOrEmail, String password) {
        ServiceResult serviceResult = null;

        // hash password
        password = this.hashPassword(password);
        // determine login method
        boolean isEmail = Regex.validate(UserRegexEnum.USER_EMAIL, usernameOrEmail);
        boolean isUsername = Regex.validate(UserRegexEnum.USERNAME, usernameOrEmail);

        User loginUser = null;
        // login by email
        if (isEmail)
            loginUser = getByEmail(usernameOrEmail);
        // login by username
        if (isUsername)
            loginUser = select(usernameOrEmail);
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

    /**
     * Handle forgot password of user
     * <br>
     * When user forgot password, create a token and send email to user
     *
     * @param username username
     * @param email    user's email in system
     * @param language language of email
     * @return ServiceResult with status code
     */
    public ServiceResult userForgotPassword(String username, String email, String language) {
        ServiceResult result = null;

        User forgotPassUser = select(username);

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
                TokenModel tokenModel = new TokenModel(forgotPassUser.getUserEmail(), forgotPassUser.getUsername(),
                        TokenTypeEnum.PASSWORD_RESET);
                // save token to database
                tokenServices.insert(tokenModel);

                // send Email
                String emailSubject = language == null || language.equals("default") ? "Bạn quên mật khẩu?"
                        : "You forgot your password?";
                //
                LanguageEnum languageEnum = language == null || language.equals("default") ? LanguageEnum.VIETNAMESE
                        : LanguageEnum.ENGLISH;

                AbstractSendEmail sendResetPasswordEmail = new SendResetPasswordEmail();
                sendResetPasswordEmail
                        .loadHTMLEmail(languageEnum)
                        .putEmailInfo("userFullName", forgotPassUser.getUserFullName())
                        .putEmailInfo("username", forgotPassUser.getUsername())
                        .putEmailInfo("token", tokenModel.getToken())
                        .loadEmailContent()
                        .sendEmailToUser(forgotPassUser.getUserEmail(), emailSubject);

                result = new ServiceResult(StatusCodeEnum.SUCCESSFUL, forgotPassUser);
            }
            return result;
        }
    }

    /**
     * Handle change password for user
     *
     * @param username    username to find user
     * @param oldPassword old password
     * @param newPassword new password
     * @return ServiceResult with status code
     */
    public ServiceResult userChangePassword(String username, String oldPassword, String newPassword) {
        ServiceResult result = null;

        User user = select(username);

        // if user not exist
        if (user == null) {
            result = new ServiceResult(StatusCodeEnum.USER_NOT_FOUND);
            return result;
        } else {
            // verify old password
            if (!user.getUserPassword().equals(StringUtils.generateSHA256String(oldPassword))) {
                result = new ServiceResult(StatusCodeEnum.PASSWORD_NOT_MATCH);
                return result;
            } else {
                // update password
                user.setUserPassword(StringUtils.generateSHA256String(newPassword));
                super.repository.update(user);
                result = new ServiceResult(StatusCodeEnum.PASSWORD_CHANGE_SUCCESSFUL, user);
            }
            return result;
        }
    }

    /**
     * Hashing password with SHA-256 algorithm and secret key
     *
     * @param password password to hash
     * @return hashed password
     */
    public String hashPassword(String password) {
        String passwordSecretKey = PropertiesUtils.getProperty("password.hash_secret_key");
        return StringUtils.generateSHA256String(password + passwordSecretKey);
    }



}
