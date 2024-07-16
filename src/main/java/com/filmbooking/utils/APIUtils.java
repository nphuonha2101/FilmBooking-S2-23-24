package com.filmbooking.utils;

import com.filmbooking.controller.apis.apiResponse.APIJSONResponse;
import com.filmbooking.controller.apis.apiResponse.RespCodeEnum;
import com.filmbooking.model.IModel;
import com.filmbooking.services.AbstractService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public class APIUtils<T extends IModel> {
    private final AbstractService<T> services;
    private final String currentLanguage;
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    @Getter
    @Setter
    private APIJSONResponse jsonResponse;


    public APIUtils(AbstractService<T> services, HttpServletRequest req, HttpServletResponse resp) {
        this.services = services;
        this.req = req;
        this.resp = resp;
        this.currentLanguage = req.getSession().getAttribute("lang") == null ? "default" : (String) req.getSession().getAttribute("lang");
    }

    /**
     * Get model by ID and return as JSON response
     */
    private void getByID() {
        String id = req.getParameter("id");

        if (id == null)
            throw new RuntimeException("ID must not be null");

        T model = services.select(id);

        if (model == null)
            this.jsonResponse = new APIJSONResponse(RespCodeEnum.NOT_FOUND.getCode(), RespCodeEnum.NOT_FOUND.getMessage(), currentLanguage, null);
        else
            this.jsonResponse = new APIJSONResponse(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMessage(), currentLanguage, model);

    }

    /**
     * Get all models and return as JSON response
     */
    private void getAll() {
        List<T> allModels = services.selectAll();

        if (allModels == null)
            this.jsonResponse = new APIJSONResponse(RespCodeEnum.NOT_FOUND.getCode(), RespCodeEnum.NOT_FOUND.getMessage(), currentLanguage, null);
        else
            this.jsonResponse = new APIJSONResponse(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMessage(), currentLanguage, allModels);

    }

    /*
     * Get response by offset and limit and return as JSON response
     */
    private void getByOffset() {
        int offset = req.getParameter("offset") != null ? Integer.parseInt(req.getParameter("offset")) : 0;
        int limit = req.getParameter("limit") != null ? Integer.parseInt(req.getParameter("limit")) : 5;

        if (offset >= 0) {
            List<T> models = services.selectAll(offset, limit);

            if (models == null)
                this.jsonResponse = new APIJSONResponse(RespCodeEnum.NOT_FOUND.getCode(), RespCodeEnum.NOT_FOUND.getMessage(), currentLanguage, null);
            else
                this.jsonResponse = new APIJSONResponse(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMessage(), currentLanguage, models);

        } else {
            throw new RuntimeException("Offset must be greater than or equal to 0");
        }
    }

    /**
     * Get response by slug and return as JSON response
     */
    private void getBySlug() {
        String slug = req.getParameter("slug");

        if (slug == null)
            throw new RuntimeException("Slug must not be null");

        T model = services.getBySlug(slug);

        if (model == null)
            this.jsonResponse = new APIJSONResponse(RespCodeEnum.NOT_FOUND.getCode(), RespCodeEnum.NOT_FOUND.getMessage(), currentLanguage, null);
        else
            this.jsonResponse = new APIJSONResponse(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMessage(), currentLanguage, model);
    }

    /**
     * Process request from client and call appropriate method to handle request
     *
     * @param command Command to execute. Use:
     * <ul>
     *  <li><b><code>id</code></b> to get model by ID. Request parameter <b>id</b></li>
     *  <li><b><code>all</code></b> to get all models</li>
     *  <li><b><code>offset</code></b> to get models by offset. Request parameter <b>offset</b> and <b>limit</b></li>
     *  <li><b><code>slug</code></b> to get model by slug. Request parameter <b>slug</b></li>
     * </ul>
     */
    public void processRequest(String command) {
        command = command.toLowerCase();
        switch (command) {
            case "id":
                this.getByID();
                break;
            case "all":
                this.getAll();
                break;
            case "offset":
                this.getByOffset();
                break;
            case "slug":
                this.getBySlug();
                break;
            default:
                throw new RuntimeException("Invalid command");
        }
    }

    /**
     * Write response to client
     *
     * @param corsDomains List of domain to allow CORS. Use can use <code>List.of("domain_name1", "domain_name2") to allow specified domain</code>
     *                    or <code>null</code> to allow all domain
     * @param cacheTime   Cache time in seconds
     * @throws IOException if an I/O error occurs when getting the writer
     */
    public void writeResponse(List<String> corsDomains, int cacheTime) throws IOException {
        initResp(corsDomains, cacheTime);
        resp.getWriter().write(jsonResponse.getResponse());
    }


    /**
     * Write response to client
     * @param json JSON response
     * @param corsDomains List of domain to allow CORS. Use can use <code>List.of("domain_name1", "domain_name2") to allow specified domain</code>
     * @param cacheTime Cache time in seconds
     * @throws IOException if an I/O error occurs when getting the writer
     */
    public void writeResponse(String json, List<String> corsDomains, int cacheTime) throws IOException {
        initResp(corsDomains, cacheTime);
        resp.getWriter().write(json);
    }

    /**
     * Initialize response header
     * @param corsDomains List of domain to allow CORS
     * @param cacheTime Cache time in seconds
     */
    private void initResp(List<String> corsDomains, int cacheTime) {
        if (corsDomains != null) {
            this.resp.setHeader("Access-Control-Allow-Origin", String.join(",", corsDomains));
            this.resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        } else
            resp.setHeader("Access-Control-Allow-Origin", "*");

        resp.setHeader("Cache-Control", "max-age=" + cacheTime);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }
}

