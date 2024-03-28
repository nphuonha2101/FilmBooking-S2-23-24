package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.services.AbstractServices;
import com.filmbooking.services.IServices;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilmBookingServicesImpl extends AbstractServices<FilmBooking> {

    public FilmBookingServicesImpl(HibernateSessionProvider sessionProvider) {
        super.decoratedDAO = new DataAccessObjects<>(FilmBooking.class);
        super.setSessionProvider(sessionProvider);
    }

    @Override
    public FilmBooking getBySlug(String slug) {
        throw  new UnsupportedOperationException("This method is not supported for FilmBooking");
    }

    @Override
    public FilmBooking getByID(String id) {
        return super.decoratedDAO.getByID(id, true);
    }

    /**
     * Get all film bookings from the database by user
     * @param user the user to get film bookings
     * @return a list of film bookings
     */
    public List<FilmBooking> getAllByUser(User user) {
        Map<String, Object> condition= Map.of("user_=", user);
        return super.getByPredicates(condition).getMultipleResults();
    }


}
