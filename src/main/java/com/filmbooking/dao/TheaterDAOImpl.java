package com.filmbooking.dao;

import com.filmbooking.model.Theater;
import com.filmbooking.hibernate.HibernateSessionProvider;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TheaterDAOImpl implements IDAO<Theater> {
    private static TheaterDAOImpl instance = null;
    private Session session;

    public TheaterDAOImpl() {
    }

    public static TheaterDAOImpl getInstance() {
        if (instance == null) {
            instance = new TheaterDAOImpl();
        }
        return instance;
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.session = sessionProvider.getSession();
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
        Theater result = null;

        try {
            CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
            CriteriaQuery<Theater> criteriaQuery = criteriaBuilder.createQuery(Theater.class);
            Root<Theater> rootEntry = criteriaQuery.from(Theater.class);
            criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("id"), lID));

            TypedQuery<Theater> typedQuery = this.session.createQuery(criteriaQuery);

            result = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace(System.out);
        }

        return result;
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
}
