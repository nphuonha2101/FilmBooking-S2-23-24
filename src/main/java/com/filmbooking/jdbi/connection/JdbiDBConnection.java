package com.filmbooking.jdbi.connection;

import com.filmbooking.utils.PropertiesUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import javax.sql.DataSource;


public class JdbiDBConnection {
    private static JdbiDBConnection instance;
    private final Jdbi jdbi;
    private static final ThreadLocal<Handle> handleThreadLocal = new ThreadLocal<>();

    private JdbiDBConnection() throws ClassNotFoundException {
        Class.forName(PropertiesUtils.getProperty("db.driver"));

        HikariDataSource dataSource = initHikariCP();
        jdbi = Jdbi.create(dataSource);
    }

    public static JdbiDBConnection getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new JdbiDBConnection();
        }
        return instance;
    }

    private HikariDataSource initHikariCP() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(PropertiesUtils.getProperty("db.url"));
        config.setUsername(PropertiesUtils.getProperty("db.username"));
        config.setPassword(PropertiesUtils.getProperty("db.password"));
        config.setMaximumPoolSize(10);

        return new HikariDataSource(config);
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
