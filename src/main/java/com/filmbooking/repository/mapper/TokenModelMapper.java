package com.filmbooking.repository.mapper;

import com.filmbooking.enumsAndConstants.enums.TokenTypeEnum;
import com.filmbooking.model.TokenModel;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenModelMapper implements RowMapper<TokenModel> {
    @Override
    public TokenModel map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new TokenModel(
                rs.getString("token"),
                rs.getString("username"),
                rs.getTimestamp("expiry_date").toLocalDateTime(),
                rs.getString("token_type"),
                rs.getString("token_state")
        );
    }
}
