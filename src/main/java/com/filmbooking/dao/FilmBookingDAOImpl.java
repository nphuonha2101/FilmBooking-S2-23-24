package com.filmbooking.dao;

import com.filmbooking.database.DatabaseServices;
import com.filmbooking.model.FilmBooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilmBookingDAOImpl implements IDAO<FilmBooking> {
    private static final String TABLE_NAME = "film_booking";
    private final List<FilmBooking> filmBookingList;

    private final DatabaseServices databaseServices;

    public FilmBookingDAOImpl() {
        filmBookingList = new ArrayList<>();
        databaseServices = new DatabaseServices();
        databaseServices.connectDatabase();
    }

    @Override
    public List<FilmBooking> getAll() {
        Connection connection = databaseServices.getConnection();
        String queryGetAll = "SELECT * FROM " + TABLE_NAME;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String filmBookingID = resultSet.getString("booking_film_id");
                String showtimeID = resultSet.getString("showtime_id");
                String username = resultSet.getString("username");
                Date bookingDate = resultSet.getDate("booking_date");
                String seat = resultSet.getString("seats");
                double totalPrice = resultSet.getDouble("total_fee");

                FilmBooking newFilmBooking = new FilmBooking(filmBookingID, showtimeID, username, bookingDate, seat.split(" "), totalPrice);

                filmBookingList.add(0, newFilmBooking);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filmBookingList;
    }

    @Override
    public FilmBooking getByID(String id) {
        getAll();
        for (FilmBooking filmBooking : filmBookingList
        ) {
            if (id.equalsIgnoreCase(filmBooking.getFilmBookingID())) return filmBooking;
        }
        return null;
    }

    @Override
    public void save(FilmBooking filmBooking) {
        Connection connection = databaseServices.getConnection();
        String queryAdd = "INSERT INTO " + TABLE_NAME + "(username, showtime_id, booking_date, seat, total_price) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryAdd);
            preparedStatement.setString(1, filmBooking.getUsername());
            preparedStatement.setString(2, filmBooking.getShowtimeID());
            preparedStatement.setDate(3,(java.sql.Date) filmBooking.getBookingDate());
//            preparedStatement.setString(4,filmBooking.getSeat());
            preparedStatement.setDouble(5,filmBooking.getTotalFee());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(FilmBooking filmBooking) {
        Connection connection = databaseServices.getConnection();

        //showtime_id, username, booking_date, seat, total_price
        String querySet = "UPDATE " + TABLE_NAME
                + " SET showtime_id = ?, username = ?, booking_date = ?, seat = ?, total_price = ? WHERE booking_film_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySet);

            preparedStatement.setString(1, filmBooking.getShowtimeID());
            preparedStatement.setString(2, filmBooking.getUsername());
            preparedStatement.setDate(3,(java.sql.Date) filmBooking.getBookingDate());
//            preparedStatement.setString(4, filmBooking.getSeat());
            preparedStatement.setDouble(5, filmBooking.getTotalFee());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(FilmBooking filmBooking) {
        Connection connection = databaseServices.getConnection();
        String queryDel = "DELETE FROM " + TABLE_NAME + " WHERE booking_film_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryDel);
            preparedStatement.setString(1, filmBooking.getFilmBookingID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }

