package com.filmbooking.dao;

import com.filmbooking.database.DatabaseServices;
import com.filmbooking.model.Showtime;
import com.filmbooking.model.Theater;

import java.sql.*;
import java.time.LocalDateTime;
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
                LocalDateTime showtimeDate = resultSet.getTimestamp("showtime_date").toLocalDateTime();
                String seatsData = resultSet.getString("seats_data");

                Showtime showtime = new Showtime(showtimeID, filmID, roomID, showtimeDate, seatsData);

                showtimeList.add(0, showtime);
            }
            preparedStatement.close();
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
        int largestID = this.getAll().isEmpty() ? 0 : Integer.parseInt(this.getAll().get(0).getShowtimeID());
        for (Showtime s: this.getAll()) {
            if (Integer.parseInt(s.getShowtimeID()) > largestID)
                largestID = Integer.parseInt(s.getShowtimeID());
        }
        largestID++;
        showtime.setShowtimeID(String.valueOf(largestID));

        Connection connection = databaseServices.getConnection();

        String querySave = "INSERT INTO " + TABLE_NAME + "(showtime_id, film_id, room_id, showtime_date, seats_data)"
                + " VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySave);

            preparedStatement.setString(1, showtime.getShowtimeID());
            preparedStatement.setString(2, showtime.getFilmID());
            preparedStatement.setString(3, showtime.getRoomID());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(showtime.getShowtimeDate()));
            preparedStatement.setString(5, showtime.getSeatsData());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Showtime showtime) {
        Connection connection = databaseServices.getConnection();

        String queryUpdate = "UPDATE " + TABLE_NAME + " SET film_id = ?, room_id = ?, showtime_date = ?, seats_data = ?"
                + " WHERE showtime_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryUpdate);
            preparedStatement.setString(1, showtime.getFilmID());
            preparedStatement.setString(2, showtime.getRoomID());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(showtime.getShowtimeDate()));
            preparedStatement.setString(4, showtime.getSeatsData());
            preparedStatement.setString(5, showtime.getShowtimeID());

            preparedStatement.executeUpdate();

            preparedStatement.close();
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

            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
