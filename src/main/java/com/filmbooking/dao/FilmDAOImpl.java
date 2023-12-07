package com.filmbooking.dao;

import com.filmbooking.model.Film;
import com.filmbooking.hibernate.HibernateSessionProvider;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FilmDAOImpl extends AbstractDAOImpl<Film> {
    private static FilmDAOImpl instance = null;

    private FilmDAOImpl() {
        super(Film.class);
    }

    public static FilmDAOImpl getInstance() {
        if (instance == null) {
            instance = new FilmDAOImpl();
        }
        return instance;
    }

    @Override
    public Film getByID(String id) {
        long lID = Long.parseLong(id);

        Film result = null;
        try {
            CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
            CriteriaQuery<Film> criteriaQuery = criteriaBuilder.createQuery(Film.class);
            Root<Film> rootEntry = criteriaQuery.from(Film.class);
            criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("id"), lID));

            TypedQuery<Film> typedQuery = this.session.createQuery(criteriaQuery);

            result = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace(System.out);
        }

        return result;
    }
}
