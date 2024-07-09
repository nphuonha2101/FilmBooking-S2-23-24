package com.filmbooking.services.impls;

import com.filmbooking.model.FilmBooking;
import com.filmbooking.repository.FilmBookingRepository;

import java.util.List;

public class RevenueServiecsImpl {
    private FilmBookingRepository filmBookingRepository;

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
}
