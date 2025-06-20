package com.filmbooking.model;

import com.filmbooking.annotations.TableIdName;
import com.filmbooking.annotations.TableName;
import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@TableName("genres")
@TableIdName("genre_id")
public class Genre extends AbstractModel implements IModel {
    public static final String TABLE_NAME = "genres";

    @Expose
    private String genreID;
    @Expose
    private String genreName;

    public Genre() {
    }

    public Genre(String genreID, String genreName) {
        this.genreID = genreID;
        this.genreName = genreName;
    }

    public Genre(String genreID, String genreName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.genreID = genreID;
        this.genreName = genreName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

}
