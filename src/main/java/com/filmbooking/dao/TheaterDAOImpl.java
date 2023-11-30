package com.filmbooking.dao;

import com.filmbooking.database.DatabaseConnection;
import com.filmbooking.model.Room;
import com.filmbooking.model.Showtime;
import com.filmbooking.model.Theater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TheaterDAOImpl implements IDAO<Theater> {
    private static TheaterDAOImpl instance = null;
    private final DatabaseConnection databaseConnection;
    private static final String TABLE_NAME = "theater";

    public TheaterDAOImpl() {
        databaseConnection = DatabaseConnection.getInstance();
    }

    public static TheaterDAOImpl getInstance() {
        if (instance == null) {
            instance = new TheaterDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Theater> getAll() {
        List<Theater> theaterList = new ArrayList<>();

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

                List<Room> roomList = RoomDAOImpl.getInstance().getAll().stream().filter(room -> room.getTheater().getTheaterID().equals(theaterID)).toList();
                Theater theater = new Theater(theaterID, theaterName, taxCode, address, roomList);

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
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryGetByID = "SELECT * FROM " + TABLE_NAME + " WHERE theater_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetByID);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String theaterID = resultSet.getString("theater_id");
                String theaterName = resultSet.getString("theater_name");
                String taxCode = resultSet.getString("tax_code");
                String address = resultSet.getString("theater_address");

                List<Room> roomList = RoomDAOImpl.getInstance().getAll().stream().filter(room -> room.getTheater().getTheaterID().equals(theaterID)).toList();
                Theater theater = new Theater(theaterID, theaterName, taxCode, address, roomList);

                resultSet.close();
                preparedStatement.close();
                databaseConnection.close();

                return theater;
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
