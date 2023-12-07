package com.filmbooking.services;


import com.filmbooking.model.Room;
import com.filmbooking.hibernate.HibernateSessionProvider;

import java.util.List;

public interface IRoomServices {
    void setSessionProvider(HibernateSessionProvider sessionProvider);
    List<Room> getAll();
    Room getByRoomID(String id);
    boolean save(Room room);
    boolean update(Room room);
    boolean delete(Room room);


}
