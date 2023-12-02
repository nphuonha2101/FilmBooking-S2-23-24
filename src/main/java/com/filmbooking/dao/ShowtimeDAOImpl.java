package com.filmbooking.dao;

import com.filmbooking.model.Showtime;
import com.filmbooking.hibernate.HibernateSessionProvider;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ShowtimeDAOImpl implements IDAO<Showtime> {
    private static ShowtimeDAOImpl instance = null;
    private Session session;

    private ShowtimeDAOImpl() {
    }

    public static ShowtimeDAOImpl getInstance() {
        if (instance == null) {
            instance = new ShowtimeDAOImpl();
        }
        return instance;
    }


    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.session = sessionProvider.getSession();
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
        long lID = Long.parseLong(id);
        Showtime result = null;

        try {
            CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
            CriteriaQuery<Showtime> criteriaQuery = criteriaBuilder.createQuery(Showtime.class);
            Root<Showtime> rootEntry = criteriaQuery.from(Showtime.class);
            criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("id"), lID));

            TypedQuery<Showtime> typedQuery = this.session.createQuery(criteriaQuery);

            result = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace(System.out);
        }

        return result;
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
}
