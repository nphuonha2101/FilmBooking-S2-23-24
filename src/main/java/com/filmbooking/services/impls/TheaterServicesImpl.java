package com.filmbooking.services.impls;

import com.filmbooking.cache.CacheManager;
import com.filmbooking.model.Theater;
import com.filmbooking.repository.CacheRepository;
import com.filmbooking.repository.TheaterRepository;
import com.filmbooking.services.AbstractService;

import java.util.concurrent.TimeUnit;

public class TheaterServicesImpl extends AbstractService<Theater> {

    public TheaterServicesImpl() {
        super(new CacheRepository<>(new TheaterRepository(), new CacheManager(10, TimeUnit.MINUTES)));
    }

    @Override
    public Theater getBySlug(String slug) {
        throw new UnsupportedOperationException("This method is not supported for Theater");
    }


}
