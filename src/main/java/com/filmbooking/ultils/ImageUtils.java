package com.filmbooking.ultils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ImageUtils {
    // reference from: https://stackoverflow.com/questions/3211156/how-to-convert-image-to-byte-array-in-java
    public static byte[] convertImageToBytes(String imagePath) {
        File imgFile = new File(imagePath);

        try {
            return Files.readAllBytes(imgFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
