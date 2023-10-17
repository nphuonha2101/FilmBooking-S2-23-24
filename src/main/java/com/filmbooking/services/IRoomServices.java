package com.filmbooking.services;


import com.filmbooking.model.Room;

import java.util.HashMap;
import java.util.List;

public interface IRoomServices {
    List<Room> getAll();
    Room getByRoomID(String id);
    void save(Room room);
    void update(Room room);
    void delete(Room room);
    HashMap<String, Integer> countAvailableSeats();
}
