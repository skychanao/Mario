package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import main.GamePanel;
import main.KeyboardHandler;

/**
 * comments.
 */
public class Player extends Entity {

    GamePanel gp;
    KeyboardHandler keyH;

    /**
     * temp.
     * 
     * @param gp   objects of class Gamepanel.
     * @param keyH object of class KeyboardHandler
     */
    public Player(GamePanel gp, KeyboardHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getImage();
    }

    /**
     * 
     */
    public void setDefaultValues() {

        x = gp.screenWidth / 2;
        y = gp.screenHeight / 2;
        // set default directon - for different images;
    }

    public void getImage() {
        try {

            up = ImageIO.read(getClass().getResourceAsStream("/Player/up.jpg"));
            down = ImageIO.read(getClass().getResourceAsStream("/Player/down.png"));
            foward = ImageIO.read(getClass().getResourceAsStream("/Player/right.jpg"));
            backward = ImageIO.read(getClass().getResourceAsStream("/Player/left.png"));
            still = ImageIO.read(getClass().getResourceAsStream("/Player/pngegg.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    public void update() {

        if (gp.velocityX == 0 && gp.velocityY == 0) {
            direction = "still";
        }
        // when palyer is not at the left border of the screen
        if (x > 0 && x <= gp.screenWidth) {
            x += gp.velocityX;
            if (gp.velocityX > 0) {
                direction = "foward";
            } else if (gp.velocityX < 0) {
                direction = "backward";
            }
        }
        // when player is at the left border of the screen, the player can only move to
        // the right
        if (x == 0 && gp.velocityX > 0) {
            x += gp.velocityX;
            direction = "foward";
        }

        if (gp.velocityY < 0) {
            direction = "up";
        }
        if (gp.velocityY > 0) {
            direction = "down";
        }
        y += gp.velocityY;

        // gravity on player when player is above the base, i.e. the floor.
        if (y < gp.base) {
            gp.velocityY += 2; // TODO make x,Y veloicty double if gravity is too big
        } else {
            y = gp.base;
            gp.velocityY = 0;
        }
    }

    /**
     * 
     */
    public void draw(Graphics g2) {
        BufferedImage image = null;
        if (direction.equals("up")) {
            image = up;
        } else if (direction.equals("down")) {
            image = down;
        } else if (direction.equals("foward")) {
            image = foward;
        } else if (direction.equals("backward")) {
            image = backward;
        } else {
            image = still;
        }

        g2.drawImage(image, x, y, gp.playerWidth, gp.playerHeight, null);
    }

}
