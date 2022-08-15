package pt.iul.poo.firefight.starterpack;
import pt.iul.ista.poo.utils.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class testeMain {
    public static void main(String[] args) {
	
	File F = new File("levels/example.txt");
	String[][] terrainMatrix = new String[111][111];
	List<Tile> tileToBoard = new ArrayList<>();

	char[][] map = readMap(F,terrainMatrix);
		for (int y=0; y < 10; y++) {
		    for (int x = 0; x < 10; x++) {
    			switch(map[y][x]) {
    			case 'p' : tileToBoard.add(new Pine(new Point2D(x,y)));
			case 'e' : tileToBoard.add(new Eucaliptus(new Point2D(x,y)));
			case 'm' : tileToBoard.add(new Grass(new Point2D(x,y)));
			case '-' : tileToBoard.add(new Land(new Point2D(x,y)));
		    }
		}


    }
		
		System.out.println(tileToBoard);
    }

    public static char[][] readMap (File F, String[][] terrainMatrix) {
	Scanner s = null;
	int y = 0;
	char[] line = new char[10];
	char[][] map = new char[10][10];

	try {
	    s=new Scanner(F);
	    while(s.hasNextLine() && y < 10) {			
		line = s.next().toCharArray();
		for (int x = 0; x < 10; x++) map[y][x] = line[x];
		y++;

	    }s.close();

	}  catch (FileNotFoundException e) {
	    System.err.println("Erro na abertura de ficheiro)");
	}	

	return map;

    }
}


