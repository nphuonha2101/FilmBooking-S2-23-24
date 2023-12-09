package com.filmbooking.dao;

import com.filmbooking.hibernate.HibernateSessionProvider;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GenericDAOImpl<T> implements IDAO<T> {
    private Session session;
    private final Class<T> typeOfData;

    public GenericDAOImpl(Class<T> typeOfData) {
        this.typeOfData = typeOfData;
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.session = sessionProvider.getSession();
    }

    @Override
    public long getTotalRecords() {
        long result;
        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();

        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> rootEntry = criteriaQuery.from(typeOfData);
        criteriaQuery.select(criteriaBuilder.count(rootEntry));

        TypedQuery<Long> typedQuery = this.session.createQuery(criteriaQuery);

        result = typedQuery.getSingleResult();

        return result;
    }

    @Override
    public List<T> getByOffset(int offset, int limit) {
        List<T> result;

        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.typeOfData);
        Root<T> rootEntry = criteriaQuery.from(this.typeOfData);
        criteriaQuery.select(rootEntry).orderBy(criteriaBuilder.desc(rootEntry.get("id")));

        TypedQuery<T> typedQuery = this.session.createQuery(criteriaQuery);
        // set first index of data that want to get
        typedQuery.setFirstResult(offset);
        // number of records from first index of data that want to get
        typedQuery.setMaxResults(limit);

        result = typedQuery.getResultList();

        return result;
    }

    @Override
    public List<T> getAll() {
        List<T> result;

        CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.typeOfData);
        Root<T> rootEntry = criteriaQuery.from(this.typeOfData);
        CriteriaQuery<T> all = criteriaQuery.select(rootEntry);

        TypedQuery<T> allQuery = this.session.createQuery(all);

        result = allQuery.getResultList();

        return result;
    }

    @Override
    public T getByID(String id, boolean isLongID) {
        T result = null;

        try {
            CriteriaBuilder criteriaBuilder = this.session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(typeOfData);
            Root<T> rootEntry = criteriaQuery.from(typeOfData);

            if (isLongID) {
                long lID = Long.parseLong(id);
                criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("id"), lID));
            } else
                criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("id"), id));

            TypedQuery<T> typedQuery = this.session.createQuery(criteriaQuery);

            result = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace(System.out);
        }

        return result;
    }

    @Override
    public boolean save(T t) {
        Transaction transaction = this.session.beginTransaction();
        try {
            session.persist(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            transaction.rollback();
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
            transaction.rollback();
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
            transaction.rollback();
            return false;
        }
    }
}
