package com.filmbooking.services.impls;

import java.util.List;
import java.util.Map;

import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.repository.FilmBookingRepository;
import com.filmbooking.services.AbstractService;

public class FilmBookingServicesImpl extends AbstractService<FilmBooking> {
    public FilmBookingServicesImpl() {
        super(new FilmBookingRepository(FilmBooking.class));
    }

    /**
     * Get all film bookings from the database by user
     *
     * @param user the user to get film bookings
     * @return a list of film bookings
     */
    public List<FilmBooking> selectAllByUser(User user) {
        Map<String, Object> conditions = Map.of("username", user.getUsername());
        return this.selectAll(conditions);
    }



}
