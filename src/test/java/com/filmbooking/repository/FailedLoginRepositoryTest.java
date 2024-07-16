package com.filmbooking.repository;
import com.filmbooking.model.FailedLogin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class FailedLoginRepositoryTest {

            @Test
            void select() {
                assertDoesNotThrow(() -> {
                    FailedLoginRepository failedLoginRepository = new FailedLoginRepository();
                    System.out.println(failedLoginRepository.select("0:0:0:0:0:0:0:1"));
                });
            }

            @Test
    void update(){
        assertDoesNotThrow(() -> {
                FailedLoginRepository failedLoginRepository = new FailedLoginRepository();
                FailedLogin failedLogin = failedLoginRepository.select("0:0:0:0:0:0:0:1");
                failedLoginRepository.update(failedLogin);
                System.out.println(failedLoginRepository.select("0:0:0:0:0:0:0:1"));
            });
        }

        @Test
        void delete(){
            assertDoesNotThrow(() -> {
                FailedLoginRepository failedLoginRepository = new FailedLoginRepository();
                FailedLogin failedLogin = failedLoginRepository.select("0:0:0:0:0:0:0:1");
                failedLoginRepository.delete(failedLogin);
            });
        }
}
