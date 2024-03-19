package com.filmbooking.utils.fileUtils;

import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Objects;

public class FileUtils {

    /**
     * Get deployed application path of the project
     * the path will be used to save file to the server.
     * <br>
     * Example: [E:/Java Workspace]/FilmBooking-WebProgramming/
     * @param req   HttpServletRequest use to get servlet context and get real path
     * @return      a real context path of the project
     */
    public static String getDeployedApplicationPath(HttpServletRequest req) {
        String[] realContextPathArr = req.getServletContext().getRealPath("/").split("\\\\");

        System.out.println(Arrays.toString(realContextPathArr));

        StringBuilder realContextPath = new StringBuilder();
        for (int i = 0; i < realContextPathArr.length - 2; i++) {
            realContextPath.append(realContextPathArr[i]).append("/");
        }
        return realContextPath.toString();
    }

    /**
     * Get real webapp path of the project
     * <br>
     * Example: [E:/Java Workspace]/FilmBooking-WebProgramming/src/main/webapp
     * @param req HttpServletRequest use to get servlet context and get real path
     * @return a real webapp path of the project
     */
    public static String getRealWebappPath(HttpServletRequest req) {
        return getDeployedApplicationPath(req) + "src/main/webapp";
    }

    public static void main(String[] args) {

    }
}
