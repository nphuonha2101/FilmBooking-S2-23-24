package com.filmbooking.repository;

import com.filmbooking.model.FilmBooking;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FilmBookingRepositoryTest {
    @Test
    void select() {
        assertDoesNotThrow(() -> {
            FilmBookingRepository filmBookingRepository = new FilmBookingRepository(FilmBooking.class);
            System.out.println(filmBookingRepository.selectAll());
        });
    }
}
