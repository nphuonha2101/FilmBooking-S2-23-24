package com.filmbooking.repository;

import com.filmbooking.model.Theater;
import com.filmbooking.repository.mapper.TheaterMapper;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.Map;

public class TheaterRepository extends AbstractRepository<Theater>{

    public TheaterRepository(Class<Theater> modelClass) {
        super(modelClass);
    }

    @Override
    RowMapper<Theater> getRowMapper() {
        return new TheaterMapper();
    }

    @Override
    Map<String, Object> mapToRow(Theater theater) {
        return Map.of(
                "theater_id", theater.getTheaterID(),
                "theater_name", theater.getTheaterName(),
                "tax_code", theater.getTaxCode(),
                "theater_address", theater.getTheaterAddress()
        );
    }
}
