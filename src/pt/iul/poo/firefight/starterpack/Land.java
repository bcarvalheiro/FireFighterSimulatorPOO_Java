package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.utils.Point2D;

public class Land extends Tile{
	
	private static final double PROB = 0.00;

    public Land(Point2D position) {
	super(position, "land", 0, false, PROB);

    }

   
}
