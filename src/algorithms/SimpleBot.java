package algorithms;

import java.util.ArrayList;

import logic.Board;
import logic.Direction;
import logic.MoveSet;
import logic.Player;
import logic.Position;
import logic.Stone;

public class SimpleBot extends PointsAlgorithm {

	public SimpleBot(int defenseFactor) {
		
		defenseFactor = 5;
		
		// Offensive points for combinations
		int twoScoreAlive = 200;
		int twoScoreDead = 50;
		
		int threeScore = 300;
		int fourScore = 800;
		int fiveScore = 1600;
		
		this.two[0] = twoScoreAlive;
		this.two[1] = twoScoreDead;
		this.two[2] = twoScoreDead;
		this.two[3] = twoScoreAlive;
		this.two[4] = twoScoreDead;
		this.two[5] = twoScoreAlive;
		this.two[6] = twoScoreDead;
		this.two[7] = twoScoreDead;
		this.two[8] = twoScoreAlive;
		this.two[9] = twoScoreDead;
		this.two[10] = twoScoreAlive;
		this.two[11] = twoScoreDead;
		this.two[12] = twoScoreDead;
		this.two[13] = twoScoreAlive;
		
		
		this.three[0] = threeScore;
		this.three[1] = threeScore;
		this.three[2] = threeScore;
		this.three[3] = threeScore;
		this.three[4] = threeScore;
		this.three[5] = threeScore;
		this.three[6] = threeScore;
		this.three[7] = threeScore;
		this.three[8] = threeScore;
		this.three[9] = threeScore;
		this.three[10] = threeScore;
		this.three[11] = threeScore;
		this.three[12] = threeScore;
		this.three[13] = threeScore;
		this.three[14] = threeScore;
		this.three[15] = threeScore;
		this.three[16] = threeScore;
		this.three[17] = threeScore;
		this.three[18] = threeScore;
		this.three[19] = threeScore;
		this.three[20] = threeScore;
		this.three[21] = threeScore;
		this.three[22] = threeScore;
		this.three[23] = threeScore;
		this.three[24] = threeScore;
		
		
		this.four[0] = fourScore;
		this.four[1] = fourScore;
		this.four[2] = fourScore;
		this.four[3] = fourScore;
		this.four[4] = fourScore;
		this.four[5] = fourScore;
		this.four[6] = fourScore;
		this.four[7] = fourScore;
		this.four[8] = fourScore;
		this.four[9] = fourScore;
		this.four[10] = fourScore;
		this.four[11] = fourScore;
		this.four[12] = fourScore;
		this.four[13] = fourScore;
		this.four[14] = fourScore;
		this.four[15] = fourScore;
		this.four[16] = fourScore;
		this.four[17] = fourScore;
		this.four[18] = fourScore;
		this.four[19] = fourScore;
		this.four[20] = fourScore;
		this.four[21] = fourScore;
		this.four[22] = fourScore;
		this.four[23] = fourScore;
		this.four[24] = fourScore;
		this.four[25] = fourScore;
		
		
		this.five[0] = fiveScore;
		this.five[1] = fiveScore;
		this.five[2] = fiveScore;
		this.five[3] = fiveScore;
		this.five[4] = fiveScore;
		this.five[5] = fiveScore;
		this.five[6] = fiveScore;
		this.five[7] = fiveScore;
		this.five[8] = fiveScore;
		this.five[9] = fiveScore;
		this.five[10] = fiveScore;
		this.five[11] = fiveScore;
		this.five[12] = fiveScore;
		this.five[13] = fiveScore;
		this.five[14] = fiveScore;
		this.five[15] = fiveScore;
		this.five[16] = fiveScore;
		this.five[17] = fiveScore;
		
		this.six[0] = 99999;
		
		// Defensive points for combinations		
		this.defensiveTwo[0] = twoScoreAlive * defenseFactor;
		this.defensiveTwo[1] = twoScoreDead * defenseFactor;
		this.defensiveTwo[2] = twoScoreDead * defenseFactor;
		this.defensiveTwo[3] = twoScoreAlive * defenseFactor;
		this.defensiveTwo[4] = twoScoreDead * defenseFactor;
		this.defensiveTwo[5] = twoScoreAlive * defenseFactor;
		this.defensiveTwo[6] = twoScoreDead * defenseFactor;
		this.defensiveTwo[7] = twoScoreDead * defenseFactor;
		this.defensiveTwo[8] = twoScoreAlive * defenseFactor;
		this.defensiveTwo[9] = twoScoreDead * defenseFactor;
		this.defensiveTwo[10] = twoScoreAlive * defenseFactor;
		this.defensiveTwo[11] = twoScoreDead * defenseFactor;
		this.defensiveTwo[12] = twoScoreDead * defenseFactor;
		this.defensiveTwo[13] = twoScoreAlive * defenseFactor;
		
		this.defensiveThree[0] = threeScore * defenseFactor;
		this.defensiveThree[1] = threeScore * defenseFactor;
		this.defensiveThree[2] = threeScore * defenseFactor;
		this.defensiveThree[3] = threeScore * defenseFactor;
		this.defensiveThree[4] = threeScore * defenseFactor;
		this.defensiveThree[5] = threeScore * defenseFactor;
		this.defensiveThree[6] = threeScore * defenseFactor;
		this.defensiveThree[7] = threeScore * defenseFactor;
		this.defensiveThree[8] = threeScore * defenseFactor;
		this.defensiveThree[9] = threeScore * defenseFactor;
		this.defensiveThree[10] = threeScore * defenseFactor;
		this.defensiveThree[11] = threeScore * defenseFactor;
		this.defensiveThree[12] = threeScore * defenseFactor;
		this.defensiveThree[13] = threeScore * defenseFactor;
		this.defensiveThree[14] = threeScore * defenseFactor;
		this.defensiveThree[15] = threeScore * defenseFactor;
		this.defensiveThree[16] = threeScore * defenseFactor;
		this.defensiveThree[17] = threeScore * defenseFactor;
		this.defensiveThree[18] = threeScore * defenseFactor;
		this.defensiveThree[19] = threeScore * defenseFactor;
		this.defensiveThree[20] = threeScore * defenseFactor;
		this.defensiveThree[21] = threeScore * defenseFactor;
		this.defensiveThree[22] = threeScore * defenseFactor;
		this.defensiveThree[23] = threeScore * defenseFactor;
		this.defensiveThree[24] = threeScore * defenseFactor;
		
		this.defensiveFour[0] = fourScore * defenseFactor;
		this.defensiveFour[1] = fourScore * defenseFactor;
		this.defensiveFour[2] = fourScore * defenseFactor;
		this.defensiveFour[3] = fourScore * defenseFactor;
		this.defensiveFour[4] = fourScore * defenseFactor;
		this.defensiveFour[5] = fourScore * defenseFactor;
		this.defensiveFour[6] = fourScore * defenseFactor;
		this.defensiveFour[7] = fourScore * defenseFactor;
		this.defensiveFour[8] = fourScore * defenseFactor;
		this.defensiveFour[9] = fourScore * defenseFactor;
		this.defensiveFour[10] = fourScore * defenseFactor;
		this.defensiveFour[11] = fourScore * defenseFactor;
		this.defensiveFour[12] = fourScore * defenseFactor;
		this.defensiveFour[13] = fourScore * defenseFactor;
		this.defensiveFour[14] = fourScore * defenseFactor;
		this.defensiveFour[15] = fourScore * defenseFactor;
		this.defensiveFour[16] = fourScore * defenseFactor;
		this.defensiveFour[17] = fourScore * defenseFactor;
		this.defensiveFour[18] = fourScore * defenseFactor;
		this.defensiveFour[19] = fourScore * defenseFactor;
		this.defensiveFour[20] = fourScore * defenseFactor;
		this.defensiveFour[21] = fourScore * defenseFactor;
		this.defensiveFour[22] = fourScore * defenseFactor;
		this.defensiveFour[23] = fourScore * defenseFactor;
		this.defensiveFour[24] = fourScore * defenseFactor;
		this.defensiveFour[25] = fourScore * defenseFactor;
		
		this.defensiveFive[0] = fiveScore * defenseFactor;
		this.defensiveFive[1] = fiveScore * defenseFactor;
		this.defensiveFive[2] = fiveScore * defenseFactor;
		this.defensiveFive[3] = fiveScore * defenseFactor;
		this.defensiveFive[4] = fiveScore * defenseFactor;
		this.defensiveFive[5] = fiveScore * defenseFactor;
		this.defensiveFive[6] = fiveScore * defenseFactor;
		this.defensiveFive[7] = fiveScore * defenseFactor;
		this.defensiveFive[8] = fiveScore * defenseFactor;
		this.defensiveFive[9] = fiveScore * defenseFactor;
		this.defensiveFive[10] = fiveScore * defenseFactor;
		this.defensiveFive[11] = fiveScore * defenseFactor;
		this.defensiveFive[12] = fiveScore * defenseFactor;
		this.defensiveFive[13] = fiveScore * defenseFactor;
		this.defensiveFive[14] = fiveScore * defenseFactor;
		this.defensiveFive[15] = fiveScore * defenseFactor;
		this.defensiveFive[16] = fiveScore * defenseFactor;
		this.defensiveFive[17] = fiveScore * defenseFactor;
		
		this.defensiveSix[0] = 99999;
	}

