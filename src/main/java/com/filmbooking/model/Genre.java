package com.filmbooking.model;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = Genre.TABLE_NAME)
public class Genre implements IModel {
    @Transient
    public static final String TABLE_NAME = "genres";

    @Expose
    @Column(name = "genre_id")
    @Id
    private String genreID;
    @Expose
    @Column(name = "genre_name")
    private String genreName;
    @ManyToMany(mappedBy = "genreList", cascade = CascadeType.ALL)
    private List<Film> filmList;

    public Genre() {
    }

    public Genre(String genreID, String genreName) {
        this.genreID = genreID;
        this.genreName = genreName;
    }

    public Genre(String genreID, String genreName, List<Film> filmList) {
        this.genreID = genreID;
        this.genreName = genreName;
        this.filmList = filmList;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Genre genre) {
            return this.genreID.equals(genre.getGenreID())
                    && this.genreName.equals(genre.getGenreName());
        }
        return false;
    }

    @Override
    public String toString() {
        return this.genreID + ", " + this.genreName;
    }

    @Override
    public String getStringID() {
        return this.genreID;
    }
}
