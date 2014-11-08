
public class Collisions {

	public static boolean entitiesCollide(Entity e1, Entity e2){
		
		if(e1.x + e1.xs > e2.x &&
				e1.x < e2.x + e2.xs){
			if(e1.y + e1.ys > e2.y &&
				e1.y < e2.y + e2.ys){
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean entityLandingOnTop(Entity entity, Entity top){
		
		if(top.yv < 0)
			return false;
		
		Entity topPrev = new Entity();
		topPrev.x = top.x;
		topPrev.y = top.y - top.yv;
		topPrev.xs = top.xs;
		topPrev.ys = top.ys;
		
		boolean collide = entitiesCollide(entity, top);
		boolean collideMinusYV = entitiesCollide(entity, topPrev);
		
		if(collide && !collideMinusYV){
			return true;
		}
		
		return false;
	}
	
	public static boolean entityCollidingFromBelow(Entity entity, Entity below){
		
		if(below.yv > 0)
			return false;
		
		Entity belowPrev = new Entity();
		belowPrev.x = below.x;
		belowPrev.y = below.y - below.yv;
		belowPrev.xs = below.xs;
		belowPrev.ys = below.ys;
		
		boolean collide = entitiesCollide(entity, below);
		boolean collideMinusYV = entitiesCollide(entity, belowPrev);
		
		if(collide && !collideMinusYV){
			return true;
		}
		
		return false;
	}
	
}
