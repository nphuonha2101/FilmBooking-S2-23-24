package com.filmbooking.repository;

import com.filmbooking.model.FilmBooking;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FilmBookingRepositoryTest {
    @Test
    void select() {

    }

    @Test
    void selectAll() {
        assertNotNull(new FilmBookingRepository().selectAll());
        System.out.println("FilmBookingRepositoryTest: selectAll" + new FilmBookingRepository().selectAll());
    }
}
