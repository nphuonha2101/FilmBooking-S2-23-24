package com.filmbooking.repository;

import com.filmbooking.jdbi.connection.JdbiDBConnection;
import com.filmbooking.model.Room;
import com.filmbooking.model.Showtime;
import com.filmbooking.repository.mapper.RoomMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomRepository extends AbstractRepository<Room>{
    public RoomRepository() {
        super(Room.class);
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
    public boolean update(Room room) {
        try {
            Handle handle = JdbiDBConnection.openHandle();
            String sql = "UPDATE rooms SET room_name = :room_name, seat_rows = :seat_rows, seat_cols = :seat_cols, seats_data = :seats_data, theater_id = :theater_id, slug = :slug WHERE room_id = :room_id";
            handle.createUpdate(sql)
                    .bind("room_id", room.getRoomID())
                    .bind("room_name", room.getRoomName())
                    .bind("seat_rows", room.getSeatRows())
                    .bind("seat_cols", room.getSeatCols())
                    .bind("seats_data", room.getSeatData())
                    .bind("theater_id", room.getTheater().getTheaterID())
                    .bind("slug", room.getSlug())
                    .execute();
            return true;
        }catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }


    @Override
    public boolean delete(Room room) {

        List<Showtime> showtimeList = room.getShowtimeList();

        if (showtimeList == null) {
            return super.delete(room);
        }

        for (Showtime showtime : showtimeList) {
            new ShowtimeRepository().delete(showtime);
        }

        return super.delete(room);
    }

    @Override
    RowMapper<Room> getRowMapper() {
        return new RoomMapper();
    }

    @Override
    public Map<String, Object> mapToRow(Room room) {
        Map<String, Object> result = new HashMap<>();
        result.put("room_name", room.getRoomName());
        result.put("seat_rows", room.getSeatRows());
        result.put("seat_cols", room.getSeatCols());
        result.put("seats_data", room.getSeatData());
        result.put("theater_id", room.getTheater().getTheaterID());
        result.put("slug", room.getSlug());
        
        return result;
    }
}
