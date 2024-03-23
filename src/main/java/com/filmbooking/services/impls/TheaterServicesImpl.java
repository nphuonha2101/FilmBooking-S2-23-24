package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.model.Theater;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.services.AbstractServices;
import com.filmbooking.services.IServices;

import java.util.List;

public class TheaterServicesImpl extends AbstractServices<Theater> {

    public TheaterServicesImpl(HibernateSessionProvider sessionProvider) {
        super.decoratedDAO = new DataAccessObjects<>(Theater.class);
        super.setSessionProvider(sessionProvider);
    }


    @Override
    public Theater getBySlug(String slug) {
        throw new UnsupportedOperationException("This method is not supported for Theater");
    }

    @Override
    public Theater getByID(String id) {
        return this.decoratedDAO.getByID(id, true);
    }
}
