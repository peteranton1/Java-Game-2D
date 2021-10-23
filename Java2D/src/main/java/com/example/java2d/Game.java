package com.example.java2d;

import javax.swing.*;

public class Game extends JFrame {

    private GameScreen gameScreen;

    public Game() {
        int width = 400;
        int height = 400;
        setSize(width, height);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(null);
        gameScreen = new GameScreen();
        add(gameScreen);
    }

    public static void main(String[] args) {
        System.out.println("Java2D Game");
        Game game = new Game();
    }
}
