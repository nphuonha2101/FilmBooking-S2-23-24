package com.filmbooking.utils.gsonUtils;

import com.google.gson.*;
import lombok.Getter;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public class GSONUtils {
    @Getter
    private static Gson gson = gsonConfig();

    /**
     * Gson configuration for LocalDateTime
     */
    private static Gson gsonConfig() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }
}
