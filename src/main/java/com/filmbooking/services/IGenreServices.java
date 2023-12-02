package com.filmbooking.services;

import com.filmbooking.model.Genre;
import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.List;

public interface IGenreServices {
    void setSessionProvider(HibernateSessionProvider sessionProvider);
    List<Genre> getAll();
    Genre getByID(String id);
    void save(Genre genre);
    void update(Genre genre);
    void delete(Genre genre);
}
