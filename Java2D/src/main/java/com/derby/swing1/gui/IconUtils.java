package com.derby.swing1.gui;

import javax.swing.*;
import java.net.URL;

public enum IconUtils {
    INSTANCE;

    public ImageIcon createIconInstance(String path) {
        URL url = getClass().getResource(path);
        if(url == null){
            System.err.println("Unable to locate: "+path);
            return null;
        }
        return new ImageIcon(url);
    }

    public static ImageIcon createIcon(String path) {
        return INSTANCE.createIconInstance(path);
    }

}
