/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */

package com.filmbooking.dao.daoDecorators;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.dao.IDAO;
import com.filmbooking.hibernate.HibernateSessionProvider;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * Decorator for adding offset and limit to the query (used for pagination)
 * @param <T> the type of the object
 */
public class OffsetDAODecorator<T> extends AbstractDAODecorator<T> {
    private final int offset;
    private final int limit;

    public OffsetDAODecorator(IDAO<T> decoratedDataAccessObjects, int offset, int limit) {
        super(decoratedDataAccessObjects);
        this.offset = offset;
        this.limit = limit;
    }

    @Override
    public IDAO<T> getAll() {
        DataAccessObjects<T> dataAccessObjects = (DataAccessObjects<T>) this.decoratedDAO.getAll();
        TypedQuery<T> typedQuery = dataAccessObjects.getTypedQuery();
        // set first index of data that want to get
        typedQuery.setFirstResult(offset);
        // number of records from first index of data that want to get
        typedQuery.setMaxResults(limit);

        return dataAccessObjects;
    }
}
