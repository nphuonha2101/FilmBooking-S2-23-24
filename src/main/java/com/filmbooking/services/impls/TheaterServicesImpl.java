package com.filmbooking.services.impls;

import com.filmbooking.dao.IDAO;
import com.filmbooking.dao.TheaterDAOImpl;
import com.filmbooking.model.Theater;
import com.filmbooking.services.ITheaterServices;

import java.util.List;

public class TheaterServicesImpl implements ITheaterServices {

    private IDAO<Theater> theaterDAO;

    public TheaterServicesImpl() {
        theaterDAO = new TheaterDAOImpl();
    }

    @Override
    public List<Theater> getAll() {
        return theaterDAO.getAll();
    }

    @Override
    public Theater getByID(String id) {
        return theaterDAO.getByID(id);
    }

    @Override
    public void save(Theater theater) {
        theaterDAO.save(theater);
    }

    @Override
    public void update(Theater theater) {
        theaterDAO.update(theater);
    }

    @Override
    public void delete(Theater theater) {
        theaterDAO.delete(theater);
    }
}
