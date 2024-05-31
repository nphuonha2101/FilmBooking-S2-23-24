package com.filmbooking.repository;

import com.filmbooking.model.Theater;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TheaterRepositoryTest {

    @Test
    void select() {
        assertDoesNotThrow(() -> {
            TheaterRepository theaterRepository = new TheaterRepository(Theater.class);
            System.out.println(theaterRepository.selectAll(2, 0));
        });
    }

}