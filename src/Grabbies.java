import java.awt.Color;
import java.awt.Graphics2D;


public class Grabbies extends Entity {
	
	boolean visible;
	
	public Grabbies(){
		visible = true;
		x = (float) (400 + Math.random() * 1800);
		y = (float) (Math.random() * 300f + 100);
		ys = 30;
		xs = 280;
	}
	
	public void update(){
		Cat cat = Level.getCurrLevel().cat;
		 
		if(Collisions.entitiesCollide(this, cat) && visible) {
			cat.boost();
			visible = false;
		}
	}
	
	public void draw(Graphics2D g){
		if(visible){
			g.setColor(Color.blue);
			g.fillRect((int) x, (int) y, (int)xs, (int)ys);
		}
	}
}
