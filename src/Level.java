import java.awt.Graphics2D;
import java.util.ArrayList;


public class Level {

	static Level currLevel;
	static Level getCurrLevel(){
		return currLevel;
	}
	
	Cat cat;
	
	ArrayList<Obstacle> obstacles;
	ArrayList<Stuff> stuffs;
	ArrayList<Grabbies> grabbies;
	Background background;
	
	float scrollSpeed;
	
	public Level(){
		cat = new Cat();
		
		scrollSpeed = -5f;
		
		obstacles = new ArrayList<Obstacle>();
		stuffs = new ArrayList<Stuff>();
		grabbies = new ArrayList<Grabbies>();
		
		background = new Background("TestBG.png");
		
		currLevel = this;
	}
	
	void addObstacle(Obstacle o){
		obstacles.add(o);
	}
	
	void addStuff(Stuff s){
		stuffs.add(s);
	}
	
	void addGrabby(Grabbies g){
		grabbies.add(g);
	}
	
	
	public void update(){
		cat.update();
		
		for(Obstacle o : obstacles){
			o.x += scrollSpeed;
			o.update();
		}
		for(Stuff s : stuffs){
			s.x += scrollSpeed;
			s.update();
		}
		for(Grabbies grabby : grabbies){
			grabby.x += scrollSpeed;
			grabby.update();
		}
		
		background.x += scrollSpeed;
		background.update();
	}
	
	public void draw(Graphics2D g){
		
		background.draw(g);
		
		for(Obstacle o : obstacles){
			o.draw(g);
		}
		for(Stuff s : stuffs){
			s.draw(g);
		}
		for(Grabbies grabby : grabbies){
			grabby.draw(g);
		}
		
		cat.draw(g);

	}
	
}

