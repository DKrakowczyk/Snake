package snake;
import java.awt.*;
public class Vision {
    
    private int x, y, width, height;
    private boolean isInDanger, isFood;
    public Vision(int x, int y, int tileSize){
        this.x = x;
        this.y = y;
        width = tileSize;
        height = tileSize;
        isInDanger = false;
        isFood = false;
    }
    
    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(new Color(5028442));
        if(isInDanger)
        g2.setColor(Color.ORANGE);
        
        if(isFood)
        g2.setColor(new Color(9314446));
        
        g2.fillRect(x*width, y*height, width, height);
    }
      // SET/GET
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