	@Override
	public MoveSet doMove(Board board, Player myColor, int turn) {
		
		boolean debug = false;
		
		if (debug)
			System.out.println("SimpleBot move turn: " + turn);
		
		
		if (turn == 1){
			
			if (debug)
				System.out.println("Default move");
			
			return new MoveSet(new Stone(9, 9, myColor), null);
		}
		else if (turn == 2){
			
			if (debug)
				System.out.println("Default move");
			
			return new MoveSet(new Stone(10, 9, myColor), new Stone(9, 10, myColor));
		}
		
		ArrayList<Position> positions = new ArrayList<Position>();
		
		for (int y=0;y < 19;y++)
		{
			for (int x=0;x < 19;x++)
			{
				Stone stone = board.getStone(x, y);
				if (stone != null){
					
					positions.add(Direction.getPosInDirection(x, y, Direction.LEFT, 1));
					positions.add(Direction.getPosInDirection(x, y, Direction.UP, 1));
					positions.add(Direction.getPosInDirection(x, y, Direction.DOWN, 1));
					positions.add(Direction.getPosInDirection(x, y, Direction.RIGHT, 1));
					
					positions.add(Direction.getPosInDirection(x, y, Direction.LEFT_DOWN, 1));
					positions.add(Direction.getPosInDirection(x, y, Direction.LEFT_UP, 1));
					positions.add(Direction.getPosInDirection(x, y, Direction.RIGHT_DOWN, 1));
					positions.add(Direction.getPosInDirection(x, y, Direction.RIGHT_UP, 1));
					
					
					positions.add(Direction.getPosInDirection(x, y, Direction.LEFT, 2));
					positions.add(Direction.getPosInDirection(x, y, Direction.UP, 2));
					positions.add(Direction.getPosInDirection(x, y, Direction.DOWN, 2));
					positions.add(Direction.getPosInDirection(x, y, Direction.RIGHT, 2));
					
					positions.add(Direction.getPosInDirection(x, y, Direction.LEFT_DOWN, 2));
					positions.add(Direction.getPosInDirection(x, y, Direction.LEFT_UP, 2));
					positions.add(Direction.getPosInDirection(x, y, Direction.RIGHT_DOWN, 2));
					positions.add(Direction.getPosInDirection(x, y, Direction.RIGHT_UP, 2));
					
				}
			}
		}
		
		Stone stone1 = getBestStonePosition(positions,board,myColor, true, turn);
		board.place(stone1);
		Stone stone2 = getBestStonePosition(positions,board,myColor, false, turn);
		board.undo(stone1);
		
		assert stone1 != null;
		assert stone2 != null;
		
		return new MoveSet(stone1, stone2);
	}

}
