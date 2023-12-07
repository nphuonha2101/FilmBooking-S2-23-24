package com.filmbooking.dao;

import com.filmbooking.model.Genre;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

public class GenreDAOImpl extends AbstractDAOImpl<Genre> {
    private static GenreDAOImpl instance;

    private GenreDAOImpl() {
        super(Genre.class);
    }

    public static GenreDAOImpl getInstance() {
        if (instance == null) {
            instance = new GenreDAOImpl();
        }
        return instance;
    }

    @Override
    public Genre getByID(String id) {
        Genre result = null;

        try {
            CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
            CriteriaQuery<Genre> criteriaQuery = criteriaBuilder.createQuery(Genre.class);
            Root<Genre> rootEntry = criteriaQuery.from(Genre.class);
            criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("id"), id));

            TypedQuery<Genre> typedQuery = this.session.createQuery(criteriaQuery);

            result = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace(System.out);
        }

        return result;
    }
}
