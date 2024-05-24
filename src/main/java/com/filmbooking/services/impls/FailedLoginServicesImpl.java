package com.filmbooking.services.impls;

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.FailedLogin;
import com.filmbooking.model.TokenModel;
import com.filmbooking.model.User;
import com.filmbooking.services.AbstractCRUDServices;

import java.time.LocalDateTime;
import java.util.Objects;

public class FailedLoginServicesImpl extends AbstractCRUDServices<FailedLogin> {

    public FailedLoginServicesImpl(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO = new DataAccessObjects<>(FailedLogin.class);
        this.setSessionProvider(sessionProvider);
    }
    public FailedLoginServicesImpl() {
        this.decoratedDAO = new DataAccessObjects<>(FailedLogin.class);
    }


    @Override
    public String getTableName() {
        return FailedLogin.TABLE_NAME;
    }

    @Override
    public void setSessionProvider(HibernateSessionProvider sessionProvider) {
        this.decoratedDAO.setSessionProvider(sessionProvider);
    }

    @Override
    public FailedLogin getByID(String id) {
        if (!Objects.equals(id, "null"))
            return this.decoratedDAO.getByID(id, false);
        else
            throw new RuntimeException("ID must not be null");
    }
    @Override
    public boolean save(FailedLogin failedLogin) {
        return this.decoratedDAO.save(failedLogin);
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
        return this.decoratedDAO.update(failedLogin);
    }

}
