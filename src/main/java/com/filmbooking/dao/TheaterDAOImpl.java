package com.filmbooking.dao;

import com.filmbooking.database.DatabaseConnection;
import com.filmbooking.model.Theater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TheaterDAOImpl implements IDAO<Theater> {
    private final DatabaseConnection databaseConnection;
    private final List<Theater> theaterList;
    private static final String TABLE_NAME = "theater";

    public TheaterDAOImpl() {
        theaterList = new ArrayList<>();
        databaseConnection = DatabaseConnection.getInstance();
    }

    @Override
    public List<Theater> getAll() {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryGetAll = "SELECT * FROM " + TABLE_NAME;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String theaterID = resultSet.getString("theater_id");
                String theaterName = resultSet.getString("theater_name");
                String taxCode = resultSet.getString("tax_code");
                String address = resultSet.getString("theater_address");

                Theater theater = new Theater(theaterID, theaterName, taxCode, address);

                theaterList.add(0, theater);
            }
            resultSet.close();
            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return theaterList;
    }

    @Override
    public Theater getByID(String id) {
        getAll();

        for (Theater theater : theaterList) {
            if (theater.getTheaterID().equalsIgnoreCase(id)) {
                return theater;
            }
        }
        return null;
    }

    @Override
    public void save(Theater theater) {
        int largestID = this.getAll().isEmpty() ? 0 : Integer.parseInt(this.getAll().get(0).getTheaterID());
        for (Theater t : this.getAll()) {
            if (Integer.parseInt(t.getTheaterID()) > largestID)
                largestID = Integer.parseInt(t.getTheaterID());
        }
        largestID++;
        theater.setTheaterID(String.valueOf(largestID));

        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String querySave = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySave);

            preparedStatement.setString(1, theater.getTheaterID());
            preparedStatement.setString(2, theater.getTheaterName());
            preparedStatement.setString(3, theater.getTaxCode());
            preparedStatement.setString(4, theater.getTheaterAddress());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Theater theater) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryUpdate = "UPDATE " + TABLE_NAME + " SET theater_name = ?, tax_number = ?, address = ? WHERE theater_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryUpdate);

            preparedStatement.setString(1, theater.getTheaterName());
            preparedStatement.setString(2, theater.getTaxCode());
            preparedStatement.setString(3, theater.getTheaterAddress());
            preparedStatement.setString(4, theater.getTheaterID());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Theater theater) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryDelete = "DELETE FROM " + TABLE_NAME + " WHERE theater_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryDelete);

            preparedStatement.setString(1, theater.getTheaterID());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}