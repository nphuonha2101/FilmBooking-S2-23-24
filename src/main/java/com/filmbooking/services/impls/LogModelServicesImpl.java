package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.LogModel;
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
        return null;
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO.setSessionProvider(sessionProvider);
    }

    @Override
    public LogModel getByID(String id) {
        throw new UnsupportedOperationException("This method is not supported for LogModel");
    }

    @Override
    public boolean update(LogModel logModel) {
        throw new UnsupportedOperationException("This method is not supported for LogModel");
    }
}
