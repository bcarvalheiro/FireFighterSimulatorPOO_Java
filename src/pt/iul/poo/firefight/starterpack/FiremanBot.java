package pt.iul.poo.firefight.starterpack;

import java.util.ArrayList;
import java.util.List;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class FiremanBot extends Character {
	
	private static final int LAYER = 2;
	private static final String CLASS_NAME = "firemanbot";
	private static List<FiremanBot> firemanBots = new ArrayList<>();
	

	public FiremanBot(Point2D position) {
		super(position, CLASS_NAME, LAYER);
		this.toogleActive();
		firemanBots.add(this);
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
	
	public static List<FiremanBot> getFiremanBot() {
	    if(!firemanBots.isEmpty())
		return firemanBots;
	    
	    return null;
	}
	
	
	
	@Override
	public void move() {

		//Vai apanhar a lista de fogos
	    	//Vai percorrer essa Lista e vai ver qual o fogo que está mais proximo
	    	//A cada update o FiremanBot vai-se mover na direção do mesmo.
		Point2D[] firePosition = new Point2D[Fire.getFires().size()];
		firePosition = Fire.getFiresPosition();
		
		Point2D positionToMove = firePosition[0];
		
		for(int i=1; i<firePosition.length; i++) {
		    if(this.getPosition().distanceTo(positionToMove) > this.getPosition().distanceTo(firePosition[i]))
			positionToMove=firePosition[i];
		}
		
		if(Collision.willCollideWithFire(getPosition().plus(this.getPosition().directionTo(positionToMove).asVector())))
			characterWillCollideWithFire(this.getPosition().directionTo(positionToMove));
		
		else if(Collision.characterCanMove(this, this.getPosition().directionTo(positionToMove))) 
		    setPosition(this.getPosition().plus(this.getPosition().directionTo(positionToMove).asVector()));
		
			    
	}
	
	public static void clearAll() {
		firemanBots.clear();
	}
	

}
