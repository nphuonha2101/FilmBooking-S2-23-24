package com.filmbooking.services;

import com.filmbooking.model.TokenModel;
import com.filmbooking.model.User;
import com.filmbooking.services.serviceResult.ServiceResult;
import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.List;

public interface IUserServices {
    void setSessionProvider(HibernateSessionProvider sessionProvider);
    List<User> getAll();
    User getByUsername(String id);
    User getByEmail(String email);
    boolean save(User user);
    boolean update(User user);
    boolean delete(User user);

    /**
     * Authenticate user by username or email and password when login
     * @param usernameOrEmail username or email
     * @param password password
     * @return ServiceResult with status code and data
     */
    ServiceResult userAuthentication(String usernameOrEmail, String password);

    /**
     * Handle user forgot password request
     * <br>
     * Send email to user with reset password link (include token)
     * @param username username
     * @param email email when user prompt, this method will verify email with username in database
     * @param language language of email content
     * @return ServiceResult with status code and data
     */
    ServiceResult userForgotPassword(String username, String email, String language);

    /**
     * Handle user reset password request by token
     * @param token token
     * @param newPassword new password
     * @return ServiceResult with status code and data
     */
    ServiceResult userResetPassword(String token, String newPassword);

    /**
     * Handle user change password request
     * @param username username
     * @param oldPassword old password
     * @param newPassword new password
     * @return ServiceResult with status code and data
     */
    ServiceResult userChangePassword(String username, String oldPassword, String newPassword);
}
