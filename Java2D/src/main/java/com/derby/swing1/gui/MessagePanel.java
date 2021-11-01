package com.derby.swing1.gui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class MessagePanel extends JPanel {

    private JTree serverTree;

    public MessagePanel() {

        serverTree = new JTree(createTree());

        setLayout(new BorderLayout());

        add(new JScrollPane(serverTree),
            BorderLayout.CENTER);
    }

    private DefaultMutableTreeNode createTree() {
        DefaultMutableTreeNode top =
            new DefaultMutableTreeNode("Servers");

        DefaultMutableTreeNode locationUSA =
            new DefaultMutableTreeNode("USA");
        DefaultMutableTreeNode serverNY =
            new DefaultMutableTreeNode("New York");
        DefaultMutableTreeNode serverBOSTON =
            new DefaultMutableTreeNode("Boston");
        DefaultMutableTreeNode serverLA =
            new DefaultMutableTreeNode("Los Angeles");

        locationUSA.add(serverNY);
        locationUSA.add(serverBOSTON);
        locationUSA.add(serverLA);

        DefaultMutableTreeNode locationUK =
            new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode serverLONDON =
            new DefaultMutableTreeNode("London");
        DefaultMutableTreeNode serverEDINBURGH =
            new DefaultMutableTreeNode("Edinburgh");

        locationUK.add(serverLONDON);
        locationUK.add(serverEDINBURGH);

        top.add(locationUSA);
        top.add(locationUK);

        return top;
    }
}
