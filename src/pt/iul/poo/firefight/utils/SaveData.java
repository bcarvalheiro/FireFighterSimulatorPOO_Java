package pt.iul.poo.firefight.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import pt.iul.poo.firefight.starterpack.Scoreboard;

public class SaveData {
	
			
	public void writeScore (List<Scoreboard> scoreboards) {
		
		
		try {
			PrintWriter pw = new PrintWriter(new File("hi_score_level"+ scoreboards.get(0).getCurrentLevel() + ".txt"));
			
			for (Scoreboard score : scoreboards) {
				if (scoreboards.indexOf(score) != scoreboards.size()-1) {
					pw.println(score);	
				} else pw.print(score);
				
			System.out.println(score);
			}

			pw.close();
			
		} catch (FileNotFoundException e) {
			System.err.println("Could't write to file");
		}
		
	}	

}