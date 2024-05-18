package com.filmbooking.model;

/*
 *  @created 05/01/2024 - 9:50 AM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = FilmVote.TABLE_NAME)
public class FilmVote implements IModel {
    @Transient
    public static final String TABLE_NAME = "film_votes";

    @Getter
    @Setter
    @Id
    @Expose
    @Column(name = "film_vote_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Expose
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    private Film film;
    @Expose
    @Column(name = "scores")
    private int scores;

    public FilmVote() {}

    public FilmVote(Film film, int scores) {
        this.film = film;
        this.scores = scores;
    }

    @Override
    public String getStringID() {
        return String.valueOf(this.id);
    }


}
