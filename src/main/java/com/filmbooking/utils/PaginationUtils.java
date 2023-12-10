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
     * @return offset (the first point of data in the database that you want to get) of the current page. If currentPage is invalid, return -1
     */
    public static int handlesPagination(int dataLimit, int currentPage, int totalPages, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int offset = 0;
        // if "page" parameter is null, it defaults to 1 due to value of currentPage parameter
        if (req.getParameter("page") != null) {
            currentPage = Integer.parseInt(req.getParameter("page"));
            offset = (currentPage - 1) * dataLimit;
        }

        if (currentPage < 0 || currentPage > totalPages) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return -1;
        } else {
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages", totalPages);
            return offset;
        }
    }
}
