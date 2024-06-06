package com.filmbooking.repository;

import com.filmbooking.model.Genre;
import com.filmbooking.model.LogModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LogRepositoryTest {
    @Test
    void selectAll(){
        assertDoesNotThrow(() -> {
            LogRepository logRepository = new LogRepository(LogModel.class);
            System.out.println(logRepository.selectAll());
        });
    }
}
