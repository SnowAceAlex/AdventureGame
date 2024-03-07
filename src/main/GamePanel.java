package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;

public class GamePanel extends JPanel implements Runnable{

    //Screen Settings
    final int originalTileSize = 16; // 16 x 16 tile
    final int scale = 4;
    public final int tileSize = originalTileSize * scale; // 48 x 48 tile
    final int maxScreenCols = 16;
    final int maxScreenRows = 12;
    final int screenWidth = tileSize * maxScreenCols; // 768 pixels
    final int screenHeight = tileSize * maxScreenRows; // 576 pixels

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    //Player default position
    int playerX = 100;
    int playerY= 100;
    int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    //cách 1
//    public void run() {
//        while(gameThread != null){
//
//            double drawInterval = 1000000000/FPS; //0.016666666 seconds
//            double nextDrawTime = System.nanoTime() + drawInterval;
//
//            //Update : update the information such as character position
//            update();
//            //Draw : draw the screen with the updated information;
//            repaint();
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime /= 1000000;
//
//                if(remainingTime < 0){
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    // Cách 2
    public void run(){

        double drawInterval = 1000000000/FPS; //0.016666666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        g2.dispose();
    }
}
