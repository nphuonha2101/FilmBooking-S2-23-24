package com.filmbooking.dao;

import com.filmbooking.database.DatabaseServices;
import com.filmbooking.model.FilmGenre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmGenreDAOImpl implements IDAO<FilmGenre> {
    private List<FilmGenre> filmGenreList;
    private DatabaseServices databaseServices;
    private static String TABLE_NAME = "film_genre";


    public FilmGenreDAOImpl() {
        filmGenreList = new ArrayList<>();
        databaseServices = new DatabaseServices();
        databaseServices.connectDatabase();
    }

    @Override
    public List<FilmGenre> getAll() {
        Connection connection = databaseServices.getConnection();
        String queryGetAll = "SELECT * FROM " + TABLE_NAME;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String filmID = resultSet.getString("film_id");
                String genreID = resultSet.getString("genre_id");

                FilmGenre filmGenre = new FilmGenre(filmID, genreID);

                filmGenreList.add(filmGenre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filmGenreList;
    }

    @Override
    public FilmGenre getByID(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(FilmGenre filmGenre) {

        Connection connection = databaseServices.getConnection();
        String queryAdd = "INSERT INTO " + TABLE_NAME + " (genre_id, film_id) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryAdd);

            preparedStatement.setString(1, filmGenre.getGenreID());
            preparedStatement.setString(2, filmGenre.getFilmID());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(FilmGenre filmGenre) {
        Connection connection = databaseServices.getConnection();
        String qyerySet = "UPDATE " + TABLE_NAME + " SET genre_id = ?, film_id = ? WHERE genre_id = ? AND film_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(qyerySet);

            preparedStatement.setString(1, filmGenre.getGenreID());
            preparedStatement.setString(1, filmGenre.getFilmID());
            preparedStatement.setString(3, filmGenre.getGenreID());
            preparedStatement.setString(4, filmGenre.getFilmID());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(FilmGenre filmGenre) {
        Connection connection = databaseServices.getConnection();
        String queryDelete = "DELETE FROM " + TABLE_NAME + " WHERE film_id = ? AND genre_id = ?";
        try {
            PreparedStatement deleteStatement = connection.prepareStatement(queryDelete);
            deleteStatement.setString(1, filmGenre.getFilmID());
            deleteStatement.setString(2, filmGenre.getGenreID());

            deleteStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
