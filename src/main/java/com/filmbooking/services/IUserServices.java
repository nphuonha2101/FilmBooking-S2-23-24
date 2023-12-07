package com.filmbooking.services;

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
    ServiceResult userAuthentication(String usernameOrEmail, String password);
    ServiceResult userForgotPassword(String username, String email);

}
