package com.filmbooking.services.impls;

import java.util.Objects;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.LogModel;
import com.filmbooking.model.User;
import com.filmbooking.repository.AbstractRepository;
import com.filmbooking.repository.LogRepository;
import com.filmbooking.services.AbstractService;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public class LogModelServicesImpl extends AbstractService<LogModel> {
    public LogModelServicesImpl() {
        super(new LogRepository(LogModel.class));
    }

}
