package com.filmbooking.DAOservices;

import com.filmbooking.databaseConfig.DatabaseServices;
import com.filmbooking.model.GenreDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDetailDAOServicesImpl implements IGenreDetailDAOServices{
    private DatabaseServices databaseServices;
    private List<GenreDetail> listGenre;
    public GenreDetailDAOServicesImpl(){
        listGenre = new ArrayList<>();

        databaseServices = new DatabaseServices();
        databaseServices.connectDatabase();
        Connection connection = databaseServices.getConnection();

        String queryGetAll = "SELECT * FROM genre_detail_view";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String genreID = resultSet.getString("genre_id");
                String genreName = resultSet.getString("genre_name");
                String filmID = resultSet.getString("film_id");

                GenreDetail genreDetail = new GenreDetail(genreID,genreName,filmID);

                listGenre.add(genreDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<GenreDetail> getAll() {
        return listGenre;
    }

    @Override
    public List<GenreDetail> findGenresByFilmID(String filmID) {
        List<GenreDetail> result = new ArrayList<>();
        for (GenreDetail genreDetail: listGenre
             ) {
            if (genreDetail.getFilmID().equalsIgnoreCase(filmID)) result.add(genreDetail);
        }
        return result;
    }
}
