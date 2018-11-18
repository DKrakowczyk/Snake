package snake;
import java.awt.*;
public class BodyPart {
    
    private int x, y, tileSize;
    public BodyPart(int x, int y, int tileSize){
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;
    }
    
    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(x*tileSize, y*tileSize, tileSize, tileSize);
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
