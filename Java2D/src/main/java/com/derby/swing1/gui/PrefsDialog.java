package com.derby.swing1.gui;

import javax.swing.*;

public class PrefsDialog extends JDialog {
    public PrefsDialog(JFrame parent) {
        super(parent,
                "Preferences",
                false);

        setSize(400,300);
    }
}
