package com.filmbooking.services;


import com.filmbooking.model.Room;
import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.List;

public interface IRoomServices {
    void setSessionProvider(HibernateSessionProvider sessionProvider);
    List<Room> getAll();
    Room getByRoomID(String id);
    void save(Room room);
    void update(Room room);
    void delete(Room room);


}
