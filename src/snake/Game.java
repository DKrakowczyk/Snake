package snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Dawid
 */
public class Game extends JPanel implements Runnable {

    // Variables for main game board
    public Frame frame;
    public static final int WIDTH = 800, HEIGHT = 800, blockSize = 20;
    
    // Game state
    private Thread thread;
    private boolean running;
    private int ticks;
    private int gameSpeed;
    //Keyboard input
    private movementHandler key;

    //Snake
    private ArrayList<BodyPart> snakeBody;
    private BodyPart b;
    protected int snakeSize;
    protected int xC, yC; //Snake head position
    protected boolean right = true, left = false, up = false, down = false;  //Snake directions
    
    //Food
    protected Food food;
    private Random random;
    
    // Booleans for different options
    private Vision[] vision;
    private boolean enableVision;
    private boolean drawVision;
    private boolean enableHamiltonWithShortcuts;
    private boolean enableHamilton;
    private boolean simplePathfinder;
    
    protected boolean hasEaten = false;
    
    //Score variables
    int score;
    private int countRuns;
    private int globalScore;
    

    public Game() {
        setPreferredSize(new Dimension(1000, HEIGHT));
        setFocusable(true);
        key = new movementHandler();
        addKeyListener(key);
        snakeBody = new ArrayList<>();
        food = null;
        random = new Random();
        running = false;
        gameSpeed = 200000;
        ticks = 0;
        snakeSize = 4;
        score = 0;
        countRuns = 1;
        globalScore = 0;
        enableVision = false;
        drawVision = false;
        enableHamiltonWithShortcuts = false;
        enableHamilton = false;
        simplePathfinder = false;
    }

    public void loop() {
        createFood();
        checkFoodCollected();

        ticks++;
        if (ticks > gameSpeed) {
            if (enableHamiltonWithShortcuts && !simplePathfinder && !enableHamilton) {
                AI.HamiltonCycleShortcuts(this);
            }
            if (!enableHamiltonWithShortcuts && !simplePathfinder && enableHamilton) {
                AI.HamiltonCycle(this);
            }
            if (!enableHamiltonWithShortcuts && simplePathfinder && !enableHamilton) {
                AI.simpleFoodFinding(this);
            }
             System.out.println(gameSpeed);
            checkBodyCollisions();
            checkBorderCollisions();
            if (enableVision) {
                checkDanger();
                checkFood();
                checkBody();
            }
            steering();
            ticks = 0;
            createSnake();
            createVision();
        }
    }

    //------------------------------------------VISION------------------------------------------
    public void createVision() {
        vision = new Vision[6];
        if (right) {
            vision[0] = new Vision(xC, yC + 1, blockSize);
            vision[1] = new Vision(xC, yC + 2, blockSize);
            vision[2] = new Vision(xC + 1, yC, blockSize);
            vision[3] = new Vision(xC + 2, yC, blockSize);
            vision[4] = new Vision(xC, yC - 1, blockSize);
            vision[5] = new Vision(xC, yC - 2, blockSize);
        }
        if (left) {
            vision[0] = new Vision(xC, yC - 1, blockSize);
            vision[1] = new Vision(xC, yC - 2, blockSize);
            vision[2] = new Vision(xC - 1, yC, blockSize);
            vision[3] = new Vision(xC - 2, yC, blockSize);
            vision[4] = new Vision(xC, yC + 1, blockSize);
            vision[5] = new Vision(xC, yC + 2, blockSize);
        }
        if (up) {
            vision[0] = new Vision(xC - 1, yC, blockSize);
            vision[1] = new Vision(xC - 2, yC, blockSize);
            vision[2] = new Vision(xC, yC - 1, blockSize);
            vision[3] = new Vision(xC, yC - 2, blockSize);
            vision[4] = new Vision(xC + 1, yC, blockSize);
            vision[5] = new Vision(xC + 2, yC, blockSize);
        }
        if (down) {
            vision[0] = new Vision(xC + 1, yC, blockSize);
            vision[1] = new Vision(xC + 2, yC, blockSize);
            vision[2] = new Vision(xC, yC + 1, blockSize);
            vision[3] = new Vision(xC, yC + 2, blockSize);
            vision[4] = new Vision(xC - 1, yC, blockSize);
            vision[5] = new Vision(xC - 2, yC, blockSize);
        }

    }

    public void checkDanger() {
        for (int i = 0; i < vision.length; i++) {
            if (vision[i].getxCoordinate() < 0 || vision[i].getxCoordinate() > (WIDTH / blockSize) - 1
                    || vision[i].getyCoordinate() < 0 || vision[i].getyCoordinate() > (WIDTH / blockSize) - 1) {
                for (int j = 0; j < snakeBody.size(); j++) {
                    snakeBody.get(j).setNearWall();
                }
            }
        }
    }

