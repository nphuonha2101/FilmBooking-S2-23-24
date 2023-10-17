package com.filmbooking.services;

import com.filmbooking.model.view.FilmGenreDetailView;

import java.util.List;

public interface IFilmGenreDetailViewServices {
    List<FilmGenreDetailView> getAll();
    List<FilmGenreDetailView> getByFilmID(String id);
}
