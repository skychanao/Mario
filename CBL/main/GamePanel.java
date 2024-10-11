package main;

import entity.Player;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Display of the game.
 */
public class GamePanel extends JPanel implements Runnable {
    // size of the tile size, 16*16 pixles.
    final int originalTilesize = 16;
    // scaling of the character to increase the size of character shown on screen.
    final int scale = 3;
    // actual size of the tiles of the game
    final int tileSize = originalTilesize * scale;
    final int maxScreenRow = 15;
    final int maxScreenCol = 25;

    // Pixels of screensize displayed
    public final int screenHeight = tileSize * maxScreenRow;
    public final int screenWidth = tileSize * maxScreenCol;
    Thread thread;

    // size of player
    public final int playerWidth = 64;
    public final int playerHeight = 64;

    // FPS
    int fps = 60;

    // implement KeyboardHandler
    KeyboardHandler keyboard = new KeyboardHandler(this);
    Player player = new Player(this, keyboard);

    // player intial position
    // player X and Y resets after new frame
    public int playerX = screenWidth / 2;
    public int playerY = screenHeight / 2;
    public int velocityX = 0;
    public int velocityY = 0;
    public final int velocityJump = 30;
    public final int gravity = 1;
    public final int base = 510;

    /**
     * Constructor of the class.
     */
    public GamePanel() {

        // intialising window
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        // initialising user input
        this.addKeyListener(keyboard);
        this.setFocusable(true);

    }

    /**
     * Using a Thread to allow the game to continue to run.
     */
    public void startThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        // how often does the system draws a new frame;
        double interval = 1000000000 / fps;
        double nextDraw = System.nanoTime() + interval;

        // while the game loop is running
        while (thread != null) {

            // TODO update character position
            // TODO draw the map with updated screen??
            update();

            // when player reaches the edge of map, then repaint
            repaint();

            // sleep during the interval
            try {
                double remainingTime = (nextDraw - System.nanoTime());
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDraw += interval;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method is constantly called, every nanosecond.
     */
    public void update() {

        player.update();
    }

    /**
     * Method to allow 2D components to frame.
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        // make the Graphics g to 2D with extended functions
        Graphics g0 = (Graphics2D) g;
        Graphics g2 = (Graphics2D) g;
        //Graphics g3 = (Graphics2D) g;

        //setting background
        // g0.setColor(Color.LIGHT_GRAY);
        // g0.fillRect(0, 0, screenWidth, screenHeight);

        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Player/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g0.drawImage(image, 0, 0, screenWidth, screenHeight, null); 


        player.draw(g2);

        //drawing the base line
        // g3.setColor(Color.BLACK);
        // g3.fillRect(0, base + playerHeight, screenWidth, 5);

        // g0.dispose();
        // g3.dispose();

    }
}
