package com.filmbooking.repository;

import com.filmbooking.model.FailedLogin;
import com.filmbooking.repository.mapper.FailedLoginMapper;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.HashMap;
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
        Map<String, Object> result = new HashMap<>();
        result.put("req_ip", failedLogin.getReqIp());
        result.put("login_count", failedLogin.getLoginCount());
        result.put("lock_time", failedLogin.getLockTime());
        result.put("created_at", failedLogin.getCreatedAt());
        result.put("updated_at", failedLogin.getUpdatedAt());

        return result;
    }
}
