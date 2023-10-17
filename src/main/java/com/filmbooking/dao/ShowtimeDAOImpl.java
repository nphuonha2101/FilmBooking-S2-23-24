package com.filmbooking.dao;

import com.filmbooking.database.DatabaseServices;
import com.filmbooking.model.Showtime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowtimeDAOImpl implements IDAO<Showtime> {
    private DatabaseServices databaseServices;
    private List<Showtime> showtimeList;
    private static final String TABLE_NAME = "showtime";

    public ShowtimeDAOImpl() {
        showtimeList = new ArrayList<>();
        databaseServices = new DatabaseServices();
        databaseServices.connectDatabase();
    }

    @Override
    public List<Showtime> getAll() {
        Connection connection = databaseServices.getConnection();

        String queryGetAll = "SELECT * FROM " + TABLE_NAME;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String showtimeID = resultSet.getString("showtime_id");
                String filmID = resultSet.getString("film_id");
                String roomID = resultSet.getString("room_id");
                Date showtimeDate = resultSet.getDate("showtime_date");

                Showtime showtime = new Showtime(showtimeID, filmID, roomID, showtimeDate);

                showtimeList.add(0, showtime);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return showtimeList;
    }

    @Override
    public Showtime getByID(String id) {
        getAll();

        for (Showtime showtimeInList : showtimeList
        ) {
            if (showtimeInList.getShowtimeID().equalsIgnoreCase(id))
                return showtimeInList;
        }
        return null;
    }

    @Override
    public void save(Showtime showtime) {
        Connection connection = databaseServices.getConnection();

        String querySave = "INSERT INTO " + TABLE_NAME + "(film_id, room_id, showtime_date)"
                + " VALUES(?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySave);

            preparedStatement.setString(1, showtime.getFilmID());
            preparedStatement.setString(2, showtime.getRoomID());
            preparedStatement.setDate(3, (java.sql.Date) showtime.getShowtimeDate());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Showtime showtime) {
        Connection connection = databaseServices.getConnection();

        String queryUpdate = "UPDATE " + TABLE_NAME + " SET film_id = ?, room_id = ?, showtime_date = ?"
                + " WHERE showtime_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryUpdate);
            preparedStatement.setString(1, showtime.getFilmID());
            preparedStatement.setString(2, showtime.getRoomID());
            preparedStatement.setDate(3, (java.sql.Date) showtime.getShowtimeDate());
            preparedStatement.setString(4, showtime.getShowtimeID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Showtime showtime) {
        Connection connection = databaseServices.getConnection();

        String queryDelete = "DELETE FROM " + TABLE_NAME + " WHERE showtime_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryDelete);
            preparedStatement.setString(1, showtime.getShowtimeID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
