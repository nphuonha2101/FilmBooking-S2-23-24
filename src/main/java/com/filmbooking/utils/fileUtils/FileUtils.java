package com.filmbooking.utils.fileUtils;

import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Objects;

public class FileUtils {
    private File dir;

    public FileUtils(String directoryPath) {
        this.dir = new File(directoryPath);
        System.out.println(dir.toURI());
    }

    public int countDuplicateFile(String fileName) {
        int result = 0;

        if (Objects.requireNonNull(dir.listFiles()).length > 0)
            for (File fileInDirectory : dir.listFiles()) {
                if (fileInDirectory.getName().equalsIgnoreCase(fileName)) result++;
            }
        return result;
    }

    public boolean isValidFile(String fileName) {
        String[] validExtension = {".jpg", ".png"};
        FilenameFilter extensionFilter = new ExtensionFilter(validExtension);
        return extensionFilter.accept(dir, fileName);
    }

    public String handlesFileName(String fileName) {
        int duplicateFiles = countDuplicateFile(fileName);
        if (duplicateFiles > 0) {
            fileName = "Copy " + duplicateFiles + "-" + fileName;
        }
        return fileName;
    }

    /**
     * Get real context path of the project
     * the path will be used to save file to the server.
     * <br>
     * Example: E:/Java Workspace/FilmBooking-WebProgramming/
     * @param req HttpServletRequest use to get servlet context and get real path
     * @return a real context path of the project
     */
    public static String getRealContextPath(HttpServletRequest req) {
        String[] realContextPathArr = req.getServletContext().getRealPath("/").split("\\\\");

        System.out.println(Arrays.toString(realContextPathArr));

        StringBuilder realContextPath = new StringBuilder();
        for (int i = 0; i < realContextPathArr.length - 2; i++) {
            realContextPath.append(realContextPathArr[i]).append("/");
        }
        return realContextPath.toString();
    }


    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils("D:\\upload\\DoanQuocDang.png");

    }
}
