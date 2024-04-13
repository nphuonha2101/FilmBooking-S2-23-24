/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */

package com.filmbooking.dao;

import com.filmbooking.hibernate.HibernateSessionProvider;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Getter
@Setter
public class DataAccessObjects<T> implements IDAO<T>, Cloneable {
    private Session session;
    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<T> criteriaQueryResult;
    private Class<T> classOfData;
    private TypedQuery<T> typedQuery;
    private Root<T> rootEntry;

    public DataAccessObjects(Class<T> classOfData) {
        this.classOfData = classOfData;
    }

    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.session = sessionProvider.getSession();
    }

    @Override
    public long getTotalRecordRows() {
        long result;
        criteriaBuilder = this.session.getCriteriaBuilder();

        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        rootEntry = criteriaQuery.from(classOfData);
        criteriaQuery.select(criteriaBuilder.count(rootEntry));

        TypedQuery<Long> typedQuery = this.session.createQuery(criteriaQuery);
        result = typedQuery.getSingleResult();

        return result;
    }

    @Override
    public IDAO<T> getAll() {
        try {
            criteriaBuilder = this.session.getCriteriaBuilder();
            criteriaQueryResult = criteriaBuilder.createQuery(this.classOfData);
            rootEntry = criteriaQueryResult.from(this.classOfData);

            criteriaQueryResult.select(rootEntry);

            // descending order by id
            criteriaQueryResult.orderBy(criteriaBuilder.desc(rootEntry.get("id")));

            typedQuery = this.session.createQuery(criteriaQueryResult);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return this;
    }


    @Override
    public T getByID(String id, boolean isLongID) {
        try {
            criteriaBuilder = this.session.getCriteriaBuilder();
            criteriaQueryResult = criteriaBuilder.createQuery(classOfData);
            rootEntry = criteriaQueryResult.from(classOfData);

            if (isLongID) {
                long lID = Long.parseLong(id);
                criteriaQueryResult.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("id"), lID));
            } else
                criteriaQueryResult.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("id"), id));

            typedQuery = this.session.createQuery(criteriaQueryResult);

            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace(System.out);
            return null;
        }
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

    @Override
    public List<T> getMultipleResults() {
        List<T> result;
        result = typedQuery.getResultList();
        return result;
    }

    @Override
    public T getSingleResult() {
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    @Override
    public DataAccessObjects<T> clone() {
        DataAccessObjects<T> clone = new DataAccessObjects<>(this.classOfData);

        clone.setSession(this.session);
        clone.setCriteriaBuilder(this.criteriaBuilder);
        clone.setCriteriaQueryResult(this.criteriaQueryResult);
        clone.setTypedQuery(this.typedQuery);
        clone.setRootEntry(this.rootEntry);

        clone.setSessionProvider(new HibernateSessionProvider());
        return clone;
    }

}
