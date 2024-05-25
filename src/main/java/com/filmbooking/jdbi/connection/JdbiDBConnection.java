package com.filmbooking.jdbi.connection;

import com.filmbooking.utils.PropertiesUtils;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;


public class JdbiDBConnection {
    private static JdbiDBConnection instance;
    private final Jdbi jdbi;
    private static final ThreadLocal<Handle> handleThreadLocal = new ThreadLocal<>();

    private JdbiDBConnection() throws ClassNotFoundException {
        Class.forName(PropertiesUtils.getProperty("db.driver"));
        jdbi = Jdbi.create(
                PropertiesUtils.getProperty("db.url"),
                PropertiesUtils.getProperty("db.username"),
                PropertiesUtils.getProperty("db.password")
        );
    }

    public static JdbiDBConnection getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new JdbiDBConnection();
        }
        return instance;
    }

    public static Handle openHandle() {
        try {
            Handle handle = getInstance().jdbi.open();
            handleThreadLocal.set(handle);
            return handle;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getException());
        }
    }

    public static void closeHandle() {
        try {
            Handle handle = handleThreadLocal.get();
            if (handle != null) {
                handle.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
