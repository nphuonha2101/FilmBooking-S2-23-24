package com.filmbooking.dao;

import com.filmbooking.database.DatabaseConnection;
import com.filmbooking.model.Film;
import com.filmbooking.model.Genre;
import com.filmbooking.utils.HibernateUtils;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAOImpl implements IDAO<Genre> {
    private static GenreDAOImpl instance;
    private final HibernateUtils hibernateUtils;
    private Session session;


    private GenreDAOImpl() {
        hibernateUtils = HibernateUtils.getInstance();
    }

    public static GenreDAOImpl getInstance() {
        if (instance == null) {
            instance = new GenreDAOImpl();
        }
        return instance;
    }

    @Override
    public void openSession() {
        this.session = hibernateUtils.openSession();
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> result;

        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
        // declare an object that want to query
        CriteriaQuery<Genre> criteriaQuery = criteriaBuilder.createQuery(Genre.class);
        Root<Genre> rootEntry = criteriaQuery.from(Genre.class);
        CriteriaQuery<Genre> all = criteriaQuery.select(rootEntry);

        TypedQuery<Genre> allQuery = this.session.createQuery(all);

        result = allQuery.getResultList();

        return result;
    }

    @Override
    public Genre getByID(String id) {

        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
        CriteriaQuery<Genre> criteriaQuery = criteriaBuilder.createQuery(Genre.class);
        Root<Genre> rootEntry = criteriaQuery.from(Genre.class);
        criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("id"), id));

        TypedQuery<Genre> typedQuery = this.session.createQuery(criteriaQuery);


        return typedQuery.getSingleResult();
    }
    @Override
    public void save(Genre genre) {
        Transaction transaction = session.beginTransaction();
        session.persist(genre);
        transaction.commit();
    }

    @Override
    public void update(Genre genre) {
        Transaction transaction = session.beginTransaction();
        session.merge(genre);
        transaction.commit();
    }

    @Override
    public void delete(Genre genre) {
        Transaction transaction = session.beginTransaction();
        session.remove(genre);
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
