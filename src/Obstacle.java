import java.awt.Color;
import java.awt.Graphics2D;


public class Obstacle extends Entity {
	
	Color currColor;
	
	public Obstacle(){
		x = (float) (400 + Math.random() * 1800);
		y = (float) (Math.random() * 300f + 100);
		ys = 30;
		xs = 280;
		
		currColor = Color.red;
	}
	
	public void update(){
		
		Cat cat = Level.getCurrLevel().cat;
		
		if(Collisions.entityLandingOnTop(this, cat)){
			cat.sitOnObstacle(this);
		} else if(Collisions.entityCollidingFromBelow(this, cat)){ 
			cat.hitHeadOnObstacle(this);
		} else if(Collisions.entitiesCollide(this, cat)) {
			cat.collideWithObstacle();
		}
	}
	
	public void draw(Graphics2D g){
		g.setColor(currColor);
		g.fillRect((int)x, (int)y, (int)xs, (int)ys);
	}

}
