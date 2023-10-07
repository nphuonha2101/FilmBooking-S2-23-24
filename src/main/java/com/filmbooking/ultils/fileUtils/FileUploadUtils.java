package com.filmbooking.ultils.fileUtils;

import com.filmbooking.ultils.ContextPathUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.IOException;

public class FileUploadUtils {

    public static void uploadFile(HttpServletRequest req, String fileName, String uploadElementName) {
        try {
            String realFilePath = FileUtils.getRealContextPath(req) + ContextPathUtils.getFileUploadPath(fileName);

            // create upload folder if not exist!
//            File uploadDir = new File(FileUtils.getRealContextPath(req) + ContextPathUtils.getUploadFolderPath());

            System.out.println("TEST UPLOAD UTILS: " + realFilePath);
            System.out.println("TEST UPLOAD UTILS: " + fileName);
            Part filePart = req.getPart(uploadElementName);
            filePart.write(realFilePath);

        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }


}
