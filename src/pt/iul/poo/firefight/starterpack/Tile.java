package pt.iul.poo.firefight.starterpack;

import java.util.ArrayList;
import java.util.List;

import pt.iul.ista.poo.utils.Point2D;


public abstract class Tile extends GameElement{

	private static final int LAYER = 0;
	private static List<Tile> tiles = new ArrayList<Tile>();

	int maxTurnsBurning;
	double fireResistance;
	double prob;

	boolean isBurning = false;
	int burningTimer = 0;
	boolean isBurnt = false;
	boolean isBurnable;

	public Tile(Point2D position, String name, int maxTurnsBurning, boolean isBurnable, double prob) {
		super(position,name, LAYER);
		this.isBurnable = isBurnable;
		this.maxTurnsBurning = maxTurnsBurning;	
		tiles.add(this);
		this.prob = prob;
	}

	@Override
	public String toString() {
		return getName() + getPosition() + isBurnt;

	}

	public static void updateTilesOnFire() {
		Point2D[] firePosition = Fire.getFiresPosition();
		for(int i = 0; i<firePosition.length; i++) {
			for(Tile t: tiles) {
				if(t.getPosition().equals(firePosition[i])) {
					t.setOnFire();
					t.burningTimer++;
					t.checkBurningStatus();
				} 
			}
		}
	}

	public int getMaxTurnsBurning() {
		return maxTurnsBurning;
	}

	public static String typeOfTile(Point2D position) {
		for(Tile t : tiles) {
			if (t.getPosition().equals(position)){
				return t.getName();
			}
		}
		System.out.println("erro");
		return "erro";
	}

	public static Tile getTilebyPosition(Point2D position) {
		for(Tile t: tiles) {
			if(t.getPosition().equals(position))
				return t;
		}
		return null;
	}
	public void burnt() {
		String newName = "burnt" + this.getName();
		this.setName(newName);
		Fire f = Fire.getFirebyPosition(this.getPosition());
		f.removeFire();
		this.isBurnt = true;
		this.isBurnable = false;
		this.isBurning = false;
		Scoreboard.getScoreboard().updateScore(-20);

	}



	public boolean isBurnable() {
		return isBurnable;
	}
	
	public double probability() {
		return prob;
	}


	public void setOnFire() {
		isBurning = true;
	}

	public void fireExtinguished() {
		this.burningTimer=0;
		isBurning = false;
	}

	public boolean isBurning() {
		return isBurning;
	}


	public void checkBurningStatus() {
		if(this.burningTimer == this.maxTurnsBurning ) {
			Scoreboard.getScoreboard().updateScore(-5);
			this.burnt();


		}

	}

	public static void clearAll() {
		tiles.clear();
	}

	public double getFireResistance() {
		return fireResistance;
	}

	public boolean isBurnt() {
		return isBurnt;
	} 

	public static void clearTileByPosition (Point2D position) {
		Tile tile = getTilebyPosition(position);
		tile.setName("land");
	}
}