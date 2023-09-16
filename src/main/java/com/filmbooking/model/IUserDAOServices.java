package com.filmbooking.model;

import java.util.List;

public interface IUserDAOServices {
    List<User> getAll();
    boolean saveUser(User user);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    boolean deleteUser(User user);
    boolean changePassword(String username, String password);
}
