package pt.iul.poo.firefight.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.iul.poo.firefight.starterpack.Scoreboard;

public class DataCollector {
	

	public static char[][] readMap (File F) {
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

	public static String[] readChar (File F){  
		Scanner s= null;
		String[] element = new String[100];
		String[] positions = new String[100] ;
		try {
			s=new Scanner(F);
			int i=0;

			for(int f = 0; f<10;f++) { //para se posicionar na linha 10
				s.nextLine();
			}

			while(s.hasNextLine()) {
				element[i] = s.next();		
				positions[i]= (s.next()+s.next());				
				i++;
			}s.close();

		}catch (FileNotFoundException e) {
			System.err.println("Error loading file");
		}
		String toGameElement[] = new String[100] ;
		for(int k=0; element[k]!=null;k++) {
			toGameElement[k]=element[k] + " " + positions[k];
			System.out.println(positions[k]);
		}

		return toGameElement;
	}

	public List <Scoreboard> scoreboardFromFile(int level) {
		List <Scoreboard> scoreboards = new ArrayList<Scoreboard>();
		String name;
		int score;


		try {
			Scanner scanner = new Scanner(new File("hi_score_level"+ level + ".txt"));

			while(scanner.hasNextLine()) {
				while (scanner.hasNext()) {
					name = scanner.next();
					score = Integer.parseInt(scanner.next());	
					scoreboards.add(new Scoreboard(level, score, name));
				}
			}			
		} catch (FileNotFoundException e) {
			System.err.println("Could't read from hi_score file");
		}
		return scoreboards;

	}

}


