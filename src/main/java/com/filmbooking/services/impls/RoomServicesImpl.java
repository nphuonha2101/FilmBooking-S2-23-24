package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.dao.daoDecorators.OffsetDAODecorator;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Room;
import com.filmbooking.services.AbstractServices;
import com.filmbooking.services.IServices;

import java.util.List;
import java.util.Map;

public class RoomServicesImpl extends AbstractServices<Room> {


    public RoomServicesImpl(HibernateSessionProvider sessionProvider) {
        super.decoratedDAO = new DataAccessObjects<>(Room.class);
        super.setSessionProvider(sessionProvider);
    }

    @Override
    public Room getByID(String id) {
        return this.decoratedDAO.getByID(id, true);
    }
}
