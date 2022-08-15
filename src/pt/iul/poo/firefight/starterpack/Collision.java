package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Collision {
	
	private Collision () {}
	
	private static Point2D newPosition (Point2D position, Direction direction) {
		return position.plus(direction.asVector());
	}

	
	public static boolean isOverlaying (Point2D firstObjectPosition, Point2D secondObjectPosition) {
		return firstObjectPosition.equals(secondObjectPosition);		
	}
	
	public static boolean outOfFrame (Point2D position) {
			
		if (position.getX() < 0 || 
				position.getX() >= GameEngine.GRID_WIDTH ||
					position.getY() < 0  || 
						position.getY() >= GameEngine.GRID_HEIGHT) 
							return true;
		
		return false;
	}
	
	public static boolean willCollideWithFire(Point2D newMovingObjectPosition) {
		Point2D[] firesPosition = Fire.getFiresPosition();
		
		for (int i=0; i < firesPosition.length; i++) {
			if (firesPosition[i].equals(newMovingObjectPosition)) return true;
		}
		return false;
	}
	
	private static boolean willCollideWithCharacter (Point2D newMovingObjectPosition) {
		Point2D[] charactersPosition = Character.getCharactersPosition();
		
		for (int i=0; i < charactersPosition.length; i++) {
			if (charactersPosition[i].equals(newMovingObjectPosition)) return true;
		}
		return false;
	}
	
	public static boolean characterCanMove(Character character, Direction direction) {
		Point2D newMovingObjectPosition = newPosition(character.getPosition(), direction);
		if (outOfFrame(newMovingObjectPosition) ||
				willCollideWithFire(newMovingObjectPosition)) return false;
		
		return true;		
	}
	
	public static boolean fireCanSpreadtoPosition(Point2D position) {

		if (outOfFrame(position) ||
				willCollideWithFire(position) ||
					willCollideWithCharacter(position)) return false;				
		
		return true;		
	}

}
