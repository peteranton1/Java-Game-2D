package com.derby.swing1.gui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ServerTreeCellEditor
    extends AbstractCellEditor
    implements TreeCellEditor {

    private final ServerTreeCellRenderer renderer;
    private JCheckBox checkBox;
    private ServerInfo info;

    public ServerTreeCellEditor() {
        renderer = new ServerTreeCellRenderer();
    }

    @Override
    public Component getTreeCellEditorComponent(
        JTree tree, Object value,
        boolean isSelected, boolean expanded,
        boolean leaf, int row) {

        boolean hasFocus = true;
        Component component = renderer
            .getTreeCellRendererComponent(
            tree, value, isSelected,
                expanded, leaf, row, hasFocus
        );
        if(leaf){
            DefaultMutableTreeNode treeNode =
                (DefaultMutableTreeNode) value;

            info = (ServerInfo) treeNode.getUserObject();

            checkBox = (JCheckBox) component;
            ItemListener itemListener = new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    fireEditingStopped();
                    checkBox.removeItemListener(this);
                }
            } ;
            checkBox.addItemListener(itemListener);
        }
        return component;
    }

    @Override
    public Object getCellEditorValue() {
        info.setChecked(checkBox.isSelected());
        return info;
    }
}
