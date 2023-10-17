package com.filmbooking.services;

import com.filmbooking.model.view.ShowtimeView;

import java.util.List;

public interface IShowtimeViewServices {
    List<ShowtimeView> getAll();

    ShowtimeView getByID(String id);
}
