package com.filmbooking.services;

import com.filmbooking.model.Film;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public interface IFilmServices {
    /**
     * Update a film with its genreIDs
     *
     * @param film     film to update
     * @param genreIDs genreIDs of the film
     * @return true if update successfully, false otherwise
     */
    boolean update(Film film, String... genreIDs);

    /**
     * Save a film with its genreIDs
     *
     * @param film     film to save
     * @param genreIDs genreIDs of the film
     * @return true if save successfully, false otherwise
     */
    boolean save(Film film, String... genreIDs);
}
