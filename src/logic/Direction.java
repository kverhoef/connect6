package logic;

/**
 * Directions are given a number see example below:
 * 
 *   6  3  7
 *    \ | /
 *  2 -   - 0
 *    / | \
 *   5  1  4
 * 
 * They are used to get positions in a direction
 */
public class Direction 
{
	public static int LEFT = 2;
	public static int UP = 3;
	public static int DOWN = 1;
	public static int RIGHT = 0;
	public static int LEFT_UP = 6;
	public static int LEFT_DOWN = 5;
	public static int RIGHT_UP = 7;
	public static int RIGHT_DOWN = 4;
	
	/**
	 * Returns the new pos int[0] = x, int[1] = y
	 * Sets given steps in the given direction.
	 */
	public static Position getPosInDirection(int x, int y, int direction, int steps)
	{
		int newX = x;
		int newY = y;

		if (direction == RIGHT)
		{
			newX += steps;
		}
		else if (direction == DOWN)
		{
			newY += steps;
		}
		else if (direction == LEFT)
		{
			newX -= steps;
		}
		else if (direction == UP)
		{
			newY -= steps;
		}
		else if (direction == RIGHT_DOWN)
		{
			newY += steps;
			newX += steps;
		}
		else if (direction == LEFT_DOWN)
		{
			newY += steps;
			newX -= steps;
		}
		else if (direction == LEFT_UP)
		{
			newY -= steps;
			newX -= steps;
		}
		else if (direction == RIGHT_UP)
		{
			newY -= steps;
			newX += steps;
		}
		
		Position pos = new Position(newX, newY);
		
		if (pos.x < 0 || pos.y < 0 || pos.x > 18 || pos.y > 18)
		{
			return null;
		}
		
		return pos;
	}
	
	/**
	 * Returns the number of the opposit direction.
	 */
	public static int getOppositeDirection(int direction)
	{
		if (direction == 0)
		{
			return 2;
		}
		else if (direction == 1)
		{
			return 3;
		}
		else if (direction == 2)
		{
			return 0;
		}
		else if (direction == 3)
		{
			return 1;
		}
		else if (direction == 4)
		{
			return 6;
		}
		else if (direction == 5)
		{
			return 7;
		}
		else if (direction == 6)
		{
			return 4;
		}
		else
		{
			return 5;
		}
	}
	
	/**
	 * Used to determine which stone comes first in a given direction.
	 * Return the stone that comes first (stone1 or stone2).
	 */
	public static Stone getFirstStone(Stone stone1, Stone stone2, int direction)
	{
		if (direction == 0 || direction == 4)
		{
			if (stone1.x < stone2.x)
				return stone1;
			else 
				return stone2;
		}
		else if (direction == 1 || direction == 5)
		{
			if (stone1.y < stone2.y)
				return stone1;
			else 
				return stone2;
		}
		else if (direction == 2 || direction == 6)
		{
			if (stone1.x > stone2.x)
				return stone1;
			else 
				return stone2;
		}
		else if (direction == 3 || direction == 7)
		{
			if (stone1.y > stone2.y)
				return stone1;
			else 
				return stone2;
		}
		
		return null;
	}
	
	/**
	 * Used to determine which stone comes last in a given direction.
	 * Return the stone that comes last (stone1 or stone2).
	 */
	public static Stone getLastStone(Stone stone1, Stone stone2, int direction)
	{
		
		if (direction == 0 || direction == 4)
		{
			if (stone1.x > stone2.x)
				return stone1;
			else 
				return stone2;
		}
		else if (direction == 1 || direction == 5)
		{
			if (stone1.y > stone2.y)
				return stone1;
			else 
				return stone2;
		}
		else if (direction == 2 || direction == 6)
		{
			if (stone1.x < stone2.x)
				return stone1;
			else 
				return stone2;
		}
		else if (direction == 3 || direction == 7)
		{
			if (stone1.y < stone2.y)
				return stone1;
			else 
				return stone2;
		}
		
		return null;
	}
}
