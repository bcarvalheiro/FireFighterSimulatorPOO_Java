package pt.iul.poo.firefight.starterpack;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

//Esta classe de exemplo esta' definida de forma muito basica, sem relacoes de heranca
//Tem atributos e metodos repetidos em relacao ao que está definido noutras classes 
//Isso sera' de evitar na versao a serio do projeto

public class Fire extends GameElement{
	private static List<Fire> fires = new ArrayList<Fire>();
	private static final int LAYER = 1;


	public Fire(Point2D position) {
		super (position, "fire", LAYER);
		fires.add(this);

	}

	public static List <Fire> getFires() {
		return fires;
	}

	public static Point2D[] getFiresPosition() {
		Point2D[] firesPosition = new Point2D[fires.size()];
		int i = 0;
		for (ImageTile fire : fires) {
			firesPosition[i] = fire.getPosition(); 
			i++;
		}

		return firesPosition;
	}




	public static void fireSpreading() {	    
		List<ImageTile> newFires = new ArrayList<ImageTile>();
		Fire newFire = null;
		Point2D[] firePositions = getFiresPosition();
		int fireSize = fires.size();

		for(int i=0; i<fireSize; i++) {
			List<Point2D> fireNeightbourns = firePositions[i].getNeighbourhoodPoints();
			for(Point2D p : fireNeightbourns) {
				Tile t = Tile.getTilebyPosition(p);
				if(Collision.fireCanSpreadtoPosition(p) 
						&& !t.isBurnt()
						&& !t.isBurning()) {	

					if	(Math.random() < t.probability()) {
							newFire = new Fire(p);
							newFires.add(newFire);
							t.setOnFire();
					}
				}
			}
		}
						
	}
	

	public static Fire getFirebyPosition(Point2D position) {
		for(Fire f: fires) {
			if(f.getPosition().equals(position))
				return f;
		}
		return null;
	}


	public void removeFire() {
		ListIterator<Fire> fIter = Fire.fires.listIterator();
		while(fIter.hasNext()){
			if(fIter.next().equals(this)){
				fIter.remove();
				this.removeFromGui();	
			}
		}

	}
}

