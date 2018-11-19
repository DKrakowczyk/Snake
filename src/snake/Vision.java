package snake;
import java.awt.*;
public class Vision {
    
    private int x, y, tileSize;
    private boolean isInDanger, isFood,isNearBody;
    public Vision(int x, int y, int tileSize){
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;
        isInDanger = false;
        isFood = false;
        isNearBody = false;
    }
    
    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;  
        g2.setColor(new Color(5028442));
        if(isInDanger)
        g2.setColor(Color.ORANGE);   
        if(isFood)
        g2.setColor(new Color(9314446));      
        if(isNearBody)
        g2.setColor(Color.RED);
        g2.fillRect(x*tileSize, y*tileSize, tileSize, tileSize);
    }
    // SET/GET
   
    public void setNearBody(){
        this.isNearBody = true;
    }
    public void setNoBody(){
        this.isNearBody = false;
    }
    public boolean isFood(){
        return this.isFood;
    }
    public void setSafe()
    {
        this.isInDanger = false;
    }
    public void setDanger()
    {
        this.isInDanger = true;
    }
    
    public void setFood()
    {
        this.isFood = true;
    }
    public void setNoFood()
    {
        this.isFood = false;
    }
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
