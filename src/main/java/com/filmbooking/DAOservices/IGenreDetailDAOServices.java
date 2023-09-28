package com.filmbooking.DAOservices;

import com.filmbooking.model.GenreDetail;

import java.util.List;

public interface IGenreDetailDAOServices {
    List<GenreDetail> getAll();
    List<GenreDetail> findGenresByFilmID(String filmID);

}
