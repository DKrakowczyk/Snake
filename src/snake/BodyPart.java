package snake;
import java.awt.*;
public class BodyPart {
    
    private int x, y, tileSize;
    private boolean nearWall, nearBody, nearFood;
    public BodyPart(int x, int y, int tileSize){
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;
        this.nearWall = false;
        this.nearBody = false;
        this.nearFood = false;
    }
    public void setNearFood(){
        this.nearFood = true;
    }
    public void setNearBody(){
        this.nearBody = true;
    }
    public void setNearWall(){
        this.nearWall = true;
    }
    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        if(nearWall)
        g2.setColor(new Color(14847055));
        if(nearBody)
        g2.setColor(new Color(15353928));
        if(nearFood)
        g2.setColor(new Color(3377028));
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
