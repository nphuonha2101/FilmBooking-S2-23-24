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

public class TheaterDAOImpl extends AbstractDAOImpl<Theater> {
    private static TheaterDAOImpl instance = null;

    public TheaterDAOImpl() {
        super(Theater.class);
    }

    public static TheaterDAOImpl getInstance() {
        if (instance == null) {
            instance = new TheaterDAOImpl();
        }
        return instance;
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

}
