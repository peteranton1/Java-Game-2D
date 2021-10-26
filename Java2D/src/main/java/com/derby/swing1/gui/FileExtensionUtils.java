package com.derby.swing1.gui;

public class FileExtensionUtils {

    public static String getFileExtension(String name) {
        int pointIndex = name.lastIndexOf(".");
        if (pointIndex == -1) {
            return null;
        }
        if (pointIndex == name.length() - 1) {
            return null;
        }
        return name.substring(pointIndex);
    }
}
