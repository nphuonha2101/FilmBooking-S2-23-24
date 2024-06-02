package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.*;
import com.filmbooking.repository.AbstractRepository;
import com.filmbooking.repository.FailedLoginRepository;
import com.filmbooking.repository.ShowtimeRepository;
import com.filmbooking.services.AbstractService;

import java.time.LocalDateTime;
import java.util.Objects;

public class FailedLoginServicesImpl extends AbstractService<FailedLogin> {


    public FailedLoginServicesImpl() {
        super(new FailedLoginRepository(FailedLogin.class));
    }
    @Override
    public boolean update(FailedLogin failedLogin) {
        int count = failedLogin.getLoginCount();
        if (count < 5) {
            failedLogin.setLoginCount(count + 1);
        }
        if (count >= 4) {
            failedLogin.setLockTime(LocalDateTime.now().plusMinutes(5));
        }
        return this.repository.update(failedLogin);
    }

}
