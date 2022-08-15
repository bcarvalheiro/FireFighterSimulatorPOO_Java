package pt.iul.poo.firefight.starterpack;

import java.util.List;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Firetruck extends Vehicle{

	private static final String CLASS_NAME = "firetruck";
	
	public Firetruck(Point2D position) {
		super (position, CLASS_NAME);
	}
	

	
	@Override
	public String getObjectName() {
		
		return CLASS_NAME;
	}



	@Override
	public void characterWillCollideWithFire(Direction dir) {
		
		List <Point2D> tilesToGetWater = this.getPosition().getFrontRect(dir, 3, 2);
		
			if(this.isActive())
			{
				for (Point2D position : tilesToGetWater) {
				GameElement newWater = new Water(position.plus(dir.asVector()), dir);
				Water.addWater(newWater);
				}
			}
	}

}
