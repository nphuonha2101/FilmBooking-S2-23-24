package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.dao.daoDecorators.OffsetDAODecorator;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Room;
import com.filmbooking.services.IRoomServices;

import java.util.List;

public class RoomServicesImpl implements IRoomServices {
    private final DataAccessObjects<Room> roomDataAccessObjects;

    public RoomServicesImpl() {
        roomDataAccessObjects = new DataAccessObjects<>(Room.class);
    }

    public RoomServicesImpl(HibernateSessionProvider sessionProvider) {
        roomDataAccessObjects = new DataAccessObjects<>(Room.class);
        setSessionProvider(sessionProvider);
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        roomDataAccessObjects.setSessionProvider(sessionProvider);
    }

    @Override
    public long getTotalRecords() {
        return roomDataAccessObjects.getTotalRecordRows();
    }

    @Override
    public Room getBySlug(String slug) {
        for (Room room : roomDataAccessObjects.getAll().getMultipleResults()) {
            if (room.getSlug().equalsIgnoreCase(slug)) return room;
        }
        return null;
    }

    @Override
    public List<Room> getByOffset(int offset, int limit) {
        return new OffsetDAODecorator<Room>(roomDataAccessObjects, offset, limit).getAll().getMultipleResults();
    }

    @Override
    public List<Room> getAll() {
        return roomDataAccessObjects.getAll().getMultipleResults();
    }

    @Override
    public Room getByRoomID(String id) {
        return roomDataAccessObjects.getByID(id, true).getSingleResult();
    }

    @Override
    public boolean save(Room room) {
        return roomDataAccessObjects.save(room);
    }

    @Override
    public boolean update(Room room) {
        return roomDataAccessObjects.update(room);
    }

    @Override
    public boolean delete(Room room) {
        return roomDataAccessObjects.delete(room);
    }

}
