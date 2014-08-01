package userinterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import algorithms.Algorithm;
import algorithms.HardBot;
import algorithms.Randoms;
import algorithms.SimpleBot;
import logic.Player;

/**
 * Creates the application menu
 */
public class Menu extends JMenuBar
{
	private static final long serialVersionUID = 1L;
	UI applet;
	
	public Menu(UI ui)
	{
		this.applet = ui;
		
		// Menu knop
		JMenu menuFile = new JMenu("File");
		menuFile.setMnemonic(KeyEvent.VK_F);
	
		JMenu menuEdit = new JMenu("Edit");
		menuEdit.setMnemonic(KeyEvent.VK_E);
		
		JMenu menuPlayer = new JMenu("Player");
		menuPlayer.setMnemonic(KeyEvent.VK_P);
		
		JMenu menuOptions = new JMenu("Options");
		menuOptions.setMnemonic(KeyEvent.VK_O);
	
		// Het menu item
		JMenuItem menuItemSave = new JMenuItem("Save", KeyEvent.VK_S);
		menuItemSave.addActionListener(new SaveButtonListener());
		menuFile.add(menuItemSave);
		
		// Menu editItems
		JMenuItem menuItemStart = new JMenuItem("Start");
		menuItemStart.addActionListener(new StartButtonListener());
		menuEdit.add(menuItemStart);
		
		// Menu editItems
		JMenuItem menuItemStop = new JMenuItem("Stop");
		menuItemStop.addActionListener(new StopButtonListener());
		menuEdit.add(menuItemStop);
		
		JMenuItem menuItemUndo = new JMenuItem("Undo", KeyEvent.VK_U);
		menuItemUndo.addActionListener(new UndoButtonListener());
		menuEdit.add(menuItemUndo);
		
		JMenuItem menuItemReset = new JMenuItem("Reset");
		menuItemReset.addActionListener(new ResetButtonListener());
		menuEdit.add(menuItemReset);
		
		// Player menu
		JMenu blackPlayer = new JMenu("Black Player");
		menuPlayer.add(blackPlayer);
		JMenu whitePlayer = new JMenu("White Player");
		menuPlayer.add(whitePlayer);
		
		ButtonGroup whitePlayerMenugroup = new ButtonGroup();
		
		JRadioButtonMenuItem rbWhite1 = new JRadioButtonMenuItem("Human Player");
		rbWhite1.addActionListener(new AlgorithmSelectButtonListener(Player.WHITE, null));
		rbWhite1.setSelected(true);
		
		JRadioButtonMenuItem rbWhite2 = new JRadioButtonMenuItem("Randoms");
		rbWhite2.addActionListener(new AlgorithmSelectButtonListener(Player.WHITE, new Randoms()));
		
//		JRadioButtonMenuItem rbWhite3 = new JRadioButtonMenuItem("Advanced bot");
//		rbWhite3.addActionListener(new AlgorithmSelectButtonListener(Player.WHITE,"Advanced bot"));
//		
		JRadioButtonMenuItem rbWhite4 = new JRadioButtonMenuItem("Simple bot");
        rbWhite4.addActionListener(new AlgorithmSelectButtonListener(Player.WHITE, new SimpleBot(5)));

		JRadioButtonMenuItem rbWhite5 = new JRadioButtonMenuItem("Hard bot");
		rbWhite5.addActionListener(new AlgorithmSelectButtonListener(Player.WHITE,new HardBot(5)));

		whitePlayerMenugroup.add(rbWhite1);
		whitePlayerMenugroup.add(rbWhite2);
//		whitePlayerMenugroup.add(rbWhite3);
		whitePlayerMenugroup.add(rbWhite4);
		whitePlayerMenugroup.add(rbWhite5);

		
		whitePlayer.add(rbWhite1);
		whitePlayer.add(rbWhite2);
//		whitePlayer.add(rbWhite3);
		whitePlayer.add(rbWhite4);
		whitePlayer.add(rbWhite5);

		ButtonGroup blackPlayerMenugroup = new ButtonGroup();
		
		JRadioButtonMenuItem rbBlack1 = new JRadioButtonMenuItem("Human Player");
		rbBlack1.addActionListener(new AlgorithmSelectButtonListener(Player.BLACK,null));
		
		JRadioButtonMenuItem rbBlack2 = new JRadioButtonMenuItem("Randoms");
		rbBlack2.addActionListener(new AlgorithmSelectButtonListener(Player.BLACK,new Randoms()));
		
//		JRadioButtonMenuItem rbBlack3 = new JRadioButtonMenuItem("Advanced bot");
//		rbBlack3.addActionListener(new AlgorithmSelectButtonListener(Player.BLACK,"Advanced bot"));
//		
		JRadioButtonMenuItem rbBlack4 = new JRadioButtonMenuItem("Simple bot");
		rbBlack4.addActionListener(new AlgorithmSelectButtonListener(Player.BLACK,new SimpleBot(5)));
		
		JRadioButtonMenuItem rbBlack5 = new JRadioButtonMenuItem("Hard bot");
		rbBlack5.addActionListener(new AlgorithmSelectButtonListener(Player.BLACK,new HardBot(5)));
		rbBlack5.setSelected(true);
		
		
		blackPlayerMenugroup.add(rbBlack1);
		blackPlayerMenugroup.add(rbBlack2);
//		blackPlayerMenugroup.add(rbBlack3);
		blackPlayerMenugroup.add(rbBlack4);
		blackPlayerMenugroup.add(rbBlack5);


		blackPlayer.add(rbBlack1);
		blackPlayer.add(rbBlack2);
//		blackPlayer.add(rbBlack3);
		blackPlayer.add(rbBlack4);
		blackPlayer.add(rbBlack5);
		
		
		//blackPlayer.add(rbBlack6);

		// option menu
		JMenu drawLines = new JMenu("Draw Lines");
		menuOptions.add(drawLines);
		
		ButtonGroup helpLinesMenugroup = new ButtonGroup();
		
		JRadioButtonMenuItem rbLinesYes = new JRadioButtonMenuItem("Yes");
		rbLinesYes.addActionListener(new OptionLinesListener(true));
		rbLinesYes.setSelected(true);
		
		JRadioButtonMenuItem rbLinesNo = new JRadioButtonMenuItem("No");
		rbLinesNo.addActionListener(new OptionLinesListener(false));
		
		helpLinesMenugroup.add(rbLinesYes);
		helpLinesMenugroup.add(rbLinesNo);
		
		drawLines.add(rbLinesYes);
		drawLines.add(rbLinesNo);
		
		this.add(menuFile);
		this.add(menuEdit);
		this.add(menuPlayer);
		this.add(menuOptions);
	}
	
	class AlgorithmSelectButtonListener implements ActionListener 
	{
		Player player;
		Algorithm algorithm;
		
		public AlgorithmSelectButtonListener(Player player, Algorithm algorithm)
		{
			this.player = player;
			this.algorithm = algorithm;
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			applet.selectAlgorithm(player, algorithm);
	    }
	}
	
	class OptionLinesListener implements ActionListener 
	{
		boolean setLines;
		
		public OptionLinesListener(boolean setLines)
		{
			this.setLines = setLines;
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			applet.drawHelpLines = setLines;
			applet.drawStones();
	    }
	}
	
	class ResetButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			applet.reset();
	    }
	}
	
	class UndoButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			applet.undo();
	    }
	}
	
	class StartButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			applet.start();
	    }
	}
	
	class StopButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			applet.stop();
	    }
	}
	
	class SaveButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			applet.save();
	    }
	}

}
