package pt.iul.poo.firefight.starterpack;

import java.util.List;

import pt.iul.poo.firefight.utils.SaveData;
import pt.iul.poo.firefight.utils.DataCollector;

public class Scoreboard {

	String player;
	int score;
	int level;
	static Scoreboard scoreboard;
	int[] scoreByLevel = new int[100];

	public Scoreboard(int level, String name){
		this.level = level;
		this.score = 0;
		this.player = name;
		Scoreboard.scoreboard=this;

	}

	public Scoreboard(int level, int score, String playerName){
		this.level=level;
		this.score = score;
		this.player=playerName;
	}

	public String nextLevel() {
		SaveData save = new SaveData();
		scoreByLevel[level-1]=score;

		DataCollector dataCollector = new DataCollector();
		List<Scoreboard> scoreBoards = dataCollector.scoreboardFromFile(this.getCurrentLevel());
		scoreBoards.add(this);
		scoreBoards.sort( (s1, s2) -> s2.getCurrentScore() - s1.getCurrentScore() );

		if (scoreBoards.size() > 5) {
			scoreBoards.remove(5);
		}
		save.writeScore(scoreBoards);//fazer alteraçao no saveData para 


		return showHiscoreList(scoreBoards);
	}

	public void resetScore () {
		this.score = 0;
		this.level++;    	
	}


	public int getCurrentScore() {
		return score;	
	}

	public int getCurrentLevel() {
		return level;
	}

	public String getPlayer() {
		return player;
	}
	@Override
	public String toString() {
		return player + " " + score;	
	}

	public String showHiscoreList (List<Scoreboard> scoreboards) {   	   	

		String result = "Best scores for level " + level + "\n \n";

		for (Scoreboard score: scoreboards) {
			result += score.toString() + "\n";
		}

		return result;

	}

	public void updateScore(int value) {
		score+=value;
		System.out.println(score);

	}

	public static Scoreboard getScoreboard() {
		return scoreboard;
	}

	public int getScoreByLevel(int level) {
		return this.scoreByLevel[level-1];
	}

	public static void resetScoreboard() {



	}	
}
