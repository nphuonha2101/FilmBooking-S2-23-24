package com.filmbooking.controller.apis.apiResponse;

import com.filmbooking.utils.gsonUtils.GSONUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIJSONResponse implements Serializable {
    @Expose
    private int status;
    @Expose
    private String message;
    @Expose
    private String language;
    @Expose
    private Object data;

    public String getResponse() {
        Gson gson = GSONUtils.getGson();
        return gson.toJson(this);
    }
}


