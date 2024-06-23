package com.filmbooking.utils;

import com.filmbooking.services.IService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class Pagination<T> {
    private final int limit;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final IService<T> service;
    private final String pageUrl;

    public Pagination(IService<T> service, HttpServletRequest request, HttpServletResponse resp, int limit, String pageUrl) {
        this.request = request;
        this.response = resp;
        this.limit = 10;
        this.service = service;
        this.pageUrl = pageUrl;
        request.setAttribute("pageUrl", pageUrl);
    }

    /**
     * Get paginated records from database
     * @return list of records
     */
    public List<T> getPaginatedRecords() {
        long totalRecords = service.countRecords();
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int totalPages = (int) Math.ceil((double) totalRecords / limit);

        if (page < 1 || page > totalPages)
            return null;

        int offset = (page - 1) * limit;

        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);

        return service.selectAll(limit, 0);
    }
}