    public void checkBody() {
        for (int i = 0; i < snakeBody.size(); i++) {
            for (int j = 0; j < vision.length; j++) {
                if (vision[j].getxCoordinate() == snakeBody.get(i).getxCoordinate()
                        && vision[j].getyCoordinate() == snakeBody.get(i).getyCoordinate()) {
                    for (int k = 0; k < snakeBody.size(); k++) {
                        snakeBody.get(k).setNearBody();
                    }
                }
            }
        }
    }

    public void checkFood() {
        for (int i = 0; i < vision.length; i++) {
            if (food != null) {
                if (vision[i].getxCoordinate() == food.getxCoordinate() && vision[i].getyCoordinate() == food.getyCoordinate()) {
                    for (int j = 0; j < snakeBody.size(); j++) {
                        snakeBody.get(j).setNearFood();
                    }
                }
            }
        }
    }

    //------------------------------------------SNAKE------------------------------------------
    public void createSnake() {
        b = new BodyPart(xC, yC, blockSize);
        snakeBody.add(b);

        if (snakeBody.size() > snakeSize) {
            snakeBody.remove(0);
        }
    }

    //------------------------------------------FOOD------------------------------------------
    public void createFood() {
        int x = 0, y = 0;
        if (food == null) {
            boolean isUnique = false;
            while (!isUnique) {
                isUnique = true;
                x = random.nextInt((WIDTH / blockSize));
                y = random.nextInt((WIDTH / blockSize));
                for (BodyPart part : snakeBody) {
                    if (part.getxCoordinate() == x && part.getyCoordinate() == y) {
                        isUnique = false;
                    }
                }
            }
            food = new Food(x, y, blockSize);
        }
    }

    public void checkFoodCollected() {
        if (xC == food.getxCoordinate() && yC == food.getyCoordinate()) {
            snakeSize++;
            score++;
            globalScore++;
            food = null;
        }
    }

    //------------------------------------------COLLISIONS------------------------------------------
    public void checkBorderCollisions() {
        if (xC < 0 || xC > (WIDTH / blockSize) - 1 || yC < 0 || yC > (WIDTH / blockSize) - 1) {
            stop();
        }
    }

    public void checkBodyCollisions() {
        for (int i = 0; i < snakeBody.size(); i++) {
            if (xC == snakeBody.get(i).getxCoordinate() && yC == snakeBody.get(i).getyCoordinate()) {
                if (i != snakeBody.size() - 1) {
                    stop();
                }
            }
        }
    }
    //------------------------------------------DRAW------------------------------------------

