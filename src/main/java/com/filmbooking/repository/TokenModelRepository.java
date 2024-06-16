package com.filmbooking.repository;

import com.filmbooking.model.TokenModel;
import com.filmbooking.repository.mapper.TokenModelMapper;
import org.jdbi.v3.core.mapper.RowMapper;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class TokenModelRepository extends AbstractRepository<TokenModel> {

    public TokenModelRepository() {
        super(TokenModel.class);
    }

    @Override
    RowMapper<TokenModel> getRowMapper() {
        return new TokenModelMapper();
    }

    @Override
    Map<String, Object> mapToRow(TokenModel tokenModel) {
        Map<String, Object> result = new HashMap<>();
        result.put("token", tokenModel.getToken());
        result.put("username", tokenModel.getUsername());
        result.put("expiry_date", tokenModel.getExpiryDate());
        result.put("token_type", tokenModel.getTokenType());
        result.put("token_state", tokenModel.getTokenState());

        return result;
    }
}
