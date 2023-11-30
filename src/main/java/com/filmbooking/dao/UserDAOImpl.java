package com.filmbooking.dao;

import com.filmbooking.database.DatabaseConnection;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IDAO<User> {
    private static UserDAOImpl instance = null;
    private final DatabaseConnection databaseConnection;
    private static final String TABLE_NAME = "user_info";

    private UserDAOImpl() {
        databaseConnection = DatabaseConnection.getInstance();
    }

    public static UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();

        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryGet = "SELECT * FROM " + TABLE_NAME;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGet);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String userFullName = resultSet.getString("user_fullname");
                String userEmail = resultSet.getString("user_email");
                String userPassword = resultSet.getString("user_password");
                String accountRole = resultSet.getString("account_role");

                List<FilmBooking> filmBookingList = FilmBookingDAOImpl.getInstance().getAll().stream().filter(filmBooking -> filmBooking.getUser().getUsername().equals(username)).toList();

                User user = new User(username, userFullName, userEmail, userPassword, accountRole, filmBookingList);

                userList.add(0, user);
            }
            resultSet.close();
            preparedStatement.close();
            databaseConnection.close();

            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getByID(String username) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryGetByID = "SELECT * FROM " + TABLE_NAME + " WHERE username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetByID);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String userFullName = resultSet.getString("user_fullname");
                String userEmail = resultSet.getString("user_email");
                String userPassword = resultSet.getString("user_password");
                String accountRole = resultSet.getString("account_role");

//                List<FilmBooking> filmBookingList = FilmBookingDAOImpl.getInstance().getAll().stream().filter(filmBooking -> filmBooking.getUser().getUsername().equals(username)).toList();

                User user = new User(username, userFullName, userEmail, userPassword, accountRole);

                resultSet.close();
                preparedStatement.close();
                databaseConnection.close();
                return user;
            } else {
                resultSet.close();
                preparedStatement.close();
                databaseConnection.close();
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(User user) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();
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
            databaseConnection.close();
            ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();
        String sql = "UPDATE " + TABLE_NAME + " SET user_fullname = ?, user_email = ?, user_password = ?, " +
                "account_role = ?  WHERE " +
                "username= ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserFullName());
            preparedStatement.setString(2, user.getUserEmail());
            preparedStatement.setString(3, user.getUserPassword());
            preparedStatement.setString(4, user.getAccountRole());
            preparedStatement.setString(5, user.getUsername());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User user) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();
        String queryDelete = "DELETE FROM " + TABLE_NAME + " WHERE username = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryDelete);
            preparedStatement.setString(1, user.getUsername());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
