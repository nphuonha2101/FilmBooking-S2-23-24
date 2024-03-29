package com.filmbooking.model;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "genres")
public class Genre {
    @Expose
    @Column(name = "genre_id")
    @Id
    private String genreID;
    @Expose
    @Column(name = "genre_name")
    private String genreName;
   @ManyToMany(mappedBy = "genreList", cascade = CascadeType.ALL)
    private List<Film> filmList;

   public Genre() {}

    public Genre(String genreID, String genreName) {
        this.genreID = genreID;
        this.genreName = genreName;
    }

    public Genre(String genreID, String genreName, List<Film> filmList) {
        this.genreID = genreID;
        this.genreName = genreName;
        this.filmList = filmList;
    }


    public String getGenreID() {
        return genreID;
    }

    public void setGenreID(String genreID) {
        this.genreID = genreID;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<Film> getFilmList() {
        return filmList;
    }

    public void setFilmList(List<Film> filmList) {
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
}
