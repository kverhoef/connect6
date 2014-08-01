package algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import combinations.Combination;
import logic.Board;
import logic.Direction;
import logic.MoveSet;
import logic.Player;
import logic.Position;
import logic.Stone;

public abstract class PointsAlgorithm extends Algorithm
{
	// multiply defensive score by factor
	int defenseFactor;
	
	// Offensive points for combinations
	long[] two = new long[14];
	long[] three = new long[25];
	long[] four = new long[26];
	long[] five = new long[18];
	long[] six = new long[1];
	
	// Defensive points for combinations
	long[] defensiveTwo = new long[14];
	long[] defensiveThree = new long[25];
	long[] defensiveFour = new long[26];
	long[] defensiveFive = new long[18];
	long[] defensiveSix = new long[1];
	
	// placing the stones in the center gets more points
	int[][] pointsMatrix = 
	{
			{-1,0,1,2,3,4,5,6,7,8,7,6,5,4,3,2,1,0,-1},
			{0,1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,1,0},
			{1,2,3,4,5,6,7,8,9,10,9,8,7,6,5,4,3,2,1},
			{2,3,4,5,6,7,8,9,10,11,10,9,8,7,6,5,4,3,2},
			{3,4,5,6,7,8,9,10,11,12,11,10,9,8,7,6,5,4,3},
			{4,5,6,7,8,9,10,11,12,13,12,11,10,9,8,7,6,5,4},
			{5,6,7,8,9,10,11,12,13,14,13,12,11,10,9,8,7,6,5},
			{6,7,8,9,10,11,12,13,14,17,16,13,12,11,10,9,8,7,6},
			{7,8,9,10,11,12,13,14,20,16,20,14,13,12,11,10,9,8,7},
			{8,9,10,11,12,13,14,17,16,30,16,17,14,13,12,11,10,9,8},
			{7,8,9,10,11,12,13,14,20,16,20,14,13,12,11,10,9,8,7},
			{6,7,8,9,10,11,12,13,14,17,14,13,12,11,10,9,8,7,6},
			{5,6,7,8,9,10,11,12,13,14,13,12,11,10,9,8,7,6,5},
			{4,5,6,7,8,9,10,11,12,13,12,11,10,9,8,7,6,5,4},
			{3,4,5,6,7,8,9,10,11,12,11,10,9,8,7,6,5,4,3},
			{2,3,4,5,6,7,8,9,10,11,10,9,8,7,6,5,4,3,2},
			{1,2,3,4,5,6,7,8,9,10,9,8,7,6,5,4,3,2,1},
			{0,1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,1,0},
			{-1,0,1,2,3,4,5,6,7,8,7,6,5,4,3,2,1,0,-1}
	};
	
	/**
	 * Method stays abstract and must be implemented by algorithms implementing this class.
	 */
	public abstract MoveSet doMove(Board board, Player player, int turn);
	
	/**
	 * Constructor
	 */
	public PointsAlgorithm()
	{

	}

	/**
	 * Gets a score based on given values for combinations
	 */
	private long getScore(Board board, Player myColor, Player openentColor, boolean firstStone, int turn)
	{
		// Start score
		long score = 0;
		
	    // add score for own combinations values
	    for (int i =0;i<board.getCombinations(myColor).size();i++)  
	    {  
	    	Combination c = board.getCombinations(myColor).get(i); 
	    	score += getSingleScore(c,true, firstStone, board, turn);
	    }
	    // decrease score for opponent combinations values 
	    for (int i =0;i<board.getCombinations(openentColor).size();i++) 
	    {  
	    	Combination c = board.getCombinations(openentColor).get(i); 
	    	//score -= ((getSingleScore(c,false, firstStone, board)) * defenseFactor);
	    	long singleScore = getSingleScore(c,false, firstStone, board, turn);
	    	score -= singleScore;
	    	
	    	if (c.combinationType > 3)
	    	{
	    		//System.out.println("Defensive value: " + singleScore + " type: " + c.combinationType + " t:" + c.type);
	    		
	    	}
	    }
	    
		return score;
	}

	/**
	 * Returns a single score for a combination, used in getScore
	 */
	private long getSingleScore(Combination c, boolean myCombination, boolean firstStone, Board board, int turn)
	{

		if (c.combinationType == 2)
		{
			if (myCombination)
				return this.two[c.type];
			else
				return this.defensiveTwo[c.type];
		}
		else if (c.combinationType == 3)
		{
			if (myCombination)
				return this.three[c.type];
			else
				return this.defensiveThree[c.type];
		}
		else if (c.combinationType == 4)
		{
			if (myCombination)
				return this.four[c.type];
			else
				return this.defensiveFour[c.type];
		}
		else if (c.combinationType == 5)
		{
			if (firstStone)
			{
				return 999999;
			}
			
			if (myCombination)
			{
				// on the first stone we want to create a five, to complete the six
				return this.five[c.type];
			}
			else
				return this.defensiveFive[c.type];
		}
		else
		{
			if (myCombination)
				return this.six[c.type];
			else
				return this.defensiveSix[c.type];
		}
	}
	
