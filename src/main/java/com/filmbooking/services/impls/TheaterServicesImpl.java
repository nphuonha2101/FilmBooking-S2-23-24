package com.filmbooking.services.impls;

import com.filmbooking.model.Theater;
import com.filmbooking.repository.TheaterRepository;
import com.filmbooking.services.AbstractService;

public class TheaterServicesImpl extends AbstractService<Theater> {

    public TheaterServicesImpl() {
        super(new TheaterRepository(Theater.class));
    }

    @Override
    public Theater getBySlug(String slug) {
        throw new UnsupportedOperationException("This method is not supported for Theater");
    }


}
