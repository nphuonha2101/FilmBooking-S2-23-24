package com.filmbooking.DAOservices;

import com.filmbooking.databaseConfig.DatabaseServices;
import com.filmbooking.model.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDAOServicesImpl implements IFilmDAOServices{
    private DatabaseServices databaseServices;
    private List<Film> filmList;

    public FilmDAOServicesImpl() {
        filmList = new ArrayList<>();

        databaseServices = new DatabaseServices();
        databaseServices.connectDatabase();
        Connection connection = databaseServices.getConnection();

        String queryGetAll = "SELECT * FROM film";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String filmID = resultSet.getString("film_id");
                String filmName = resultSet.getString("film_name");
                double filmPrice = Double.parseDouble(resultSet.getString("film_price"));
                String roomId = resultSet.getString("room_id");

                Film film = new Film(filmID, filmName, filmPrice, roomId);

                filmList.add(film);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Film> getAll() {
        return filmList;
    }

    @Override
    public boolean saveFilm(Film film) {
        return false;
    }

    @Override
    public Film getFilmByID(String filmID) {
        return null;
    }

    @Override
    public List<Film> getFilmByName(String filmName) {
        return null;
    }

    @Override
    public boolean deleteFilm(Film film) {
        return false;
    }
}
