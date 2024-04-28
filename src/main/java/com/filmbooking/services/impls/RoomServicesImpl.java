package com.filmbooking.services.impls;

import java.util.Objects;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Room;
import com.filmbooking.model.User;
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
        if (!Objects.equals(id, "null"))
            return this.decoratedDAO.getByID(id, true);
        else
            throw new RuntimeException("ID must not be null");
    }

    @Override
    public User newUser(String username, String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newUser'");
    }
}
