package com.filmbooking.services;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.dao.IDAO;
import com.filmbooking.dao.IPredicateQuery;
import com.filmbooking.dao.PredicateFactory;
import com.filmbooking.dao.daoDecorators.OffsetDAODecorator;
import com.filmbooking.dao.daoDecorators.PredicatesDAODecorator;
import com.filmbooking.hibernate.HibernateSessionProvider;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Map;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public abstract class AbstractServices<T> implements IServices<T> {
    protected IDAO<T> decoratedDAO;


    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO.setSessionProvider(sessionProvider);
    }

    @Override
    public T getBySlug(String slug) {
        Map<String, Object> conditions = Map.of("slug_=", slug);
        return getByPredicates(conditions).getMultipleResults().get(0);
    }

    @Override
    public abstract T getByID(String id);

    @Override
    public IServices<T> getAll() {
        this.decoratedDAO = decoratedDAO.getAll();
        return this;
    }

    @Override
    public IServices<T> getByOffset(int offset, int limit) {
        this.decoratedDAO = new OffsetDAODecorator<>(decoratedDAO, offset, limit).getAll();
        return this;
    }


    @Override
    public IServices<T> getByPredicates(Map<String, Object> conditions) {
        this.decoratedDAO = new PredicatesDAODecorator<>(decoratedDAO, new IPredicateQuery() {
            @Override
            public Predicate createPredicate(CriteriaBuilder criteriaBuilder, Root root) {
                // create predicate from a map of conditions
                PredicateFactory predicateFactory = new PredicateFactory(conditions, criteriaBuilder, root);
                return predicateFactory.createPredicate();
            }
        }).getAll();

        return this;
    }

    @Override
    public long getTotalRecordRows() {
        return decoratedDAO.getTotalRecordRows();
    }

    @Override
    public boolean save(T t) {
        return decoratedDAO.save(t);
    }

    @Override
    public boolean update(T t) {
        return decoratedDAO.update(t);
    }

    @Override
    public boolean delete(T t) {
        return decoratedDAO.delete(t);
    }

    @Override
    public List<T> getMultipleResults() {
        return decoratedDAO.getMultipleResults();
    }
}
