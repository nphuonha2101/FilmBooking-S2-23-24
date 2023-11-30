package com.filmbooking.dao;

import com.filmbooking.database.DatabaseConnection;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Room;
import com.filmbooking.model.Showtime;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShowtimeDAOImpl implements IDAO<Showtime> {
    private static ShowtimeDAOImpl instance = null;
    private DatabaseConnection databaseConnection;
    private static final String TABLE_NAME = "showtime";

    private ShowtimeDAOImpl() {
        databaseConnection = DatabaseConnection.getInstance();
    }

    public static ShowtimeDAOImpl getInstance() {
        if (instance == null) {
            instance = new ShowtimeDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Showtime> getAll() {
        List<Showtime> showtimeList = new ArrayList<>();

        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

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

                Room room = RoomDAOImpl.getInstance().getByID(roomID);
                Film film = FilmDAOImpl.getInstance().getByID(filmID);

                List<FilmBooking> filmBookingList = FilmBookingDAOImpl.getInstance().getAll().stream().filter(filmBooking -> filmBooking.getShowtime().getShowtimeID().equals(showtimeID)).toList();

                Showtime showtime = new Showtime(showtimeID, film, room, showtimeDate, seatsData, filmBookingList);

                showtimeList.add(0, showtime);
            }
            resultSet.close();
            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return showtimeList;
    }

    @Override
    public Showtime getByID(String id) {
      databaseConnection.connect();
      Connection connection = databaseConnection.getConnection();

      String queryGetByID = "SELECT * FROM " + TABLE_NAME + " WHERE showtime_id = ?";

      try {
          PreparedStatement preparedStatement = connection.prepareStatement(queryGetByID);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String showtimeID = resultSet.getString("showtime_id");
                String filmID = resultSet.getString("film_id");
                String roomID = resultSet.getString("room_id");
                LocalDateTime showtimeDate = resultSet.getTimestamp("showtime_date").toLocalDateTime();
                String seatsData = resultSet.getString("seats_data");

                Room room = RoomDAOImpl.getInstance().getByID(roomID);
                Film film = FilmDAOImpl.getInstance().getByID(filmID);

                List<FilmBooking> filmBookingList = FilmBookingDAOImpl.getInstance().getAll().stream().filter(filmBooking -> filmBooking.getShowtime().getShowtimeID().equals(showtimeID)).toList();

                Showtime showtime = new Showtime(showtimeID, film, room, showtimeDate, seatsData, filmBookingList);

                resultSet.close();
                preparedStatement.close();
                databaseConnection.close();
                return showtime;
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
    public void save(Showtime showtime) {
        int largestID = this.getAll().isEmpty() ? 0 : Integer.parseInt(this.getAll().get(0).getShowtimeID());
        for (Showtime s: this.getAll()) {
            if (Integer.parseInt(s.getShowtimeID()) > largestID)
                largestID = Integer.parseInt(s.getShowtimeID());
        }
        largestID++;
        showtime.setShowtimeID(String.valueOf(largestID));
databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String querySave = "INSERT INTO " + TABLE_NAME + "(showtime_id, film_id, room_id, showtime_date, seats_data)"
                + " VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySave);

            preparedStatement.setString(1, showtime.getShowtimeID());
            preparedStatement.setString(2, showtime.getFilm().getFilmID());
            preparedStatement.setString(3, showtime.getRoom().getRoomID());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(showtime.getShowtimeDate()));
            preparedStatement.setString(5, showtime.getSeatsData());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Showtime showtime) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryUpdate = "UPDATE " + TABLE_NAME + " SET film_id = ?, room_id = ?, showtime_date = ?, seats_data = ?"
                + " WHERE showtime_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryUpdate);
            preparedStatement.setString(1, showtime.getFilm().getFilmID());
            preparedStatement.setString(2, showtime.getRoom().getRoomID());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(showtime.getShowtimeDate()));
            preparedStatement.setString(4, showtime.getSeatsData());
            preparedStatement.setString(5, showtime.getShowtimeID());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Showtime showtime) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryDelete = "DELETE FROM " + TABLE_NAME + " WHERE showtime_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryDelete);
            preparedStatement.setString(1, showtime.getShowtimeID());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
