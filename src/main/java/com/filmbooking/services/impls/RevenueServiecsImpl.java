package com.filmbooking.services.impls;

import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Revenue;
import com.filmbooking.model.Showtime;
import com.filmbooking.repository.FilmBookingRepository;

import java.util.*;
import java.util.stream.Collectors;

public class RevenueServiecsImpl {
    private FilmBookingRepository filmBookingRepository;

    public RevenueServiecsImpl() {
        this.filmBookingRepository = new FilmBookingRepository();
    }

    public List<Revenue> calculateRevenueByYear(String year) {
        List<FilmBooking> filmBookings = filmBookingRepository.selectAllByYear(year);
        List<Film> films = new ArrayList<>();
        List<Revenue> revenues = new ArrayList<>();
        if (filmBookings.size() == 0) {
            Revenue revenue = new Revenue(year, 0, 0);
            for (int i = 1; i < 13; i++) {
                revenues.add(new Revenue(i + "", 0, 0));
            }
            return revenues;
        } else {
            for (FilmBooking filmBooking : filmBookings) {
                if (!films.contains(filmBooking.getShowtime().getFilm())) {
                    films.add(filmBooking.getShowtime().getFilm());
                }
            }
            for (Film film : films) {
                List<FilmBooking> fbs = filmBookings.stream().filter(filmBooking -> filmBooking.getShowtime().getFilm().equals(film)).collect(Collectors.toList());
                int count = 0;
                double totalRevenue = 0;
                for (FilmBooking filmBooking : fbs) {
                    count++;
                    count = count * filmBooking.getBookedSeats().length;
                    totalRevenue += filmBooking.getTotalFee();
                }
                revenues.add(new Revenue(film.getFilmName(), count, totalRevenue));
            }
        }
        return revenues;
    }

    public Revenue calculateRevenueByMonth(String year, String month) {
        List<FilmBooking> filmBookings = filmBookingRepository.selectAllByMonth(month + "", year);
        List<Film> films = new ArrayList<>();
        List<Revenue> revenues = new ArrayList<>();
        Revenue revenue = null;
        if (filmBookings.size() == 0) {
            return new Revenue(month, 0, 0);
        } else {
            for (FilmBooking filmBooking : filmBookings) {
                if (!films.contains(filmBooking.getShowtime().getFilm())) {
                    films.add(filmBooking.getShowtime().getFilm());
                }
            }
            for (Film film : films) {
                List<FilmBooking> fbs = filmBookings.stream().filter(filmBooking -> filmBooking.getShowtime().getFilm().equals(film)).collect(Collectors.toList());
                int count = 0;
                double totalRevenue = 0;
                for (FilmBooking filmBooking : fbs) {
                    count++;
                    count = count * filmBooking.getBookedSeats().length;
                    totalRevenue += filmBooking.getTotalFee();
                }
                revenues.add(new Revenue(film.getFilmName(), count, totalRevenue));
            }
            int count = 0;
            double total = 0;
            for (Revenue rev : revenues) {
                count += rev.getTicketSold();
                total += rev.getFilmRevenue();
            }
            revenue = new Revenue(month, count, total);
        }
        return revenue;
    }

    public List<Revenue> getByDates(String dateStart, String dateEnd) {
        List<FilmBooking> filmBookings = filmBookingRepository.selectAllByDates(dateStart, dateEnd);
        List<Film> films = new ArrayList<>();
        List<Revenue> revenues = new ArrayList<>();
        if (filmBookings.size() == 0) {
            return null;
        } else {
            for (FilmBooking filmBooking : filmBookings) {
                if (!films.contains(filmBooking.getShowtime().getFilm())) {
                    films.add(filmBooking.getShowtime().getFilm());
                }
            }
            for (Film film : films) {
                List<FilmBooking> fbs = filmBookings.stream().filter(filmBooking -> filmBooking.getShowtime().getFilm().equals(film)).collect(Collectors.toList());
                int count = 0;
                double totalRevenue = 0;
                for (FilmBooking filmBooking : fbs) {
                    count++;
                    count = count * filmBooking.getBookedSeats().length;
                    totalRevenue += filmBooking.getTotalFee();
                }
                revenues.add(new Revenue(film.getFilmName(), count, totalRevenue));
            }
        }
        return revenues;
    }

}
