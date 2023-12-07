package com.filmbooking.services.impls;

import com.filmbooking.dao.IDAO;
import com.filmbooking.dao.RoomDAOImpl;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Room;
import com.filmbooking.services.IRoomServices;

import java.util.List;

public class RoomServicesImpl implements IRoomServices {
    private final IDAO<Room> roomDAO;

    public RoomServicesImpl() {
        roomDAO = RoomDAOImpl.getInstance();
    }

    public RoomServicesImpl(HibernateSessionProvider sessionProvider) {
        roomDAO = RoomDAOImpl.getInstance();
        setSessionProvider(sessionProvider);
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        roomDAO.setSessionProvider(sessionProvider);
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
    public boolean save(Room room) {
        return roomDAO.save(room);
    }

    @Override
    public boolean update(Room room) {
        return roomDAO.update(room);
    }

    @Override
    public boolean delete(Room room) {
        return roomDAO.delete(room);
    }

}
