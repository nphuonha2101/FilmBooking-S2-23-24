package com.filmbooking.dao;

import com.filmbooking.database.DatabaseConnection;
import com.filmbooking.model.Film;
import com.filmbooking.model.Room;
import com.filmbooking.model.Showtime;
import com.filmbooking.model.Theater;
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

public class TheaterDAOImpl implements IDAO<Theater> {
    private static TheaterDAOImpl instance = null;
    private final HibernateUtils hibernateUtils;
    private Session session;

    public TheaterDAOImpl() {
        hibernateUtils = HibernateUtils.getInstance();
    }

    public static TheaterDAOImpl getInstance() {
        if (instance == null) {
            instance = new TheaterDAOImpl();
        }
        return instance;
    }

    @Override
    public void openSession() {
        this.session = hibernateUtils.openSession();
    }

    @Override
    public List<Theater> getAll() {
        List<Theater> result;

        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
        // declare an object that want to query
        CriteriaQuery<Theater> criteriaQuery = criteriaBuilder.createQuery(Theater.class);
        Root<Theater> rootEntry = criteriaQuery.from(Theater.class);
        CriteriaQuery<Theater> all = criteriaQuery.select(rootEntry);

        TypedQuery<Theater> allQuery = this.session.createQuery(all);

        result = allQuery.getResultList();

        return result;
    }


    @Override
    public Theater getByID(String id) {
        long lID = Long.parseLong(id);

        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
        CriteriaQuery<Theater> criteriaQuery = criteriaBuilder.createQuery(Theater.class);
        Root<Theater> rootEntry = criteriaQuery.from(Theater.class);
        criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("id"), lID));

        TypedQuery<Theater> typedQuery = this.session.createQuery(criteriaQuery);


        return typedQuery.getSingleResult();
    }

    @Override
    public void save(Theater theater) {
        Transaction transaction = session.beginTransaction();
        session.persist(theater);
        transaction.commit();
    }

    @Override
    public void update(Theater theater) {
        Transaction transaction = session.beginTransaction();
        session.merge(theater);
        transaction.commit();
    }

    @Override
    public void delete(Theater theater) {
        Transaction transaction = session.beginTransaction();
        session.remove(theater);
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
