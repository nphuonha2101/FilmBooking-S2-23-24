package com.filmbooking.DAOservices;

import com.filmbooking.databaseConfig.DatabaseServices;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmGenre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.function.Predicate;

public class FilmGenreDAOServicesImpl implements IFilmGenreDAOServices {
    private DatabaseServices databaseServices;
    private List<FilmGenre> filmGenreList;

    public FilmGenreDAOServicesImpl() {
        filmGenreList = new ArrayList<>();

        databaseServices = new DatabaseServices();
        databaseServices.connectDatabase();
        Connection connection = databaseServices.getConnection();

        String queryGetAll = "SELECT * FROM film_genre";

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
    }

    @Override
    public List<FilmGenre> getAll() {
        return filmGenreList;
    }

    @Override
    public boolean saveFilmGenre(FilmGenre filmGenre) {
        this.filmGenreList.add(filmGenre);

        Connection connection = databaseServices.getConnection();
        String queryAdd = "INSERT INTO film_genre(genre_id, film_id) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryAdd);

            preparedStatement.setString(1, filmGenre.getGenreID());
            preparedStatement.setString(2, filmGenre.getFilmID());

            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FilmGenre> getFilmGenreByFilmID(String filmID) {
        List<FilmGenre> result = new ArrayList<>();

        for (FilmGenre filmGenre : filmGenreList
        ) {
            if (filmGenre.getFilmID().equalsIgnoreCase(filmID))
                result.add(filmGenre);
        }
        return result;
    }

    @Override
    public void removeFilmGenresWithFilmID(String filmID) {
        System.out.println(filmGenreList.size());

//        filmGenreList.removeIf(filmGenre -> filmGenre.getFilmID().equalsIgnoreCase(filmID));

        filmGenreList.removeIf(new Predicate<FilmGenre>() {
            @Override
            public boolean test(FilmGenre filmGenre) {
                return filmGenre.getFilmID().equalsIgnoreCase(filmID);
            }
        });

        Connection connection = databaseServices.getConnection();
        String queryDelete = "DELETE FROM film_genre WHERE film_id = ?";
        try {
            PreparedStatement deleteStatement = connection.prepareStatement(queryDelete);
            deleteStatement.setString(1, filmID);

            deleteStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeFilmGenreWithFilmIDAndGenreID(String filmID, String genreID) {
        for (FilmGenre filmGenre : filmGenreList
        ) {
            if (filmGenre.getFilmID().equalsIgnoreCase(filmID) && filmGenre.getGenreID().equalsIgnoreCase(genreID))
                filmGenreList.remove(filmGenre);
        }

        Connection connection = databaseServices.getConnection();
        String queryDelete = "DELETE FROM film_genre WHERE film_id = ? AND genre_id = ?";
        try {
            PreparedStatement deleteStatement = connection.prepareStatement(queryDelete);
            deleteStatement.setString(1, filmID);
            deleteStatement.setString(2, genreID);

            deleteStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void changeGenre(String filmID, String genreIDs) {
        String[] genreIDArray = genreIDs.split("/");

        removeFilmGenresWithFilmID(filmID);

        for (String genreID : genreIDArray
        ) {
            FilmGenre filmGenre = new FilmGenre(filmID, genreID);
            filmGenreList.add(filmGenre);
        }


        String queryDelete = "DELETE FROM film_genre WHERE film_id = ? ";
        Connection connection = databaseServices.getConnection();

        try {
            PreparedStatement deleteStatement = connection.prepareStatement(queryDelete);
            deleteStatement.setString(1, filmID);

            if (deleteStatement.execute()) {
                for (String genreID : genreIDArray
                ) {
                    String queryAdd = "INSERT INTO film_genre(genre_id, film_id) VALUES(?, ?)";
                    PreparedStatement addStatement = connection.prepareStatement(queryAdd);

                    addStatement.setString(1, genreID);
                    addStatement.setString(2, filmID);

                    addStatement.execute();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
