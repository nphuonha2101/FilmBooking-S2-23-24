package com.filmbooking.repository;

import com.filmbooking.model.Genre;
import com.filmbooking.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class GenreRepositoryTest {
    @Test
    void selectAll(){
        assertDoesNotThrow(() -> {
            GenreRepository genreRepository = new GenreRepository();
            System.out.println(genreRepository.selectAll());
        });
    }
}
