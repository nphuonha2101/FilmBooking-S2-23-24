package com.filmbooking.services.impls;

import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Revenue;
import com.filmbooking.model.Showtime;
import com.filmbooking.repository.FilmBookingRepository;
import com.filmbooking.repository.RevenueRepository;

import java.util.*;
import java.util.stream.Collectors;

public class RevenueServiecsImpl {
    private RevenueRepository revenueRepository;

    public RevenueServiecsImpl() {
        this.revenueRepository = new RevenueRepository();
    }

    public Revenue calculateRevenueByYear(String year) {
        return revenueRepository.selectAllByYear(year);
    }

    public Revenue calculateRevenueByMonth(String month, String year) {
        return revenueRepository.selectAllByMonth(month, year);

    }

    public List<Revenue> getByDates(String dateStart, String dateEnd){
        return revenueRepository.getByDates(dateStart, dateEnd);
    }
}
