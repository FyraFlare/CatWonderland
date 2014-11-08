import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


public class Stuff extends Entity {
	
	Color currColor;
	Random rand = new Random();
	int points;
	
	public Stuff(){
		x = (float) (400 + Math.random() * 1800);
		y = (float) (Math.random() * 300f + 100);
		ys = 30;
		xs = 60;
		points = 10;
		
		currColor = Color.cyan;
	}
	
	public Stuff(int pointsAssigned){
		x = (float) (400 + Math.random() * 1800);
		y = (float) (Math.random() * 300f + 100);
		ys = 30;
		xs = 60;
		points = pointsAssigned;
		
		currColor = Color.cyan;
	}
	
	public int addPoints(){
		return points;
	}

	public void update(){
		
		Cat cat = Level.getCurrLevel().cat;
		
		if(Collisions.entitiesCollide(this, cat) && Input.getInstance().batButton == true){
			Input.getInstance().batButton = false;
			yv = rand.nextInt(40) + 10;
			xv = rand.nextInt(40);
		}
		x += xv;
		y += yv;
	}
	
	public void draw(Graphics2D g){
		g.setColor(currColor);
		g.fillRect((int)x, (int)y, (int)xs, (int)ys);
	}
}
