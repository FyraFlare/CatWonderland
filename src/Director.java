import java.awt.Graphics2D;

public class Director {
	
	Level gameplay;
	
	public Director(){
		gameplay = new EarthLevel();
	}

	public void update(){
		gameplay.update();
	}
	
	public void draw(Graphics2D g){
		gameplay.draw(g);
	}
	
}
