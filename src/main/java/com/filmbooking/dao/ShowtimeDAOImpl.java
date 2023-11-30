package com.filmbooking.dao;

import com.filmbooking.database.DatabaseConnection;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Room;
import com.filmbooking.model.Showtime;
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

public class ShowtimeDAOImpl implements IDAO<Showtime> {
    private static ShowtimeDAOImpl instance = null;
    private final HibernateUtils hibernateUtils;
    private Session session;

    private ShowtimeDAOImpl() {
        hibernateUtils = HibernateUtils.getInstance();
    }

    public static ShowtimeDAOImpl getInstance() {
        if (instance == null) {
            instance = new ShowtimeDAOImpl();
        }
        return instance;
    }

    @Override
    public void openSession() {
        this.session = hibernateUtils.openSession();
    }

    @Override
    public List<Showtime> getAll() {
        List<Showtime> result;

        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
        // declare an object that want to query
        CriteriaQuery<Showtime> criteriaQuery = criteriaBuilder.createQuery(Showtime.class);
        Root<Showtime> rootEntry = criteriaQuery.from(Showtime.class);
        CriteriaQuery<Showtime> all = criteriaQuery.select(rootEntry);

        TypedQuery<Showtime> allQuery = this.session.createQuery(all);

        result = allQuery.getResultList();

        return result;
    }


    @Override
    public Showtime getByID(String id) {

        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
        CriteriaQuery<Showtime> criteriaQuery = criteriaBuilder.createQuery(Showtime.class);
        Root<Showtime> rootEntry = criteriaQuery.from(Showtime.class);
        criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("id"), id));

        TypedQuery<Showtime> typedQuery = this.session.createQuery(criteriaQuery);


        return typedQuery.getSingleResult();
    }

    @Override
    public void save(Showtime showtime) {
        Transaction transaction = session.beginTransaction();
        session.persist(showtime);
        transaction.commit();
    }

    @Override
    public void update(Showtime showtime) {
        Transaction transaction = session.beginTransaction();
        session.merge(showtime);
        transaction.commit();
    }

    @Override
    public void delete(Showtime showtime) {
        Transaction transaction = session.beginTransaction();
        session.remove(showtime);
        transaction.commit();
    }

    @Override
    public Session getSession() {
        if (session != null)
            return this.session;
        else {
            throw new RuntimeException("Not active Hibernate Session");
        }
    }
}
