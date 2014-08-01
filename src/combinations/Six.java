package combinations;

import logic.Board;
import logic.Direction;
import logic.Pattern;
import logic.Position;
import logic.Stone;

/**
 * When a player has a connect6 he has won the game!
 * Used for checking and displaying purposes
 */
public class Six extends Combination
{
	
	/**
	 * Constructor
	 */
	public Six(Stone stone1, Stone stone2, Stone stone3, Stone stone4, Stone stone5, Stone stone6)
	{
		// Combination and type not important
		super(0, 0, 6,stone1,stone6);
		this.stone1 = stone1;
		this.stone2 = stone2;
		this.stone3 = stone3;
		this.stone4 = stone4;
		this.stone5 = stone5;
		this.stone6 = stone6;
	}
	
	/**
	 * Only found when checked on third or fourth stone.
	 */
	public static boolean checkConnectSix(Board board, int x, int y, int direction)
	{
		
		Stone stone1 = board.getStone(x, y);
		
		if (stone1 != null)
		{
			
			// Only 1 type available
			int[][] connectSixCheck = 
			{
				{0,0,0,0,0,1,1,1,1,1,1}
			};
			
			Pattern p = new Pattern(connectSixCheck);
			
			// For each direction
			boolean rotate45degrees = false;
			
			// If not last, rotate
			if (direction > 0)
			{
				int rotate = direction;
				if (rotate > 3)
					rotate -= 4;
				for (int i = 0;i<rotate;i++)
				{
					p.rotatePattern();
				}
			}
			// rotate 45 degrees from 3
			if (direction > 3)
			{
				rotate45degrees = true;
			}
			
			// check if connectSix
			if (p.checkPattern(board.field, x, y, rotate45degrees))
			{
				//int[] posStone1 = Direction.getPosInDirection(x, y, direction, 1);
				Position posStone2 = Direction.getPosInDirection(x, y, direction, 1);
				Position posStone3 = Direction.getPosInDirection(x, y, direction, 2);
				Position posStone4 = Direction.getPosInDirection(x, y, direction, 3);
				Position posStone5 = Direction.getPosInDirection(x, y, direction, 4);
				Position posStone6 = Direction.getPosInDirection(x, y, direction, 5);
				
				
				Stone stone2 = board.getStone(posStone2.x, posStone2.y);
				Stone stone3 = board.getStone(posStone3.x, posStone3.y);
				Stone stone4 = board.getStone(posStone4.x, posStone4.y);
				Stone stone5 = board.getStone(posStone5.x, posStone5.y);
				Stone stone6 = board.getStone(posStone6.x, posStone6.y);
				
				Six c = new Six(stone1, stone2, stone3, stone4,stone5,stone6);
				try
				{
				// add the ConnectSix
				
				c.stone1.combinations[direction] = c;
				c.stone2.combinations[direction] = c;
				c.stone3.combinations[direction] = c;
				c.stone4.combinations[direction] = c;
				c.stone5.combinations[direction] = c;
				c.stone6.combinations[direction] = c;
				Combination.addCombination(board, stone1.player, c,stone1,stone6);
				}
				catch (NullPointerException e)
				{
					System.out.println("Pattern: " + c.stone4);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Add connect six without adding duplicate using keys
	
	public static void addConnectSix(Board board, Six connectSix)
	{
		String key1 = connectSix.stone1.y + "-" + connectSix.stone1.x + "," + connectSix.stone6.y + "-" + connectSix.stone6.x;
		String key2 = connectSix.stone6.y + "-" + connectSix.stone6.x + "," + connectSix.stone1.y + "-" + connectSix.stone1.x;
		
		int colorNr = connectSix.stone1.colorNr;
		
		// If it not already contains the opposit key
		if (!board.getSixMap(colorNr).containsKey(key2))
		{
			board.getSixMap(colorNr).put(key1, connectSix);
		}
	}
	*/
}
