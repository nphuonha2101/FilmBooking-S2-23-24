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

    @Test
    void selectById() {
        assertDoesNotThrow(() -> {
            UserRepository userRepository = new UserRepository(User.class);
            System.out.println(userRepository.select("nphuonha"));
            assertEquals("nphuonha", userRepository.select("nphuonha").getUsername());
        });
    }

    @Test
    void insert() {
        assertDoesNotThrow(() -> {
            UserRepository userRepository = new UserRepository(User.class);
            User user = new User("nphuonha1",
                    "Nguyen Phuong Nha",
                    "abc@gmail.com",
                    "123456",
                    "admin",
                    "normal",
                    1,
                    null);

            assertTrue(userRepository.insert(user));

        });
    }

    @Test
    void update() {
        assertDoesNotThrow(() -> {
            UserRepository userRepository = new UserRepository(User.class);
            User user = userRepository.select("nphuonha1");
            user.setUserFullName("Nguyen Phuong Nha 1");

            assertTrue(userRepository.update(user));
            assertEquals("Nguyen Phuong Nha 1", userRepository.select("nphuonha1").getUserFullName());
        });
    }

    @Test
    void delete() {
        assertDoesNotThrow(() -> {
            UserRepository userRepository = new UserRepository(User.class);
            User user = userRepository.select("nphuonha1");
            assertTrue(userRepository.delete(user));
            assertNull(userRepository.select("nphuonha1"));
        });
    }
}