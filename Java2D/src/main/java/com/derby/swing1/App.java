package com.derby.swing1;

import javax.swing.*;

public class App {
    public static void main(String[] args) {

        SwingUtilities
                .invokeLater(MainFrame::new);

    }
}
