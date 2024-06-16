package com.filmbooking.repository;

import com.filmbooking.model.Room;
import com.filmbooking.model.Theater;
import com.filmbooking.repository.mapper.TheaterMapper;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheaterRepository extends AbstractRepository<Theater>{

    public TheaterRepository() {
        super(Theater.class);
    }

    @Override
    public boolean delete(Theater theater) {

        List<Room> roomList = theater.getRoomList();

        if (roomList == null) {
            return super.delete(theater);
        }

        for (Room room : roomList) {
            new RoomRepository().delete(room);
        }

        return super.delete(theater);
    }

    @Override
    RowMapper<Theater> getRowMapper() {
        return new TheaterMapper();
    }

    @Override
    Map<String, Object> mapToRow(Theater theater) {
        Map<String, Object> result = new HashMap<>();
        result.put("theater_name", theater.getTheaterName());
        result.put("tax_code", theater.getTaxCode());
        result.put("theater_address", theater.getTheaterAddress());

        return result;
    }
}
