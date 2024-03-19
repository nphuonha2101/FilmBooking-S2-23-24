/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */

package com.filmbooking.dao.daoDecorators;

import com.filmbooking.dao.IDAO;

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

}
