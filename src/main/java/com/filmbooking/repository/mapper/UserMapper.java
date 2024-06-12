package com.filmbooking.repository.mapper;

import com.filmbooking.enumsAndConstants.enums.AccountRoleEnum;
import com.filmbooking.model.FilmBooking;
import com.filmbooking.model.User;
import com.filmbooking.repository.FilmBookingRepository;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements RowMapper<User> {
    @Override
    public User map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new User(
                rs.getString("username"),
                rs.getString("user_fullname"),
                rs.getString("user_email"),
                rs.getString("user_password"),
                rs.getString("account_role").equals("ADMIN") ? AccountRoleEnum.ADMIN : AccountRoleEnum.CUSTOMER,
                rs.getString("account_type"),
                rs.getInt("account_status")
        );
    }
}