    public void paint(Graphics g) {
        // Clear
        g.clearRect(0, 0, WIDTH, HEIGHT);
        // Fill background
        g.setColor(new Color(3421752));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        //-------------INFO-BAR-------------
        g.setColor(new Color(1381910));
        g.fillRect(WIDTH, 0, 1000, 50);
        g.setColor(new Color(2698029));
        g.fillRect(WIDTH, 50, 1000, HEIGHT);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("snakeAI", 870, 30);
        g.drawString("--------------------------", 810, 70);
        if (score > 80) {
            g.setColor(new Color(2781744));
        }
        g.drawString("Score:", 810, 100);
        g.drawString(Integer.toString(score), 920, 100);
        g.setColor(Color.white);
        g.drawString("Score(avg):", 810, 135);
        g.drawString(Float.toString(globalScore / countRuns), 920, 135);
        g.drawString("Runs:", 810, 170);
        g.drawString(Integer.toString(countRuns), 920, 170);
        g.drawString("--------------------------", 810, 190);

        g.drawString("Shortcuts", 810, 235);
        g.drawString("--------------------------", 810, 250);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        if (enableVision) {
            g.setColor(new Color(2781744));
        }
        g.drawString("C - enable color view", 810, 275);
        g.setColor(Color.white);
        if (drawVision) {
            g.setColor(new Color(2781744));
        }
        g.drawString("V - enable view array", 810, 300);
        g.setColor(Color.white);
        g.drawString("P - start game", 810, 325);
        g.drawString("Space - restart", 810, 350);
        g.drawString("Arrows - steering", 810, 375);
        g.drawString("Game speed - F/S", 810, 400);
        
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("AI", 810, 460);
        g.drawString("--------------------------", 810, 475);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        if (enableHamilton) {
            g.setColor(new Color(2781744));
        }
        g.drawString("1 - Hamilton", 810, 500);
        g.setColor(Color.white);
        if (enableHamiltonWithShortcuts) {
            g.setColor(new Color(2781744));
        }
        g.drawString("2 - Hamilton /w shortcuts", 810, 525);
        g.setColor(Color.white);
        if (simplePathfinder) {
            g.setColor(new Color(2781744));
        }
        g.drawString("3 - Simple pathfinder", 810, 550);
        g.setColor(Color.white);
        
        //-------------DRAW-VISION-------------
        if (drawVision) {
            for (int i = 0; i < vision.length; i++) {
                if (vision[i] != null) {
                    vision[i].draw(g);
                }
            }
        }
        //-------------DRAW-SNAKEBODY-------------
        g.setColor(new Color(16737792));
        for (int i = 0; i < snakeBody.size(); i++) {
            if (snakeBody.get(i) != null) {
                snakeBody.get(i).draw(g);
            }
        }
        //-------------DRAW-FOOD-------------
        if (food != null) {
            food.draw(g);
        }
        //-------------DRAW-X-AXIS-GRID-------------
        g.setColor(new Color(1052945));
        for (int i = 0; i < WIDTH / blockSize; i++) {
            g.drawLine(i * blockSize, 0, i * blockSize, HEIGHT);
        }
        //-------------DRAW-Y-AXIS-GRID-------------
        for (int i = 0; i < HEIGHT / blockSize; i++) {
            g.drawLine(0, i * blockSize, WIDTH, i * blockSize);
        }
        //-------------LOGO-------------
        if (!running || score==1597) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.PLAIN, 160));
            g.drawString("S N A K E", 40, 400);
        }
    }

    //------------------------------------------GAME------------------------------------------
    @Override
    public void run() {
        while (running) {
            loop();
            repaint();
        }
    }

    public void start() {
        running = true;
        thread = new Thread(this, "Game");
        thread.start();

    }

    public void stop() {
        running = false;
        System.out.println(score);
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void restart() {
        xC = 0;
        yC = 0;
        snakeSize = 4;
        score = 0;
        countRuns++;
        snakeBody.removeAll(snakeBody);
        right = true;
        up = false;
        food = null;
        down = false;
        left = false;
        start();
    }

    //--------------------STEERING--------------------
    public void steering() {
        if (right) xC++;
        if (left)  xC--;
        if (up)    yC--;
        if (down)  yC++;
    }

    public void goUp() {
        if (!down) {
            up = true;
            right = false;
            left = false;
        }
    }

    public void goDown() {
        if (!up) {
            down = true;
            left = false;
            right = false;
        }
    }

    public void goLeft() {
        if (!right) {
            down = false;
            up = false;
            left = true;
        }
    }

    public void goRight() {
        if (!left) {
            down = false;
            up = false;
            right = true;
        }
    }

    private class movementHandler implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    goDown();
                    break;
                case KeyEvent.VK_UP:
                    goUp();
                    break;
                case KeyEvent.VK_LEFT:
                    goLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    goRight();
                    break;
                case KeyEvent.VK_C:
                    enableVision = !enableVision;
                    break;
                case KeyEvent.VK_V:
                    drawVision = !drawVision;
                    break;
                case KeyEvent.VK_1:
                    enableHamiltonWithShortcuts = false;
                    simplePathfinder = false;
                    enableHamilton = !enableHamilton;
                    break;
                case KeyEvent.VK_2:
                    enableHamilton = false;
                    simplePathfinder = false;
                    enableHamiltonWithShortcuts = !enableHamiltonWithShortcuts;
                    break;
                case KeyEvent.VK_3:
                    enableHamilton = false;
                    enableHamiltonWithShortcuts = false;
                    simplePathfinder = !simplePathfinder;
                    break;
                case KeyEvent.VK_P:
                    start();
                    break;
                case KeyEvent.VK_SPACE:
                    restart();
                    break;
                case KeyEvent.VK_F:
                    if(gameSpeed > 10000)
                    gameSpeed -=10000;
                    else if(gameSpeed <= 10000 && gameSpeed > 200)
                    {
                        gameSpeed -=200;
                    }
                    break;
                case KeyEvent.VK_S:
                    if(gameSpeed <500000)
                    gameSpeed +=10000;
                    break;
                 
                    
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {}

    }
    //Printing game state as an array for each frame - for future use
    public void generateArray() {
        int[][] gameBoard = new int[40][40];
        for (int i = 0; i < snakeBody.size(); i++) {
            for (int j = 0; j <= (WIDTH / blockSize) - 1; j++) {
                for (int k = 0; k <= (HEIGHT / blockSize) - 1; k++) {
                    if (j == snakeBody.get(i).getxCoordinate() && k == snakeBody.get(i).getyCoordinate()) {
                        gameBoard[j][k] = 2;
                    }
                    if (j == xC && k == yC) {
                        gameBoard[j][k] = 1;
                    }
                    if (j == food.getxCoordinate() && k == food.getyCoordinate()) {
                        gameBoard[j][k] = 5;
                    }
                }
            }
        }
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                System.out.print("[" + gameBoard[j][i] + "]");
            }
            System.out.println();
        }
        System.out.println();
    }
}
