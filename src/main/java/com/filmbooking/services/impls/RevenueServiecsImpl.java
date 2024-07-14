package com.filmbooking.services.impls;

import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Showtime;
import com.filmbooking.repository.FilmBookingRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RevenueServiecsImpl {
    private FilmBookingRepository filmBookingRepository;
    private ShowtimeServicesImpl showtimeServices;
    private FilmServicesImpl filmServices;

    public RevenueServiecsImpl() {
        this.filmBookingRepository = new FilmBookingRepository();
    }

  public double calculateRevenueByYear(String year) {
    List<FilmBooking> filmBookings = filmBookingRepository.selectAllByYear(year);
    double revenue = 0;
    for (FilmBooking filmBooking : filmBookings) {
      revenue += filmBooking.getTotalFee();
    }
    return revenue;
  }

    public double calculateRevenueByMonth(String year, String month) {
        List<FilmBooking> filmBookings = filmBookingRepository.selectAllByMonth(month, year);
        double revenue = 0;
        for (FilmBooking filmBooking : filmBookings) {
        revenue += filmBooking.getTotalFee();
        }
        return revenue;
    }

    public double calculateRevenueByDate(String dateStart, String dateEnd) {
        List<FilmBooking> filmBookings = filmBookingRepository.selectAllByDates(dateStart, dateEnd);
        double revenue = 0;
        for (FilmBooking filmBooking : filmBookings) {
        revenue += filmBooking.getTotalFee();
        }
        return revenue;
    }
    public double calculateRevenueByFilm(long filmID) {
        List<FilmBooking> filmBookings = filmBookingRepository.selectAllByFilmID(filmID);
        double revenue = 0;
        for (FilmBooking filmBooking : filmBookings) {
        revenue += filmBooking.getTotalFee();
        }
        return revenue;
    }
}
