/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */

package com.filmbooking.dao.daoDecorators;

import com.filmbooking.dao.IDAO;
import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.List;

/**
 * <ul>
 * <li>This class is an abstract class for DAO decorators</li>
 * <li>This class is used to decorate the DAO such as adding offset and limit to the query, adding predicates to the query,...
 * </li>
 * </
 * @param <T> the type of the object
 */
public abstract class AbstractDAODecorator<T> implements IDAO<T> {
    protected IDAO<T> decoratedDAO;

    public AbstractDAODecorator(IDAO<T> decoratedDAO) {
        this.decoratedDAO = decoratedDAO;
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO.setSessionProvider(sessionProvider);
    }

    @Override
    public long getTotalRecordRows() {
        return this.decoratedDAO.getTotalRecordRows();
    }

    @Override
    public T getByID(String id, boolean isLongID) {
        return this.decoratedDAO.getByID(id, isLongID);
    }

    @Override
    public boolean save(T t) {
        return this.decoratedDAO.save(t);
    }

    @Override
    public boolean update(T t) {
        return this.decoratedDAO.update(t);
    }

    @Override
    public boolean delete(T t) {
        return this.decoratedDAO.delete(t);
    }

    @Override
    public List<T> getMultipleResults() {
        return this.decoratedDAO.getMultipleResults();
    }

    @Override
    public T getSingleResult() {
        return this.decoratedDAO.getSingleResult();
    }

}
