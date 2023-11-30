package com.filmbooking.dao;

import com.filmbooking.database.DatabaseConnection;
import com.filmbooking.model.Film;
import com.filmbooking.model.Genre;
import com.filmbooking.model.Showtime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDAOImpl implements IDAO<Film> {
    private static FilmDAOImpl instance = null;
    private static final String TABLE_NAME = "film";
    private final DatabaseConnection databaseConnection;

    private FilmDAOImpl() {
        databaseConnection = DatabaseConnection.getInstance();
    }

    public static FilmDAOImpl getInstance() {
        if (instance == null) {
            instance = new FilmDAOImpl();
        }
        return instance;
    }


    @Override
    public List<Film> getAll() {
        List<Film> filmList = new ArrayList<>();

        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryGetAll = "SELECT * FROM " + TABLE_NAME;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetAll);
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

                Film film = new Film(filmID, filmName, filmPrice, filmDirector, filmCast, filmLength, filmDescription
                        , filmTrailerLink, imgPath, null, null);

                filmList.add(0, film);


            }
            resultSet.close();
            preparedStatement.close();
            databaseConnection.close();

            for (Film film :
                    filmList) {

                // find genre list of this film
                List<Genre> filmGenreList = FilmGenreDAOImpl.getInstance().getAllTByO(this.getByID(film.getFilmID()));

                // find showtime list of this film
                List<Showtime> filmShowtimeList = ShowtimeDAOImpl.getInstance().getAll().stream().filter(showtime -> showtime.getFilm().getFilmID().equals(film.getFilmID())).toList();

                film.setGenreList(filmGenreList);
                film.setShowtimeList(filmShowtimeList); 
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filmList;
    }


    @Override
    public Film getByID(String id) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();

        String queryGetByID = "SELECT * FROM " + TABLE_NAME + " WHERE film_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetByID);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                String filmID = resultSet.getString("film_id");
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

                // find genre list of this film
                List<Genre> filmGenreList = FilmGenreDAOImpl.getInstance().getAllTByO(this.getByID(filmID));

                // find showtime list of this film
                List<Showtime> filmShowtimeList = ShowtimeDAOImpl.getInstance().getAll().stream().filter(showtime -> showtime.getFilm().getFilmID().equals(filmID)).toList();

                Film film = new Film(filmID, filmName, filmPrice, filmDirector, filmCast, filmLength, filmDescription
                        , filmTrailerLink, imgPath, filmGenreList, filmShowtimeList);

                return film;
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
    public void save(Film film) {
        int largestID = this.getAll().isEmpty() ? 0 : Integer.parseInt(this.getAll().get(0).getFilmID());
        for (Film f : this.getAll()) {
            if (Integer.parseInt(f.getFilmID()) > largestID)
                largestID = Integer.parseInt(f.getFilmID());

        }
        largestID++;
        film.setFilmID(String.valueOf(largestID));

        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();
        String queryAdd = "INSERT INTO " + TABLE_NAME + "(film_id, film_name, film_price, film_director, film_cast ," +
                "film_length,film_description, film_trailer_link, img_path) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryAdd);
            preparedStatement.setString(1, film.getFilmID());
            preparedStatement.setString(2, film.getFilmName());
            preparedStatement.setDouble(3, film.getFilmPrice());
            preparedStatement.setString(4, film.getDirector());
            preparedStatement.setString(5, film.getCast());
            preparedStatement.setInt(6, film.getFilmLength());
            preparedStatement.setString(7, film.getFilmDescription());
            preparedStatement.setString(8, film.getFilmTrailerLink());
            preparedStatement.setString(9, film.getImgPath());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Film film) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();
        String querySet = "UPDATE " + TABLE_NAME
                + " SET film_name = ?, film_price = ?, film_director = ?, film_cast = ?, film_length = ?, " +
                "film_description = ?, film_trailer_link = ?, img_path = ? WHERE film_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySet);

            preparedStatement.setString(1, film.getFilmName());
            preparedStatement.setDouble(2, film.getFilmPrice());
            preparedStatement.setString(3, film.getDirector());
            preparedStatement.setString(4, film.getCast());
            preparedStatement.setInt(5, film.getFilmLength());
            preparedStatement.setString(6, film.getFilmDescription());
            preparedStatement.setString(7, film.getFilmTrailerLink());
            preparedStatement.setString(8, film.getImgPath());
            preparedStatement.setString(9, film.getFilmID());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Film film) {
        databaseConnection.connect();
        Connection connection = databaseConnection.getConnection();
        String queryDel = "DELETE FROM " + TABLE_NAME + " WHERE film_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryDel);
            preparedStatement.setString(1, film.getFilmID());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
