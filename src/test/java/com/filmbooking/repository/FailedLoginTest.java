package com.filmbooking.repository;
import com.filmbooking.model.FailedLogin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class FailedLoginTest {

            @Test
            void select() {
                assertDoesNotThrow(() -> {
                    FailedLoginRepository failedLoginRepository = new FailedLoginRepository(FailedLogin.class);
                    System.out.println(failedLoginRepository.selectAll());
                });
            }
}
