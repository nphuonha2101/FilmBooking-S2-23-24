package com.filmbooking.dao;

import com.filmbooking.hibernate.HibernateSessionProvider;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class AbstractDAOImpl<T> implements IDAO<T> {
    protected Session session;
    private final Class<T> typeOfData;

    public AbstractDAOImpl(Class<T> typeOfData) {
        this.typeOfData = typeOfData;
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.session = sessionProvider.getSession();
    }

    @Override
    public List<T> getAll() {
        List<T> result;

        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
        // declare an object that want to query
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.typeOfData);
        Root<T> rootEntry = criteriaQuery.from(this.typeOfData);
        CriteriaQuery<T> all = criteriaQuery.select(rootEntry);

        TypedQuery<T> allQuery = this.session.createQuery(all);

        result = allQuery.getResultList();

        return result;
    }

    @Override
    public abstract T getByID(String id);

    @Override
    public boolean save(T t) {
        Transaction transaction = this.session.beginTransaction();
        try {
            session.persist(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    @Override
    public boolean update(T t) {
        Transaction transaction = this.session.beginTransaction();
        try {
            session.merge(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    @Override
    public boolean delete(T t) {
        Transaction transaction = this.session.beginTransaction();
        try {
            session.remove(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }
}
