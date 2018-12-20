/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

/**
 *
 * @author Dawid
 */
public class AI {
    
    public void HamiltonCycle(Game g){
        
    }
    public static void HamiltonCycleShortcuts(Game g){
        int x = g.food.getxCoordinate();
        int y = g.food.getyCoordinate();
        //PODSTAWOWE PORUSZANIE Z POSZUKIWANIEM JEDZENIA
        if (g.xC == 0 && g.hasEaten) {
            g.goUp();
            g.hasEaten = false;
        }
        if (g.xC == 39 && g.yC == 0) g.goDown();
        if (g.xC == 39 && g.yC == 39) g.goLeft();
        if (g.xC == 0 && g.yC == 39) g.goUp();

        if ((g.yC == y || g.yC - 1 == y) && g.xC == 0) g.hasEaten = goForFood(g,g.xC, g.yC);
        
        //If the snake is too long...
        if (g.xC == 0 && g.yC == 38 && g.snakeSize > 150) g.goRight();
        if (g.xC == 0 && g.yC == 37 && g.snakeSize > 150) g.goUp();
        if (g.xC == 38 && g.yC == 38) g.goUp();
        if (g.xC == 38 && g.yC == 37) g.goLeft();
        if (g.xC == 0 && g.yC == 37 && g.snakeSize > 200) g.goUp();
        if (g.xC == 0 && g.yC == 36 && g.snakeSize > 200) g.goRight();
        if (g.xC == 38 && g.yC == 36) g.goUp();
        if (g.xC == 38 && g.yC == 35) g.goLeft();
        if (g.xC == 0 && g.yC == 35) g.goUp();
        if (g.xC == 0 && g.yC == 34 && g.snakeSize>250) g.goRight();
        if (g.xC == 38 && g.yC == 34) g.goUp();
        if (g.xC == 38 && g.yC == 33) g.goLeft();
        if (g.xC == 0 && g.yC == 33) g.goUp();
        if (g.xC == 0 && g.yC == 32 && g.snakeSize>300) g.goRight();
        if (g.xC == 38 && g.yC == 32) g.goUp();
        if (g.xC == 38 && g.yC == 31) g.goLeft();
        if (g.xC == 0 && g.yC == 31 ) g.goUp();
        if (g.xC == 0 && g.yC == 30 && g.snakeSize>350) g.goRight();
        if (g.xC == 38 && g.yC == 30) g.goUp();
        if (g.xC == 38 && g.yC == 29) g.goLeft();
        if (g.xC == 0 && g.yC == 29) g.goUp();
        if (g.xC == 0 && g.yC == 28 && g.snakeSize>400) g.goRight();
        if (g.xC == 38 && g.yC == 28) g.goUp();
        if (g.xC == 38 && g.yC == 27) g.goLeft();
        if (g.xC == 0 && g.yC == 27) g.goUp();
        if (g.xC == 0 && g.yC == 26 && g.snakeSize>450) g.goRight();
        if (g.xC == 38 && g.yC == 26) g.goUp();
        if (g.xC == 38 && g.yC == 25) g.goLeft();
        if (g.xC == 0 && g.yC == 25) g.goUp();
        if (g.xC == 0 && g.yC == 24 && g.snakeSize>500) g.goRight();
        if (g.xC == 38 && g.yC == 24) g.goUp();
        if (g.xC == 38 && g.yC == 23) g.goLeft();
        if (g.xC == 0 && g.yC == 23) g.goUp();
        if (g.xC == 0 && g.yC == 22 && g.snakeSize>550) g.goRight();
        if (g.xC == 38 && g.yC == 22) g.goUp();
        if (g.xC == 38 && g.yC == 21) g.goLeft();
        if (g.xC == 0 && g.yC == 21) g.goUp();
        if (g.xC == 0 && g.yC == 20 && g.snakeSize>600) g.goRight();
        if (g.xC == 38 && g.yC == 20) g.goUp();
        if (g.xC == 38 && g.yC == 19) g.goLeft();
        if (g.xC == 0 && g.yC == 19) g.goUp();
        if (g.xC == 0 && g.yC == 18 && g.snakeSize>650) g.goRight();
        if (g.xC == 38 && g.yC == 18) g.goUp();
        if (g.xC == 38 && g.yC == 17) g.goLeft();
        if (g.xC == 0 && g.yC == 17) g.goUp();
        if (g.xC == 0 && g.yC == 16 && g.snakeSize>700) g.goRight();
        if (g.xC == 38 && g.yC == 16) g.goUp();
        if (g.xC == 38 && g.yC == 15) g.goLeft();
        if (g.xC == 0 && g.yC == 15) g.goUp();
        if (g.xC == 0 && g.yC == 14 && g.snakeSize>750) g.goRight();
        if (g.xC == 38 && g.yC == 14) g.goUp();
        if (g.xC == 38 && g.yC == 13) g.goLeft();
        if (g.xC == 0 && g.yC == 13) g.goUp();
        if (g.xC == 0 && g.yC == 12 && g.snakeSize>800) g.goRight();
        if (g.xC == 38 && g.yC == 12) g.goUp();
        if (g.xC == 38 && g.yC == 11) g.goLeft();
        if (g.xC == 0 && g.yC == 11) g.goUp();
        if (g.xC == 0 && g.yC == 10 && g.snakeSize>850) g.goRight();
        if (g.xC == 38 && g.yC == 10) g.goUp();
        if (g.xC == 38 && g.yC == 9) g.goLeft();
        if (g.xC == 0 && g.yC == 9) g.goUp();
        if (g.xC == 0 && g.yC == 8 && g.snakeSize>900) g.goRight();
        if (g.xC == 38 && g.yC == 8) g.goUp();
        if (g.xC == 38 && g.yC == 7) g.goLeft();
        if (g.xC == 0 && g.yC == 7) g.goUp();
        if (g.xC == 0 && g.yC == 6 && g.snakeSize>950) g.goRight();
        if (g.xC == 38 && g.yC == 6) g.goUp();
        if (g.xC == 38 && g.yC == 5) g.goLeft();
        if (g.xC == 0 && g.yC == 5) g.goUp();
        if (g.xC == 0 && g.yC == 4 && g.snakeSize>1000) g.goRight();
        if (g.xC == 38 && g.yC == 4) g.goUp();
        if (g.xC == 38 && g.yC == 3) g.goLeft();
        if (g.xC == 0 && g.yC == 3) g.goUp();
        if (g.xC == 0 && g.yC == 2 && g.snakeSize>1050) g.goRight();
        if (g.xC == 38 && g.yC == 2) g.goUp();
        if (g.xC == 38 && g.yC == 1) g.goLeft();
        if (g.xC == 0 && g.yC == 1 ) g.goUp();
        if (g.xC == 0 && g.yC == 0) g.goRight();
    }
    
