package com.filmbooking.repository;

import com.filmbooking.model.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomRepositoryTest {
    @Test
    void insert(){
        assertDoesNotThrow(() -> {
            RoomRepository repository = new RoomRepository(Room.class);
            Room room = new Room(
                    "R001", 1, 10, 10, "abc", "xyz"
            );
            assertTrue(repository.insert(room));
        });
    }
}
