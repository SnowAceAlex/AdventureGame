package main;

import javax.swing.*;

public class Main {

    public static JFrame window;

    public static void main(String[] args) {
        window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Thang Ngu Phieu Luu Ky");
//        window.setUndecorated(true);

        //icon
        ImageIcon icon = new ImageIcon("res/icon.png");
        window.setIconImage(icon.getImage());

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
