package com.filmbooking.services.impls;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.services.AbstractCRUDServices;

public class FilmBookingServicesImpl extends AbstractCRUDServices<FilmBooking> {

    public FilmBookingServicesImpl(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO = new DataAccessObjects<>(FilmBooking.class);
        this.setSessionProvider(sessionProvider);
    }

    public FilmBookingServicesImpl() {
        this.decoratedDAO = new DataAccessObjects<>(FilmBooking.class);
    }

    @Override
    public String getTableName() {
        return FilmBooking.TABLE_NAME;
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO.setSessionProvider(sessionProvider);
    }

    @Override
    public FilmBooking getBySlug(String slug) {
        throw new UnsupportedOperationException("This method is not supported for FilmBooking");
    }

    @Override
    public FilmBooking getByID(String id) {
        if (!Objects.equals(id, "null"))
            return this.decoratedDAO.getByID(id, true);
        else {
            throw new RuntimeException("ID must not be null");
        }
    }

    /**
     * Get all film bookings from the database by user
     *
     * @param user the user to get film bookings
     * @return a list of film bookings
     */
    public List<FilmBooking> getAllByUser(User user) {
        Map<String, Object> condition = Map.of("user_=", user);
        return this.getByPredicates(condition).getMultipleResults();
    }



}
