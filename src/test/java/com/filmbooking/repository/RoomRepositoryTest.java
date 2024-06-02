package com.filmbooking.repository;

import com.filmbooking.model.Room;
import com.filmbooking.model.Theater;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomRepositoryTest {

    @Test
    void select(){
        assertDoesNotThrow(() -> {
            RoomRepository roomRepository = new RoomRepository(Room.class);
            System.out.println(roomRepository.select(1));
        });
    }
    @Test
    void insert(){
//        assertDoesNotThrow(() -> {
//            RoomRepository repository = new RoomRepository(Room.class);
//            Room room = new Room(
//                    "R001", 10, 10, theater);
//
//            assertTrue(repository.insert(room));
//        });
    }
}
