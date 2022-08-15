package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.utils.Point2D;

public class Abie extends Tile{


	private static final int MAXTURNSBURNING=20;
	private static final double PROB = 0.05;

	public Abie(Point2D position) {
		super(position,"abies", MAXTURNSBURNING, true, PROB);

	}
}




