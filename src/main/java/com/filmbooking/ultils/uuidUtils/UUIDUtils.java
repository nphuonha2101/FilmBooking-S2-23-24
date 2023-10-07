package com.filmbooking.ultils.uuidUtils;

import java.util.UUID;

public class UUIDUtils {
    public static String generateRandomUUID(String fileName) {
        int lastIndexOfSeparate = fileName.lastIndexOf(".");

        String extension = fileName.substring(lastIndexOfSeparate);
        UUID uuid = UUID.randomUUID();
        return uuid + extension;
    }

    public static void main(String[] args) {
        System.out.println(UUIDUtils.generateRandomUUID("test.jpg"));
    }
}
