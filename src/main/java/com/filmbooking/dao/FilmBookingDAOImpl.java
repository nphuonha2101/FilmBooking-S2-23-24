package com.filmbooking.dao;

import com.filmbooking.database.DatabaseConnection;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Showtime;
import com.filmbooking.model.User;
import com.filmbooking.utils.HibernateUtils;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilmBookingDAOImpl implements IDAO<FilmBooking> {
    private static FilmBookingDAOImpl instance = null;
    private final HibernateUtils hibernateUtils;
    private Session session;

    private FilmBookingDAOImpl() {
        hibernateUtils = HibernateUtils.getInstance();
    }

    public static FilmBookingDAOImpl getInstance() {
        if (instance == null)
            instance = new FilmBookingDAOImpl();
        return instance;
    }

    @Override
    public void openSession() {
        this.session = hibernateUtils.openSession();
    }

    @Override
    public List<FilmBooking> getAll() {
        List<FilmBooking> result;

        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
        // declare an object that want to query
        CriteriaQuery<FilmBooking> criteriaQuery = criteriaBuilder.createQuery(FilmBooking.class);
        Root<FilmBooking> rootEntry = criteriaQuery.from(FilmBooking.class);
        CriteriaQuery<FilmBooking> all = criteriaQuery.select(rootEntry);

        TypedQuery<FilmBooking> allQuery = this.session.createQuery(all);

        result = allQuery.getResultList();

        return result;
    }

    @Override
    public FilmBooking getByID(String id) {
        long lID = Long.parseLong(id);

        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
        CriteriaQuery<FilmBooking> criteriaQuery = criteriaBuilder.createQuery(FilmBooking.class);
        Root<FilmBooking> rootEntry = criteriaQuery.from(FilmBooking.class);
        criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("id"), lID));

        TypedQuery<FilmBooking> typedQuery = this.session.createQuery(criteriaQuery);


        return typedQuery.getSingleResult();
    }

    @Override
    public void save(FilmBooking filmBooking) {
        Transaction transaction = session.beginTransaction();
        session.persist(filmBooking);
        transaction.commit();
    }

    @Override
    public void update(FilmBooking filmBooking) {
        Transaction transaction = session.beginTransaction();
        session.merge(filmBooking);
        transaction.commit();
    }

    @Override
    public void delete(FilmBooking filmBooking) {
        Transaction transaction = session.beginTransaction();
        session.remove(filmBooking);
        transaction.commit();
    }

    @Override
    public Session getSession() {
        if (session != null) {
            return this.session;
        } else {
            throw new RuntimeException("Not active Hibernate Session");
        }
    }
}