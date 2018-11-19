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
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Dawid
 */
public class Game extends JPanel implements Runnable {

    //
    public static final int WIDTH = 800, HEIGHT = 800, blockSize = 20;
    private Thread thread;
    private int ticks = 0;
    private movementHandler key;

    // Game state
    private boolean running = false;

    // Variables that are defying snake
    private BodyPart b;
    private ArrayList<BodyPart> snakeBody;
    private int snakeSize = 4;
    private int xC, yC;

    // Snake directions
    private boolean right = true, left = false, up = false, down = false;

    //Food
    private Food food;
    private Random random;
    // Vision
    private Vision[] vision;
    private boolean enableVision;
    private boolean drawVision;
    
    public Game() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        key = new movementHandler();
        addKeyListener(key);
        snakeBody = new ArrayList<>();
        food = null;
        random = new Random();
        createVision();
        enableVision= false;
        drawVision = false;
        start();
    }

    public void start() {
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
        
    }
    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loop() {
        createFood();
        checkFoodCollected();
        
        ticks++;
        if (ticks >300000) { 
       
        checkBodyCollisions();
        checkBorderCollisions();
        
        if(enableVision){
        checkDanger();
        checkFood();
        checkBody();
        }//500000 zmienic na jakas stala wynikajaca z procesora/czasu systemu dla stalych FPS
            steering();
            ticks = 0;
            createSnake();
            createVision();
        }
    }
  
    
    //--------------------VISION--------------------
    public void createVision(){
        vision = new Vision[6];
            if (right) {
            vision[0] = new Vision(xC,yC+1,blockSize);
            vision[1] = new Vision(xC,yC+2,blockSize);
            vision[2] = new Vision(xC+1,yC,blockSize);
            vision[3] = new Vision(xC+2,yC,blockSize);
            vision[4] = new Vision(xC,yC-1,blockSize);
            vision[5] = new Vision(xC,yC-2,blockSize);
            }
            if (left) {
            vision[0] = new Vision(xC,yC-1,blockSize);
            vision[1] = new Vision(xC,yC-2,blockSize);
            vision[2] = new Vision(xC-1,yC,blockSize);
            vision[3] = new Vision(xC-2,yC,blockSize);
            vision[4] = new Vision(xC,yC+1,blockSize);
            vision[5] = new Vision(xC,yC+2,blockSize);
            }
            if (up) {
            vision[0] = new Vision(xC-1,yC,blockSize);
            vision[1] = new Vision(xC-2,yC,blockSize);
            vision[2] = new Vision(xC,yC-1,blockSize);
            vision[3] = new Vision(xC,yC-2,blockSize);
            vision[4] = new Vision(xC+1,yC,blockSize);
            vision[5] = new Vision(xC+2,yC,blockSize);
            }
            if (down) {
            vision[0] = new Vision(xC+1,yC,blockSize);
            vision[1] = new Vision(xC+2,yC,blockSize);
            vision[2] = new Vision(xC,yC+1,blockSize);
            vision[3] = new Vision(xC,yC+2,blockSize);
            vision[4] = new Vision(xC-1,yC,blockSize);
            vision[5] = new Vision(xC-2,yC,blockSize);
            }
        
    }
    public void checkDanger(){
        for(int i=0;i<vision.length;i++){
        if(vision[i].getxCoordinate() < 0 || vision[i].getxCoordinate() > (WIDTH / blockSize) - 1 ||
           vision[i].getyCoordinate() < 0 || vision[i].getyCoordinate() > (WIDTH / blockSize) - 1) {     
            for(int j =0; j<snakeBody.size();j++)
            snakeBody.get(j).setNearWall();
        } 
        }
    } 
    public void checkBody(){
        for (int i = 0; i < snakeBody.size(); i++) {
            for(int j=0;j<vision.length;j++){
                if(vision[j].getxCoordinate() == snakeBody.get(i).getxCoordinate() && 
                   vision[j].getyCoordinate() == snakeBody.get(i).getyCoordinate()){       
                   for(int k =0; k<snakeBody.size();k++)
                   snakeBody.get(k).setNearBody();  
                }
            }  
        }
    }
    public void checkFood() {
        for (int i = 0; i < vision.length; i++) {
            if(food!=null){
            if (vision[i].getxCoordinate() == food.getxCoordinate() && vision[i].getyCoordinate() == food.getyCoordinate()) {
            for(int j =0; j<snakeBody.size();j++)
            snakeBody.get(j).setNearFood();    
            }
            }
        }
    }
    //--------------------SNAKE--------------------
    public void createSnake() {
        b = new BodyPart(xC, yC, blockSize);
        snakeBody.add(b);

        if (snakeBody.size() > snakeSize) {
            snakeBody.remove(0);
        }
    }  
    //--------------------FOOD--------------------
    public void createFood(){
        if(food == null){
            int x = random.nextInt((WIDTH / blockSize) - 1);
            int y = random.nextInt((WIDTH / blockSize) - 1);
            food = new Food(x, y, blockSize);
        }  
    }
    public void checkFoodCollected(){
        if(xC == food.getxCoordinate() && yC== food.getyCoordinate()){
            snakeSize++;
            food = null;         
        }
    }
    //--------------------COLLISIONS--------------------
    public void checkBorderCollisions(){
        if (xC < 0 || xC > (WIDTH / blockSize) - 1 || yC < 0 || yC > (WIDTH / blockSize) - 1) {
            stop();
        }
    }
    public void checkBodyCollisions(){
        for (int i = 0; i < snakeBody.size(); i++) {
            if (xC == snakeBody.get(i).getxCoordinate() && yC == snakeBody.get(i).getyCoordinate()) {
                if (i != snakeBody.size() - 1) {
                    stop();
                }
            }
        }
    }
    //--------------------DRAW--------------------
    public void paint(Graphics g) {
        // Clear
        g.clearRect(0, 0, WIDTH, HEIGHT);
        // Fill background
        g.setColor(new Color(3421752));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        // Draw vision
        if(drawVision){
        for(int i =0; i< vision.length;i++){
            if(vision[i]!=null)
            vision[i].draw(g);
        }
        }
        // Draw snakeBody;
        g.setColor(new Color(16737792));
        for (int i = 0; i < snakeBody.size(); i++) {
            if(snakeBody.get(i)!=null)
            snakeBody.get(i).draw(g);
        }



        // Draw food
        if(food!=null)
        food.draw(g);
        // Draw x-axis grid 
        g.setColor(new Color(1052945));
        for (int i = 0; i < WIDTH / blockSize; i++) {
            g.drawLine(i * blockSize, 0, i * blockSize, HEIGHT);
        }
        // Draw y-axis grid 
        for (int i = 0; i < HEIGHT / blockSize; i++) {
            g.drawLine(0, i * blockSize, WIDTH, i * blockSize);
        }
    }

    //Runnable class abstract method
    @Override
    public void run() {
        while (running) {
            loop();
            repaint();
        }
    }

    //--------------------STEERING--------------------
      public void steering(){
         if (right) xC++;
         if (left)  xC--;
         if (up)    yC--;
         if (down)  yC++;
    }
    private class movementHandler implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    if (!up) {
                        down = true;
                        left = false;
                        right = false;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (!down) {
                        up = true;
                        right = false;
                        left = false;
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (!right) {
                        down = false;
                        up = false;
                        left = true;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (!left) {
                        down = false;
                        up = false;
                        right = true;
                    }
                    break;
                case KeyEvent.VK_V:
                    enableVision = !enableVision;
                    break;
                case KeyEvent.VK_S:
                    drawVision = !drawVision;
                    break;
                case KeyEvent.VK_SPACE:
                    xC = 0;
                    yC = 0;
                    snakeSize = 4;
                    snakeBody.removeAll(snakeBody);
                    right = true;
                    up = false;
                    food = null;
                    down = false;
                    left = false;
                    start();
                    break;
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
