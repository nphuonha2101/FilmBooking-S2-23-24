package com.filmbooking.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

public class CacheManager {
    private final Cache<String, Object> cache;

    public CacheManager(int cacheDuration, TimeUnit cacheTimeUnit) {
        this.cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(cacheDuration, cacheTimeUnit)
                .build();
    }

    public Object get(String key) {
        System.out.println("CacheManager: get " + key);
        return this.cache.getIfPresent(key);
    }

    public void put(String key, Object value) {
        this.cache.put(key, value);
    }

    public void remove(String key) {
        this.cache.invalidate(key);
    }

    public void removeAll() {
        this.cache.invalidateAll();
    }

    public long size() {
        return this.cache.size();
    }

    public boolean containsKey(String key) {
        return this.cache.asMap().containsKey(key);
    }
}
