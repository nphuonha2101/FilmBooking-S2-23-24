package com.filmbooking.services;

import com.filmbooking.services.serviceResult.ServiceResult;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public interface IUserServices {
    /**
     * Authenticate user by username or email
     * <br>
     * Used for login
     *
     * @param usernameOrEmail username or email
     * @param password        password
     * @return ServiceResult with status code and user object
     */
    ServiceResult userAuthentication(String usernameOrEmail, String password);

    /**
     * Handle forgot password of user
     * <br>
     * When user forgot password, create a token and send email to user
     * @param username username
     * @param email user's email in system
     * @param language language of email
     * @return ServiceResult with status code
     */
    ServiceResult userForgotPassword(String username, String email, String language);

    /**
     * Handle change password for user
     * @param username username to find user
     * @param oldPassword old password
     * @param newPassword new password
     * @return ServiceResult with status code
     */
    public ServiceResult userChangePassword(String username, String oldPassword, String newPassword);
}
