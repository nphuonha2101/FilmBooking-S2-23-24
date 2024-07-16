package com.filmbooking.services.impls;

import com.filmbooking.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServicesImplTest {

    @Test
    void selectByUsername() {
        assertDoesNotThrow(() -> {
            UserServicesImpl userServices = new UserServicesImpl();
            User user = userServices.getByUsername("nphuonha");
            System.out.println("selected user: " + user);
        });
    }
}