package com.filmbooking.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WebAppPathUtils {
    private static final String COMPONENT_PAGES_BASE_PATH = "/views/components/";
    private static final String CLIENT_PAGES_BASE_PATH = "/views/pages/client/";
    private static final String ADMIN_PAGES_BASE_PATH = "/views/pages/admin/";
    private static final String LAYOUT_BASE_PATH = "/views/layout/";
    private static final String ERROR_PAGES_BASE_PATH = "/views/error/";
    private static final String UPLOAD_FOLDER_PATH = "src/main/webapp/resources/images/upload/";
    private static final String UPLOAD_FOLDER_RELATIVE_PATH = "/resources/images/upload/";
    private static final String STYLE_SHEETS_PATH = "/resources/css/";

    /**
     * Get path to components' jsp such as: navigation-bar.jsp, footer.jsp, ...
     *
     * @param page components' page such as:  navigation-bar.jsp, footer.jsp, ...
     * @return path to these page (relative path)
     */
    public static String getComponentPagesPath(String page) {
        return COMPONENT_PAGES_BASE_PATH + page;
    }


    /**
     * Get path to client's jsp such as: home.jsp, login.jsp, ...
     *
     * @param page client's page such as:  home.jsp, login.jsp, ...
     * @return path to these page (relative path)
     */
    public static String getClientPagesPath(String page) {
        return CLIENT_PAGES_BASE_PATH + page;
    }

    /**
     * Get path to administrator's jsp such as: film-management.jsp, edit-film.jsp, ...
     *
     * @param page administrator's page such as:  film-management.jsp, edit-film.jsp, ...
     * @return path to these page (relative path)
     */
    public static String getAdminPagesPath(String page) {
        return ADMIN_PAGES_BASE_PATH + page;
    }

    /**
     * Get path to layout's jsp such as: master.jsp, ...
     *
     * @param layout layout's page such as:  master.jsp, ...
     * @return path to these page (relative path)
     */
    public static String getLayoutPath(String layout) {
        return LAYOUT_BASE_PATH + layout;
    }

    /**
     * Get path to upload folder. Start from src folder to write file with File Part
     *
     * @param fileName name of file
     * @return path to upload folder. Ex: src/main/webapp/resources/images/upload/{fileName}
     */
    public static String getFileUploadPath(String fileName) {
        return UPLOAD_FOLDER_PATH + fileName;
    }

    public static String getUploadFolderPath() {
        return UPLOAD_FOLDER_PATH;
    }

    /**
     * Get path to upload folder. Start from webapp folder to display file with URL in JSP
     *
     * @param fileName name of file
     * @return path to upload folder. Ex: /resources/images/upload/{fileName}
     */
    public static String getUploadFileRelativePath(String fileName) {
        return UPLOAD_FOLDER_RELATIVE_PATH + fileName;
    }

    /**
     * Get path to error's jsp such as: 404.jsp, 500.jsp, ...
     *
     * @param page error's page such as:  404.jsp, 500.jsp, ...
     * @return path to these page (relative path)
     */
    public static String getErrorPagesPath(String page) {
        return ERROR_PAGES_BASE_PATH + page;
    }

    /**
     * Get path to style sheets
     * @param styleSheet style sheet name such as: stylesheet.css, ...
     * @return path to style sheet
     */
    public static String getStyleSheetsPath(String styleSheet) {
        return STYLE_SHEETS_PATH + styleSheet;
    }

    /**
     * Get URL with full application context path
     *
     * @param req to get Application context path
     * @param url url must start with /. Ex: "/home"
     * @return url with context path with encoded. Ex: FilmBooking-WebProgramming/home
     */
    public static String getURLWithContextPath(HttpServletRequest req, HttpServletResponse resp, String url) {
        String result = "";
        if (req.getContextPath() == null)
            result = url;
        else
            result = req.getContextPath() + url;

        return resp.encodeURL(result);

    }
}
