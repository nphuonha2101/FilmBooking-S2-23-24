package com.filmbooking.services.impls;

import com.filmbooking.dao.GenericDAOImpl;
import com.filmbooking.dao.IDAO;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Room;
import com.filmbooking.services.IRoomServices;

import java.util.List;

public class RoomServicesImpl implements IRoomServices {
    private final IDAO<Room> roomDAO;

    public RoomServicesImpl() {
        roomDAO = new GenericDAOImpl<>(Room.class);
    }

    public RoomServicesImpl(HibernateSessionProvider sessionProvider) {
        roomDAO = new GenericDAOImpl<>(Room.class);
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
        return roomDAO.getByID(id, true);
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
