package com.filmbooking.services.impls;

import com.filmbooking.dao.IDAO;
import com.filmbooking.dao.view.ShowtimeViewDAOImpl;
import com.filmbooking.model.Showtime;
import com.filmbooking.model.view.ShowtimeView;
import com.filmbooking.services.IGetObjectAndObjectIDService;
import com.filmbooking.services.IShowtimeViewServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowtimeViewServicesImpl implements IShowtimeViewServices, IGetObjectAndObjectIDService<ShowtimeView> {
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

    @Override
    public List<ShowtimeView> getViewFromShowtimes(List<Showtime> showtimeList) {
        List<ShowtimeView> result = new ArrayList<>();
        for (Showtime showtime: showtimeList
             ) {
            ShowtimeView showtimeView = getByID(showtime.getShowtimeID());
            result.add(showtimeView);
        }
        return result;
    }

    @Override
    public HashMap<String, ShowtimeView> getShowtimeViewAndShowtimeID() {
        return this.getObjectAndObjectID(this.getAll());
    }

    @Override
    public String getObjectID(ShowtimeView obj) {
        return obj.getShowtimeID();
    }
}
