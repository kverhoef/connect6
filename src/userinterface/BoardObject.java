package userinterface;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import logic.Stone;

/**
 * A graphical representation of the board.
 * It also handels the mouse clicks on the board for placing human player stones.
 */
public class BoardObject extends JLabel implements MouseListener 
{
	private static final long serialVersionUID = 1L;
	UI applet;
	
	/**
	 * Constructor
	 */
	public BoardObject(UI applet)
	{
		
		Icon c = new ImageIcon(getClass().getResource("/images/field.gif"));
		this.setIcon(c);
		this.setBounds(0,0,650,672);
		this.addMouseListener(this);
		this.applet = applet;
	}

	/**
	 * Handles the mouse clicks for placing stone.
	 */
	public void mouseClicked(MouseEvent e) 
	{
		// Offset from the board image.
		int topOffset = 16;
		int leftOffset = 26;

		// determine positions on the field
		int x = (int)Math.floor((e.getX() - leftOffset) / 32);
		int y = (int)Math.floor((e.getY() - topOffset) / 32);
		
		if (x < 19 && y < 19)
		{

			// Voeg toe aan bord
			Stone newStone = new Stone(x,y,applet.controller.playingColor);
			
			// Probeer te plaatsen
			if (newStone.place(applet.board))
			{
				newStone.stoneNr = applet.controller.getNextStoneNr();
				applet.drawStones();
		
				applet.controller.addStone(newStone);

				if (applet.inputEnabled)
				{
					// Check to see next step
					if (applet.controller.firstStone)
					{
						applet.step();
					}
				}

			}
		}
	}

	public void mouseEntered(MouseEvent arg0) 
	{

	}

	public void mouseExited(MouseEvent arg0) 
	{

	}

	public void mousePressed(MouseEvent arg0) 
	{

	}

	public void mouseReleased(MouseEvent arg0)
	{

	}

}
