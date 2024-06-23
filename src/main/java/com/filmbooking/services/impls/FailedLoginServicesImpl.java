package com.filmbooking.services.impls;


import com.filmbooking.model.*;
import com.filmbooking.repository.FailedLoginRepository;
import com.filmbooking.services.AbstractService;

import java.time.LocalDateTime;

public class FailedLoginServicesImpl extends AbstractService<FailedLogin> {


    public FailedLoginServicesImpl() {
        super(new FailedLoginRepository());
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
