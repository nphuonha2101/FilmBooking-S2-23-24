package com.filmbooking.services.impls;

import java.util.Objects;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Theater;
import com.filmbooking.model.User;
import com.filmbooking.services.AbstractCRUDServices;

public class TheaterServicesImpl extends AbstractCRUDServices<Theater> {

    public TheaterServicesImpl(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO = new DataAccessObjects<>(Theater.class);
        this.setSessionProvider(sessionProvider);
    }

    public TheaterServicesImpl() {
        this.decoratedDAO = new DataAccessObjects<>(Theater.class);
    }

    @Override
    public String getTableName() {
        return Theater.TABLE_NAME;
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO.setSessionProvider(sessionProvider);
    }

    @Override
    public Theater getBySlug(String slug) {
        throw new UnsupportedOperationException("This method is not supported for Theater");
    }

    @Override
    public Theater getByID(String id) {
        if (!Objects.equals(id, "null"))
            return this.decoratedDAO.getByID(id, true);
        else
            throw new RuntimeException("ID must not be null");
    }


}
