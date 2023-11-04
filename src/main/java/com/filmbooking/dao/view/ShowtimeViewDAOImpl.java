package com.filmbooking.dao.view;

import com.filmbooking.dao.IDAO;
import com.filmbooking.database.DatabaseServices;
import com.filmbooking.model.view.ShowtimeView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShowtimeViewDAOImpl implements IDAO<ShowtimeView> {
    private final DatabaseServices databaseServices;
    private final List<ShowtimeView> showtimeViewList;
    private static final String TABLE_NAME = "v_showtime_details";

    public ShowtimeViewDAOImpl() {
        showtimeViewList = new ArrayList<>();
        databaseServices = DatabaseServices.getInstance();
    }

    @Override
    public List<ShowtimeView> getAll() {
        databaseServices.connect();
        Connection connection = databaseServices.getConnection();
        String queryGetALl = "SELECT * FROM " + TABLE_NAME;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetALl);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                String showtimeID = resultSet.getString("showtime_id");
                String filmName = resultSet.getString("film_name");
                String roomName = resultSet.getString("room_name");
                String theaterName = resultSet.getString("theater_name");
                LocalDateTime showtimeDate = resultSet.getTimestamp("showtime_date").toLocalDateTime();

                ShowtimeView newShowtimeView = new ShowtimeView(showtimeID, filmName, roomName, theaterName, showtimeDate);
                showtimeViewList.add(0, newShowtimeView);
            }
            resultSet.close();
            preparedStatement.close();
            databaseServices.close();
        return showtimeViewList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ShowtimeView getByID(String id) {
        getAll();
        for (ShowtimeView showtimeView : showtimeViewList
        ) {
            if (showtimeView.getShowtimeID().equalsIgnoreCase(id)) return showtimeView;
        }
        return null;
    }

    @Override
    public void save(ShowtimeView showtimeView) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(ShowtimeView showtimeView) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(ShowtimeView showtimeView) {
        throw new UnsupportedOperationException();
    }
}
