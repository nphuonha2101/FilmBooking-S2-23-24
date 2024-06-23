package com.filmbooking.repository;
import com.filmbooking.model.Showtime;
import com.filmbooking.services.impls.ShowtimeServicesImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShowtimeRepositoryTest {
    @Test
    void select(){
        assertDoesNotThrow(() -> {
            ShowtimeServicesImpl showtimeRepository = new ShowtimeServicesImpl();
            showtimeRepository.getBySlug("oppenheimer-r001-vip-2024-06-27t20:28");

        });
    }
    @Test
    void update(){
        assertDoesNotThrow(() -> {
            ShowtimeRepository showtimeRepository = new ShowtimeRepository();
            Showtime before = showtimeRepository.select(2);
            before.setSlug("S001");
            assertTrue(showtimeRepository.update(before));
        });
    }
}
