package com.filmbooking.repository;

import com.filmbooking.jdbi.connection.JdbiDBConnection;
import com.filmbooking.model.Room;
import com.filmbooking.model.Showtime;
import com.filmbooking.repository.mapper.RoomMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.List;
import java.util.Map;

public class RoomRepository extends AbstractRepository<Room>{
    public RoomRepository(Class<Room> modelClass) {
        super(modelClass);
    }

    public List<Room> selectAllByTheaterId(Long theaterId) {
       try {
            Handle handle = JdbiDBConnection.openHandle();

            String sql = "SELECT * FROM rooms WHERE theater_id = :theater_id";
            System.out.println("Select All By Theater ID SQL: " + sql);

           return handle.createQuery(sql)
                    .bind("theater_id", theaterId)
                    .map(getRowMapper())
                    .list();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            JdbiDBConnection.closeHandle();
       }
    }

    @Override
    public boolean delete(Room room) {

        List<Showtime> showtimeList = room.getShowtimeList();

        if (showtimeList == null) {
            return super.delete(room);
        }

        for (Showtime showtime : showtimeList) {
            new ShowtimeRepository(Showtime.class).delete(showtime);
        }

        return super.delete(room);
    }

    @Override
    RowMapper<Room> getRowMapper() {
        return new RoomMapper();
    }

    @Override
    public Map<String, Object> mapToRow(Room room) {
        return Map.of(
                "room_id", room.getRoomID(),
                "room_name", room.getRoomName(),
                "seat_rows", room.getSeatRows(),
                "seat_cols", room.getSeatCols(),
                "seat_data", room.getSeatData(),
                "theater_id", room.getTheater().getTheaterID(),
                "slug", room.getSlug()
        );
    }
}
