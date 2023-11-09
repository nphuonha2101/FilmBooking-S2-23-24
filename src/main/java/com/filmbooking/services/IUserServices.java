package com.filmbooking.services;

import com.filmbooking.model.User;

import java.util.List;

public interface IUserServices {
    List<User> getAll();
    User getByUsername(String id);
    User getByEmail(String email);
    void save(User user);
    void update(User user);
    void delete(User user);
}