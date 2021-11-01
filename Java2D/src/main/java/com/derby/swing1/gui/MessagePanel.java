package com.derby.swing1.gui;

import lombok.Builder;
import lombok.Data;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

@Builder
@Data
class ServerInfo {
    private int id;
    private String name;
    private String location;

    public String toString(){
        return name;
    }
}

public class MessagePanel extends JPanel {

    private JTree serverTree;

    public MessagePanel() {

        serverTree = new JTree(createTree());

        serverTree.getSelectionModel()
            .setSelectionMode(TreeSelectionModel
                .SINGLE_TREE_SELECTION);

        serverTree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node =
                (DefaultMutableTreeNode) serverTree
                    .getLastSelectedPathComponent();

            Object userObject = node.getUserObject();
            if(userObject instanceof String location) {
                System.out.println("Location: " + location);
            } else if(userObject instanceof ServerInfo server) {
                System.out.println("Server: " +
                    server.getLocation() + ", " +
                    server.getId() +
                    ": " + server.getName());
            }
        });

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
            new DefaultMutableTreeNode(ServerInfo.builder()
                .id(11).location("USA")
                .name("New York").build());
        DefaultMutableTreeNode serverBOSTON =
            new DefaultMutableTreeNode(ServerInfo.builder()
                .id(12).location("USA")
                .name("Boston").build());
        DefaultMutableTreeNode serverLA =
            new DefaultMutableTreeNode(ServerInfo.builder()
                .id(13).location("USA")
                .name("Los Angeles").build());

        locationUSA.add(serverNY);
        locationUSA.add(serverBOSTON);
        locationUSA.add(serverLA);

        DefaultMutableTreeNode locationUK =
            new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode serverLONDON =
            new DefaultMutableTreeNode(ServerInfo.builder()
                .id(21).location("UK")
                .name("London").build());
        DefaultMutableTreeNode serverEDINBURGH =
            new DefaultMutableTreeNode(ServerInfo.builder()
                .id(22).location("UK")
                .name("Edinburgh").build());

        locationUK.add(serverLONDON);
        locationUK.add(serverEDINBURGH);

        top.add(locationUSA);
        top.add(locationUK);

        return top;
    }
}
