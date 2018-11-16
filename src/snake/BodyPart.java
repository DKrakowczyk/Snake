package snake;
import java.awt.*;
public class BodyPart {
    
    private int x, y, width, height;
    public BodyPart(int x, int y, int tileSize){
        this.x = x;
        this.y = y;
        width = tileSize;
        height = tileSize;
    }
    
    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(16737792));
        g2.fillRect(x*width, y*height, width, height);
    }
    
    public void tick(){
        
    }
}
