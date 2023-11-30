package com.filmbooking.services.impls;

import com.filmbooking.dao.IDAO;
import com.filmbooking.dao.RoomDAOImpl;
import com.filmbooking.model.Room;
import com.filmbooking.services.IFilmBookingServices;
import com.filmbooking.services.IRoomServices;
import com.filmbooking.services.IShowtimeServices;

import java.util.List;

public class RoomServicesImpl implements IRoomServices {
    private final IDAO<Room> roomDAO;

    public RoomServicesImpl() {
        roomDAO = RoomDAOImpl.getInstance();
    }

    @Override
    public void openSession() {
        roomDAO.openSession();
    }

    @Override
    public void closeSession() {
        roomDAO.closeSession();
    }

    @Override
    public List<Room> getAll() {
        return roomDAO.getAll();
    }

    @Override
    public Room getByRoomID(String id) {
        return roomDAO.getByID(id);
    }

    @Override
    public void save(Room room) {
        roomDAO.save(room);
    }

    @Override
    public void update(Room room) {
        roomDAO.update(room);
    }

    @Override
    public void delete(Room room) {
        roomDAO.delete(room);
    }

}
