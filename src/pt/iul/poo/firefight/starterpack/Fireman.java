package pt.iul.poo.firefight.starterpack;

import java.util.List;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;



public class Fireman extends Character{

	private static final int LAYER = 2;
	private static final String CLASS_NAME = "fireman";

	public Fireman(Point2D position) {
		super(position, CLASS_NAME, LAYER);
		this.toogleActive();

	}

	public static Fireman getFireman() {
		List <Character> characters = Character.getCharacters();

		for (ImageTile character : characters) {

			if (character instanceof Fireman) return (Fireman) character;
		}
		return null;

	}

	public void leaveVehicle () {		
		Vehicle vehicle = Vehicle.getActiveVehicle();
		vehicle.toogleActive();
		this.setPosition(vehicle.getPosition());
		this.toogleActive();
		this.addToGui();
	}

	@Override
	public String getObjectName() {
		return CLASS_NAME;
	}

	@Override
	public void characterWillCollideWithFire(Direction dir) {

		if(this.isActive())
		{
			GameElement newWater = new Water(getPosition().plus(dir.asVector()), dir);
			Water.addWater(newWater);
		}
	}

}
