package com.filmbooking.services.impls;

import com.filmbooking.dao.IDAO;
import com.filmbooking.dao.ShowtimeDAOImpl;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IShowtimeServices;

import java.util.List;

public class ShowtimeServicesImpl implements IShowtimeServices {
    private IDAO<Showtime> showtimeDAO;

    public ShowtimeServicesImpl() {
        this.showtimeDAO = new ShowtimeDAOImpl();
    }

    @Override
    public List<Showtime> getAll() {
        return showtimeDAO.getAll();
    }

    @Override
    public Showtime getByID(String id) {
        return showtimeDAO.getByID(id);
    }

    @Override
    public void save(Showtime showtime) {
        showtimeDAO.save(showtime);
    }

    @Override
    public void update(Showtime showtime) {
        showtimeDAO.update(showtime);
    }

    @Override
    public void delete(Showtime showtime) {
        showtimeDAO.delete(showtime);
    }
}
