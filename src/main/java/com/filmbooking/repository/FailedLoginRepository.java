package com.filmbooking.repository;

import com.filmbooking.model.FailedLogin;
import com.filmbooking.repository.mapper.FailedLoginMapper;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.Map;

public class FailedLoginRepository extends AbstractRepository<FailedLogin> {

    public FailedLoginRepository() {
        super(FailedLogin.class);
    }

    @Override
    RowMapper<FailedLogin> getRowMapper() {
        return new FailedLoginMapper();
    }

    @Override
    Map<String, Object> mapToRow(FailedLogin failedLogin) {
        return Map.of(
                "req_ip", failedLogin.getReqIp(),
                "login_count", failedLogin.getLoginCount(),
                "lock_time", failedLogin.getLockTime()
        );
    }
}
