package com.filmbooking.services.logProxy;

import com.filmbooking.model.IModel;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public class ShowtimeServicesLogProxy extends AbstractServicesLogProxy<Showtime> implements IShowtimeServices {
    private final ShowtimeServicesImpl showtimeServices;

    public ShowtimeServicesLogProxy(ShowtimeServicesImpl showtimeServices, HttpServletRequest req) {
        super(req, Showtime.class);
        this.showtimeServices = showtimeServices;
    }

    @Override
    public Map<Long, Integer> getAvailableSeatsByShowtimeId() {
        return this.showtimeServices.getAvailableSeatsByShowtimeId();
    }
}
