package com.filmbooking.repository;

import com.filmbooking.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    @Test
    void selectAll() {
        assertDoesNotThrow(() -> {
            UserRepository userRepository = new UserRepository(User.class);
            System.out.println(userRepository.selectAll());
        });
    }

}