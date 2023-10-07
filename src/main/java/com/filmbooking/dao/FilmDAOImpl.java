package com.filmbooking.dao;

import com.filmbooking.database.DatabaseServices;
import com.filmbooking.model.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDAOImpl implements IDAO<Film>{
    private List<Film> filmList;
    private DatabaseServices databaseServices;
    private static final String TABLE_NAME = "film";

    public FilmDAOImpl() {
        filmList = new ArrayList<>();
        databaseServices = new DatabaseServices();
        databaseServices.connectDatabase();
    }
    @Override
    public List<Film> getAll() {
        Connection connection = databaseServices.getConnection();

        String queryGetAll = "SELECT * FROM " + TABLE_NAME;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String filmID = resultSet.getString("film_id");
                String filmName = resultSet.getString("film_name");
                double filmPrice = Double.parseDouble(resultSet.getString("film_price"));
                String filmDirector = resultSet.getString("film_director");
                String filmActors= resultSet.getString("film_actors");
                int filmLength = resultSet.getInt("film_length");
                String filmDescription = resultSet.getString("film_description");
                String imgPath = resultSet.getString("img_path");
                Film film = new Film(filmID, filmName, filmPrice, filmDirector, filmActors,filmLength,filmDescription, imgPath);

                filmList.add(film);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filmList;
    }


    @Override
    public Film getByID(String id) {
        for (Film filmInList: filmList
             ) {
            if (filmInList.getFilmID().equalsIgnoreCase(id))
                return filmInList;
        }
        return null;
    }

    @Override
    public void save(Film film) {
        Connection connection = databaseServices.getConnection();
        String queryAdd = "INSERT INTO " + TABLE_NAME + "(film_id, film_name, film_price, film_director,film_actors,film_length,film_description, img_path) VALUES(?, ?, ?, ?, ?, ?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryAdd);
            preparedStatement.setString(1, film.getFilmID());
            preparedStatement.setString(2, film.getFilmName());
            preparedStatement.setDouble(3, film.getFilmPrice());
            preparedStatement.setString(4, film.getDirector());
            preparedStatement.setString(5, film.getActors());
            preparedStatement.setInt(6, film.getFilmLength());
            preparedStatement.setString(7,film.getFilmDescription());
            preparedStatement.setString(8, film.getImgPath());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
//            databaseServices.disconnectDatabase();
        }
    }

    @Override
    public void update(Film film) {
        Connection connection = databaseServices.getConnection();
        String querySet = "UPDATE " + TABLE_NAME
                + " SET film_name = ?, "
                + "film_price = ?, "
                + "film_director = ?, "
                + "film_actors = ?, "
                + "film_length = ?, "
                + "film_description = ?, "
                + "img_path = ?"
                + "WHERE film_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySet);

            preparedStatement.setString(1, film.getFilmName());
            preparedStatement.setDouble(2, film.getFilmPrice());
            preparedStatement.setString(3, film.getDirector());
            preparedStatement.setString(4, film.getActors());
            preparedStatement.setInt(5, film.getFilmLength());
            preparedStatement.setString(6, film.getFilmDescription());
            preparedStatement.setString(7, film.getImgPath());
            preparedStatement.setString(8, film.getFilmID());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Film film) {
        Connection connection = databaseServices.getConnection();
        String queryDel = "DELETE FROM " + TABLE_NAME + " WHERE film_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryDel);
            preparedStatement.setString(1, film.getFilmID());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
