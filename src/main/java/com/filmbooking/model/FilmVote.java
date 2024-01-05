package com.filmbooking.model;

/*
 *  @created 05/01/2024 - 9:50 AM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import jakarta.persistence.*;

@Entity
@Table(name = "film_votes")
public class FilmVote {
    @Id
    @Column(name = "film_vote_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;
    @Column(name = "scores")
    private int scores;

    public FilmVote() {
    }

    public FilmVote(Film film, int scores) {
        this.film = film;
        this.scores = scores;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public int getScores() {
        return scores;
    }

    public void setScore(int scores) {
        this.scores = scores;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
