package com.filmbooking.services;

import com.filmbooking.model.Genre;
import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.List;

public interface IGenreServices {
    void setSessionProvider(HibernateSessionProvider sessionProvider);
    List<Genre> getAll();
    Genre getByID(String id);
    boolean save(Genre genre);
    boolean update(Genre genre);
    boolean delete(Genre genre);
}
