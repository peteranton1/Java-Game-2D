package com.derby.swing1.gui;

import com.derby.swing1.controller.MessageServer;
import com.derby.swing1.model.Message;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class MessagePanel extends JPanel {

    private final JTree serverTree;
    private ServerTreeCellRenderer treeCellRenderer;
    private ServerTreeCellEditor treeCellEditor;
    private ProgressDialog progressDialog;

    private Set<Integer> selectedServers;
    private MessageServer messageServer;

    public MessagePanel() {

        progressDialog = new ProgressDialog((Window)getParent());
        messageServer = new MessageServer();

        selectedServers = new TreeSet<>();
        selectedServers.add(0);
        selectedServers.add(1);
        selectedServers.add(4);

        treeCellRenderer = new ServerTreeCellRenderer();
        treeCellEditor = new ServerTreeCellEditor();

        serverTree = new JTree(createTree());
        serverTree.setCellRenderer(treeCellRenderer);
        serverTree.setCellEditor(treeCellEditor);
        serverTree.setEditable(true);

        serverTree.getSelectionModel()
            .setSelectionMode(TreeSelectionModel
                .SINGLE_TREE_SELECTION);

        treeCellEditor.addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                ServerInfo info = (ServerInfo)
                    treeCellEditor.getCellEditorValue();
                System.out.println(info + ": " +
                    info.getId() + ": checked: " +
                    info.isChecked());

                int serverId = info.getId();
                if (info.isChecked()) {
                    selectedServers.add(serverId);
                } else {
                    selectedServers.remove(serverId);
                }

                messageServer.setSelectedServers(selectedServers);

                retrieveMessages();

            }

            @Override
            public void editingCanceled(ChangeEvent e) {
                System.out.println("Cancelled");
            }
        });


        setLayout(new BorderLayout());

        add(new JScrollPane(serverTree),
            BorderLayout.CENTER);
    }

    private void retrieveMessages() {
        final int messageCount = messageServer.getMessageCount();
        System.out.println("messages waiting: " + messageCount);

        SwingUtilities.invokeLater(() -> {
            System.out.println("Showing modal");
            progressDialog.setVisible(true);
            System.out.println("Finished Showing modal");
        });

        SwingWorker<java.util.List<Message>, Integer> worker =
            new SwingWorker<>() {

                @Override
                protected void done() {
                    try {
                        java.util.List<Message> retrievedMessages = get();
                        System.out.println("retrieved " +
                            retrievedMessages.size() + " messages");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    progressDialog.setVisible(false);
                }

                @Override
                protected void process(java.util.List<Integer> counts) {
                    int retrieved = counts.get(counts.size() - 1);
                    System.out.println(" Got " + retrieved +
                        " of " + messageCount + " messages.");
                }

                @Override
                protected java.util.List<Message> doInBackground() {
                    java.util.List<Message> retrievedMessages =
                        new ArrayList<>();
                    int count = 0;
                    for (Message message : messageServer) {
                        System.out.print("Message: " +
                            message.getTitle());
                        retrievedMessages.add(message);
                        count++;
                        publish(count);
                    }
                    return retrievedMessages;
                }
            };
        worker.execute();

    }

    private DefaultMutableTreeNode createTree() {
        DefaultMutableTreeNode top =
            new DefaultMutableTreeNode("Servers");

        DefaultMutableTreeNode locationUSA =
            new DefaultMutableTreeNode("USA");
        DefaultMutableTreeNode serverNY =
            new DefaultMutableTreeNode(ServerInfo.builder()
                .id(0).location("USA")
                .name("New York")
                .checked(selectedServers.contains(0))
                .build());
        DefaultMutableTreeNode serverBOSTON =
            new DefaultMutableTreeNode(ServerInfo.builder()
                .id(1).location("USA")
                .name("Boston")
                .checked(selectedServers.contains(1))
                .build());
        DefaultMutableTreeNode serverLA =
            new DefaultMutableTreeNode(ServerInfo.builder()
                .id(2).location("USA")
                .name("Los Angeles")
                .checked(selectedServers.contains(2))
                .build());

        locationUSA.add(serverNY);
        locationUSA.add(serverBOSTON);
        locationUSA.add(serverLA);

        DefaultMutableTreeNode locationUK =
            new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode serverLONDON =
            new DefaultMutableTreeNode(ServerInfo.builder()
                .id(3).location("UK")
                .name("London")
                .checked(selectedServers.contains(3))
                .build());
        DefaultMutableTreeNode serverEDINBURGH =
            new DefaultMutableTreeNode(ServerInfo.builder()
                .id(4).location("UK")
                .name("Edinburgh")
                .checked(selectedServers.contains(4))
                .build());

        locationUK.add(serverLONDON);
        locationUK.add(serverEDINBURGH);

        top.add(locationUSA);
        top.add(locationUK);

        return top;
    }
}
