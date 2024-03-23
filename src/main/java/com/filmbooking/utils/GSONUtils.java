package com.filmbooking.utils;

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
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        Instant instant = Instant.ofEpochMilli(jsonElement.getAsJsonPrimitive().getAsLong());
                        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                    }
                }).create();
    }

}
