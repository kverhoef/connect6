package userinterface;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import logic.Player;
import logic.Stone;

public class StoneObject extends JLabel
{
	private static final long serialVersionUID = 1L;

	public StoneObject(Stone stone)
	{
		Icon c;
		if (stone.player == Player.WHITE)
		{
			c = new ImageIcon(getClass().getResource("/images/white-stone.gif"));
		}
		else
		{
			this.setForeground(Color.white);
			c = new ImageIcon(getClass().getResource("/images/black-stone.gif"));
		}
		
		this.setIcon(c);

		this.setText(String.valueOf(stone.stoneNr));

		this.setVerticalTextPosition(JLabel.CENTER);
		this.setHorizontalTextPosition(JLabel.CENTER);

		int[] pos = getFieldPosition(stone.x,stone.y);
		
		this.setBounds(pos[0],pos[1],32,32);
	}
	
	public static int[] getFieldPosition(int x,int y)
	{
		int topOffset = 16;
		int leftOffset = 26;
		
		// determine positions on the field
		int newX = (int)Math.floor((x * 32) + leftOffset);
		int newY = (int)Math.floor((y * 32) + topOffset);
		
		int[] pos = new int[2];
		pos[0] = newX;
		pos[1] = newY;
		return pos;
	}

}
