package com.filmbooking.services;

import com.filmbooking.model.Theater;
import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.List;

public interface ITheaterServices {
    void setSessionProvider(HibernateSessionProvider sessionProvider);
    List<Theater> getAll();
    Theater getByID(String id);
    void save(Theater theater);
    void update(Theater theater);
    void delete(Theater theater);
}
