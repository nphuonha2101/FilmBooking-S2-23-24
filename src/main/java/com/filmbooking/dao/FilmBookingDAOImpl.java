package com.filmbooking.dao;

import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FilmBooking;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FilmBookingDAOImpl extends AbstractDAOImpl<FilmBooking> {
    private static FilmBookingDAOImpl instance = null;

    private FilmBookingDAOImpl() {
        super(FilmBooking.class);
    }

    public static FilmBookingDAOImpl getInstance() {
        if (instance == null)
            instance = new FilmBookingDAOImpl();
        return instance;
    }

    @Override
    public FilmBooking getByID(String id) {
        long lID = Long.parseLong(id);
        FilmBooking result = null;

        try {
            CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
            CriteriaQuery<FilmBooking> criteriaQuery = criteriaBuilder.createQuery(FilmBooking.class);
            Root<FilmBooking> rootEntry = criteriaQuery.from(FilmBooking.class);
            criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("id"), lID));

            TypedQuery<FilmBooking> typedQuery = this.session.createQuery(criteriaQuery);

            result = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace(System.out);
        }

        return result;
    }

}