package com.filmbooking;

import com.filmbooking.dao.FilmBookingDAOImpl;
import com.filmbooking.dao.FilmDAOImpl;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        FilmDAOImpl filmBookingDAO = FilmDAOImpl.getInstance();

        filmBookingDAO.openSession();

        List<Film> filmList = filmBookingDAO.getAll();
        System.out.println(filmList);

        filmBookingDAO.closeSession();




    }
}
