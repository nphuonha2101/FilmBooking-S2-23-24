package com.filmbooking.repository;

import com.filmbooking.cache.CacheManager;
import com.filmbooking.model.IModel;
import com.filmbooking.repository.AbstractRepository;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.List;
import java.util.Map;

public class CacheRepository<T extends IModel> extends AbstractRepository<T> {
    private final AbstractRepository<T> decoratedRepository;
    private final CacheManager cacheManager;

    public CacheRepository(AbstractRepository<T> decoratedRepository, CacheManager cacheManager) {
        super(decoratedRepository.getModelClass());
        this.decoratedRepository = decoratedRepository;
        this.cacheManager = cacheManager;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> selectAll(Map<String, Object> filters) {
        String cacheKey = getModelClass().getSimpleName() + "_all_filtered";
        if (this.cacheManager.containsKey(cacheKey)) {
            return (List<T>) this.cacheManager.get(cacheKey);
        }
        List<T> result = super.selectAll(filters);
        this.cacheManager.put(cacheKey, result);

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> selectAll() {
        String cacheKey = getModelClass().getSimpleName() + "_all";
        if (this.cacheManager.containsKey(cacheKey)) {
            return (List<T>) this.cacheManager.get(cacheKey);
        }
        List<T> result = super.selectAll();
        this.cacheManager.put(cacheKey, result);

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> selectAll(int limit, int offset) {
        String cacheKey = getModelClass().getSimpleName() + "_all_" + limit + "_" + offset;
        if (this.cacheManager.containsKey(cacheKey)) {
            return (List<T>) this.cacheManager.get(cacheKey);
        }
        List<T> result = super.selectAll(limit, offset);
        this.cacheManager.put(cacheKey, result);

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> selectAll(int limit, int offset, String order) {
        String cacheKey = getModelClass().getSimpleName() + "_all_" + limit + "_" + offset + "_" + order;
        if (this.cacheManager.containsKey(cacheKey)) {
            return (List<T>) this.cacheManager.get(cacheKey);
        }
        List<T> result = super.selectAll(limit, offset, order);
        this.cacheManager.put(cacheKey, result);

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> selectAll(int limit, int offset, String order, Map<String, Object> filters) {
        String cacheKey = getModelClass().getSimpleName() + "_all_" + limit + "_" + offset + "_" + order + "_filtered";
        if (this.cacheManager.containsKey(cacheKey)) {
            return (List<T>) this.cacheManager.get(cacheKey);
        }
        List<T> result = super.selectAll(limit, offset, order, filters);
        this.cacheManager.put(cacheKey, result);

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T select(Object id) {
        String cacheKey = getModelClass().getSimpleName() + "_id_" + id;
        if (this.cacheManager.containsKey(cacheKey)) {
            return (T) this.cacheManager.get(cacheKey);
        }
        T result = super.select(id);
        this.cacheManager.put(cacheKey, result);

        return result;
    }

    @Override
    RowMapper<T> getRowMapper() {
        return this.decoratedRepository.getRowMapper();
    }

    @Override
    Map<String, Object> mapToRow(T t) {
        return this.decoratedRepository.mapToRow(t);
    }
}
