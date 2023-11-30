package com.filmbooking;

import com.filmbooking.dao.FilmBookingDAOImpl;
import com.filmbooking.dao.FilmDAOImpl;
import com.filmbooking.model.Film;

public class Test {
    public static void main(String[] args) {
        FilmDAOImpl filmBookingDAO = FilmDAOImpl.getInstance();
        System.out.println(filmBookingDAO.getByID("1"));
    }
}
