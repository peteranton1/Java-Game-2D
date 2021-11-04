package com.derby.swing1.gui;

import javax.swing.*;
import java.awt.*;

public class ProgressDialog extends JDialog {

    public ProgressDialog(Window parent) {
        super(parent, "Messages Downloading...",
            Dialog.ModalityType.APPLICATION_MODAL);

        setSize(400, 200);
    }

}
