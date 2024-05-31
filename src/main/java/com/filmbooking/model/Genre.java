package com.filmbooking.model;

import com.filmbooking.annotations.StringID;
import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@ToString
@TableName("genres")
@TableIdName("genre_id")
@StringID
public class Genre implements IModel {
    public static final String TABLE_NAME = "genres";

    @Expose
    private String genreID;
    @Expose
    private String genreName;
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
    public Object getIdValue() {
        return this.genreID;
    }

    public Map<String, Object> mapToRow() {
        return Map.of(
                "genre_id", this.genreID,
                "genre_name", this.genreName
        );
    }
}
