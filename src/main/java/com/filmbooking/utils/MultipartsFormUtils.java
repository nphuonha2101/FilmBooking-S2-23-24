package com.filmbooking.utils;

import com.filmbooking.utils.fileUtils.FileUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
public class MultipartsFormUtils {
    private HttpServletRequest request;

    public MultipartsFormUtils(HttpServletRequest request) {
        this.request = request;
    }

    public Map<String, String> getFormFields(List<String> fields) {
        Map<String, String> result = new HashMap<>();

        for (String field: fields) {
            try {
              Part part = request.getPart(field);
              result.put(field, getPartStringContent(part));
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        return result;
    }

    public boolean uploadFile(String filePartName) {
        return true;
    }


    private String getPartStringContent(Part part) throws IOException {
        InputStream partInputStream = part.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(partInputStream, StandardCharsets.UTF_8));
        StringBuilder result = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null ) {
            result.append(line);
        }
        reader.close();

        return result.toString();
    }
}
