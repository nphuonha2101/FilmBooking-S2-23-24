package com.filmbooking.services;

import java.util.Map;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public interface IShowtimeServices {
    Map<Long, Integer> getAvailableSeatsByShowtimeId();
}
