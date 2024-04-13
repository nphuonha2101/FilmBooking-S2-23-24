/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */

package com.filmbooking.dao.daoDecorators;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.dao.IDAO;
import com.filmbooking.dao.IPredicateQuery;
import com.filmbooking.hibernate.HibernateSessionProvider;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;

import java.util.List;

/**
 * Decorator for adding predicates to the query
 * @param <T> the type of the object
 */
public class PredicatesDAODecorator<T> extends AbstractDAODecorator<T> {
    private final IPredicateQuery predicateQuery;

    public PredicatesDAODecorator(IDAO<T> decoratedDAO, IPredicateQuery predicateQuery) {
        super(decoratedDAO);
        this.predicateQuery = predicateQuery;
    }

    @Override
    public IDAO<T> getAll() {
        DataAccessObjects<T> dataAccessObjects = (DataAccessObjects<T>) this.decoratedDAO.getAll();
        // clone dataAccessObjects to avoid changing the original dataAccessObjects
        DataAccessObjects<T> dataAccessObjectsWithPredicate = dataAccessObjects.clone();

        CriteriaQuery<T> criteriaQuery = dataAccessObjectsWithPredicate.getCriteriaQueryResult();

        // create predicate from IPredicateQuery
        Predicate predicate = this.predicateQuery.createPredicate(
                dataAccessObjectsWithPredicate.getCriteriaBuilder(),
                dataAccessObjectsWithPredicate.getRootEntry()
        );

        // add predicate to criteriaQuery
        if (predicate != null)
            criteriaQuery.where(predicate);
        // create a new typedQuery with the new the predicate
        TypedQuery<T> typedQuery = dataAccessObjectsWithPredicate.getSession().createQuery(criteriaQuery);
        dataAccessObjectsWithPredicate.setTypedQuery(typedQuery);

        return dataAccessObjectsWithPredicate;
    }
}
