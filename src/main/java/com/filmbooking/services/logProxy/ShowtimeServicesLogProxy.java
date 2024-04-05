package com.filmbooking.services.logProxy;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.IModel;
import com.filmbooking.services.IShowtimeServices;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public class ShowtimeServicesLogProxy<T extends IModel> extends AbstractServicesLogProxy<T> implements IShowtimeServices {
    private final ShowtimeServicesImpl showtimeServices;

    public ShowtimeServicesLogProxy(ShowtimeServicesImpl showtimeServices, HttpServletRequest request, HibernateSessionProvider sessionProvider) {
        super(request);
        this.logModelServices.setSessionProvider(sessionProvider);
        this.showtimeServices = showtimeServices;
        this.showtimeServices.setSessionProvider(sessionProvider);
    }

    @Override
    public Map<Long, Integer> getAvailableSeatsByShowtimeId() {
        return this.showtimeServices.getAvailableSeatsByShowtimeId();
    }
}
