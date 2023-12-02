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

public class FilmDAOImpl implements IDAO<Film> {
    private static FilmDAOImpl instance = null;
    private Session session;

    private FilmDAOImpl() {
    }

    public static FilmDAOImpl getInstance() {
        if (instance == null) {
            instance = new FilmDAOImpl();
        }
        return instance;
    }


    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.session = sessionProvider.getSession();
    }

    @Override
    public List<Film> getAll() {
        List<Film> result;

        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
        // declare an object that want to query
        CriteriaQuery<Film> criteriaQuery = criteriaBuilder.createQuery(Film.class);
        Root<Film> rootEntry = criteriaQuery.from(Film.class);
        CriteriaQuery<Film> all = criteriaQuery.select(rootEntry);

        TypedQuery<Film> allQuery = this.session.createQuery(all);

        result = allQuery.getResultList();

        return result;
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

    @Override
    public void save(Film film) {
        Transaction transaction = session.beginTransaction();
        session.persist(film);
        transaction.commit();
    }

    @Override
    public void update(Film film) {
        Transaction transaction = session.beginTransaction();
        session.merge(film);
        transaction.commit();
    }

    @Override
    public void delete(Film film) {
        Transaction transaction = session.beginTransaction();
        session.remove(film);
        transaction.commit();
    }

}
