package logic;
import java.util.ArrayList;
import java.util.HashMap;
import combinations.Combination;

/**
 * This class keeps track of the board, the pieces on it 
 * and the combinations that are formed. 
 */
public class Board
{
	// Huidige speelveld
	public Player[][] field = new Player[19][19];
	
	// zwart 1
	// wit 2
	
	// when a 6 has formed, the game ends en this will be true
	public boolean gameEnded = false;
	
	// Key van de hashmap is y-x van de steen, zoals x=1 y=11 wordt: 11-1
	public HashMap<String, Stone> blackStones = new HashMap<String, Stone>();
	public HashMap<String, Stone> whiteStones = new HashMap<String, Stone>();
	
	public ArrayList<Combination> blackCombinations = new ArrayList<Combination>();
	public ArrayList<Combination> whiteCombinations = new ArrayList<Combination>();
	
	/**
	 * Returns the opponents colornr
	 * @param myColor
	 * @return
	 */
	public Player getOpponent(Player player)
	{
		if (player == Player.BLACK)
			return Player.WHITE;
		else
			return Player.BLACK;
	}
	
	/**
	 * returns black or white stones depending on the given coloNr.
	 */
	public HashMap<String, Stone> getStonesMap(Player player)
	{
		if (player == Player.BLACK)
		{
			return blackStones;
		}
		else
		{
			return whiteStones;
		}
	}
	
	/**
	 * returns black or white combinations depending on the given coloNr.
	 */
	public ArrayList<Combination> getCombinations(Player player)
	{
		if (player == Player.BLACK)
		{
			return blackCombinations;
		}
		else
		{
			return whiteCombinations;
		}
	}
	
	/**
	 * This function checks for combinations, it uses the first stone to 
	 * check in one direction and the last stone to check in the opposit direction
	 */
	public void analyzeCombinations(Stone firstStone, Stone lastStone, int direction, int stoneCount)
	{
		// Only check in one way if both stones are the same
		if (firstStone == lastStone)
		{
			lastStone = null;
		}
		
		int oppositDirection = Direction.getOppositeDirection(direction);
		
		if (firstStone != null)
		{
			if (stoneCount == 2)
			{
				if (!firstStone.checkTwo(this, direction))
					lastStone.checkTwo(this, oppositDirection);
			}
			else if (stoneCount == 3)
			{
				if (!firstStone.checkThree(this, direction))
					lastStone.checkThree(this, oppositDirection);
			}
			else if (stoneCount == 4)
			{
				if (!firstStone.checkFour(this, direction))		
					lastStone.checkFour(this, oppositDirection);
			}
			else if (stoneCount == 5)
			{
				if (!firstStone.checkFive(this, direction))		
					lastStone.checkFive(this, oppositDirection);
			}
			else
			{
				if (!firstStone.checkSix(this, direction)){	
					if (!lastStone.checkSix(this, direction)){
						if (!firstStone.checkFive(this, direction))		
							lastStone.checkFive(this, direction);
					}
					else {
						gameEnded = true;
					}
				}
				else {
					gameEnded = true;
				}
			}
		}
	}
	
	/**
	 * Get a stone from a position using the 
	 * list of black stones or whiteStones
	 */
	public Stone getStone(int x, int y)
	{
		String key = y + "-" + x;

		Stone result = blackStones.get(key);
		if (result != null)
			return result;
		else 
		{
			result = whiteStones.get(key);
			if (result != null)
			{
				return result;
			}
			else
				return null;
		}
			
	}
	
	/**
	 * Removes a stone from the bord and updates 
	 * combinations using analyze.
	 */
	public void undo(Stone analyseStone)
	{
		
		// Reset the stone on the field
		field[analyseStone.y][analyseStone.x] = null;
		Player player = analyseStone.player;
		
		// remove the stone
		getStonesMap(player).remove(analyseStone.y + "-" + analyseStone.x);
		
		analyze(analyseStone,true);
	}

