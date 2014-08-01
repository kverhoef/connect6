package logic;

import combinations.Combination;
import combinations.Five;
import combinations.Four;
import combinations.Six;
import combinations.Three;
import combinations.Two;

/**
 * A representation of a stone that is/has to be placed on the board.
 */
public class Stone 
{
	public int x;
	public int y;
	// color nr 1 black 2 white
	public Player player;
	// count of the stones
	public int stoneNr = 0;
	
	// Combination in direction
	public Combination[] combinations = new Combination[8];
	
	/**
	 * Constructor
	 */
	public Stone(int x, int y, Player player)
	{
		this.x = x;
		this.y = y;
		this.player = player;
		
		for (int i =0;i<8;i++)
		{
			combinations[i] = null;
		}
	}
	
	/**
	 * Checks if a two combination is found on this stone in a given direction. 
	 * Includeds life two's but also dead two's.
	 */
	public boolean checkTwo(Board board, int direction)
	{
		return Two.checkTwo(board, this.x, this.y, direction);
	}
	
	/**
	 * Checks if a six combination is found on this stone in a given direction.
	 */
	public boolean checkSix(Board board, int direction)
	{
		return Six.checkConnectSix(board, this.x, this.y, direction);
	}
	
	/**
	 * Checks if a Five combination is found on this stone in a given direction.
	 */
	public boolean checkFive(Board board, int direction)
	{
		return Five.checkFive(board, this.x, this.y, direction);
	}
	
	/**
	 * Checks if a three combination is found on this stone in a given direction.
	 * Includeds life threes but also dead threes.
	 */
	public boolean checkThree(Board board, int direction)
	{
		return Three.checkThree(board, this.x, this.y, direction);
	}
	
	/**
	 * Checks if a four combination is found on this stone in a given direction.
	 */
	public boolean checkFour(Board board, int direction)
	{
		return Four.checkFour(board, this.x, this.y, direction);
	}
	
	/**
	 * Places a stone on the board, and reanlyzes combination after adding.
	 */
	public boolean place(Board board)
	{
		if (board.getStone(x, y) == null && x > -1 && y > -1 && x < 19 && y < 19)
		{
			board.field[this.y][this.x] = this.player;
			
			String key = this.y + "-" + this.x;
	
			if (player == Player.BLACK)
			{
				board.blackStones.put(key,this);
			}
			else
			{
				board.whiteStones.put(key,this);
			}

			board.analyze(this,false);
			
			return true;
		}
		return false;
	}
	
	public String toString()
	{
		return this.x + " - " + this.y;
	}
	
}
