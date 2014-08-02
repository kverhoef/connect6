package userinterface;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.Timer;
import algorithms.Algorithm;
import algorithms.SimpleBot;
import logic.Board;
import logic.GameController;
import logic.MoveSet;
import logic.Player;
import logic.Stone;

public class UI extends JApplet{

	private static final long serialVersionUID = 1L;
	public Timer swingTimer;
	public boolean drawHelpLines = true;
	public GameController controller;
	public Board board;
	public boolean inputEnabled = true;
	
	// Container for holding a graphical representation of the stones.
	Container stoneContainer = new Container();

	// Zwart
	public Algorithm player1Algorithm;
	
	// Wit
	public Algorithm player2Algorithm;
	BoardObject boardObject;
	
	public UI(){

        System.out.println("Starting UI");

		this.setName("Connect6");
        
		//Set up the content pane.
        this.getContentPane().setLayout(null);
        
        //Size and display the window.
        Insets insets = this.getInsets();
        this.setSize(658 + insets.left + insets.right, 700 + insets.top + insets.bottom);
		
        // Help lines
        HelpLines helpLines = new HelpLines(this);  
		helpLines.setBounds(0, 0, 650,672);
        this.add(helpLines);
        
        // Container bounds
        stoneContainer.setBounds(0, 0, 650,672);
        this.add(stoneContainer);

		Menu menu = new Menu(this);
		this.setJMenuBar(menu);
		
		board = new Board();
		
		controller = new GameController();
		
		boardObject = new BoardObject(this);
		this.add(boardObject);
		
//		player1Algorithm = new Randoms();
//		player2Algorithm = new SimpleBot(1);

        player1Algorithm = new SimpleBot(5);

        this.setVisible(true);

        step();


	}
	
	public void init(){
		this.setSize(new Dimension(650,700));
	}
	
	public void selectAlgorithm(Player player, Algorithm algorithm){
		System.out.println("Set algorithm " + algorithm + " for player " + player);
		
		if (player.equals(Player.BLACK)){
			this.player1Algorithm = algorithm;
		}
		else {
			this.player2Algorithm = algorithm;
		}
	}
	
	public void drawStones(){
		
		// Remove the stones on the current board
		stoneContainer.removeAll();
		
		for (int y=0;y < 19;y++)
		{
			for (int x=0;x < 19;x++)
			{
				Stone stone = board.getStone(x, y);
				
				if (stone != null){
					
					StoneObject stoneObject = new StoneObject(stone);
					this.add(stoneObject);
					
					stoneContainer.add(stoneObject);
				}
			}
		}
		
		// Repaint
		stoneContainer.repaint();
		
	}
	
	public void undo(){
		this.controller.prevTurn();
	}
	
	public void reset(){
		if (swingTimer != null){
			swingTimer.stop();
		}
		this.controller = new GameController();
		this.board = new Board();
		drawStones();
	}
	
	
	public void step(){
		
		System.out.println("step");
		
		if (controller.playingColor == Player.BLACK && player1Algorithm != null){
			handleAlgorithmStep(player1Algorithm,Player.BLACK);
		}
		else if (controller.playingColor == Player.WHITE && player2Algorithm != null){
			handleAlgorithmStep(player2Algorithm,Player.WHITE);
		}
		else {
			inputEnabled = true;

            if (swingTimer != null){
                swingTimer.stop();
            }

		}
		
		drawStones();
		
	}
	
	public void handleAlgorithmStep(Algorithm algorithm, Player player){

		MoveSet moveSet = algorithm.doMove(board, player, controller.turn);
		
		moveSet.stone1.stoneNr = this.controller.getNextStoneNr();
		board.place(moveSet.stone1);
		controller.addStone(moveSet.stone1);

        if (moveSet.stone2 != null){
            moveSet.stone2.stoneNr = this.controller.getNextStoneNr();
            board.place(moveSet.stone2);
            controller.addStone(moveSet.stone2);
        }
		
		if (this.board.gameEnded){
			inputEnabled = false;
			System.out.println("Game ended");
		}
		else {
			step();
		}

	}
	
	public void doStep(){
		
	};
	
	public void save(){
		
	}
	
	ActionListener listener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent event){
			step();
	  	}
	};
	
}
