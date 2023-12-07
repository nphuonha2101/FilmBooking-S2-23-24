package com.filmbooking.dao;

import com.filmbooking.model.Showtime;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Transaction;

public class ShowtimeDAOImpl extends AbstractDAOImpl<Showtime> {
    private static ShowtimeDAOImpl instance = null;

    private ShowtimeDAOImpl() {
        super(Showtime.class);
    }

    public static ShowtimeDAOImpl getInstance() {
        if (instance == null) {
            instance = new ShowtimeDAOImpl();
        }
        return instance;
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
}
