package logic;

import java.util.ArrayList;

/**
 * Used to let the game flow, following the rules of connect6
 */
public class GameController 
{
	public int stoneCount = 0;
	public Player playingColor = Player.BLACK; // 1 black 2 white
	public boolean firstStone = true;
	public int turn = 1;
	public ArrayList<Stone> stones = new ArrayList<Stone>();

	/**
	 * Keep track of the stones and turns by calling this 
	 * method when a stone has added to the board.
	 */
	public void addStone(Stone s)
	{
		stones.add(s);
		nextTurn();
	}
	
	/**
	 * Keep track of the active player 
	 * and the turns of the game.
	 */
	public void nextTurn()
	{
		if (!firstStone || turn == 1)
		{
			// Switch colors
			if (playingColor == Player.BLACK)
			{
				playingColor = Player.WHITE;
			}
			else
			{
				playingColor = Player.BLACK;
			}
				
			turn++;
			firstStone = true;
		}
		else
		{
			// Second stone
			firstStone = false;
		}
	}
	
	/**
	 * Returns the stone that has to be removed.
	 * Decrements the turns.
	 */
	public Stone prevTurn()
	{
		if (stoneCount == 0)
		{
			return null;
		}
		else
		{
			nextTurn();
			nextTurn();
			nextTurn();
			turn = turn - 2;
		
			stoneCount--;
			Stone s = stones.get(stoneCount);
			stones.remove(stoneCount);
			return s;
		}
	}
	
	/**
	 * Keep track of stone numbers
	 */
	public int getNextStoneNr()
	{
		stoneCount++;
		return stoneCount;
	}
}
