package com.filmbooking.utils;

import com.filmbooking.controller.apis.apiResponse.APIJSONResponse;
import com.filmbooking.controller.apis.apiResponse.RespCodeEnum;
import com.filmbooking.model.IModel;
import com.filmbooking.services.AbstractCRUDServices;
import com.filmbooking.services.ICRUDServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public class APIUtils<T extends IModel> {
    private final AbstractCRUDServices<T> services;
    private String currentLanguage;

    public APIUtils(AbstractCRUDServices<T> services, HttpServletRequest req) {
        this.services = services;
        this.currentLanguage = (String) req.getSession().getAttribute("lang");
    }

    public String getByID(String id) {
        T model = services.getByID(id);
        APIJSONResponse response;
        if (model == null)
            response = new APIJSONResponse(RespCodeEnum.NOT_FOUND.getCode(), RespCodeEnum.NOT_FOUND.getMessage(), currentLanguage, null);
        else
            response = new APIJSONResponse(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMessage(), currentLanguage, model);

        return response.getResponse();
    }

    public String getAll() {
        List<T> allModels = services.getAll().getMultipleResults();
        APIJSONResponse response;
        if (allModels == null)
            response = new APIJSONResponse(RespCodeEnum.NOT_FOUND.getCode(), RespCodeEnum.NOT_FOUND.getMessage(), currentLanguage, null);
        else
            response = new APIJSONResponse(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMessage(), currentLanguage, allModels);

        return response.getResponse();
    }

    public String getByOffset(int offset, int limit) {
        ICRUDServices<T> models = services.getByOffset(offset, limit);
        APIJSONResponse response;
        if (models == null)
            response = new APIJSONResponse(RespCodeEnum.NOT_FOUND.getCode(), RespCodeEnum.NOT_FOUND.getMessage(), currentLanguage, null);
        else
            response = new APIJSONResponse(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMessage(), currentLanguage, models);

        return response.getResponse();
    }

    public String getBySlug(String slug) {
        T model = services.getBySlug(slug);
        APIJSONResponse response;
        if (model == null)
            response = new APIJSONResponse(RespCodeEnum.NOT_FOUND.getCode(), RespCodeEnum.NOT_FOUND.getMessage(), currentLanguage, null);
        else
            response = new APIJSONResponse(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMessage(), currentLanguage, model);

        return response.getResponse();
    }

    /**
     * Write response to client
     *
     * @param resp        HttpServletResponse used to write response
     * @param jsonResp    JSON response to write
     * @param corsDomains List of domain to allow CORS. Use can use <code>List.of("domain_name1", "domain_name2") to allow specified domain</code>
     *                    or <code>null</code> to allow all domain
     * @param cacheTime   Cache time in seconds
     * @throws IOException if an I/O error occurs when getting the writer
     */
    public static void writeResponse(HttpServletResponse resp, String jsonResp, List<String> corsDomains, int cacheTime) throws IOException {
        if (corsDomains != null) {
            resp.setHeader("Access-Control-Allow-Origin", String.join(",", corsDomains));
            resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        } else
            resp.setHeader("Access-Control-Allow-Origin", "*");


        resp.setHeader("Cache-Control", "max-age=" + cacheTime);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResp);
    }
}

