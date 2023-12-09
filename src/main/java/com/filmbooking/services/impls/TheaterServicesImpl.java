package com.filmbooking.services.impls;

import com.filmbooking.dao.GenericDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.model.Theater;
import com.filmbooking.services.ITheaterServices;
import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.List;

public class TheaterServicesImpl implements ITheaterServices {
    private final IDAO<Theater> theaterDAO;
    public TheaterServicesImpl() {
        theaterDAO = new GenericDAOImpl<>(Theater.class);
    }

    public TheaterServicesImpl(HibernateSessionProvider sessionProvider) {
        theaterDAO = new GenericDAOImpl<>(Theater.class);
        setSessionProvider(sessionProvider);
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        theaterDAO.setSessionProvider(sessionProvider);
    }

    @Override
    public List<Theater> getAll() {
        return theaterDAO.getAll();
    }

    @Override
    public Theater getByID(String id) {
        return theaterDAO.getByID(id, true);
    }

    @Override
    public boolean save(Theater theater) {
        return theaterDAO.save(theater);
    }

    @Override
    public boolean update(Theater theater) {
        return theaterDAO.update(theater);
    }

    @Override
    public boolean delete(Theater theater) {
        return theaterDAO.delete(theater);
    }


}
