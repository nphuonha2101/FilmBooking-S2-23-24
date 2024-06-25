package com.filmbooking.repository;

import com.filmbooking.jdbi.connection.JdbiDBConnection;
import com.filmbooking.jdbi.builder.JdbiBuilder;
import com.filmbooking.model.Film;
import com.filmbooking.model.IModel;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractRepository<T extends IModel> implements IRepository<T> {
    protected final JdbiBuilder<T> jdbiBuilder;

    public AbstractRepository(Class<T> modelClass) {
        System.out.println("AbstractRepository: " + modelClass);
        this.jdbiBuilder = new JdbiBuilder<>(modelClass);
    }

    @Override
    public boolean insert(T t) {
        try {
            Handle handle = JdbiDBConnection.openHandle();

            jdbiBuilder.setMapToRow(mapToRow(t));
            String sql = jdbiBuilder.buildInsertSQL();
            System.out.println("Insert SQL: " + sql);

            handle.createUpdate(sql)
                    .bindMap(jdbiBuilder.getMapToRow())
                    .execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            JdbiDBConnection.closeHandle();
        }

    }

    @Override
    public boolean update(T t) {
        try {
            Handle handle = JdbiDBConnection.openHandle();

            jdbiBuilder.setMapToRow(mapToRow(t));
            String sql = jdbiBuilder.buildUpdateSQL();

            System.out.println("Map to row: " + mapToRow(t));

            handle.createUpdate(sql)
                    .bindMap(mapToRow(t))
                    .execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            JdbiDBConnection.closeHandle();
        }
    }

    @Override
    public boolean delete(T t) {
        try {
            Handle handle = JdbiDBConnection.openHandle();

            String sql = jdbiBuilder.buildDeleteSQL();
            System.out.println("Delete SQL: " + sql);

            Object id = t.getIdValue();

            System.out.println("AbstractRepository: delete: " + id + ", key name: " + jdbiBuilder.getPrimaryKeyName());

            handle.createUpdate(sql)
                    .bind(jdbiBuilder.getPrimaryKeyName(), id)
                    .execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            JdbiDBConnection.closeHandle();
        }

    }

    @Override
    public T select(Object id) {
        try {
            Handle handle = JdbiDBConnection.openHandle();

            String sql = jdbiBuilder.buildSelectSQL();
            System.out.println("Select SQL: " + sql);

            return handle.createQuery(sql)
                    .bind(jdbiBuilder.getPrimaryKeyName(), id)
                    .map(getRowMapper())
                    .one();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            JdbiDBConnection.closeHandle();
        }
    }

    @Override
    public List<T> selectAll() {
        try {
            Handle handle = JdbiDBConnection.openHandle();

            String sql = jdbiBuilder.buildSelectAllSQL();
            System.out.println("Select All SQL: " + sql);

            return handle.createQuery(sql)
                    .map(getRowMapper())
                    .list();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            JdbiDBConnection.closeHandle();
        }
    }

    @Override
    public List<T> selectAll(Map<String, Object> filters) {
        try {
            Handle handle = JdbiDBConnection.openHandle();

            String sql = jdbiBuilder.buildSelectAllSQL(filters);
            System.out.println("Select All SQL: " + sql);

            Map<String, Object> modifiedFilters = new HashMap<>();
            filters.forEach((k, v) -> modifiedFilters.put(k.split("_")[0], v));

            System.out.println("Filters: " + filters);

            return handle.createQuery(sql)
                    .bindMap(modifiedFilters)
                    .map(getRowMapper())
                    .list();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            JdbiDBConnection.closeHandle();
        }
    }

    @Override
    public List<T> selectAll(int limit, int offset) {
        try {
            Handle handle = JdbiDBConnection.openHandle();

            String sql = jdbiBuilder.buildSelectAllSQL(limit, offset);
            System.out.println("Select All SQL: " + sql);

            return handle.createQuery(sql)
                    .map(getRowMapper())
                    .list();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            JdbiDBConnection.closeHandle();
        }
    }

    @Override
    public List<T> selectAll(int limit, int offset, String order) {
        try {
            Handle handle = JdbiDBConnection.openHandle();

            String sql = jdbiBuilder.buildSelectAllSQL(limit, offset, order);
            System.out.println("Select All SQL: " + sql);

            return handle.createQuery(sql)
                    .map(getRowMapper())
                    .list();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            JdbiDBConnection.closeHandle();
        }
    }

    @Override
    public List<T> selectAll(int limit, int offset, String order, Map<String, Object> filters) {
        try {
            Handle handle = JdbiDBConnection.openHandle();

            String sql = jdbiBuilder.buildSelectAllSQL(limit, offset, order, filters);
            System.out.println("Select All SQL: " + sql);

            Map<String, Object> modifiedFilters = new HashMap<>();
            filters.forEach((k, v) -> modifiedFilters.put(k.split("_")[0], v));

            return handle.createQuery(sql)
                    .bindMap(modifiedFilters)
                    .map(getRowMapper())
                    .list();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            JdbiDBConnection.closeHandle();
        }
    }

    @Override
    public long countRecords() {
        try {
            Handle handle = JdbiDBConnection.openHandle();

            String sql = jdbiBuilder.buildCountSQL();
            System.out.println("Count SQL: " + sql);

            return handle.createQuery(sql)
                    .mapTo(Long.class)
                    .one();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return 0;
        } finally {
            JdbiDBConnection.closeHandle();
        }
    }

    /**
     * Get the RowMapper for the model. It is used to map the {@link java.sql.ResultSet} to the model
     *
     * @return a class that implements {@link RowMapper}
     */
    abstract RowMapper<T> getRowMapper();

    /**
     * Map the model to a row in the database.
     * The key of the map is the column name in the database
     * The value of the map is the value of the model
     * Example: "username" -> "abc"
     *
     * @param t the model to be mapped
     * @return a map that contains the column name and the value of the model
     */
    abstract Map<String, Object> mapToRow(T t);
}
