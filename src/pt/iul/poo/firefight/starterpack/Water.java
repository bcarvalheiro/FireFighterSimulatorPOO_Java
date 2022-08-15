package pt.iul.poo.firefight.starterpack;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Water extends GameElement {
    private static ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    private static List<Water> waterList = new ArrayList<>();
    private int waterTimer;
    private static final int LAYER = 2;


    public Water(Point2D position, Direction direction) {
	super(position, "water_" + direction.toString().toLowerCase(), LAYER);
	this.waterTimer = 1;
	waterList.add(this);

    }

    public Water(ImageTile water) {
	super(water.getPosition(), water.getName(), LAYER);
	this.waterTimer = 1;
	waterList.add(this);

    }

 
    public static Point2D[] getAllWaterPositions() {
	Point2D[] waterPositions = new Point2D[waterList.size()];
	int i = 0;
	for (ImageTile water : waterList) {
	    waterPositions[i] = water.getPosition(); 
	    i++;
	}

	return waterPositions;
    }

    public static void extinguishFires( ) {

    }



    @Override
    public int getLayer() {
	return LAYER;
    }

    public int getTimer() {
	return waterTimer;
    }
    
	public static void clearAll() {
		waterList.clear();
	}

    public static void addWater(GameElement newWater) {
	gui = ImageMatrixGUI.getInstance();
	gui.addImage(newWater); 
	Scoreboard.getScoreboard().updateScore(10);
    }

    public void removeWater() {
	ListIterator<Water> wIter = Water.waterList.listIterator();
	while(wIter.hasNext()){
	    if(wIter.next().equals(this)){
		wIter.remove();
		this.removeFromGui();	
	    }
	}

    }


    public static Water getWaterbyPosition(Point2D position) {
	for(Water w: waterList) {
	    if(w.getPosition().equals(position))
		return w;
	}
	return null;
    }


    public static void cleanWaters() {
	List<ImageTile> waterToRemove = new ArrayList<>();
	for(ImageTile w: waterList) {
	    waterToRemove.add(w);
	}
	gui.removeImages(waterToRemove);
	waterList.clear();

    }


    public static void cleanFires() {
	Fire f ;
	Tile t;
	for(Water w : waterList) {
	    f = Fire.getFirebyPosition(w.getPosition());
	    t = Tile.getTilebyPosition(w.getPosition());
	    if(f!=null) {
		f.removeFire();
		t.fireExtinguished();
	    }
	}
	cleanWaters();

    }









}
