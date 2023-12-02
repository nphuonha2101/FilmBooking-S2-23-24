package com.filmbooking.dao;

import com.filmbooking.model.User;
import com.filmbooking.hibernate.HibernateSessionProvider;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAOImpl implements IDAO<User> {
    private static UserDAOImpl instance = null;
    private Session session;

    private UserDAOImpl() {
    }

    public static UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.session = sessionProvider.getSession();
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
        User result = null;

        try {
            CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> rootEntry = criteriaQuery.from(User.class);
            criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("id"), id));

            TypedQuery<User> typedQuery = this.session.createQuery(criteriaQuery);

            result = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace(System.out);
        }

        return result;
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

}
