package com.example.java2d;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {

    public GameScreen() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.ORANGE);
        g.fillRect(50, 50, 100, 100);
    }
}
