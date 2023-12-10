package com.filmbooking.utils;

public class PaginationUtils {

    public static int getOffset(int currentPage, int limit) {
        return (currentPage - 1) * limit;
    }

    public static int getTotalPages(int totalRecords, int limit) {
        return (int) Math.ceil((double) totalRecords / limit);
    }

}
