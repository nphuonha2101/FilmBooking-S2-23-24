package com.filmbooking.utils.fileUtils;

import com.filmbooking.utils.WebAppPathUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.IOException;

public class FileUploadUtils {

    public static boolean uploadFile(HttpServletRequest req, String fileName, String uploadElementName) {
        try {
            String realFilePath = FileUtils.getDeployedApplicationPath(req) + WebAppPathUtils.getFileUploadPath(fileName);

            System.out.println(realFilePath);
            Part filePart = req.getPart(uploadElementName);

            if (filePart == null) {
                System.out.println("File part is null");
                return false;
            }
  
            // write file to real directory
            filePart.write(realFilePath);
            return true;

        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }


}
