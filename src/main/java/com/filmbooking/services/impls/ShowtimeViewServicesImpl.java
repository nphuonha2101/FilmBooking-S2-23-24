package com.filmbooking.services.impls;

import com.filmbooking.dao.IDAO;
import com.filmbooking.dao.ShowtimeViewDAOImpl;
import com.filmbooking.model.ShowtimeView;
import com.filmbooking.services.IShowtimeViewServices;

import java.util.List;

public class ShowtimeViewServicesImpl implements IShowtimeViewServices {
    private IDAO<ShowtimeView> showtimeViewIDAO;

    public ShowtimeViewServicesImpl(){
        showtimeViewIDAO = new ShowtimeViewDAOImpl();
    }
    @Override
    public List<ShowtimeView> getAll() {

        return showtimeViewIDAO.getAll();
    }

    @Override
    public ShowtimeView getByID(String id) {
        return showtimeViewIDAO.getByID(id);
    }
}
