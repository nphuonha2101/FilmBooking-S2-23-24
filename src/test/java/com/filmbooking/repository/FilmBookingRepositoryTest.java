package com.filmbooking.repository;

import com.filmbooking.model.FilmBooking;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FilmBookingRepositoryTest {
    @Test
    void select() {
        assertDoesNotThrow(() -> {
            FilmBookingRepository filmBookingRepository = new FilmBookingRepository();
            double totalFee = 0;
            for (FilmBooking filmBooking : filmBookingRepository.selectAllByDates("01/01/2024","01/01/2025")) {
                totalFee += filmBooking.getTotalFee();
                System.out.println("FilmBookingRepositoryTest: select" + totalFee);
            }
            System.out.println(totalFee);
        });
    }

    @Test
    void selectAll() {
        assertNotNull(new FilmBookingRepository().selectAll());
        System.out.println("FilmBookingRepositoryTest: selectAll" + new FilmBookingRepository().selectAll());
    }
}
