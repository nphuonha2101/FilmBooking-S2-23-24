package com.filmbooking.dao;

import com.filmbooking.database.DatabaseConnection;
import com.filmbooking.model.Film;
import com.filmbooking.model.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmGenreDAOImpl implements IManyToManyDAO<Film, Genre> {
    private static FilmGenreDAOImpl instance = null;
    private final DatabaseConnection databaseConnection;


    private FilmGenreDAOImpl() {
        databaseConnection = DatabaseConnection.getInstance();
    }

    public static FilmGenreDAOImpl getInstance() {
        if (instance == null) {
            instance = new FilmGenreDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Genre> getAllTByO(Film film) {
        List<Genre> genreList = new ArrayList<>();

        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryGetAll = "SELECT genre.* FROM genre INNER JOIN film_genre ON genre.genre_id = film_genre.genre_id " +
                "INNER JOIN film ON film.film_id = film_genre.film_id " +
                "WHERE film_genre.film_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetAll);
            preparedStatement.setString(1, film.getFilmID());

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
        return null;
    }

    @Override
    public List<Film> getAllOByT(Genre genre) {
        List<Film> filmList = new ArrayList<>();

        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryGetAll = "SELECT film.* FROM film INNER JOIN film_genre ON film.film_id = film_genre.film_id " +
                "INNER JOIN genre ON genre.genre_id = film_genre.genre_id " +
                "WHERE film_genre.genre_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetAll);
            preparedStatement.setString(1, genre.getGenreID());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String filmID = resultSet.getString("film_id");
                String filmName = resultSet.getString("film_name");
                double filmPrice = Double.parseDouble(resultSet.getString("film_price"));
                String filmDirector = resultSet.getString("film_director");
                String filmCast = resultSet.getString("film_cast");
                int filmLength = resultSet.getInt("film_length");
                String filmDescription = resultSet.getString("film_description");
                String filmTrailerLink = resultSet.getString("film_trailer_link");
                String imgPath = resultSet.getString("img_path");

                Film film = new Film(filmID, filmName, filmPrice, filmDirector, filmCast, filmLength, filmDescription, filmTrailerLink, imgPath);


                filmList.add(0, film);
            }

            resultSet.close();
            preparedStatement.close();
            databaseConnection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return filmList;
    }


    @Override
    public Film getOByID(String filmID, String genreID) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryGetByID = "SELECT film.* FROM film INNER JOIN film_genre ON film.film_id = film_genre.film_id " +
                "INNER JOIN genre ON genre.genre_id = film_genre.genre_id " +
                "WHERE film_genre.genre_id = ? AND film_genre.film_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetByID);
            preparedStatement.setString(1, genreID);
            preparedStatement.setString(2, filmID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                String filmName = resultSet.getString("film_name");
                double filmPrice = Double.parseDouble(resultSet.getString("film_price"));
                String filmDirector = resultSet.getString("film_director");
                String filmCast = resultSet.getString("film_cast");
                int filmLength = resultSet.getInt("film_length");
                String filmDescription = resultSet.getString("film_description");
                String filmTrailerLink = resultSet.getString("film_trailer_link");
                String imgPath = resultSet.getString("img_path");

                resultSet.close();
                preparedStatement.close();

                Film newFilm = new Film(filmID, filmName, filmPrice, filmDirector, filmCast, filmLength, filmDescription, filmTrailerLink, imgPath);

                return newFilm;
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
    public Genre getTByID(String filmID, String genreID) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryGetByID = "SELECT film.* FROM film INNER JOIN film_genre ON film.film_id = film_genre.film_id " +
                "INNER JOIN genre ON genre.genre_id = film_genre.genre_id " +
                "WHERE film_genre.genre_id = ? AND film_genre.film_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetByID);
            preparedStatement.setString(1, genreID);
            preparedStatement.setString(2, filmID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {


                String genreName = resultSet.getString("genre_name");

                resultSet.close();
                preparedStatement.close();

                return new Genre(genreID, genreName);
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
    public void save(Film film, Genre genre) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String querySave = "INSERT INTO film_genre(film_id, genre_id) VALUES(?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySave);

            preparedStatement.setString(1, film.getFilmID());
            preparedStatement.setString(2, genre.getGenreID());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void delete(Film film, Genre genre) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryDelete = "DELETE FROM film_genre WHERE film_id = ? AND genre_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryDelete);
            preparedStatement.setString(1, film.getFilmID());
            preparedStatement.setString(2, genre.getGenreID());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
