package com.filmbooking.DAOservices;

import com.filmbooking.DAOservices.IUserDAOServices;
import com.filmbooking.databaseConfig.DatabaseServices;
import com.filmbooking.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOServicesImpl implements IUserDAOServices {
    private List<User> userList;
    private DatabaseServices databaseServices = null;

    public UserDAOServicesImpl() {
        userList = new ArrayList<>();
        databaseServices = new DatabaseServices();
        Connection connection = null;

        databaseServices.connectDatabase();
        if (databaseServices.getConnection() != null) connection = databaseServices.getConnection();

        String queryGet = "SELECT * FROM user_info";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGet);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String userFullName = resultSet.getString("user_fullname");
                String userEmail = resultSet.getString("user_email");
                String userPassword = resultSet.getString("user_password");
                String userRole = resultSet.getString("user_role");

                User user = new User(username, userFullName, userEmail, userPassword, userRole);

                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            databaseServices.disconnectDatabase();
        }


    }

    @Override
    public List<User> getAll() {
        return userList;
    }

    @Override
    public boolean saveUser(User user) {
        userList.add(user);

        Connection connection = databaseServices.getConnection();
        String queryInsert = "INSERT INTO user_info(username, user_fullname, user_email, user_password, user_role) " + "VALUES(?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(queryInsert);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getUserFullName());
            preparedStatement.setString(3, user.getUserEmail());
            preparedStatement.setString(4, user.getUserPassword());
            preparedStatement.setString(5, user.getAccountRole());

            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            databaseServices.disconnectDatabase();
        }

    }

    @Override
    public User getUserByUsername(String username) {
        for (User userInList : userList) {
            if (userInList.getUsername().equalsIgnoreCase(username)) {
                return userInList;
            }
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        for (User userInList : userList) {
            if (userInList.getUserEmail().equalsIgnoreCase(email)) {
                return userInList;
            }
        }
        return null;
    }

    @Override
    public boolean deleteUser(User user) {
        if (userList.contains(user)) {
            userList.remove(user);

            Connection connection = databaseServices.getConnection();
            String queryDelete = "DELETE FROM user_info WHERE username = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(queryDelete);
                preparedStatement.setString(1, user.getUsername());

                return preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                databaseServices.disconnectDatabase();
            }

        }
        return false;
    }

    @Override
    public boolean changePassword(String username, String password) {
        for (User userInList : userList) {
            if (userInList.getUsername().equalsIgnoreCase(username)) userInList.setUserPassword(password);
        }

        Connection connection = databaseServices.getConnection();
        String queryChangePassword = "UPDATE user_info SET user_password = ? WHERE username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryChangePassword);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, username);

            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            databaseServices.disconnectDatabase();
        }
    }
}
