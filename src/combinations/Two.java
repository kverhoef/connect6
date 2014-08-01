package combinations;

import logic.Board;
import logic.Direction;
import logic.Pattern;
import logic.PatternCollection;
import logic.Player;
import logic.Position;
import logic.Stone;

public class Two extends Combination
{

	/**
	 * Constructor
	 */
	public Two(Stone stone1, Stone stone2, int direction, int type)
	{
		super(direction, type, 2,stone1,stone2);
		this.stone1 = stone1;
		this.stone2 = stone2;
	}
	
	/**
	 * Checks if a active two has been created for checkactive
	 */
	private int getActiveTwoValue(Board b, Player player)
	{
		int valueCount = 0;
		for (int i=0;i<b.getCombinations(player).size();i++)
		{
			Combination c = b.getCombinations(player).get(i);
			if (c.combinationType == 2)
			{
				if (c.type == 0 || c.type == 3 || c.type == 5 || c.type == 8)
				{
					valueCount++;
				}
			}
		}
		return valueCount;
	}
	
	/**
	 * Place 1 stone and check if active
	 */
	public boolean checkActive(Board b)
	{
		boolean isActive = false;
		Player myColor = this.stone1.player;
		// Used to determine if a new live two was created
		int startActiveValue = getActiveTwoValue(b,myColor);
		
		Stone returnStone = null;
		
		// case 1: next to each other
		if (this.type == 0 || this.type == 3)
		{
			// before
			Position pos = Direction.getPosInDirection(this.stone1.x,this.stone1.y,Direction.getOppositeDirection(this.direction),1);
    		Stone s = new Stone(pos.x,pos.y,myColor);
    		s.place(b);
    		
    		if (startActiveValue == getActiveTwoValue(b, myColor))
    		{
    			isActive = true;
    		}
    		b.undo(s);
    		// after
    		if (returnStone == null)
    		{
    			pos = Direction.getPosInDirection(this.lastStone.x,this.lastStone.y,this.direction,1);
        		s = new Stone(pos.x,pos.y,myColor);
        		s.place(b);
        		if (startActiveValue == getActiveTwoValue(b, myColor))
        		{
        			isActive = true;
        		}
        		b.undo(s);
    		}
		}
		// case 2: 1 open
		else if (this.type == 5 || this.type == 8)
		{
			// between
			Position pos = Direction.getPosInDirection(this.stone1.x,this.stone1.y,this.direction,1);
    		Stone s = new Stone(pos.x,pos.y,myColor);
    		s.place(b);
    		
    		if (startActiveValue <= getActiveTwoValue(b, myColor))
    		{
    			isActive = true;
    		}
    		b.undo(s);
			
		}
		return isActive;
	}
	
	public static boolean checkTwo(Board board, int x, int y, int direction)
	{
		// case 1: next to each other
		// type 0: completly open
		int[][] two0 = 
		{
			{0,0,2,2,2,1,1,2,2,2,0}
		}; // alive
		// type 1: blocked 1 side 
		int[][] two1 = 
		{
			{0,0,0,0,3,1,1,2,2,2,0}
		}; // dead
		// type 2: blocked 1 side 
		int[][] two2 = 
		{
			{0,0,0,3,2,1,1,2,2,2,0}
		}; // dead
		// type3: blocked 1 side
		int[][] two3 = 
		{
			{0,0,3,2,2,1,1,2,2,2,0}
		}; // alive
		// type4: blocked 2 sides
		int[][] two4 = 
		{
			{0,0,3,2,2,1,1,2,2,3,0}
		}; // dead
		
		// case 2: 1open
		// type 5: completly open
		int[][] two5 = 
		{
			{0,0,2,2,2,1,2,1,2,2,2}
		}; // alive
		// type 6: completly open
		int[][] two6 = 
		{
			{0,0,0,0,3,1,2,1,2,2,2}
		}; // dead
		// type 7: blocked 1 side
		int[][] two7 = 
		{
			{0,0,0,3,2,1,2,1,2,2,2}
		}; // dead
		// type 8: blocked 1 side
		int[][] two8 = 
		{
			{0,0,3,2,2,1,2,1,2,2,2}
		}; // alive
		// type 9: blocked 2 sides
		int[][] two9 = 
		{
			{0,0,3,2,2,1,2,1,2,2,3}
		}; // dead
		
		// case 3: 2open
		// type 10: blocked 2 sides
		int[][] two10 = 
		{
			{0,0,2,2,2,1,2,2,1,2,2}
		}; // alive
		// type 11: blocked 1 sides
		int[][] two11 = 
		{
			{0,0,0,0,3,1,2,2,1,2,2}
		}; // dead
		// type 12: blocked 1 sides
		int[][] two12 = 
		{
			{0,0,0,3,2,1,2,2,1,2,2}
		}; // dead
		// type 13: blocked 1 sides
		int[][] two13 = 
		{
			{0,0,3,2,2,1,2,2,1,2,2}
		}; // alive
		
		// Add all patterns to a collection
		PatternCollection twoPatterns = new PatternCollection();
		twoPatterns.addPatern(new Pattern(two0,true));
		twoPatterns.addPatern(new Pattern(two1,true));
		twoPatterns.addPatern(new Pattern(two2,true));
		twoPatterns.addPatern(new Pattern(two3,true));
		twoPatterns.addPatern(new Pattern(two4,true));
		twoPatterns.addPatern(new Pattern(two5,true));
		twoPatterns.addPatern(new Pattern(two6,true));
		twoPatterns.addPatern(new Pattern(two7,true));
		twoPatterns.addPatern(new Pattern(two8,true));
		twoPatterns.addPatern(new Pattern(two9,true));
		twoPatterns.addPatern(new Pattern(two10,true));
		twoPatterns.addPatern(new Pattern(two11,true));
		twoPatterns.addPatern(new Pattern(two12,true));
		twoPatterns.addPatern(new Pattern(two13,true));
		
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
				twoPatterns.rotatePatterns();
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
			int patternMatchNr = twoPatterns.checkPatterns(board.field, x, y,rotate45degrees);
			
			// check patterns
			if (twoPatterns.checkPatterns(board.field, x, y,rotate45degrees) != -1)
			{
				Position posStone2;
				
				// case 1: next to each other
				if (patternMatchNr == 0 || patternMatchNr == 1 || patternMatchNr == 2 || patternMatchNr == 3 || patternMatchNr == 4)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 1);
				}
				// case 2: 1open
				else if (patternMatchNr == 5 || patternMatchNr == 6 || patternMatchNr == 7 || patternMatchNr == 8 || patternMatchNr == 9)
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 2);
				}
				// case 3: 2open
				else
				{
					posStone2 = Direction.getPosInDirection(x, y, direction, 3);
				}
				
				Stone stone2 = board.getStone(posStone2.x, posStone2.y);
				
				
				Two c = new Two(stone1,stone2,direction,patternMatchNr);
				stone1.combinations[direction] = c;
				stone2.combinations[direction] = c;
				
				Combination.addCombination(board, stone1.player, c,stone1,stone2);
				
				return true;
				
			}
		}
		
		return false;
	}
	
	/**
	 * Gets the stone that matched for the live2
	 * @return
	 */
	public static Stone getCombinedLiveTwoStone(Board board, int direction, int steps, int x, int y)
	{
		Position pos = Direction.getPosInDirection(x, y, direction, steps);
		return board.getStone(pos.x, pos.y);
	}

	@Override
    public String toString()
    {
        return "Livetwo Stone1 x:" + stone1.x + ",y:" + stone1.y + " Stone2 x:" + stone2.x + ",y:" + stone2.y;
    }
	
}
