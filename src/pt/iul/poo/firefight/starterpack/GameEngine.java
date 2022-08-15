package pt.iul.poo.firefight.starterpack;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.poo.firefight.utils.CreateBoard;


public class GameEngine implements Observer {

	// Dimensoes da grelha de jogo
	public static final int GRID_HEIGHT = 10;
	public static final int GRID_WIDTH = 10;

	private ImageMatrixGUI gui;  		// Referencia para ImageMatrixGUI (janela de interface com o utilizador) 
	private int level = 1;
	private String playerName ="";
	private JOptionPane getName = new JOptionPane();
	private static final int MAXLEVEL = 6;

	Fireman fireman;
	Plane plane;
	Vehicle vehicle;

	private static Scoreboard scoreboard ;


	private int gameTurns=0;


	public GameEngine() {
		gui = ImageMatrixGUI.getInstance();    // 1. obter instancia ativa de ImageMatrixGUI	
		gui.setSize(GRID_HEIGHT, GRID_WIDTH);  // 2. configurar as dimensoes 
		gui.registerObserver(this);            // 3. registar o objeto ativo GameEngine como observador da GUI
		gui.go();                              // 4. lancar a GUI

	}

	
	@Override
	public void update(Observed source) {    	

		int tecla = ImageMatrixGUI.getInstance().keyPressed();
		// Se teclas de Direccao
		if (tecla > 36 && tecla < 41) {
			gameTurns++; //added 14/11 

			Water.cleanFires();
			Tile.updateTilesOnFire();

			fireman.move();
			plane.move();

			Fire.fireSpreading();

			if (vehicle == null) 
				vehicle = Vehicle.firemanOverlay(fireman.getPosition());
			if (vehicle != null) {
				vehicle.update();
			}
		}

		if (vehicle != null && vehicle.isActive() && tecla == KeyEvent.VK_ENTER) {
			fireman.leaveVehicle();
			vehicle = null;
		}
		
		if (Fire.getFires().size() != 0 ) {
			if(FiremanBot.getFiremanBot()!=null) {
			    for(FiremanBot f : FiremanBot.getFiremanBot()) {
				    f.move();
				}		
			}
		}

		if (tecla == KeyEvent.VK_P) {
			plane.setActiveOn();
			gameTurns++;

		}
		gui.setStatusMessage(getGameStatus ());
		gui.update();
		// redesenha as imagens na GUI, tendo em conta as novas posicoes

		if(Fire.getFiresPosition().length == 0)	loadNewLevel();		
	}

	// Criacao dos objetos e envio das imagens para GUI
	public void start() {

		CreateBoard.createTerrainFromLevel(levelPath());
		CreateBoard.createCharactersFromLevel(levelPath());
		if (level == 1) welcome();

		gui.setStatusMessage(getGameStatus ());
		gui.setName("Fireman");

		fireman = Fireman.getFireman();
		plane = Plane.getPlane();
		Tile.updateTilesOnFire();

		gui.setStatusMessage(getGameStatus ());

		gui.update();

	}

	public void welcome() {

		playerName = getNamePane();
		gui.setMessage("Welcome to the game, Fireman " + playerName);
		scoreboard = new Scoreboard(level,playerName);

	}


	private String levelPath() {
		return "levels/level" + level + ".txt";
	}

	private String getGameStatus () {
		return  "Level " + level +
				"   Score: " + scoreboard.getCurrentScore() +
				"   Turns " + gameTurns;
	}

	private void loadNewLevel() {

		this.level++;

		gui.clearImages();
		Tile.clearAll();
		Character.clearAll();
		Water.cleanWaters();
		Vehicle.clearAll();	
		FiremanBot.clearAll();
		fireman=null;
		vehicle=null;
		plane=null;
		gui.setMessage(scoreboard.nextLevel()); 
		scoreboard.resetScore();

		if (level > MAXLEVEL) gameEnd();

		this.start();

	}

	public String getNamePane() {
		getName.setAlignmentX(SwingConstants.LEFT);
		getName.setAlignmentY(SwingConstants.CENTER);
		return getName.showInputDialog("what's your name?");
	}

	private void gameEnd () {

		gui.dispose();		
		gui.setMessage("Well done fireman " + playerName + 
				" you've ended this game!!!"); 

		System.exit(0);
	}
}
