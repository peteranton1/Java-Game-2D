package com.derby.swing1.gui;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class PersonFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {

        if(f.isDirectory()){
            return true;
        }

        String name = f.getName();

        String extension = FileExtensionUtils
                .getFileExtension(name);

        return ".per".equals(extension);
    }

    @Override
    public String getDescription() {
        return "Person database files (*.per)";
    }
}
