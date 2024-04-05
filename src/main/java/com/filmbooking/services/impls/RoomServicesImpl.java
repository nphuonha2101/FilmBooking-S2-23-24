package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Room;
import com.filmbooking.services.AbstractCRUDServices;

public class RoomServicesImpl extends AbstractCRUDServices<Room> {


    public RoomServicesImpl(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO = new DataAccessObjects<>(Room.class);
        this.setSessionProvider(sessionProvider);
    }

    public RoomServicesImpl() {
        this.decoratedDAO = new DataAccessObjects<>(Room.class);
    }

    @Override
    public String getTableName() {
        return Room.TABLE_NAME;
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO.setSessionProvider(sessionProvider);
    }

    @Override
    public Room getByID(String id) {
        return this.decoratedDAO.getByID(id, true);
    }
}
