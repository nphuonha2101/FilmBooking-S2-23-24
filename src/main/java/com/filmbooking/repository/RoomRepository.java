package com.filmbooking.repository;

import com.filmbooking.model.Room;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.Map;

public class RoomRepository extends AbstractRepository<com.filmbooking.model.Room> {
    public RoomRepository(Class<Room> modelClass) {
        super(modelClass);
    }

    @Override
    RowMapper<Room> getRowMapper() {
        return null;
    }

    @Override
    Map<String, Object> mapToRow(Room room) {
        return null;
    }
}
