/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static snake.Game.WIDTH;

/**
 *
 * @author Dawid
 */
public class Frame extends JFrame{
    
    public Frame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Snake Game");
        setResizable(false);
        init();
    }
    
    public void init(){
        setLayout(new GridLayout(1,1,0,0));
        Game s = new Game();
        add(s);
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args){
        new Frame();
    }
    
}
