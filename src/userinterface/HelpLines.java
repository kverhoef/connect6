package userinterface;

import java.awt.*;

import javax.swing.*;

import combinations.Combination;
import combinations.Five;
import combinations.Four;
import combinations.Six;
import combinations.Three;
import combinations.Two;
import logic.Board;
import logic.Player;

/**
 * Used for drawing the helplines for the combinations.
 */
public class HelpLines extends JPanel
{   
	private static final long serialVersionUID = 1L;
	UI applet;
	
	/**
	 * Constructor
	 */
	public HelpLines(UI applet)
	{
		this.applet = applet;
	}     
	
	/**
	 * Repaints the lines.
	 */
	public void paintComponent(Graphics g)
	{
		drawLines(g,Player.BLACK);
		drawLines(g,Player.WHITE);
	}
	
	/**
	 * Draws the helplines for combinations
	 */
	private void drawLines(Graphics g, Player colorNr)
	{
		if (applet.drawHelpLines)
		{
			Board b = applet.board;
			
		    for(int i=0;i < b.getCombinations(colorNr).size();i++)
			{
				Combination c = b.getCombinations(colorNr).get(i);
				Color color;
				
				int[] pos1;
				int[] pos2;
				
				if (c.combinationType == 2)
				{
					Two two = (Two)c;
					pos1 = StoneObject.getFieldPosition(two.stone1.x,two.stone1.y);
			 		pos2 = StoneObject.getFieldPosition(two.stone2.x,two.stone2.y);

			 		if (two.type == 0 || two.type == 3 || two.type == 5 || two.type == 8 || two.type == 10 || two.type == 13)
			 		{
			 			color = Color.BLUE;
			 		}
			 		else
			 		{
			 			color = Color.darkGray;
			 		}
			 		
				}
				else if (c.combinationType == 3)
				{
					Three three = (Three)c;
					pos1 = StoneObject.getFieldPosition(three.stone1.x,three.stone1.y);
			 		pos2 = StoneObject.getFieldPosition(three.stone3.x,three.stone3.y);
			 		
			 		if (three.type == 0 || three.type == 3 || three.type == 5 || three.type == 8 || three.type == 10 || three.type == 13)
			 		{
			 			color = Color.orange;
			 		}
			 		else
			 		{
			 			color = Color.CYAN;
			 		}
				}
				else if (c.combinationType == 4)
				{
					Four four = (Four)c;
					pos1 = StoneObject.getFieldPosition(four.stone1.x,four.stone1.y);
			 		pos2 = StoneObject.getFieldPosition(four.stone4.x,four.stone4.y);
			 		
			 		color = Color.red;
				}
				else if (c.combinationType == 5)
				{
					Five five = (Five)c;
					pos1 = StoneObject.getFieldPosition(five.stone1.x,five.stone1.y);
			 		pos2 = StoneObject.getFieldPosition(five.stone5.x,five.stone5.y);
			 		
			 		color = Color.pink;
				}
				else
				{
					Six six = (Six)c;
					pos1 = StoneObject.getFieldPosition(six.stone1.x,six.stone1.y);
			 		pos2 = StoneObject.getFieldPosition(six.stone6.x,six.stone6.y);
			 		color = Color.green;
				}
				
				if (color != Color.darkGray)
				{
					// Draw the line
					drawLine(g,pos1,pos2,color);
				}
			}
		}
	}

	/**
	 * Draw one line
	 */
	private void drawLine(Graphics g,int[] pos1, int[] pos2, Color c)
	{
		drawThickLine(g,pos1[0] + 16,pos1[1] + 16,pos2[0] + 16,pos2[1] + 16,4,c);
	}
	
	/**
	 * Draw thick lines
	 * Used from http://www.rgagnon.com/javadetails/java-0260.html
	 */
	private void drawThickLine(Graphics g, int x1, int y1, int x2, int y2, int thickness, Color c) 
	{
		// The thick line is in fact a filled polygon
		g.setColor(c);
		int dX = x2 - x1;
		int dY = y2 - y1;
		
		// line length
		double lineLength = Math.sqrt(dX * dX + dY * dY);

		double scale = (double)(thickness) / (2 * lineLength);

		// The x,y increments from an endpoint needed to create a rectangle...
		double ddx = -scale * (double)dY;
		double ddy = scale * (double)dX;
		ddx += (ddx > 0) ? 0.5 : -0.5;
		ddy += (ddy > 0) ? 0.5 : -0.5;
		int dx = (int)ddx;
		int dy = (int)ddy;

		// Now we can compute the corner points...
		int xPoints[] = new int[4];
		int yPoints[] = new int[4];

		xPoints[0] = x1 + dx; yPoints[0] = y1 + dy;
		xPoints[1] = x1 - dx; yPoints[1] = y1 - dy;
		xPoints[2] = x2 - dx; yPoints[2] = y2 - dy;
		xPoints[3] = x2 + dx; yPoints[3] = y2 + dy;

		g.fillPolygon(xPoints, yPoints, 4);
	}
	
}