	/**
	 * Analyze and updates combinations of the board.
	 * Is used after insert and after undo. It check in 4 directions: vertical, 
	 * horizontal and both diagonal ways. It finds the first and last stone 
	 * in the direction, to check if a combination exists.
	 * 
	 * Analyzing only the added or removed stones is faster then analyzing the full board. 
	 */
	public void analyze(Stone analyseStone, boolean doUndo)
	{
		Player myPlayer = analyseStone.player;
		
		int directions[] = {0,4,1,5};
		
		// In 4 directions, 
		for (int i = 0;i<4;i++)
		{	
			int direction = directions[i];
			int oppositDirection = Direction.getOppositeDirection(direction);
			
			Player forwardColorNr = null;
			Player backwardColorNr = null;
			
			Stone firstStoneBackward = null;
			Stone firstStoneForward = null;
			
			Stone lastStoneBackward = null;
			Stone lastStoneForward = null;
			
			boolean findForward = true;
			boolean findBackward = true;
			
			// used to count the gap between stones, if more than 2 there can`t be a combination with it
			int forwardGap = 0;
			int backwardGap = 0;
			
			// 8 positions each way
			for (int p=1;p<8;p++)
			{
				if (findForward)
				{
					// position forward
					Position posForward = Direction.getPosInDirection(analyseStone.x, analyseStone.y, direction, p);
					
					if (posForward != null)
					{
						Stone forwardStone = this.getStone(posForward.x, posForward.y);
	
						if (forwardStone != null)
						{
							
							// stone found, reset the gap count
							forwardGap = 0;
							
							if (forwardColorNr == null)
							{
								forwardColorNr = forwardStone.player;
								firstStoneForward = forwardStone;
							}
							
							if (forwardStone.player == forwardColorNr)
							{
								lastStoneForward = forwardStone;
								// remove old combinations if they exist
								removeComb(forwardStone.combinations[direction]);
								removeComb(forwardStone.combinations[oppositDirection]);
							}
							else
							{
								findForward = false;
							}
							
						}
						else
						{
							forwardGap++;
							if(forwardGap == 3)
								findForward = false;
						}	
					}
				}
				
				if (findBackward)
				{
					// position backwards
					Position posBackward = Direction.getPosInDirection(analyseStone.x, analyseStone.y, oppositDirection, p);
					
					if (posBackward != null)
					{
						Stone backwardStone = this.getStone(posBackward.x, posBackward.y);
						
						if (backwardStone != null)
						{
							
							// stone found, reset the gap count
							backwardGap = 0;
							
							if (backwardStone != null)
							{
								// stone found, reset the gap count
								forwardGap = 0;
								
								if (backwardColorNr == null)
								{
									backwardColorNr = backwardStone.player;
									firstStoneBackward = backwardStone;
								}
								
								if (backwardStone.player == backwardColorNr)
								{
									lastStoneBackward = backwardStone;
									// remove old combinations if they exist
									removeComb(backwardStone.combinations[direction]);
									removeComb(backwardStone.combinations[oppositDirection]);
								}
								else
								{
									findBackward = false;
								}
							}
						}
						else
						{
							backwardGap++;
							if(backwardGap == 3)
								findBackward = false;
						}
					}
				}
			}
			
			// next part is different for insert and undo
			if (doUndo)
			{
				// try to join them, only if everything is the same color
				if (forwardColorNr == backwardColorNr)
				{
					firstStoneForward = lastStoneBackward;
					lastStoneBackward = null;
					firstStoneBackward = null;
				}
			}
			else
			{
				// first try to add the analyzeStone
				if (forwardColorNr == myPlayer)
				{
					firstStoneForward = analyseStone;
				}
				if (backwardColorNr == myPlayer)
				{
					firstStoneBackward = analyseStone;
				}
				
				// try to join them, only if everything is the same color
				if (forwardColorNr == backwardColorNr && forwardColorNr == myPlayer)
				{
					firstStoneForward = lastStoneBackward;
					lastStoneBackward = null;
					firstStoneBackward = null;
				}
			}
			
			// analyze the stones
			if (firstStoneForward != null && firstStoneForward != lastStoneForward)
			{
				/*
				if (Direction.getFirstStone(lastStoneForward, firstStoneForward, direction) != firstStoneForward)
					analyzeCombinations(lastStoneForward, firstStoneForward, direction);
				else
					analyzeCombinations(firstStoneForward, lastStoneForward, direction);
					*/
				
				if (Direction.getFirstStone(lastStoneForward, firstStoneForward, direction) != firstStoneForward)
					checkCombination(lastStoneForward, firstStoneForward, direction);
				else
					checkCombination(firstStoneForward, lastStoneForward, direction);
			}
			if (firstStoneBackward != null && firstStoneBackward != lastStoneBackward)
			{
				/*
				if (Direction.getFirstStone(lastStoneBackward, firstStoneBackward, direction) != firstStoneBackward)
					analyzeCombinations(lastStoneBackward, firstStoneBackward, direction);
				else
					analyzeCombinations(firstStoneBackward, lastStoneBackward, direction);
				*/
				if (Direction.getFirstStone(lastStoneBackward, firstStoneBackward, direction) != firstStoneBackward)
					checkCombination(lastStoneBackward, firstStoneBackward, direction);
				else
					checkCombination(firstStoneBackward, lastStoneBackward, direction);
			}
		}
	}
	
	private void checkCombination(Stone firstStone, Stone lastStone, int direction)
	{
		int stoneCount = 1;
	
		Position p = new Position(firstStone.x,firstStone.y);
		int x = p.x;
		int y = p.y;
		
		while (!(x == lastStone.x && y == lastStone.y))
		{
			
			p = Direction.getPosInDirection(p.x, p.y, direction, 1);
			x = p.x;
			y = p.y;
			
			if (this.field[y][x] == Player.BLACK || this.field[y][x] == Player.WHITE)
			{
				stoneCount++;
			}
			
		}
		
		analyzeCombinations(firstStone, lastStone, direction, stoneCount);
	}
	
	/**
	 * Removes a connect6 combination. Stones contain the combinations they will be reset to null.
	 * whiteCombinations or blackCombination contains the combination itself. It will be removed
	 * from the list.
	 */
	public void removeComb(Combination c)
	{
		if (c != null)
		{
			int direction = c.direction;
		
			if (c.stone1 != null)
				c.stone1.combinations[direction] = null;
			if (c.stone2 != null)
				c.stone2.combinations[direction] = null;
			if (c.stone3 != null)
				c.stone3.combinations[direction] = null;
			if (c.stone4 != null)
				c.stone4.combinations[direction] = null;
			if (c.stone5 != null)
				c.stone5.combinations[direction] = null;
			if (c.stone6 != null)
				c.stone6.combinations[direction] = null;
			if (c.lastStone != null)
				c.lastStone.combinations[direction] = null;
	
			if (!getCombinations(c.stone1.player).remove(c))
			{
				
			}
		}
	}
	
	/**
	 * Places a stone on the board, and reanlyzes combination after adding.
	 */
	public boolean place(Stone stone)
	{
		return stone.place(this);
	}
	
	/**
	 * A test function to print a representation of the board to the console
	 */
	public void printBoard()
	{
		for (int y=0;y < 19;y++)
		{
			for (int x=0;x < 19;x++)
			{
				System.out.print(field[y][x] + " ");
			}
			System.out.println();
		}
	}
	
}
