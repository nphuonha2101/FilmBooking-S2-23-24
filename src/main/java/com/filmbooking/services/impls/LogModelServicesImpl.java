package com.filmbooking.services.impls;

import com.filmbooking.model.LogModel;
import com.filmbooking.repository.LogRepository;
import com.filmbooking.services.AbstractService;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public class LogModelServicesImpl extends AbstractService<LogModel> {
    public LogModelServicesImpl() {
        super(new LogRepository());
    }

}
