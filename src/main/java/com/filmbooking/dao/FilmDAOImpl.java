package com.filmbooking.dao;

import com.filmbooking.database.DatabaseConnection;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.Genre;
import com.filmbooking.model.Showtime;
import com.filmbooking.utils.HibernateUtils;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDAOImpl implements IDAO<Film> {
    private static FilmDAOImpl instance = null;
    private final HibernateUtils hibernateUtils;
    private Session session;

    private FilmDAOImpl() {
        hibernateUtils = HibernateUtils.getInstance();
    }

    public static FilmDAOImpl getInstance() {
        if (instance == null) {
            instance = new FilmDAOImpl();
        }
        return instance;
    }


    @Override
    public void openSession() {
        this.session = hibernateUtils.openSession();
    }

    @Override
    public List<Film> getAll() {
        List<Film> result;

        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
        // declare an object that want to query
        CriteriaQuery<Film> criteriaQuery = criteriaBuilder.createQuery(Film.class);
        Root<Film> rootEntry = criteriaQuery.from(Film.class);
        CriteriaQuery<Film> all = criteriaQuery.select(rootEntry);

        TypedQuery<Film> allQuery = this.session.createQuery(all);

        result = allQuery.getResultList();

        return result;
    }


    @Override
    public Film getByID(String id) {

        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
        CriteriaQuery<Film> criteriaQuery = criteriaBuilder.createQuery(Film.class);
        Root<Film> rootEntry = criteriaQuery.from(Film.class);
        criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("id"), id));

        TypedQuery<Film> typedQuery = this.session.createQuery(criteriaQuery);


        return typedQuery.getSingleResult();
    }

    @Override
    public void save(Film film) {
        Transaction transaction = session.beginTransaction();
        session.persist(film);
        transaction.commit();
    }

    @Override
    public void update(Film film) {
        Transaction transaction = session.beginTransaction();
        session.merge(film);
        transaction.commit();
    }

    @Override
    public void delete(Film film) {
        Transaction transaction = session.beginTransaction();
        session.remove(film);
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
