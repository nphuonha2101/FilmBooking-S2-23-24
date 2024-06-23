package com.filmbooking.services.impls;

import com.filmbooking.model.Room;
import com.filmbooking.repository.RoomRepository;
import com.filmbooking.services.AbstractService;

public class RoomServicesImpl extends AbstractService<Room> {

    public RoomServicesImpl() {
        super(new RoomRepository());
    }
}
