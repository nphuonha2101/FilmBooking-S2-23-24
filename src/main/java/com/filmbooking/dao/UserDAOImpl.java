package com.filmbooking.dao;

import com.filmbooking.database.DatabaseServices;
import com.filmbooking.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IDAO<User> {
    private final List<User> userList;
    private final DatabaseServices databaseServices;
    private static final String TABLE_NAME = "user_info";

    public UserDAOImpl() {
        userList = new ArrayList<>();
        databaseServices = new DatabaseServices();
        databaseServices.connectDatabase();
    }

    @Override
    public List<User> getAll() {
        Connection connection = null;

        if (databaseServices.getConnection() != null) connection = databaseServices.getConnection();

        String queryGet = "SELECT * FROM " + TABLE_NAME;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGet);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String userFullName = resultSet.getString("user_fullname");
                String userEmail = resultSet.getString("user_email");
                String userPassword = resultSet.getString("user_password");
                String userRole = resultSet.getString("account_role");

                User user = new User(username, userFullName, userEmail, userPassword, userRole);

                userList.add(0, user);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
//            databaseServices.disconnectDatabase();
        }

        return userList;
    }

    @Override
    public User getByID(String username) {
        getAll();

        for (User userInList : userList) {
            if (userInList.getUsername().equalsIgnoreCase(username)) {
                return userInList;
            }
        }
        return null;

    }

    @Override
    public void save(User user) {

        Connection connection = databaseServices.getConnection();
        String queryInsert = "INSERT INTO " + TABLE_NAME + " (username, user_fullname, user_email, user_password, account_role) " + "VALUES(?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(queryInsert);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getUserFullName());
            preparedStatement.setString(3, user.getUserEmail());
            preparedStatement.setString(4, user.getUserPassword());
            preparedStatement.setString(5, user.getAccountRole());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
//            databaseServices.disconnectDatabase();
        }
    }

    @Override
    public void update(User user) {
        Connection connection = databaseServices.getConnection();
        String sql = "UPDATE " + TABLE_NAME + " SET user_fullname = ?, user_email = ?, user_password = ? WHERE username= ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserFullName());
            preparedStatement.setString(2, user.getUserEmail());
            preparedStatement.setString(3, user.getUserPassword());
            preparedStatement.setString(4, user.getUsername());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User user) {
        if (userList.contains(user)) {

            Connection connection = databaseServices.getConnection();
            String queryDelete = "DELETE FROM " + TABLE_NAME + " WHERE username = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(queryDelete);
                preparedStatement.setString(1, user.getUsername());

                preparedStatement.executeUpdate();

                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
//                databaseServices.disconnectDatabase();
            }

        }

    }
}
