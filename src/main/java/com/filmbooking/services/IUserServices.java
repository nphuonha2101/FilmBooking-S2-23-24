package com.filmbooking.services;

import com.filmbooking.model.User;
import com.filmbooking.services.serviceResult.ServiceResult;

import java.util.List;

public interface IUserServices {
    List<User> getAll();
    User getByUsername(String id);
    User getByEmail(String email);
    void save(User user);
    void update(User user);
    void delete(User user);
    ServiceResult userAuthentication(String usernameOrEmail, String password);
    ServiceResult userForgotPassword(String username, String email);

}
