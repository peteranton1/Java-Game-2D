package com.derby.swing1.gui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

public class ServerTreeCellRenderer
    implements TreeCellRenderer {

    private final JCheckBox leafRenderer;
    private final DefaultTreeCellRenderer nonLeafRenderer;
    private final Color textForeground;
    private final Color textBackground;
    private final Color selectionForeground;
    private final Color selectionBackground;

    public ServerTreeCellRenderer() {
        leafRenderer = new JCheckBox();
        nonLeafRenderer = new DefaultTreeCellRenderer();

        nonLeafRenderer.setLeafIcon(IconUtils
            .createIcon("/images/Server16.gif"));
        nonLeafRenderer.setOpenIcon(IconUtils
            .createIcon("/images/WebComponent16.gif"));
        nonLeafRenderer.setClosedIcon(IconUtils
            .createIcon("/images/WebComponentAdd16.gif"));

        textForeground = UIManager
            .getColor("Tree.textForeground");
        textBackground = UIManager
            .getColor("Tree.textBackground");
        selectionForeground = UIManager
            .getColor("Tree.selectionForeground");
        selectionBackground = UIManager
            .getColor("Tree.selectionBackground");

    }

    @Override
    public Component getTreeCellRendererComponent(
        JTree tree, Object value, boolean selected,
        boolean expanded, boolean leaf, int row,
        boolean hasFocus) {

        if (leaf) {
            DefaultMutableTreeNode node =
                (DefaultMutableTreeNode) value;
            ServerInfo nodeInfo = (ServerInfo)
                node.getUserObject();

            if(selected) {
                leafRenderer.setForeground(selectionForeground);
                leafRenderer.setBackground(selectionBackground);
            } else {
                leafRenderer.setForeground(textForeground);
                leafRenderer.setBackground(textBackground);
            }

            leafRenderer.setText(nodeInfo.toString());
            leafRenderer.setSelected(nodeInfo.isChecked());
            return leafRenderer;
        }
        return nonLeafRenderer
            .getTreeCellRendererComponent(
                tree, value, selected,
                expanded, leaf, row,
                hasFocus);
    }
}
