package com.filmbooking.ultils;

import java.io.File;
import java.io.FilenameFilter;

public class ExtensionFilter implements FilenameFilter {
    private String[] extensions;

    ExtensionFilter(String[] extensions) {
        this.extensions = extensions;
    }

    @Override
    public boolean accept(File dir, String name) {
        for (String extension : extensions
        ) {
            if (name.toLowerCase().endsWith(extension))
                return true;
        }
        return false;
    }
}
