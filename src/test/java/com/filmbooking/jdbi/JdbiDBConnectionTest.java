package com.filmbooking.jdbi;

import com.filmbooking.jdbi.connection.JdbiDBConnection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JdbiDBConnectionTest {

    @Test
    void openHandle() {
        assertDoesNotThrow(() -> {
            JdbiDBConnection.openHandle();
        });
    }
}