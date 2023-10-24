package com.filmbooking.services;

import com.filmbooking.model.view.RoomView;
import com.filmbooking.model.view.ShowtimeView;

import java.util.List;

public interface IRoomViewServices {
    List<RoomView> getAll();

    RoomView getByID(String id);
}
