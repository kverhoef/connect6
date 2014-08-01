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
		
		defenseFactor = 2;
		
		// Offensive points for combinations
		int twoScoreAlive = 200;
		int twoScoreDead = 50;
		
		int threeScoreDead = 100;
        int threeScoreAlive = 300;

		int fourScore = 1800;

        int defensiveFourScore = 99999;
        int defensiveFiveScore = 99999;

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

		this.three[0] = threeScoreAlive;
		this.three[1] = threeScoreDead;
		this.three[2] = threeScoreDead;
		this.three[3] = threeScoreAlive;
		this.three[4] = threeScoreDead;
		this.three[5] = threeScoreAlive;
		this.three[6] = threeScoreDead;
		this.three[7] = threeScoreDead;
		this.three[8] = threeScoreAlive;
		this.three[9] = threeScoreDead;
		this.three[10] = threeScoreAlive;
		this.three[11] = threeScoreDead;
		this.three[12] = threeScoreDead;
		this.three[13] = threeScoreAlive;
		this.three[14] = threeScoreDead;
		this.three[15] = threeScoreDead;
		this.three[16] = threeScoreDead;
		this.three[17] = threeScoreDead;
		this.three[18] = threeScoreDead;
		this.three[19] = threeScoreDead;
		this.three[20] = threeScoreDead;
		this.three[21] = threeScoreDead;
		this.three[22] = threeScoreDead;
		this.three[23] = threeScoreDead;
		this.three[24] = threeScoreDead;
		
		
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
		
		this.defensiveThree[0] = threeScoreAlive * defenseFactor;
		this.defensiveThree[1] = threeScoreDead * defenseFactor;
		this.defensiveThree[2] = threeScoreDead * defenseFactor;
		this.defensiveThree[3] = threeScoreAlive * defenseFactor;
		this.defensiveThree[4] = threeScoreDead * defenseFactor;
		this.defensiveThree[5] = threeScoreAlive * defenseFactor;
		this.defensiveThree[6] = threeScoreDead * defenseFactor;
		this.defensiveThree[7] = threeScoreDead * defenseFactor;
		this.defensiveThree[8] = threeScoreAlive * defenseFactor;
		this.defensiveThree[9] = threeScoreDead * defenseFactor;
		this.defensiveThree[10] = threeScoreAlive * defenseFactor;
		this.defensiveThree[11] = threeScoreDead * defenseFactor;
		this.defensiveThree[12] = threeScoreDead * defenseFactor;
		this.defensiveThree[13] = threeScoreAlive * defenseFactor;
		this.defensiveThree[14] = threeScoreDead * defenseFactor;
		this.defensiveThree[15] = threeScoreDead * defenseFactor;
		this.defensiveThree[16] = threeScoreDead * defenseFactor;
		this.defensiveThree[17] = threeScoreDead * defenseFactor;
		this.defensiveThree[18] = threeScoreDead * defenseFactor;
		this.defensiveThree[19] = threeScoreDead * defenseFactor;
		this.defensiveThree[20] = threeScoreDead * defenseFactor;
		this.defensiveThree[21] = threeScoreDead * defenseFactor;
		this.defensiveThree[22] = threeScoreDead * defenseFactor;
		this.defensiveThree[23] = threeScoreDead * defenseFactor;
		this.defensiveThree[24] = threeScoreDead * defenseFactor;
		
		this.defensiveFour[0] = defensiveFourScore * defenseFactor;
		this.defensiveFour[1] = defensiveFourScore * defenseFactor;
		this.defensiveFour[2] = defensiveFourScore * defenseFactor;
		this.defensiveFour[3] = defensiveFourScore * defenseFactor;
		this.defensiveFour[4] = defensiveFourScore * defenseFactor;
		this.defensiveFour[5] = defensiveFourScore * defenseFactor;
		this.defensiveFour[6] = defensiveFourScore * defenseFactor;
		this.defensiveFour[7] = defensiveFourScore * defenseFactor;
		this.defensiveFour[8] = defensiveFourScore * defenseFactor;
		this.defensiveFour[9] = defensiveFourScore * defenseFactor;
		this.defensiveFour[10] = defensiveFourScore * defenseFactor;
		this.defensiveFour[11] = defensiveFourScore * defenseFactor;
		this.defensiveFour[12] = defensiveFourScore * defenseFactor;
		this.defensiveFour[13] = defensiveFourScore * defenseFactor;
		this.defensiveFour[14] = defensiveFourScore * defenseFactor;
		this.defensiveFour[15] = defensiveFourScore * defenseFactor;
		this.defensiveFour[16] = defensiveFourScore * defenseFactor;
		this.defensiveFour[17] = defensiveFourScore * defenseFactor;
		this.defensiveFour[18] = defensiveFourScore * defenseFactor;
		this.defensiveFour[19] = defensiveFourScore * defenseFactor;
		this.defensiveFour[20] = defensiveFourScore * defenseFactor;
		this.defensiveFour[21] = defensiveFourScore * defenseFactor;
		this.defensiveFour[22] = defensiveFourScore * defenseFactor;
		this.defensiveFour[23] = defensiveFourScore * defenseFactor;
		this.defensiveFour[24] = defensiveFourScore * defenseFactor;
		this.defensiveFour[25] = defensiveFourScore * defenseFactor;
		
		this.defensiveFive[0] = defensiveFiveScore * defenseFactor;
		this.defensiveFive[1] = defensiveFiveScore * defenseFactor;
		this.defensiveFive[2] = defensiveFiveScore * defenseFactor;
		this.defensiveFive[3] = defensiveFiveScore * defenseFactor;
		this.defensiveFive[4] = defensiveFiveScore * defenseFactor;
		this.defensiveFive[5] = defensiveFiveScore * defenseFactor;
		this.defensiveFive[6] = defensiveFiveScore * defenseFactor;
		this.defensiveFive[7] = defensiveFiveScore * defenseFactor;
		this.defensiveFive[8] = defensiveFiveScore * defenseFactor;
		this.defensiveFive[9] = defensiveFiveScore * defenseFactor;
		this.defensiveFive[10] = defensiveFiveScore * defenseFactor;
		this.defensiveFive[11] = defensiveFiveScore * defenseFactor;
		this.defensiveFive[12] = defensiveFiveScore * defenseFactor;
		this.defensiveFive[13] = defensiveFiveScore * defenseFactor;
		this.defensiveFive[14] = defensiveFiveScore * defenseFactor;
		this.defensiveFive[15] = defensiveFiveScore * defenseFactor;
		this.defensiveFive[16] = defensiveFiveScore * defenseFactor;
		this.defensiveFive[17] = defensiveFiveScore * defenseFactor;
		
		this.defensiveSix[0] = 99999 * defenseFactor;
	}

	@Override
	public MoveSet doMove(Board board, Player player, int turn) {
		
		boolean debug = true;
		
		if (debug)
			System.out.println("SimpleBot move turn: " + turn);
		
		
		if (turn == 1){
			
			if (debug)
				System.out.println("Default move");
			
			return new MoveSet(new Stone(9, 9, player), null);
		}
		else if (turn == 2){
			
			if (debug)
				System.out.println("Default move");
			
			return new MoveSet(new Stone(10, 9, player), new Stone(9, 10, player));
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
		
		StonePoints stonePoints1 = getBestStonePosition(positions,board,player, true, turn);
		board.place(stonePoints1.stone);
        StonePoints stonePoints2 = getBestStonePosition(positions,board,player, false, turn);
		board.undo(stonePoints2.stone);

		assert stonePoints1.stone != null;
		assert stonePoints2.stone != null;

		return new MoveSet(stonePoints1.stone, stonePoints2.stone);

	}

}