	/**
	 * Find the best stone for the given positions.
	 * Uses the score system to find the one with the best score.
	 * @param positions				list of possitions to check
	 * @param board Board 			board object
	 * @param myColor int			my color nr				
	 * @param firstStone boolean 	indicates if this is the first stone
	 * @return Stone				the stone with the best score 
	 */
	public Stone getBestStonePosition(ArrayList<Position> positions, Board board, Player myColor, boolean firstStone, int turn)
	{
		// Get opponent color
		Player openentColor = Player.getOpponent(myColor);
		
		// Holding the best stone found
		Stone bestStone = null;
		
	    long bestScore = 0;
		
		// for each possible position for first stone
	    for (int position1=0;position1<positions.size();position1++)
	    {
	    	Position position = positions.get(position1);
	    	
	    	Stone testStone = new Stone(position.x, position.y, myColor);
			
			if (testStone.place(board))
			{
				long score = getScore(board,myColor,openentColor, firstStone, turn);
				score += pointsMatrix[position.y][position.x];
				
				if (score >= bestScore || bestScore == 0)
				{
					bestStone = testStone;
					bestScore = score;
				}

				board.undo(testStone);
			}
	    }
		
		return bestStone;
	}
	
	/**
	 * Add open positions for a combination to a position list.
	 * Positions can be used to check how good a position is.
	 */
	public ArrayList<Position> addOpenCombinationPositions(Combination c, ArrayList<Position> positions, Board board)
	{
		
		int direction = c.direction;
		int oppositDirection = Direction.getOppositeDirection(direction);

		// First stone of the combination, we get open positions from here
		Stone firstStone = c.stone1;
		int x = firstStone.x;
		int y = firstStone.y;
		
		// Empty positions for adding
		Position pos1 = null;
		Position pos2 = null;
		Position pos3 = null;
		Position pos4 = null;
		Position pos5 = null;
		
		// get combination type, and try to add open positions
		if (c.combinationType == 2)
		{
			// case 1: next to each other
			if (c.type == 0 || c.type == 1 || c.type == 2 || c.type == 3 || c.type == 4)
			{
				// two before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 2);
				pos2 = Direction.getPosInDirection(x, y, oppositDirection, 1);
				
				// two after
				pos3 = Direction.getPosInDirection(x, y, direction, 2);
				pos4 = Direction.getPosInDirection(x, y, direction, 3);
				//pos3 = Direction.getPosInDirection(x, y, direction, 4);
				//pos4 = Direction.getPosInDirection(x, y, direction, 5);
			}
			// case 2: 1open
			else if (c.type == 5 || c.type == 6 || c.type == 7 || c.type == 8 || c.type == 9)
			{
				// two before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 2);
				pos2 = Direction.getPosInDirection(x, y, oppositDirection, 1);
				// between
				pos5 = Direction.getPosInDirection(x, y, direction, 1);
				// two after
				pos3 = Direction.getPosInDirection(x, y, direction, 3);
				pos4 = Direction.getPosInDirection(x, y, direction, 4);
			}
			// case 3: 2open
			else
			{
				// one before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 1);
				// between
				pos2 = Direction.getPosInDirection(x, y, direction, 1);
				pos3 = Direction.getPosInDirection(x, y, direction, 2);		
				// one after
				pos4 = Direction.getPosInDirection(x, y, direction, 4);	
			}
			
		}
		else if (c.combinationType == 3)
		{
			// case 1: 3 next to each other
			if (c.type == 0 || c.type == 1 || c.type == 2 || c.type == 3 || c.type == 4)
			{
				// two before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 2);
				pos2 = Direction.getPosInDirection(x, y, oppositDirection, 1);
				// two after
				pos3 = Direction.getPosInDirection(x, y, direction, 3);
				pos4 = Direction.getPosInDirection(x, y, direction, 4);
			}
			// case 2: 2 next to eachother, 1 open, 1
			else if (c.type == 5 || c.type == 6 || c.type == 7 || c.type == 8 || c.type == 9)
			{
				// two before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 2);
				pos2 = Direction.getPosInDirection(x, y, oppositDirection, 1);
				// between
				pos5 = Direction.getPosInDirection(x, y, direction, 2);
				// two after
				pos3 = Direction.getPosInDirection(x, y, direction, 4);
				pos4 = Direction.getPosInDirection(x, y, direction, 5);
			}
			// case 3: 1 next to eachother, 1 open, 2
			else if (c.type == 10 || c.type == 11 || c.type == 12 || c.type == 13 || c.type == 14)
			{
				// two before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 2);
				pos2 = Direction.getPosInDirection(x, y, oppositDirection, 1);
				// between
				pos5 = Direction.getPosInDirection(x, y, direction, 1);
				// two after
				pos3 = Direction.getPosInDirection(x, y, direction, 4);
				pos4 = Direction.getPosInDirection(x, y, direction, 5);
			}
			// case4: 2, 2 open, 1
			else if (c.type == 15 || c.type == 16 || c.type == 17 || c.type == 18)
			{
				// one before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 1);
				// between
				pos2 = Direction.getPosInDirection(x, y, direction, 2);
				pos3 = Direction.getPosInDirection(x, y, direction, 3);		
				// one after
				pos4 = Direction.getPosInDirection(x, y, direction, 5);	
			}
			// case5: 1, 2 open, 2
			else if (c.type == 19 || c.type == 20 || c.type == 21 || c.type == 22)
			{
				// one before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 1);
				// between
				pos2 = Direction.getPosInDirection(x, y, direction, 1);
				pos3 = Direction.getPosInDirection(x, y, direction, 2);		
				// one after
				pos4 = Direction.getPosInDirection(x, y, direction, 5);	
			}
			// case 6: 1, 1 open, 1, 1 open
			else
			{
				// one before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 1);
				// between
				pos2 = Direction.getPosInDirection(x, y, direction, 1);
				pos3 = Direction.getPosInDirection(x, y, direction, 3);		
				// one after
				pos4 = Direction.getPosInDirection(x, y, direction, 5);	
			}
		}
		else if (c.combinationType == 4)
		{
			// case 1: 4 next to each other
			if (c.type == 0 || c.type == 1 || c.type == 2 || c.type == 3)
			{
				// two before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 2);
				pos2 = Direction.getPosInDirection(x, y, oppositDirection, 1);

				// two after
				pos3 = Direction.getPosInDirection(x, y, direction, 4);
				pos4 = Direction.getPosInDirection(x, y, direction, 5);
			}
			// case 2: 1 open between third and last
			else if (c.type == 4 || c.type == 5 || c.type == 6)
			{
				// two before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 2);
				pos2 = Direction.getPosInDirection(x, y, oppositDirection, 1);
				// between
				pos5 = Direction.getPosInDirection(x, y, direction, 3);
				// two after
				pos3 = Direction.getPosInDirection(x, y, direction, 5);
				pos4 = Direction.getPosInDirection(x, y, direction, 6);
			}
			// case 3: 1 open in the middle
			else if (c.type == 7 || c.type == 8)
			{
				// two before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 2);
				pos2 = Direction.getPosInDirection(x, y, oppositDirection, 1);
				// between
				pos5 = Direction.getPosInDirection(x, y, direction, 2);
				// two after
				pos3 = Direction.getPosInDirection(x, y, direction, 5);
				pos4 = Direction.getPosInDirection(x, y, direction, 6);
			}
			// case 4: 2 open between last and third
			else if (c.type == 9)
			{
				// one before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 1);
				// between
				pos2 = Direction.getPosInDirection(x, y, direction, 3);
				pos3 = Direction.getPosInDirection(x, y, direction, 4);		
				// one after
				pos4 = Direction.getPosInDirection(x, y, direction, 6);	
			}
			// case 5: 2 open between second and third
			else if (c.type == 10)
			{
				// one before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 1);
				// between
				pos2 = Direction.getPosInDirection(x, y, direction, 2);
				pos3 = Direction.getPosInDirection(x, y, direction, 3);		
				// one after
				pos4 = Direction.getPosInDirection(x, y, direction, 6);	
			}
			// case 6: 2, 1 open, 1, 1
			else if (c.type == 11 || c.type == 12 || c.type == 13)
			{
				// one before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 1);
				// between
				pos2 = Direction.getPosInDirection(x, y, direction, 2);
				pos3 = Direction.getPosInDirection(x, y, direction, 4);		
				// one after
				pos4 = Direction.getPosInDirection(x, y, direction, 6);	
			}
			// case 7: 1, 1 open, 1, 1 open, 2
			else if (c.type == 14 || c.type == 15 || c.type == 16 || c.type == 17)
			{
				// one before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 1);
				// between
				pos2 = Direction.getPosInDirection(x, y, direction, 1);
				pos3 = Direction.getPosInDirection(x, y, direction, 3);		
				// one after
				pos4 = Direction.getPosInDirection(x, y, direction, 6);	
			}
			// case 8: 1, 1 open, 2, 1 open, 1
			else if (c.type == 18 || c.type == 19 || c.type == 20 || c.type == 21)
			{
				// one before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 1);
				// between
				pos2 = Direction.getPosInDirection(x, y, direction, 1);
				pos3 = Direction.getPosInDirection(x, y, direction, 4);		
				// one after
				pos4 = Direction.getPosInDirection(x, y, direction, 6);	
			}
			// case 9: 2, 1 open, 1, 1 open, 1
			else
			{
				// one before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 1);
				// between
				pos2 = Direction.getPosInDirection(x, y, direction, 2);
				pos3 = Direction.getPosInDirection(x, y, direction, 4);		
				// one after
				pos4 = Direction.getPosInDirection(x, y, direction, 6);	
			}
		}
		else if (c.combinationType == 5)
		{
			// case 1: 5 next to each other
			if (c.type == 0 || c.type == 1 || c.type == 2)
			{
				// one before
				pos1 = Direction.getPosInDirection(x, y, oppositDirection, 1);
				// one after
				pos2 = Direction.getPosInDirection(x, y, direction, 5);	
			}
			// case 2: 4 next to each other, 1 open between four and five
			else if (c.type == 3 || c.type == 4 || c.type == 5)
			{
				// between
				pos1 = Direction.getPosInDirection(x, y, direction, 4);
			}
			// case 3: 3 next to each other, 1 open between third and fourth
			else if (c.type == 6 || c.type == 7 || c.type == 8)
			{
				// between
				pos1 = Direction.getPosInDirection(x, y, direction, 3);
			}
			// case 4: 3 next to each other, 2 open, 2 next to eachother
			else if (c.type == 9 || c.type == 10 || c.type == 11)
			{
				// between
				pos1 = Direction.getPosInDirection(x, y, direction, 3);
			}
			// case 5: 4 next to each other, 2 open, 1
			else if (c.type == 12 || c.type == 13 || c.type == 14)
			{
				// between
				pos1 = Direction.getPosInDirection(x, y, direction, 4);
				pos2 = Direction.getPosInDirection(x, y, direction, 5);
			}
			// case 6: 2, 1open, 1, 1open, 2
			else
			{
				// between
				pos1 = Direction.getPosInDirection(x, y, direction, 2);
				pos2 = Direction.getPosInDirection(x, y, direction, 4);
			}
		}
		// No open positions for 6, game is over
		
		// Try to add the positions
		addPositionToPossitionList(positions, pos1, board);
		addPositionToPossitionList(positions, pos2, board);
		addPositionToPossitionList(positions, pos3, board);
		addPositionToPossitionList(positions, pos4, board);
		addPositionToPossitionList(positions, pos5, board);
		
		return positions;
	}
	
