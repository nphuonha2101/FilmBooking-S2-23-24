package com.filmbooking.repository;

import com.filmbooking.model.Room;
import com.filmbooking.model.Theater;
import com.filmbooking.repository.mapper.TheaterMapper;
import org.jdbi.v3.core.mapper.RowMapper;

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
        return Map.of(
                "theater_name", theater.getTheaterName(),
                "tax_code", theater.getTaxCode(),
                "theater_address", theater.getTheaterAddress()
        );
    }
}
