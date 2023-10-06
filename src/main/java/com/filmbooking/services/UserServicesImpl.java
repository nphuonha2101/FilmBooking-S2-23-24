package com.filmbooking.services;

import com.filmbooking.dao.IDAO;
import com.filmbooking.dao.UserDAOImpl;
import com.filmbooking.model.User;

import java.util.List;

public class UserServicesImpl implements IUserServices {
    private IDAO<User> userDAO;

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
        getAll();
        return userDAO.getByID(id);
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
}
