/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Dawid
 */
public class Food {
    
    private int x,y,width,height;
    
    public Food(int x, int y, int tileSize){
        this.x = x;
        this.y = y;
        width = tileSize;
        height = tileSize;
    }
    
    public void tick(){
        
    }
    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillOval(x*width, y*height, width, height);
    }
    // SET/GET
    public void setxCoordinate(int x){
        this.x = x;
    }
    public void setyCoordinate(int y){
        this.y = y;
    }
    public int getxCoordinate(){
        return this.x;
    }
    public int getyCoordinate(){
        return this.y;
    }
}
