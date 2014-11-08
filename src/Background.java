import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Background extends Entity {

	BufferedImage image;
	
	public Background(String filename){
		image = Resources.getInstance().getImage(filename);
	}
	
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g){
		int width = image.getWidth();
		
		int x = (int)this.x;
		int y = (int)this.y;
		g.drawImage(image, x % width, y, null);
		g.drawImage(image, x % width + width, y, null);
		
	}
	
}
