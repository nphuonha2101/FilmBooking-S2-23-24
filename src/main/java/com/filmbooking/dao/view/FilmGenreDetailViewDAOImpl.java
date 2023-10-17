package com.filmbooking.dao.view;

import com.filmbooking.dao.IDAO;
import com.filmbooking.database.DatabaseServices;
import com.filmbooking.model.view.FilmGenreDetailView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmGenreDetailViewDAOImpl implements IDAO<FilmGenreDetailView> {

    private List<FilmGenreDetailView> filmGenreDetailViewList;
    private DatabaseServices databaseServices;
    private static final String TABLE_NAME = "v_film_genre_details";

    public FilmGenreDetailViewDAOImpl() {
        filmGenreDetailViewList = new ArrayList<>();
        databaseServices = new DatabaseServices();
        databaseServices.connectDatabase();
    }
    @Override
    public List<FilmGenreDetailView> getAll() {
        Connection connection = databaseServices.getConnection();

        String queryGetAll = "SELECT * FROM " + TABLE_NAME;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String genreID = resultSet.getString("genre_id");
                String genreName = resultSet.getString("genre_name");
                String filmID = resultSet.getString("film_id");

                FilmGenreDetailView newFilmGenreDetailView = new FilmGenreDetailView(genreID, genreName, filmID);

                filmGenreDetailViewList.add(newFilmGenreDetailView);
            }
            return filmGenreDetailViewList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FilmGenreDetailView getByID(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(FilmGenreDetailView filmGenreDetailView) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(FilmGenreDetailView filmGenreDetailView) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(FilmGenreDetailView filmGenreDetailView) {
        throw new UnsupportedOperationException();
    }


}
