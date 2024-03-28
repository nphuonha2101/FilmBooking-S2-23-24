package com.filmbooking.enumsAndConstants.enums;

import lombok.Getter;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public enum LogActionsEnums {
    CREATE("create"),
    UPDATE("update"),
    DELETE("delete"),
    LOGIN("login");

    @Getter
    private final String description;

    LogActionsEnums(String description) {
        this.description = description;
    }

}
