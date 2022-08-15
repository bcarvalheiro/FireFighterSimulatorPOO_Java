package pt.iul.poo.firefight.starterpack;


import java.util.List;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;


public class Plane extends Character{

	private static final int LAYER = 3;
	private static final String CLASS_NAME = "plane";


	public Plane(Point2D position) {
		super(position, "plane", LAYER);

	}

	public void setInitialPosition() {

		Point2D[] firesPosition = Fire.getFiresPosition();
		int[] countFiresPerX = new int[10];
		int xPosition= 0;
		int maxFires = -1;

		for (int i = 0; i < firesPosition.length; i++) {

			countFiresPerX[firesPosition[i].getX()]++;
		}

		for (int i = 0; i < countFiresPerX.length; i++) {
			if (maxFires < countFiresPerX[i]) {
				xPosition = i;
				maxFires = countFiresPerX[i];
			}
		}
		this.setPosition (new Point2D(xPosition, GameEngine.GRID_HEIGHT));	
		Scoreboard.getScoreboard().updateScore(-30);
	}
	public static Plane getPlane() {
		List <Character> characters = Character.getCharacters();

		for (Character character : characters) {

			if (character instanceof Plane) return (Plane) character;
		}
		return null;

	}


	@Override
	public void move() {
		if (this.isActive() ) {
			this.setPosition(this.getPosition().plus(new Vector2D(0,-2)));
			if (Collision.outOfFrame(getPosition())) {
				setPosition(new Point2D(-1, -1));
				this.toogleActive();
				this.removeFromGui();
			}		
			dropWater();	
		}
	}

	public void setActiveOn () {
		if (!this.isActive()) {
			this.setInitialPosition();
			this.addToGui();				
			this.toogleActive();
			this.move();
		}
	}

	private Point2D lastPosition() {

		return (new Point2D(this.getPosition().getX(), this.getPosition().getY()+1));
	}

	public void dropWater () {
		Water water;

		if (!Collision.outOfFrame(getPosition())) {
			if (Tile.getTilebyPosition(this.getPosition()).isBurning)
				water = new Water(getPosition(), Direction.DOWN);
		}
		if (!Collision.outOfFrame(this.lastPosition())) {
			if (Tile.getTilebyPosition(this.lastPosition()).isBurning)
				water = new Water(this.lastPosition(), Direction.DOWN);
		}
	}


	@Override
	public String getObjectName() {
		return CLASS_NAME;
	}

	@Override
	public String imageSelect (Direction dir) {
		return CLASS_NAME;
	}

	@Override
	public void characterWillCollideWithFire(Direction dir) {

	}



}
