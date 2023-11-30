package com.filmbooking.dao;

import com.filmbooking.database.DatabaseConnection;
import com.filmbooking.model.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAOImpl implements IDAO<Genre> {
    private static GenreDAOImpl instance;
    private final DatabaseConnection databaseConnection;
    private static final String TABLE_NAME = "genre";

    private GenreDAOImpl() {
        databaseConnection = DatabaseConnection.getInstance();
    }

    public static GenreDAOImpl getInstance() {
        if (instance == null) {
            instance = new GenreDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> genreList = new ArrayList<>();

        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryGetAll = "SELECT * FROM " + TABLE_NAME;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String genreID = resultSet.getString("genre_id");
                String genreName = resultSet.getString("genre_name");

                Genre genre = new Genre(genreID, genreName);

                genreList.add(0, genre);
            }

            resultSet.close();
            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return genreList;
    }

    @Override
    public Genre getByID(String id) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryGetAll = "SELECT * FROM " + TABLE_NAME + " WHERE genre_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetAll);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String genreID = resultSet.getString("genre_id");
                String genreName = resultSet.getString("genre_name");

                Genre genre = new Genre(genreID, genreName);

                resultSet.close();
                preparedStatement.close();
                databaseConnection.close();
                return genre;
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
    public void save(Genre genre) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String querySave = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySave);
            preparedStatement.setString(1, genre.getGenreID());
            preparedStatement.setString(2, genre.getGenreName());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Genre genre) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String querySave = "UPDATE " + TABLE_NAME + " SET genre_name = ? WHERE genre_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySave);
            preparedStatement.setString(1, genre.getGenreName());
            preparedStatement.setString(2, genre.getGenreID());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Genre genre) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryDelete = "DELETE FROM " + TABLE_NAME + " WHERE genre_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryDelete);
            preparedStatement.setString(1, genre.getGenreID());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