    public static boolean goForFood(Game g,int x, int y) {
        if (y % 2 == 0) {
            g.goRight();
        } else {
            return false;
        }
        return true;
    }
    public void simpleFoodFinding(Game g){
          
        int fx = 0;
        int fy = 0;
        if (g.food != null) {
            fx = g.food.getxCoordinate();
            fy = g.food.getyCoordinate();
        }
        if (g.right) {
            if (g.xC == fx && fy > g.yC) g.goDown();               
            if (g.xC == fx && fy < g.yC) g.goUp();
            if (g.xC + 1 >= 40 && g.yC - 1 < 0) g.goDown();
            if (g.xC + 1 >= 40 && fy < g.yC) g.goUp();
            if (g.xC + 1 >= 40 && fy > g.yC) g.goDown();
            if (g.xC + 1 >= 40 && g.yC + 1 >= 40) g.goUp();              
        }
        if (g.up) {
            if (g.yC == fy && fx >g.xC) g.goRight();
            if (g.yC == fy && fx < g.xC) g.goLeft();
            if (g.xC - 1 < 0 && g.yC - 1 < 0) g.goRight();
            else if (g.yC - 1 < 0 && fx < g.xC) g.goLeft();
            else if (g.yC - 1 < 0 && fx > g.xC) g.goRight();
            else if (g.xC + 1 >= 40 && g.yC - 1 < 0) g.goLeft();
        }
        if (g.down) {
            if (g.yC == fy && fx > g.xC) g.goRight();
            if (g.yC == fy && fx < g.xC) g.goLeft();
            if (g.yC + 1 >= 40 && g.xC - 1 < 0) g.goRight();
            if (g.yC + 1 >= 40 && fx < g.xC) g.goLeft();
            if (g.yC + 1 >= 40 && fx > g.xC) g.goRight();
            if (g.yC + 1 >= 40 && g.xC + 1 >= 40) g.goLeft();        
        }
        if (g.left) {
            if (g.xC == fx && fy > g.yC) g.goDown();
            if (g.xC == fx && fy < g.yC) g.goUp();
            if (g.xC - 1 < 0 && g.yC - 1 < 0) g.goDown();
            if (g.xC - 1 < 0 && fy < g.yC) g.goUp();
            if (g.xC - 1 < 0 && fy > g.yC) g.goDown();
            if (g.xC - 1 < 0 && g.yC + 1 >= 40) g.goUp();
        }
    }
}
