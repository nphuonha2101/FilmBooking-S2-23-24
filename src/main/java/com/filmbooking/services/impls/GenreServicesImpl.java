package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Genre;
import com.filmbooking.services.AbstractCRUDServices;

import java.util.Objects;

public class GenreServicesImpl extends AbstractCRUDServices<Genre> {

    public GenreServicesImpl(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO = new DataAccessObjects<>(Genre.class);
        this.setSessionProvider(sessionProvider);
    }

    public GenreServicesImpl() {
        this.decoratedDAO = new DataAccessObjects<>(Genre.class);
    }

    @Override
    public String getTableName() {
        return Genre.TABLE_NAME;
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO.setSessionProvider(sessionProvider);
    }

    @Override
    public Genre getBySlug(String slug) {
        throw new UnsupportedOperationException("This method is not supported for Genre");
    }


    @Override
    public Genre getByID(String id) {
        if (!Objects.equals(id, "null"))
            return this.decoratedDAO.getByID(id, false);
        else
            throw new RuntimeException("ID must not be null");
    }


}
