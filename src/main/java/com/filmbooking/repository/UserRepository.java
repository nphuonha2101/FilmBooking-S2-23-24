package com.filmbooking.repository;

import com.filmbooking.model.User;
import com.filmbooking.repository.mapper.UserMapper;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.Map;

public class UserRepository extends AbstractRepository<User> {

    public UserRepository(Class<User> modelClass) {
        super(modelClass);
    }

    @Override
    RowMapper<User> getRowMapper() {
        return new UserMapper();
    }

    @Override
    Map<String, Object> mapToRow(User user) {
        return Map.of(
                "username", user.getUsername(),
                "user_fullname", user.getUserFullName(),
                "user_email", user.getUserEmail(),
                "user_password", user.getUserPassword(),
                "account_role", user.getAccountRole(),
                "account_type", user.getAccountType(),
                "account_status", user.getAccountStatus()
        );
    }

}
