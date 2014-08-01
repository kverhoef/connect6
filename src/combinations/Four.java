package combinations;

import logic.Board;
import logic.Direction;
import logic.Pattern;
import logic.PatternCollection;
import logic.Position;
import logic.Stone;

public class Four extends Combination
{
	
	public Four(Stone stone1, Stone stone2, Stone stone3, Stone stone4, int direction, int type)
	{
		super(direction, type, 4,stone1,stone4);
		this.stone1 = stone1;
		this.stone2 = stone2;
		this.stone3 = stone3;
		this.stone4 = stone4;
	}
	
	/**
	 * Check if a stone is four threat
	 * Note: 	FourThreats will only be found if checked on the 
	 * 			first or last stone of the four threat.
	 */
	public static boolean checkFour(Board board, int x, int y, int direction)
	{
		// case 1: 4 next to each other
		// type 0: competely free (both ends 2 stones can be connected)
		int[][] fourThreat0 = 
		{
			{0,0,0,2,2,1,1,1,1,2,2}
		};
		// type1: 1 side blocked
		int[][] fourThreat1 = 
		{
			{0,0,0,0,3,1,1,1,1,2,2}
		};

		// type2: 1 side blocked with 1 between
		int[][] fourThreat2 = 
		{
			{0,0,0,3,2,1,1,1,1,2,2}
		};
		// type3: 2 sides blocked with 1 between
		int[][] fourThreat3 = 
		{
			{0,0,0,3,2,1,1,1,1,2,3}
		};
		
		// case 2: 1 open between third and last
		// type 4: completly open (both ends 1 stone can be connected)
		int[][] fourThreat4 = 
		{
			{0,0,0,0,2,1,1,1,2,1,2}
		};
		// type 5: left side blocked
		int[][] fourThreat5 = 
		{
			{0,0,0,0,3,1,1,1,2,1,2}
		};
		// type 6: right side blocked
		int[][] fourThreat6 = 
		{
			{0,0,0,0,2,1,1,1,2,1,3}
		};
		
		// case 3: 1 open in the middle
		// type 7: colpletly open (both ends 1 stone can be connected)
		int[][] fourThreat7 = 
		{
			{0,0,0,0,2,1,1,2,1,1,2}
		};
		// type 8: 1 side blocked
		int[][] fourThreat8 = 
		{
			{0,0,0,0,3,1,1,2,1,1,2}
		};
		
		// case 4: 2 open between last and third
		// type 9
		int[][] fourThreat9 = 
		{
			{0,0,0,0,0,1,1,1,2,2,1}
		};
		
		// case 5: 2 open between second and third
		// type 10
		int[][] fourThreat10 = 
		{
			{0,0,0,0,0,1,1,2,2,1,1}
		};
		
		// case 6: 2, 1 open, 1, 1
		// type 11: completly open
		int[][] fourThreat11 = 
		{
			{0,0,0,0,0,2,1,1,2,1,2,1,2}
		};
		// type 12: 1 side blocked
		int[][] fourThreat12 = 
		{
			{0,0,0,0,0,0,3,1,1,2,1,2,1,2,0}
		};
		// type 13: 1 side blocked
		int[][] fourThreat13 = 
		{
			{0,0,0,0,0,3,2,1,1,2,1,2,1,2,0}
		};
		
		// case 7: 1, 1 open, 1, 1 open, 2
		// type 14: both sides open
		int[][] fourThreat14 = 
		{
			{0,0,0,0,0,2,1,2,1,2,1,1,2}
		};
		// type 15: 1 side blocked
		int[][] fourThreat15 = 
		{
			{0,0,0,0,0,3,1,2,1,2,1,1,2}
		};
		// type 16: 1 side blocked
		int[][] fourThreat16 = 
		{
			{0,0,0,0,0,2,1,2,1,2,1,1,3}
		};
		// type 17: 2 sides blocked
		int[][] fourThreat17 = 
		{
			{0,0,0,0,0,3,1,2,1,2,1,1,3}
		};
		
		// case 8: 1, 1 open, 2, 1 open, 1
		// type 18: both sides open
		int[][] fourThreat18 = 
		{
			{0,0,0,0,0,2,1,2,1,1,2,1,2}
		};
		// type 19: 1 side blocked
		int[][] fourThreat19 = 
		{
			{0,0,0,0,0,3,1,2,1,1,2,1,0}
		};
		// type 20: 1 side blocked
		int[][] fourThreat20 = 
		{
			{0,0,0,0,0,2,1,2,1,1,2,1,3}
		};
		// type 21: both sides blocked
		int[][] fourThreat21 = 
		{
			{0,0,0,0,0,3,1,2,1,1,2,1,3}
		};
		
		// case 9: 2, 1 open, 1, 1 open, 1
		// type 22: both sides open
		int[][] fourThreat22 = 
		{
			{0,0,0,0,0,2,1,1,2,1,2,1,2}
		};
		// type 23: 1 side blocked
		int[][] fourThreat23 = 
		{
			{0,0,0,0,0,3,1,1,2,1,2,1,2}
			
		};
		// type 24: 1 side blocked
		int[][] fourThreat24 = 
		{
			{0,0,0,0,0,2,1,1,2,1,2,1,3}
		};
		// type 25: 2 sides blocked
		int[][] fourThreat25 = 
		{
			{0,0,0,0,0,3,1,1,2,1,2,1,3}
		};
		
		// Add all patterns to a collection
		PatternCollection fourThreatsPatterns = new PatternCollection();
		fourThreatsPatterns.addPatern(new Pattern(fourThreat0,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat1,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat2,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat3,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat4,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat5,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat6,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat7,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat8,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat9,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat10,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat11,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat12,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat13,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat14,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat15,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat16,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat17,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat18,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat19,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat20,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat21,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat22,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat23,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat24,true));
		fourThreatsPatterns.addPatern(new Pattern(fourThreat25,true));
		
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
				fourThreatsPatterns.rotatePatterns();
			}
		}
		// rotate 45 degrees from 3
		if (direction > 3)
		{
			rotate45degrees = true;
		}
		
		// Simple pre check
		if (preCheck(board, direction, 4, stone1))
		{
			// if pattern matched, it will return the matched number
			int patternMatchNr = fourThreatsPatterns.checkPatterns(board.field, x, y,rotate45degrees);
			
			// check if dead
			if (fourThreatsPatterns.checkPatterns(board.field, x, y,rotate45degrees) != -1)
			{
				Position posStone2;
				Position posStone3;
				Position posStone4;
				
				// case 1: 4 next to each other
				if (patternMatchNr == 0 || patternMatchNr == 1 || patternMatchNr == 2 || patternMatchNr == 3)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 1);
					posStone3 = Direction.getPosInDirection(x, y, direction, 2);
					posStone4 = Direction.getPosInDirection(x, y, direction, 3);
				}
				// case 2: 1 open between third and last
				else if (patternMatchNr == 4 || patternMatchNr == 5 || patternMatchNr == 6)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 1);
					posStone3 = Direction.getPosInDirection(x, y, direction, 2);
					posStone4 = Direction.getPosInDirection(x, y, direction, 4);
				}
				// case 3: 1 open in the middle
				else if (patternMatchNr == 7 || patternMatchNr == 8)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 1);
					posStone3 = Direction.getPosInDirection(x, y, direction, 3);
					posStone4 = Direction.getPosInDirection(x, y, direction, 4);
				}
				// case 4: 2 open between last and third
				else if (patternMatchNr == 9)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 1);
					posStone3 = Direction.getPosInDirection(x, y, direction, 2);
					posStone4 = Direction.getPosInDirection(x, y, direction, 5);
				}
				// case 5: 2 open between second and third
				else if (patternMatchNr == 10)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 1);
					posStone3 = Direction.getPosInDirection(x, y, direction, 4);	
					posStone4 = Direction.getPosInDirection(x, y, direction, 5);
				}
				// case 6: 2, 1 open, 1, 1
				else if (patternMatchNr == 11 || patternMatchNr == 12 || patternMatchNr == 13)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 1);
					posStone3 = Direction.getPosInDirection(x, y, direction, 3);	
					posStone4 = Direction.getPosInDirection(x, y, direction, 5);
				}
				// case 7: 1, 1 open, 1, 1 open, 2
				else if (patternMatchNr == 14 || patternMatchNr == 15 || patternMatchNr == 16 || patternMatchNr == 17)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 2);
					posStone3 = Direction.getPosInDirection(x, y, direction, 4);	
					posStone4 = Direction.getPosInDirection(x, y, direction, 5);
				}
				
				
				// case 8: 1, 1 open, 2, 1 open, 1
				else if (patternMatchNr == 18 || patternMatchNr == 19 || patternMatchNr == 20 || patternMatchNr == 21)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 2);
					posStone3 = Direction.getPosInDirection(x, y, direction, 3);	
					posStone4 = Direction.getPosInDirection(x, y, direction, 5);
				}
				// case 9: 2, 1 open, 1, 1 open, 1
				else
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 1);
					posStone3 = Direction.getPosInDirection(x, y, direction, 3);	
					posStone4 = Direction.getPosInDirection(x, y, direction, 5);
				}
				
				// 1 is always current position
				//stone1 = board.getStone(x, y);
				Stone stone2 = board.getStone(posStone2.x, posStone2.y);
				Stone stone3 = board.getStone(posStone3.x, posStone3.y);
				Stone stone4 = board.getStone(posStone4.x, posStone4.y);
				
				// Add the threat
				
				
				try
				{
					Four c = new Four(stone1, stone2, stone3, stone4,direction,patternMatchNr);
					c.stone1.combinations[direction] = c;
					c.stone2.combinations[direction] = c;
					c.stone3.combinations[direction] = c;
					c.stone4.combinations[direction] = c;
				
				Combination.addCombination(board, stone1.player, c,stone1,stone4);
				}
				catch (NullPointerException e)
				{
					System.out.println("Failed at pattern:" + patternMatchNr);
					e.printStackTrace();
				}
				return true;
			}
		
		}
		return false;
	}
	
	/**
	 * Add four threads without adding duplicate using keys
	
	public static void addFourThreat(Board board, Four fourThreat)
	{
		String key1 = fourThreat.stone1.y + "-" + fourThreat.stone1.x + "," + fourThreat.stone4.y + "-" + fourThreat.stone4.x;
		String key2 = fourThreat.stone4.y + "-" + fourThreat.stone4.x + "," + fourThreat.stone1.y + "-" + fourThreat.stone1.x;
		
		int colorNr = fourThreat.stone1.colorNr;
		
		// If it not already contains the opposit key
		if (!board.getFourThreatsMap(colorNr).containsKey(key2))
		{
			board.getFourThreatsMap(colorNr).put(key1, fourThreat);
		}
	}
	 */
	
}
