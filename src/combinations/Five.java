package combinations;

import logic.Board;
import logic.Direction;
import logic.Pattern;
import logic.PatternCollection;
import logic.Position;
import logic.Stone;

public class Five extends Combination
{
	
	public Five(Stone stone1, Stone stone2, Stone stone3, Stone stone4, Stone stone5, int direction, int type)
	{
		super(direction, type,5,stone1,stone5);
		this.stone1 = stone1;
		this.stone2 = stone2;
		this.stone3 = stone3;
		this.stone4 = stone4;
		this.stone5 = stone5;
	}
	
	/**
	 * Check if a stone is five threat
	 * Note: 	FiveThreats will only be found if checked on the 
	 * 			first or last stone of the five threat.
	 */
	public static boolean checkFive(Board board, int x, int y, int direction)
	{

		// case 1: 5 next to each other
		// type0: both sides open
		int[][] fiveThreat0 = 
		{
			{0,0,0,2,2,1,1,1,1,1,2}
		};
		// type1: one side blocked
		int[][] fiveThreat1 = 
		{
			{0,0,0,0,3,1,1,1,1,1,2}
		};
		// type2: one side blocked
		int[][] fiveThreat2 = 
		{
			{0,0,0,3,2,1,1,1,1,1,2}
		};
		
		// case 2: 4 next to each other, 1 open between fourt and five
		// type3 completly open
		int[][] fiveThreat3 = 
		{
			{0,0,0,2,2,1,1,1,1,2,1}
		};
		// type4: blocked 1 side
		int[][] fiveThreat4 = 
		{
			{0,0,0,0,3,1,1,1,1,2,1}
		};
		// type5: blocked 1 side
		int[][] fiveThreat5 = 
		{
			{0,0,0,3,2,1,1,1,1,2,1}
		};
		
		
		// case 3: 3 next to each other, 1 open between third and fourth
		// type6
		int[][] fiveThreat6 = 
		{
			{0,0,0,2,2,1,1,1,2,1,1}
		};
		// type7
		int[][] fiveThreat7 = 
		{
			{0,0,0,0,3,1,1,1,2,1,1}
		};
		// type8
		int[][] fiveThreat8 = 
		{
			{0,0,0,3,2,1,1,1,2,1,1}
		};
		
		// case 4: 3 next to each other, 2 open, 2 next to eachother
		// type9
		int[][] fiveThreat9 = 
		{
			{0,0,0,0,2,2,1,1,1,2,2,1,1}
		};
		int[][] fiveThreat10 = 
		{
			{0,0,0,0,0,3,1,1,1,2,2,1,1}
		};
		int[][] fiveThreat11 = 
		{
			{0,0,0,0,3,2,1,1,1,2,2,1,1}
		};
		
		
		// case 5: 6 next to each other, 2 open, 1 
		// type12
		int[][] fiveThreat12 = 
		{
			{0,0,0,0,2,2,1,1,1,1,2,2,1}
		};
		// type13
		int[][] fiveThreat13 = 
		{
			{0,0,0,0,0,3,1,1,1,1,2,2,1}
		};
		// type14
		int[][] fiveThreat14 = 
		{
			{0,0,0,0,3,2,1,1,1,1,2,2,1}
		};
		
		// case 6: 2, 1open, 1, 1open, 2
		// type15
		int[][] fiveThreat15 = 
		{
			{0,0,0,0,2,2,1,1,2,1,2,1,1}
		};
		// type16
		int[][] fiveThreat16 = 
		{
			{0,0,0,0,0,3,1,1,2,1,2,1,1}
		};
		// type17
		int[][] fiveThreat17 = 
		{
			{0,0,0,0,3,2,1,1,2,1,2,1,1}
		};
		
		// Add all patterns to a collection
		PatternCollection fiveThreatsPatterns = new PatternCollection();
		fiveThreatsPatterns.addPatern(new Pattern(fiveThreat0,true));
		fiveThreatsPatterns.addPatern(new Pattern(fiveThreat1,true));
		fiveThreatsPatterns.addPatern(new Pattern(fiveThreat2,true));
		fiveThreatsPatterns.addPatern(new Pattern(fiveThreat3,true));
		fiveThreatsPatterns.addPatern(new Pattern(fiveThreat4,true));
		fiveThreatsPatterns.addPatern(new Pattern(fiveThreat5,true));
		fiveThreatsPatterns.addPatern(new Pattern(fiveThreat6,true));
		fiveThreatsPatterns.addPatern(new Pattern(fiveThreat7,true));
		fiveThreatsPatterns.addPatern(new Pattern(fiveThreat8,true));
		fiveThreatsPatterns.addPatern(new Pattern(fiveThreat9,true));
		fiveThreatsPatterns.addPatern(new Pattern(fiveThreat10,true));
		fiveThreatsPatterns.addPatern(new Pattern(fiveThreat11,true));
		fiveThreatsPatterns.addPatern(new Pattern(fiveThreat12,true));
		fiveThreatsPatterns.addPatern(new Pattern(fiveThreat13,true));
		fiveThreatsPatterns.addPatern(new Pattern(fiveThreat14,true));
		fiveThreatsPatterns.addPatern(new Pattern(fiveThreat15,true));
		fiveThreatsPatterns.addPatern(new Pattern(fiveThreat16,true));
		fiveThreatsPatterns.addPatern(new Pattern(fiveThreat17,true));
		
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
				fiveThreatsPatterns.rotatePatterns();
			}
		}
		// rotate 45 degrees from 3
		if (direction > 3)
		{
			rotate45degrees = true;
		}
			
		if (preCheck(board, direction, 5, stone1))
		{
			
			// if pattern matched, it will return the matched number
			int patternMatchNr = fiveThreatsPatterns.checkPatterns(board.field, x, y,rotate45degrees);
			
			// check if dead
			if (fiveThreatsPatterns.checkPatterns(board.field, x, y,rotate45degrees) != -1)
			{
				Position posStone2;
				Position posStone3;
				Position posStone4;
				Position posStone5;
				
				// case 1: 5 next to each other
				if (patternMatchNr == 0 || patternMatchNr == 1 || patternMatchNr == 2)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 1);
					posStone3 = Direction.getPosInDirection(x, y, direction, 2);
					posStone4 = Direction.getPosInDirection(x, y, direction, 3);
					posStone5 = Direction.getPosInDirection(x, y, direction, 4);
				}
				// case 2: 4 next to each other, 1 open between fourt and five
				else if (patternMatchNr == 3 || patternMatchNr == 4 || patternMatchNr == 5)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 1);
					posStone3 = Direction.getPosInDirection(x, y, direction, 2);
					posStone4 = Direction.getPosInDirection(x, y, direction, 3);
					posStone5 = Direction.getPosInDirection(x, y, direction, 5);
				}
				// case 3: 3 next to each other, 1 open between third and fourth
				else if (patternMatchNr == 6 || patternMatchNr == 7 || patternMatchNr == 8)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 1);
					posStone3 = Direction.getPosInDirection(x, y, direction, 2);
					posStone4 = Direction.getPosInDirection(x, y, direction, 4);
					posStone5 = Direction.getPosInDirection(x, y, direction, 5);
				}
				// case 4: 3 next to each other, 2 open, 2 next to eachother
				else if (patternMatchNr == 9 || patternMatchNr == 10 || patternMatchNr == 11)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 1);
					posStone3 = Direction.getPosInDirection(x, y, direction, 2);
					posStone4 = Direction.getPosInDirection(x, y, direction, 5);
					posStone5 = Direction.getPosInDirection(x, y, direction, 6);
				}
				// case 5: 4 next to each other, 2 open, 1 
				else if (patternMatchNr == 12 || patternMatchNr == 13 || patternMatchNr == 14)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 1);
					posStone3 = Direction.getPosInDirection(x, y, direction, 2);
					posStone4 = Direction.getPosInDirection(x, y, direction, 3);
					posStone5 = Direction.getPosInDirection(x, y, direction, 6);
				}
				// case 6: 2, 1open, 1, 1open, 2
				else
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 1);
					posStone3 = Direction.getPosInDirection(x, y, direction, 3);
					posStone4 = Direction.getPosInDirection(x, y, direction, 5);
					posStone5 = Direction.getPosInDirection(x, y, direction, 6);
				}

				Stone stone2 = board.getStone(posStone2.x, posStone2.y);
				Stone stone3 = board.getStone(posStone3.x, posStone3.y);
				Stone stone4 = board.getStone(posStone4.x, posStone4.y);
				Stone stone5 = board.getStone(posStone5.x, posStone5.y);
				
			
				// Add the threat
				Five c = new Five(stone1,stone2,stone3,stone4,stone5,direction,patternMatchNr);
				c.stone1.combinations[direction] = c;
				c.stone2.combinations[direction] = c;
				c.stone3.combinations[direction] = c;
				c.stone4.combinations[direction] = c;
				c.stone5.combinations[direction] = c;
				
				Combination.addCombination(board, stone1.player, c,stone1,stone5);
				return true;
			}	
			
		}

		return false;
	}
	
	/**
	 * Add five threads without adding duplicate using keys
	 
	public static void addFiveThreat(Board board, Five fiveThreat)
	{
		String key1 = fiveThreat.stone1.y + "-" + fiveThreat.stone1.x + "," + fiveThreat.stone5.y + "-" + fiveThreat.stone5.x;
		String key2 = fiveThreat.stone5.y + "-" + fiveThreat.stone5.x + "," + fiveThreat.stone1.y + "-" + fiveThreat.stone1.x;
		
		int colorNr = fiveThreat.stone1.colorNr;
		
		// If it not already contains the opposit key
		if (!board.getFiveMap(colorNr).containsKey(key2))
		{
			board.getFiveMap(colorNr).put(key1, fiveThreat);
		}
	}
	*/
	
}
