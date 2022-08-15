package pt.iul.poo.firefight.starterpack;

import java.util.ArrayList;
import java.util.List;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;



public abstract class Character extends GameElement{

	private static List <Character> characters = new ArrayList<Character>();

	private boolean active;

	public Character(Point2D position, String type, int layer) {
		super(position, type, layer);
		characters.add(this);
		active = false;
	}

	public static Point2D[] getCharactersPosition() {
		Point2D[] charactersPosition = new Point2D[characters.size()];
		int i = 0;
		for (Character character : characters) {
			charactersPosition[i] = character.getPosition(); 
			i++;
		}    	
		return charactersPosition;
	}


	public static List<Character> getCharacters() {
		return characters;
	}

	public void move() {

		int tecla = ImageMatrixGUI.getInstance().keyPressed();			
		Direction dir = Direction.directionFor(tecla);

		if(Collision.willCollideWithFire(getPosition().plus(dir.asVector())))
			characterWillCollideWithFire(dir);
		
		else if(Collision.characterCanMove(this, dir) && this.isActive())  {
			setPosition (getPosition().plus(dir.asVector()));
			this.setName(imageSelect (dir));
		}
	}
	
	public abstract void characterWillCollideWithFire (Direction dir);
	
	public static void clearAll() {
		characters.clear();
	}

	public abstract String getObjectName();

	public String imageSelect (Direction dir) {
		String imgName = getObjectName();

		switch (dir) {			
		case UP: imgName = imgName + "_left";
		break;
		case DOWN: imgName = imgName + "_right";
		break;
		case LEFT: imgName = imgName + "_left";
		break;
		case RIGHT: imgName = imgName + "_right";
		break;
		default: System.out.println("Direction error in imageSelect()");

		}
		return imgName;
	}

	public boolean isActive() {return active;}

	public void toogleActive() {
		if (active == true) active = false;
		else {
			active = true;
		}
	}

}
