package com.filmbooking.utils;

public class ContextPathUtils {
    private static final String COMPONENT_PAGES_BASE_PATH = "/views/components/";
    private static final String CLIENT_PAGES_BASE_PATH = "/views/pages/client/";
    private static final String ADMIN_PAGES_BASE_PATH = "/views/pages/admin/";
    private static final String LAYOUT_BASE_PATH = "/views/layout/";
    private static final String ERROR_PAGES_BASE_PATH = "/views/error/";
    private static final String UPLOAD_FOLDER_PATH = "src/main/webapp/resources/images/upload/";
    private static final String UPLOAD_FOLDER_RELATIVE_PATH = "/resources/images/upload/";

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

    public static String getFileUploadPath(String fileName) {
        return UPLOAD_FOLDER_PATH + fileName;
    }
    public static String getUploadFolderPath() {
        return UPLOAD_FOLDER_PATH;
    }

    public static String getUploadFileRelativePath(String fileName) {
        return UPLOAD_FOLDER_RELATIVE_PATH + fileName;
    }
    public static String getErrorPagesPath(String page) {
        return ERROR_PAGES_BASE_PATH + page;
    }

}
