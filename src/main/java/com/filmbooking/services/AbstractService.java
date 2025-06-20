package com.filmbooking.services;

import com.filmbooking.model.IModel;
import com.filmbooking.repository.AbstractRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public abstract class AbstractService<T extends IModel> implements IService<T> {
    protected final AbstractRepository<T> repository;

    protected AbstractService(AbstractRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public T getBySlug(String slug) {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("slug_=", slug);
        List<T> result = repository.selectAll(conditions);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public boolean insert(T t) {
        t.setCreatedAt(LocalDateTime.now());
        t.setUpdatedAt(LocalDateTime.now());
        return repository.insert(t);
    }

    @Override
    public boolean update(T t) {
        t.setCreatedAt(t.getCreatedAt());
        t.setUpdatedAt(LocalDateTime.now());
        return repository.update(t);
    }

    @Override
    public boolean delete(T t) {
        return repository.delete(t);
    }

    @Override
    public T select(Object id) {
        return repository.select(id);
    }

    @Override
    public List<T> selectAll(int limit, int offset, String order) {
        return repository.selectAll(limit, offset, order);
    }

    @Override
    public List<T> selectAll(int limit, int offset) {
        return repository.selectAll(limit, offset);
    }

    @Override
    public List<T> selectAll(int limit, int offset, String order, Map<String, Object> filters) {
        return repository.selectAll(limit, offset, order, filters);
    }

    @Override
    public List<T> selectAll() {
        return repository.selectAll();
    }

    @Override
    public List<T> selectAll(Map<String, Object> filters) {
        return repository.selectAll(filters);
    }

    @Override
    public long countRecords() {
        return repository.countRecords();
    }
}
