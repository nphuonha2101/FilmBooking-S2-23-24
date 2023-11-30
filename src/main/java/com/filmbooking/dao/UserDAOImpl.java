package com.filmbooking.dao;

import com.filmbooking.database.DatabaseConnection;
import com.filmbooking.model.Film;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
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

public class UserDAOImpl implements IDAO<User> {
    private static UserDAOImpl instance = null;
    private final HibernateUtils hibernateUtils;
    private Session session;

    private UserDAOImpl() {
        hibernateUtils = HibernateUtils.getInstance();
    }

    public static UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    @Override
    public void openSession() {
        this.session = hibernateUtils.openSession();
    }

    @Override
    public List<User> getAll() {
        List<User> result;

        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
        // declare an object that want to query
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> rootEntry = criteriaQuery.from(User.class);
        CriteriaQuery<User> all = criteriaQuery.select(rootEntry);

        TypedQuery<User> allQuery = this.session.createQuery(all);

        result = allQuery.getResultList();

        return result;
    }


    @Override
    public User getByID(String id) {
        long lID = Long.parseLong(id);

        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> rootEntry = criteriaQuery.from(User.class);
        criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("id"), lID));

        TypedQuery<User> typedQuery = this.session.createQuery(criteriaQuery);


        return typedQuery.getSingleResult();
    }

    @Override
    public void save(User user) {
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
    }

    @Override
    public void update(User user) {
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
    }

    @Override
    public void delete(User user) {
        Transaction transaction = session.beginTransaction();
        session.remove(user);
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
