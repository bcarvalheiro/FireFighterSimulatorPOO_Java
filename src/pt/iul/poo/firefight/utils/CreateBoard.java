package pt.iul.poo.firefight.utils;

import java.io.File;

import pt.iul.ista.poo.utils.Point2D;
import pt.iul.poo.firefight.starterpack.Abie;
import pt.iul.poo.firefight.starterpack.Bulldozer;
import pt.iul.poo.firefight.starterpack.Eucaliptus;
import pt.iul.poo.firefight.starterpack.Fire;
import pt.iul.poo.firefight.starterpack.Fireman;
import pt.iul.poo.firefight.starterpack.FiremanBot;
import pt.iul.poo.firefight.starterpack.Firetruck;
import pt.iul.poo.firefight.starterpack.FuelBarrel;
import pt.iul.poo.firefight.starterpack.GameEngine;
import pt.iul.poo.firefight.starterpack.Grass;
import pt.iul.poo.firefight.starterpack.Land;
import pt.iul.poo.firefight.starterpack.Pine;
import pt.iul.poo.firefight.starterpack.Plane;

public class CreateBoard {
	
	private CreateBoard () {}


	public static void createCharactersFromLevel (String levelPath) {

		String[] elements = DataCollector.readChar(new File(levelPath));

		String[] splittedString;
		String[] position;
		
		new Plane(new Point2D(-1,-1));



		for(int k=0; elements[k]!=null;k++) {
		    splittedString = elements[k].split(" ");
		    position = splittedString[1].split("");

		    switch(splittedString[0]){

		    case "Fireman" : new Fireman(new Point2D(Integer.parseInt(position[0]),Integer.parseInt(position[1])));
		    break;

		    case "Bulldozer" : new Bulldozer(new Point2D(Integer.parseInt(position[0]),Integer.parseInt(position[1])));
		    break;
		    
		    case "FireTruck" : new Firetruck(new Point2D(Integer.parseInt(position[0]),Integer.parseInt(position[1])));
		    break;
		    
		    case "FiremanBot" : new FiremanBot(new Point2D(Integer.parseInt(position[0]),Integer.parseInt(position[1])));
		    break;

		    case "Fire" : new Fire(new Point2D(Integer.parseInt(position[0]),Integer.parseInt(position[1])));
		    break;

		    default: System.out.println("erro");
		    }
		}
	}
	
	public static void createTerrainFromLevel(String levelPath) {		

		char[][] map = DataCollector.readMap(new File(levelPath));
		
		for (int y=0; y < GameEngine.GRID_HEIGHT; y++) {
			for (int x = 0; x < GameEngine.GRID_HEIGHT; x++) {
				switch(map[y][x]) {
				case 'p' : new Pine(new Point2D(x,y)); 
				break;
				case 'e' : new Eucaliptus(new Point2D(x,y));
				break;
				case 'm' : new Grass(new Point2D(x,y));
				break;
				case 'a' : new Abie(new Point2D(x,y));
				break;
				case 'b' : new FuelBarrel(new Point2D(x,y));
				break;	
				case '_' : new Land(new Point2D(x,y));
				break;				
				default : System.out.println("erro");
				}
			}
		}	
	}



}
