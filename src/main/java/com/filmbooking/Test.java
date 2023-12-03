package com.filmbooking;

import com.filmbooking.dao.FilmBookingDAOImpl;
import com.filmbooking.dao.FilmDAOImpl;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.services.impls.FilmServicesImpl;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        HibernateSessionProvider hibernateSessionProvider = new HibernateSessionProvider();
        FilmServicesImpl filmServices = new FilmServicesImpl(hibernateSessionProvider);

        for (Film film :
                filmServices.getAll()
             ) {
            System.out.println(film.getFilmGenresStr());
        };

        hibernateSessionProvider.closeSession();
    }
}
