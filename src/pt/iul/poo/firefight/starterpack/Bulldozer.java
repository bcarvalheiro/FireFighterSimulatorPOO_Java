package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Bulldozer extends Vehicle {
	
	private static final String CLASS_NAME = "bulldozer";
	

	public Bulldozer(Point2D position) {
		
		super (position, CLASS_NAME);

	}
	

	@Override
	public String getObjectName() {
		
		return CLASS_NAME;
	}
	
	@Override
	public void characterWillCollideWithFire(Direction dir) {
		
	}
	
	@Override
	public String imageSelect (Direction dir) {
		String imgName = getObjectName();

		switch (dir) {			
		case UP: imgName = imgName + "_up";
		break;
		case DOWN: imgName = imgName + "_down";
		break;
		case LEFT: imgName = imgName + "_left";
		break;
		case RIGHT: imgName = imgName + "_right";
		break;
		default: System.out.println("Direction error in imageSelect()");

		}
		return imgName;
	}

}