	/**
	 * Add a position to the position list without adding duplicate positions.
	 * @param positions
	 * @param pos
	 * @return
	 */
	private ArrayList<Position> addPositionToPossitionList(ArrayList<Position> positions, Position pos, Board board)
	{
		// position must be set
		if (pos != null)
		{
			// try to get a stone at new position
			Stone tryStone = board.getStone(pos.x, pos.y);
	
			// if no stone at position, lets see if we want to place it
			if (tryStone == null)
			{
				boolean positionExists = false;
				for (int a=0;a<positions.size();a++)
				{
					Position positionTest = positions.get(a);
					if (positionTest.x == pos.x && positionTest.y == pos.y)
						positionExists = true;
				}
				
				if (!positionExists)
				{
					Position position = new Position(pos.x,pos.y);
					positions.add(position);
				}
			}
		}
		return positions;
	}
	
	public ArrayList<Position> addSurroundingPositions(Board board, int myColor, ArrayList<Position> positions)
	{
	    
	    // For both colors
	    for (int p=1;p<3;p++)
	    {
	    	Player player;
	    	if (p==1){
	    		player = Player.BLACK;
	    	}
	    	else {
	    		player = Player.WHITE;
	    	}
	    	
	    	// for each stone of color
	    	Set<String> set = board.getStonesMap(player).keySet();
	    	Iterator<String> iter = set.iterator(); 
	    	
		    while (iter.hasNext())  
		    {  
		    	Stone s = board.getStonesMap(player).get(iter.next()); 
		    	
		    	// Try two stones in each way
		    	for (int i = 0;i<16;i++)
				{
		    		int stepsInDirection = 1;
		    		int direction = i;
		    		if (i > 7)
		    		{
		    			direction -= 9;
		    			stepsInDirection = 2;
		    		}
		    		
		    		// get a step in a direction
		    		Position pos = Direction.getPosInDirection(s.x,s.y,direction,stepsInDirection);
		    		
		    		// Try to add the position
		    		positions = addPositionToPossitionList(positions, pos, board);
				}
		    }
	    }
	    return positions;
	}
	
}
