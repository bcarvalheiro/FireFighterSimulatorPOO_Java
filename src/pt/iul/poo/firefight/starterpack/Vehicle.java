package pt.iul.poo.firefight.starterpack;

import java.util.ArrayList;
import java.util.List;

import pt.iul.ista.poo.utils.Point2D;

public abstract class Vehicle extends Character{	

	private static final int LAYER = 2;
	private static List <Vehicle> vehicles = new ArrayList<Vehicle>();


	public Vehicle(Point2D position, String name) {
		super(position, name, LAYER);
		vehicles.add(this);
	}
	


	public static Vehicle getActiveVehicle() {
		for (Vehicle vehicle: vehicles) {
			if (vehicle.isActive()) return vehicle;
		}
		return null;
	}

	public void update() {
		Fireman fireman = Fireman.getFireman();

		if (this.isActive()) {
			this.move();
			if (this.getObjectName() == "bulldozer") Tile.clearTileByPosition(this.getPosition());
		}


		if (!this.isActive() ) {
			this.toogleActive();
			fireman.toogleActive();
			fireman.removeFromGui();
		}
	}

	public static Vehicle firemanOverlay (Point2D position) {

		for (Vehicle vehicle: vehicles) {
			if (vehicle.getPosition().equals(position)) {
				Scoreboard.getScoreboard().updateScore(10);
				return vehicle;
			}
		}
		return null;
	}

	public static void clearAll() {
		vehicles.clear();
	}

}
