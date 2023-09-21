package com.filmbooking.ultils;

public class ContextPathUltil {
    private static final String COMPONENT_PAGES_BASE_PATH = "/views/components/";
    private static final String CLIENT_PAGES_BASE_PATH = "/views/pages/client/";
    private static final String ADMIN_PAGES_BASE_PATH = "/views/pages/admin/";
    private static final String LAYOUT_BASE_PATH = "/views/layout/";

    public static String getComponentPagesPath(String page) {
        return COMPONENT_PAGES_BASE_PATH + page;
    }

    public static String getClientPagesPath(String page) {
        return CLIENT_PAGES_BASE_PATH + page;
    }

    public static String getAdminPagesPath(String page) {
        return ADMIN_PAGES_BASE_PATH + page;
    }

    public static String getLayoutPath(String layout) {
        return LAYOUT_BASE_PATH + layout;
    }
}
