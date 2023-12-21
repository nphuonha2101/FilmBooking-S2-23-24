package com.filmbooking.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class PaginationUtils {

    /**
     * Set pagination attributes for a request
     *
     * @param dataLimit   limit of data per page
     * @param currentPage current page number
     * @param totalPages  total number of pages
     * @param req         request used to set attributes for pagination
     * @param resp        response used to send error if currentPage is invalid
     * @return offset (the first point of data in the database that you want to get) of the current page.
     * <ul>
     *     <li>If no data (totalPages = 0), return -1.</li>
     *     <li>If currentPage is invalid (currentPage < 0 || currentPage > totalPages), return -2.</li>
     * </ul>
     */
    public static int handlesPagination(int dataLimit, int currentPage, int totalPages, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int offset = 0;
        // if "page" parameter is null, it defaults to 1 due to value of currentPage parameter
        if (req.getParameter("page") != null) {
            currentPage = Integer.parseInt(req.getParameter("page"));
            offset = (currentPage - 1) * dataLimit;
        }

        // if no data
        if (totalPages == 0) {
            return -1;
        }
        // if currentPage is invalid
        if (currentPage < 0 || currentPage > totalPages) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return -2;
        } else {
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages", totalPages);
            return offset;
        }
    }
}
