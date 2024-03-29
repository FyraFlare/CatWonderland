import java.awt.Color;
import java.awt.Graphics2D;


public class Cat extends Entity {
	
	static final float GRAVITY = .45f;
	static final float JUMP_STRENGTH = -12f;
	static final int GROUND = 500;
	
	boolean collidingWithObject;
	int jumpsSinceLanding;
	boolean sittin; 
	
	public Cat(){
		x = 200;
		y = 200;
		xs = ys = 50;
		yv = 0;
		
		collidingWithObject = false;
		jumpsSinceLanding = 0;
		sittin = false;
	}
	
	public void collideWithObstacle(){
		collidingWithObject = true;
	}
	
	public void sitOnObstacle(Obstacle o){
		y = o.y - ys;
		yv = 0;
		sittin = true;
	}
	
	public void hitHeadOnObstacle(Obstacle o){
		y = o.y + o.ys;
		yv = 0;
	}
	
	public void boost(){
		x += 20;
	}
	
	public void update(){
		if(collidingWithObject){
			xv = Level.getCurrLevel().scrollSpeed;
			collidingWithObject = false;
		} else {
			xv = 0;
		}

		yv += GRAVITY;
		
		if(Input.getInstance().jumpButton && jumpsSinceLanding < 1){
			Input.getInstance().jumpButton = false;
			yv = JUMP_STRENGTH;
			if(!sittin)
				jumpsSinceLanding++;
		}
		
		if(sittin){
			jumpsSinceLanding = 0;
			sittin = false;
		}
		
		y += yv;
		x += xv;
		
		if(y > GROUND - ys){
			y = GROUND - ys;
			sittin = true;
		}
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.black);
		g.fillRect((int) x, (int) y, (int)xs, (int)ys);
	}
	
}
