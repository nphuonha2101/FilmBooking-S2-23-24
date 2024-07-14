package com.filmbooking.repository;

import com.filmbooking.jdbi.connection.JdbiDBConnection;
import com.filmbooking.model.User;
import com.filmbooking.repository.mapper.UserMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository extends AbstractRepository<User> {

    public UserRepository() {
        super(User.class);
    }

    @Override
    RowMapper<User> getRowMapper() {
        return new UserMapper();
    }

    @Override
    Map<String, Object> mapToRow(User user) {
        Map<String, Object> result = new HashMap<>();
        result.put("username", user.getUsername());
        result.put("user_fullname", user.getUserFullName());
        result.put("user_email", user.getUserEmail());
        result.put("user_password", user.getUserPassword());
        result.put("account_role", user.getAccountRole());
        result.put("account_type", user.getAccountType());
        result.put("account_status", user.getAccountStatus());
        result.put("created_at", user.getCreatedAt());
        result.put("updated_at", user.getUpdatedAt());

        return result;
    }

    public List<String> getAdminEmails() {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "SELECT * FROM user_infos WHERE account_rode = 'admin'";
            return handle.createQuery(sql)
                    .mapTo(String.class)
                    .list();
        }  catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            JdbiDBConnection.closeHandle();
        }

    }

}
