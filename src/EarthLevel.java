
public class EarthLevel extends Level {

	public EarthLevel(){
		
		
		for(int i = 0; i < 10; i++){
			addObstacle(new Obstacle());
			addGrabby(new Grabbies());
		}
		
		for(int i = 0; i < 5; i++)
			addStuff(new Stuff());
		
	}
	
}
