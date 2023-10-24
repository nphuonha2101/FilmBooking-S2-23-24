package com.filmbooking.services.impls;

import com.filmbooking.dao.IDAO;
import com.filmbooking.dao.RoomDAOImpl;
import com.filmbooking.model.Room;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.IRoomServices;
import com.filmbooking.services.IShowtimeServices;

import java.util.HashMap;
import java.util.List;

public class RoomServicesImpl implements IRoomServices {
    private IDAO<Room> roomDAO;
    private IShowtimeServices showtimeServices;

    public RoomServicesImpl() {
        roomDAO = new RoomDAOImpl();
        showtimeServices = new ShowtimeServicesImpl();
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
        List<Showtime> showtimeList = showtimeServices.getAll();
        for (Showtime showtime: showtimeList) {
            if (showtime.getRoomID().equalsIgnoreCase(room.getRoomID())) showtimeServices.delete(showtime);
        }
        roomDAO.delete(room);
    }

    @Override
    public HashMap<String, Integer> countAvailableSeats() {
        HashMap<String, Integer> result = new HashMap<>();

        for (Room room : getAll()
        ) {
            int availableSeats = room.countAvailableSeats();
            result.put(room.getRoomID(), availableSeats);
        }
        return result;
    }
}
