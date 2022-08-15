package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.utils.Point2D;


public class Pine extends Tile {
	private static final int MAXTURNSBURNING=10;
	private static final double PROB = 0.05;

	public Pine(Point2D position) {
		super(position,"pine", MAXTURNSBURNING , true, PROB);

	}




}
