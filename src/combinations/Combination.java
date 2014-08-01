package combinations;

import logic.Board;
import logic.Direction;
import logic.Player;
import logic.Position;
import logic.Stone;

public class Combination 
{
	// Direction of the dead three
	public int direction; 
	// the matched pattern type
	public int type;
	// Is it a two, three, four, five or six
	public int combinationType;
	// Combination has always a first stone
	public Stone stone1 = null;
	public Stone stone2 = null;
	public Stone stone3 = null;
	public Stone stone4 = null;
	public Stone stone5 = null;
	public Stone stone6 = null;
	public Stone lastStone = null;
	
	/**
	 * Constructor
	 */
	public Combination(int direction, int type, int combinationType, Stone stone1, Stone lastStone)
	{
		this.combinationType = combinationType;
		this.direction = direction;
		this.type = type;
		this.lastStone = lastStone;
		this.stone1 = stone1;
	}
	
	public static void addCombination(Board board, Player player, Combination c, Stone firstStone, Stone lastStone)
	{
		
		/*
		//System.out.println("Combination type: " + c.combinationType + " type: " + c.type + " type: " + c.type + " direction: " + c.direction);
		String key1 = firstStone.y + "-" + firstStone.x + "," + lastStone.y + "-" + lastStone.x;
		String key2 = lastStone.y + "-" + lastStone.x + "," + firstStone.y + "-" + firstStone.x;
		
		// If it not already contains the opposit key
		if (!board.getCombinations(colorNr).containsKey(key2))
		{
			board.getCombinations(colorNr).put(key1, c);
		}
		*/
		board.getCombinations(player).add(c);
	}
	
	/**
	 * A simple check to see if a combination is even possible
	 * A openents stone is searched in a row
	 * Speeds up the pattern selection
	 * Returns false if no combination can be used
	 */
	public static boolean preCheck(Board b, int direction, int length, Stone s)
	{
		if (s == null)
			return true;
		
		Player openentColor;
		if (s.player == Player.BLACK)
			openentColor = Player.WHITE;
		else
			openentColor = Player.BLACK;
		
		// check for each position of the given length
		for (int d = 1;d<length;d++)
		{
			Position tryPos = Direction.getPosInDirection(s.x, s.y, direction, d);
			if (tryPos == null)
				return false;
			if (b.field[tryPos.y][tryPos.x] == openentColor)
				return false;
		}
		
		return true;
	}
	
	/**
	 * Used on combinations two and three, to check if they are active
	 * @return
	 */
	public boolean checkLive()
	{
		if (this.combinationType == 3)
		{
			Three three = (Three)this;

	 		if (three.type == 0 || three.type == 3 || three.type == 5 || three.type == 8 || three.type == 10 || three.type == 13)
	 		{
	 			return true;
	 		}
		}
		else if (this.combinationType == 2)
		{
			Two two = (Two)this;

	 		if (two.type == 0 || two.type == 3 || two.type == 5 || two.type == 8 || two.type == 10 || two.type == 13)
	 		{
	 			return true;
	 		}

		}
		
		return false;
	}
}
