package com.filmbooking.ultils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.IOException;

public class FileUploadUtils {

    public static void uploadFile(HttpServletRequest req, String filePath, String uploadElementName) {
        try {
            Part filePart = req.getPart(uploadElementName);
            filePart.write(filePath);

        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }




}
