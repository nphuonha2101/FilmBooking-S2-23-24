package com.filmbooking.services.impls;

import java.util.Objects;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.LogModel;
import com.filmbooking.model.User;
import com.filmbooking.services.AbstractCRUDServices;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public class LogModelServicesImpl extends AbstractCRUDServices<LogModel> {

    public LogModelServicesImpl(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO = new DataAccessObjects<>(LogModel.class);
        this.setSessionProvider(sessionProvider);
    }

    public LogModelServicesImpl() {
        this.decoratedDAO = new DataAccessObjects<>(LogModel.class);
    }

    @Override
    public String getTableName() {
        return LogModel.TABLE_NAME;
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO.setSessionProvider(sessionProvider);
    }

    @Override
    public LogModel getByID(String id) {
        if (!Objects.equals(id, "null"))
            return this.decoratedDAO.getByID(id, true);
        else
            throw new RuntimeException("ID must not be null");
    }

    @Override
    public boolean update(LogModel logModel) {
        throw new UnsupportedOperationException("This method is not supported for LogModel");
    }

    @Override
    public User newUser(String username, String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newUser'");
    }
}
