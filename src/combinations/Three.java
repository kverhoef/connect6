package combinations;

import logic.Board;
import logic.Direction;
import logic.Pattern;
import logic.PatternCollection;
import logic.Position;
import logic.Stone;

public class Three extends Combination
{
	
	/**
	 * Constructor
	 */
	public Three(Stone stone1, Stone stone2, Stone stone3, int direction, int type)
	{
		super(direction, type, 3, stone1, stone3);
		this.stone1 = stone1;
		this.stone2 = stone2;
		this.stone3 = stone3;
		this.stone4 = null;
		this.stone5 = null;
		this.stone6 = null;
	}
	
	public static boolean checkThree(Board board, int x, int y, int direction)
	{
		// case 1: three next to each other
		// type 0: completly open
		int[][] three0 = 
		{
			{0,0,2,2,2,1,1,1,2,2,2}
		}; // alive
		
		// type 1: 1 side blocked
		int[][] three1 = 
		{
			{0,0,0,0,3,1,1,1,2,2,2}
		}; // dead
		
		// type 2: 1 side blocked
		int[][] three2 = 
		{
			{0,0,0,3,2,1,1,1,2,2,2}
		}; // dead
		
		// type 3: 1 side blocked
		int[][] three3 = 
		{
			{0,0,3,2,2,1,1,1,2,2,2}
		}; // alive
		
		// type 4: two sides blocked
		int[][] three4 = 
		{
			{0,0,3,2,2,1,1,1,2,2,3}
		}; // dead
		
		// case 2: 2 next to eachother, 1 open, 1
		// type 5: completly open
		int[][] three5 = 
		{
			{0,0,2,2,2,1,1,2,1,2,2}
		}; // alive
		
		// type 6: blocked 1 side
		int[][] three6 = 
		{
			{0,0,0,0,3,1,1,2,1,2,2}
		}; // dead
		// type 7: blocked 1 side
		int[][] three7 = 
		{
			{0,0,0,3,2,1,1,2,1,2,2}
		}; // dead
		
		// type 8: blocked 1 side
		int[][] three8 = 
		{
			{0,0,3,2,2,1,1,2,1,2,2}
		}; // alive
		
		// type 9: blocked 2 sides
		int[][] three9 = 
		{
			{0,0,3,2,2,1,1,2,1,2,3}
		}; // dead
		
		// case 3: 1 next to eachother, 1 open, 2
		// type 10: completly open
		int[][] three10 = 
		{
			{0,0,2,2,2,1,2,1,1,2,2}
		}; // alive
		// type 11: blocked 1 side
		int[][] three11 = 
		{
			{0,0,0,0,3,1,2,1,1,2,2}
		}; // dead
		
		// type 12: blocked 1 side
		int[][] three12 = 
		{
			{0,0,0,3,2,1,2,1,1,2,2}
		}; // dead
		
		// type 13: blocked 1 side
		int[][] three13 = 
		{
			{0,0,3,2,2,1,2,1,1,2,2}
		}; // alive
		
		// type 14: blocked 1 side
		int[][] three14 = 
		{
			{0,0,3,2,2,1,2,1,1,2,3}
		}; // dead
		
		// case4: 2, 2 open, 1
		// type 15: completly open
		int[][] three15 = 
		{
			{0,0,0,0,2,1,1,2,2,1,2}
		}; // dead
		// type 16: blocked 1 side
		int[][] three16 = 
		{
			{0,0,0,0,3,1,1,2,2,1,2}
		}; // dead
		// type 17: blocked 1 side
		int[][] three17 = 
		{
			{0,0,0,3,2,1,1,2,2,1,2}
		}; // dead
		// type 18: blocked 2 side
		int[][] three18 = 
		{
			{0,0,0,3,2,1,1,2,2,1,3}
		}; // dead
		
		// case5: 1, 2 open, 2
		// type 19: completly open
		int[][] three19 = 
		{
			{0,0,0,2,2,1,2,2,1,1,2}
		}; // dead
		// type 20: blocked 1 side
		int[][] three20 = 
		{
			{0,0,0,0,3,1,2,2,1,1,2}
		}; // dead
		// type 21: blocked 1 side
		int[][] three21 = 
		{
			{0,0,0,3,2,1,2,2,1,1,2}
		}; // dead
		// type 22: blocked 2 side
		int[][] three22 = 
		{
			{0,0,0,3,2,1,2,2,1,1,3}
		}; // dead
		
		// case 6: 1, 1 open, 1, 1 open
		// type 23: both sides open
		int[][] three23 = 
		{
			{0,0,0,0,2,1,2,1,2,1,2}
		}; // dead
		// type 24: 1 side blocked
		int[][] three24 = 
		{
			{0,0,0,0,3,1,2,1,2,1,2}
		}; // dead
		
		// Add all patterns to a collection
		PatternCollection threePatterns = new PatternCollection();
		threePatterns.addPatern(new Pattern(three0,true));
		threePatterns.addPatern(new Pattern(three1,true));
		threePatterns.addPatern(new Pattern(three2,true));
		threePatterns.addPatern(new Pattern(three3,true));
		threePatterns.addPatern(new Pattern(three4,true));
		threePatterns.addPatern(new Pattern(three5,true));
		threePatterns.addPatern(new Pattern(three6,true));
		threePatterns.addPatern(new Pattern(three7,true));
		threePatterns.addPatern(new Pattern(three8,true));
		threePatterns.addPatern(new Pattern(three9,true));
		threePatterns.addPatern(new Pattern(three10,true));
		threePatterns.addPatern(new Pattern(three11,true));
		threePatterns.addPatern(new Pattern(three12,true));
		threePatterns.addPatern(new Pattern(three13,true));
		threePatterns.addPatern(new Pattern(three14,true));
		threePatterns.addPatern(new Pattern(three15,true));
		threePatterns.addPatern(new Pattern(three16,true));
		threePatterns.addPatern(new Pattern(three17,true));
		threePatterns.addPatern(new Pattern(three18,true));
		threePatterns.addPatern(new Pattern(three19,true));
		threePatterns.addPatern(new Pattern(three20,true));
		threePatterns.addPatern(new Pattern(three21,true));
		threePatterns.addPatern(new Pattern(three22,true));
		threePatterns.addPatern(new Pattern(three23,true));
		threePatterns.addPatern(new Pattern(three24,true));
		
		// 1 is always current position
		Stone stone1 = board.getStone(x, y);
		
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
				threePatterns.rotatePatterns();
			}
		}
		// rotate 45 degrees from 3
		if (direction > 3)
		{
			rotate45degrees = true;
		}
			
		if (preCheck(board, direction, 3, stone1))
		{
			// if pattern matched, it will return the matched number
			int patternMatchNr = threePatterns.checkPatterns(board.field, x, y,rotate45degrees);
			
			// check if dead
			if (threePatterns.checkPatterns(board.field, x, y,rotate45degrees) != -1)
			{
				Position posStone2;
				Position posStone3;
				
				// case 1: 3 next to each other
				if (patternMatchNr == 0 || patternMatchNr == 1 || patternMatchNr == 2 || patternMatchNr == 3 || patternMatchNr == 4)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 1);
					posStone3 = Direction.getPosInDirection(x, y, direction, 2);
				}
				// case 2: 2 next to eachother, 1 open, 1
				else if (patternMatchNr == 5 || patternMatchNr == 6 || patternMatchNr == 7 || patternMatchNr == 8 || patternMatchNr == 9)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 1);
					posStone3 = Direction.getPosInDirection(x, y, direction, 3);
				}
				// case 3: 1 next to eachother, 1 open, 2
				else if (patternMatchNr == 10 || patternMatchNr == 11 || patternMatchNr == 12 || patternMatchNr == 13 || patternMatchNr == 14)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 2);
					posStone3 = Direction.getPosInDirection(x, y, direction, 3);
				}
				// case4: 2, 2 open, 1
				else if (patternMatchNr == 15 || patternMatchNr == 16 || patternMatchNr == 17 || patternMatchNr == 18)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 1);
					posStone3 = Direction.getPosInDirection(x, y, direction, 4);
				}
				// case5: 1, 2 open, 2
				else if (patternMatchNr == 19 || patternMatchNr == 20 || patternMatchNr == 21 || patternMatchNr == 22)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 3);
					posStone3 = Direction.getPosInDirection(x, y, direction, 4);
				}
				// case 6: 1, 1 open, 1, 1 open
				else
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 2);
					posStone3 = Direction.getPosInDirection(x, y, direction, 4);
				}
				
				//stone1 = board.getStone(x, y);
				Stone stone2 = board.getStone(posStone2.x, posStone2.y);
				Stone stone3 = board.getStone(posStone3.x, posStone3.y);
				
				try
				{
					// Add the threat
					Three c = new Three(stone1,stone2,stone3,direction,patternMatchNr);
					c.stone1.combinations[direction] = c;
					c.stone2.combinations[direction] = c;
					c.stone3.combinations[direction] = c;
					
					Combination.addCombination(board, stone1.player, c,stone1,stone3);
				}
				catch (NullPointerException e)
				{
					e.printStackTrace();
					System.out.println("Pattern: " + patternMatchNr);
				}
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Add three combination without adding dupicates
	 
	public static void addThree(Board board, Three three)
	{
		String key1 = three.stone1.y + "-" + three.stone1.x + "," + three.stone3.y + "-" + three.stone3.x;
		String key2 = three.stone3.y + "-" + three.stone3.x + "," + three.stone1.y + "-" + three.stone1.x;
		
		int colorNr = three.stone1.colorNr;
		
		// If it not already contains the opposit key
		if (!board.getThreeMap(colorNr).containsKey(key2))
		{
			board.getThreeMap(colorNr).put(key1, three);
		}
	}
	*/
}
