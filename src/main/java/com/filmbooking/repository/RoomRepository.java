package com.filmbooking.repository;

import com.filmbooking.model.Room;
import com.filmbooking.repository.mapper.RoomMapper;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.Map;

public class RoomRepository extends AbstractRepository<Room>{
    public RoomRepository(Class<Room> modelClass) {
        super(modelClass);
    }

    @Override
    RowMapper<Room> getRowMapper() {
        return new RoomMapper();
    }

    @Override
    Map<String, Object> mapToRow(Room room) {
        return Map.of(
                "room_name", room.getRoomName(),
                "theater_id", room.getTheater().getTheaterID(),
                "seat_rows", room.getSeatRows(),
                "seat_cols", room.getSeatCols(),
                "seat_data", room.getSeatData(),
                "slug", room.getSlug()
        );
    }
}
