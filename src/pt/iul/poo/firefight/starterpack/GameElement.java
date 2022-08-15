package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;


public abstract class GameElement implements ImageTile{

    
    private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    private Point2D position;
    private String name;
    int layer;

  

    public GameElement(Point2D position, String type, int layer) {
	this.position = position;
	this.name=type;
	this.layer = layer;
	addToGui();
    }
    
 
  
    public void addToGui () {
    	gui.addImage(this);
    }
    
    public void removeFromGui () {
    	gui.removeImage(this);
    }
    

    public Point2D getPosition() {
	return position;
    }

    public String getName() {
	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
	public void setPosition(Point2D position) {
		this.position = position;
	}

    public int getLayer() {
	return layer;
    }
    @Override
    public String toString() {
	return name + position;
    }
    
   
}










