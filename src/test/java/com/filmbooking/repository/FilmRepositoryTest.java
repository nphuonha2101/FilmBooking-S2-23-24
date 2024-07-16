package com.filmbooking.repository;
import com.filmbooking.model.Film;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FilmRepositoryTest {

        @Test
        void select() {
            assertDoesNotThrow(() -> {
                FilmRepository filmRepository = new FilmRepository();
                System.out.println(filmRepository.select(1));
            });
        }
}
