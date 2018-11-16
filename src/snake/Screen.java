/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Dawid
 */
public class Screen extends JPanel implements Runnable {

    //
    public static final int WIDTH = 800, HEIGHT = 800, blockSize = 20;
    private Thread thread;
    private boolean running = false;

    //Variables that are defying snake
    private BodyPart b;
    private ArrayList<BodyPart> snakeBody;
    private int snakeSize = 4;
    private int xC, yC = blockSize;

    private boolean right = true, left = false, up = false, down = false;
    private int ticks = 0;
    private movementHandler key;
    public Screen() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        key = new movementHandler();
        addKeyListener(key);
        snakeBody = new ArrayList<>();
        start();
    }

    public void start() {
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
    }

    public void stop() {

    }

    public void tick() {
        //Length of the size = 0, create new snake body
        if (snakeBody.size() == 0) {
            b = new BodyPart(xC, yC, blockSize);
            snakeBody.add(b);
        }
        ticks++;
        if (ticks > 900000) {
            if (right) {
                xC++;
            }
            if (left) {
                xC--;
            }
            if (up) {
                yC--;
            }
            if (down) {
                yC++;
            }

            ticks = 0;
            b = new BodyPart(xC, yC, blockSize);
            snakeBody.add(b);

            if (snakeBody.size() > snakeSize) {
                snakeBody.remove(0);
            }
        }
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        // Fill background
        g.setColor(new Color(3421752));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw snakeBody;
        g.setColor(Color.WHITE);
        for (int i = 0; i < snakeBody.size(); i++) {
            snakeBody.get(i).draw(g);
        }

        g.setColor(new Color(1052945));
        for (int i = 0; i < WIDTH / blockSize; i++) {
            g.drawLine(i * blockSize, 0, i * blockSize, HEIGHT);
        }
        for (int i = 0; i < HEIGHT / blockSize; i++) {
            g.drawLine(0, i * blockSize, WIDTH, i * blockSize);
        }

    }

    //Runnable class
    @Override
    public void run() {
        while (running) {
            tick();
            repaint();
        }
    }

    private class movementHandler implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_DOWN: 
                    if(!up){
                    down = true;
                    up = false;
                    right = false;
                    } break;
                case KeyEvent.VK_UP: 
                    if(!down){
                    up = true;
                    right = false;
                    left = false;
                    } break;
                case KeyEvent.VK_LEFT: 
                    if(!right){
                    down = false;
                    up = false;
                    left = true;
                    } break;    
                case KeyEvent.VK_RIGHT: 
                    if(!left){
                    down = false;
                    up = false;
                    right = true;
                    } break;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }
}
