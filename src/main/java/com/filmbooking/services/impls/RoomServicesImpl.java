package com.filmbooking.services.impls;

import com.filmbooking.dao.IDAO;
import com.filmbooking.dao.RoomDAOImpl;
import com.filmbooking.model.Room;
import com.filmbooking.services.IRoomServices;

import java.util.List;

public class RoomServicesImpl implements IRoomServices {
    private IDAO<Room> roomDAO;

    public RoomServicesImpl() {
        roomDAO = new RoomDAOImpl();
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

    @Override
    public int countAvailableSeats(Room room) {
        int count = 0;
        String[][] seatMatrix = room.getSeatMatrix();

        for (int i = 0; i < seatMatrix.length; i++) {
            for (int j = 0; j < seatMatrix[i].length; j++) {
                if (seatMatrix[i][j].equalsIgnoreCase("1"))
                    count++;
            }

        }

        return count;
    }
}
