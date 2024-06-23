package com.filmbooking.repository;

import com.filmbooking.model.Theater;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TheaterRepositoryTest {

    @Test
    void select() {
        assertDoesNotThrow(() -> {
            TheaterRepository theaterRepository = new TheaterRepository();
            System.out.println(theaterRepository.select(1));
        });
    }

}