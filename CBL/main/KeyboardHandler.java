package main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 * 
 */
public class KeyboardHandler implements KeyListener {

    public boolean foward;
    public boolean backward;
    public boolean up;
    public boolean down;
    public boolean falling;
    public boolean jumping;

    private GamePanel panel;

    /**
     * Constructor of the class.
     * 
     * @param panel to create object of panel class and use variables of the object
     */

    public KeyboardHandler(GamePanel panel) {
        this.panel = panel;
        panel.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            panel.velocityX = -5;
        }
        if (code == KeyEvent.VK_D) {
            panel.velocityX = 5;

        }
        if ((code == KeyEvent.VK_SPACE) && panel.velocityY == 0) {
            panel.velocityY = -panel.velocityJump;
        }

        // consider gravity
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_D) {
            panel.velocityX = 0;
        }
        if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_W) {
            panel.velocityY = 0;
            // consider gravity
        }
        if (code == KeyEvent.VK_S) {
            panel.velocityY = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
