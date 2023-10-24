package com.filmbooking.services.impls;

import com.filmbooking.dao.IDAO;
import com.filmbooking.dao.view.RoomViewDAOImpl;
import com.filmbooking.model.view.RoomView;
import com.filmbooking.services.IRoomViewServices;

import java.util.List;

public class RoomViewServicesImpl implements IRoomViewServices {
    private IDAO<RoomView> roomViewDAO;

    public RoomViewServicesImpl() {
        roomViewDAO = new RoomViewDAOImpl();
    }
    @Override
    public List<RoomView> getAll() {
        return roomViewDAO.getAll();
    }

    @Override
    public RoomView getByID(String id) {
        return roomViewDAO.getByID(id);
    }
}
