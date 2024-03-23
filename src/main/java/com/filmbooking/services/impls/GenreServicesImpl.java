package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Genre;
import com.filmbooking.services.AbstractServices;
import com.filmbooking.services.IServices;

import java.util.List;

public class GenreServicesImpl extends AbstractServices<Genre> {

    public GenreServicesImpl(HibernateSessionProvider sessionProvider) {
        super.decoratedDAO = new DataAccessObjects<>(Genre.class);
        super.setSessionProvider(sessionProvider);
    }

    @Override
    public Genre getBySlug(String slug) {
        throw new UnsupportedOperationException("This method is not supported for Genre");
    }


    @Override
    public Genre getByID(String id) {
        return this.decoratedDAO.getByID(id, false);
    }


}
