package com.filmbooking.services.impls;

import com.filmbooking.cache.CacheManager;
import com.filmbooking.model.Room;
import com.filmbooking.repository.CacheRepository;
import com.filmbooking.repository.RoomRepository;
import com.filmbooking.repository.mapper.RoomMapper;
import com.filmbooking.services.AbstractService;

import java.util.concurrent.TimeUnit;

public class RoomServicesImpl extends AbstractService<Room> {

    public RoomServicesImpl() {
        super(
                new CacheRepository<>(new RoomRepository(),
                        new CacheManager(5, TimeUnit.MINUTES))
        );
    }
}
