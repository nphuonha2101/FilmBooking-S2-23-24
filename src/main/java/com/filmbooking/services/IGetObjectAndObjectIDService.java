package com.filmbooking.services;

import java.util.HashMap;
import java.util.List;

public interface IGetObjectAndObjectIDService<T> {
    default HashMap<String, T> getObjectAndObjectID(List<T> objList) {
        HashMap<String, T> result = new HashMap<>();
        for (T object: objList) {
            result.put(getObjectID(object), object);
        }
        return result;
    }

    String getObjectID(T obj);
}
