package pt.iul.poo.firefight.starterpack;

import java.util.ArrayList;
import java.util.List;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class FuelBarrel extends Tile {
	
	private static final int MAXTURNSBURNING=3;
	private static final double PROB = 0.9;

	public FuelBarrel(Point2D position) {

			super(position,"fuelbarrel", MAXTURNSBURNING , true, PROB);

		}
	
	@Override
	public void burnt() {
		String newName = "burnt" + this.getName();
		this.setName(newName);
		Fire f = Fire.getFirebyPosition(this.getPosition());
		f.removeFire();
		List<Point2D> neighbournPointsWide = new ArrayList<Point2D>(this.getPosition().getWideNeighbourhoodPoints());
		Fire newFire = null;
		List<ImageTile> newFires = new ArrayList<ImageTile>();
		for(Point2D p : neighbournPointsWide) {
		    Tile t = Tile.getTilebyPosition(p);
		    if(Collision.fireCanSpreadtoPosition(p) 
			    && !t.isBurnt() 
			    && !t.isBurning() ) {
				newFire = new Fire(p);
				newFires.add(newFire);
				t.setOnFire();			
		}
	} 		
		this.isBurnt = true;
		this.isBurnable = false;
		this.isBurning = false;
		Scoreboard.getScoreboard().updateScore(-20);
	}


}
