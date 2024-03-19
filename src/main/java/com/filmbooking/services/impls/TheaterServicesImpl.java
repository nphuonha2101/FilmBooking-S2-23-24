package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.model.Theater;
import com.filmbooking.services.ITheaterServices;
import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.List;

public class TheaterServicesImpl implements ITheaterServices {
    private final DataAccessObjects<Theater> theaterDataAccessObjects;
    public TheaterServicesImpl() {
        theaterDataAccessObjects = new DataAccessObjects<>(Theater.class);
    }

    public TheaterServicesImpl(HibernateSessionProvider sessionProvider) {
        theaterDataAccessObjects = new DataAccessObjects<>(Theater.class);
        setSessionProvider(sessionProvider);
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        theaterDataAccessObjects.setSessionProvider(sessionProvider);
    }

    @Override
    public List<Theater> getAll() {
        return theaterDataAccessObjects.getAll().getMultipleResults();
    }

    @Override
    public Theater getByID(String id) {
        return theaterDataAccessObjects.getByID(id, true).getSingleResult();
    }

    @Override
    public boolean save(Theater theater) {
        return theaterDataAccessObjects.save(theater);
    }

    @Override
    public boolean update(Theater theater) {
        return theaterDataAccessObjects.update(theater);
    }

    @Override
    public boolean delete(Theater theater) {
        return theaterDataAccessObjects.delete(theater);
    }


}
