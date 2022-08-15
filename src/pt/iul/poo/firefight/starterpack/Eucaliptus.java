package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.utils.Point2D;

public class Eucaliptus extends Tile {
   
    private static final int MAXTURNSBURNING=5;
	private static final double PROB = 0.1;
	
    public Eucaliptus(Point2D position) {
	super(position,"eucaliptus", MAXTURNSBURNING, true, PROB);
	
    }

    

}