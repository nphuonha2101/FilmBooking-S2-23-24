package com.filmbooking.model;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public abstract class AbstractModel implements IModel {
    @Expose
    protected LocalDateTime createdAt;
    @Expose
    protected LocalDateTime updatedAt;
}
