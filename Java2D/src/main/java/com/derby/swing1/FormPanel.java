package com.derby.swing1;

import javax.swing.*;
import java.awt.*;

public class FormPanel extends JPanel {
    public FormPanel() {
        Dimension dim = getPreferredSize();
        System.out.println(dim);
        dim.width = 250;
        setPreferredSize(dim);
    }
}
