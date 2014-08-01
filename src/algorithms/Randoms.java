package algorithms;

import java.util.Random;

import logic.Board;
import logic.MoveSet;
import logic.Player;
import logic.Stone;

public class Randoms extends Algorithm
{
	Random rgen = new Random(System.currentTimeMillis());
	
	public MoveSet doMove(Board board, Player myColor, int turn)
	{
		
		Stone s1 = createRandomStone(board, myColor);
		Stone s2 = createRandomStone(board, myColor);
		while (s1 == s2)
		{
			s2 = createRandomStone(board, myColor);
		}
		
		return new MoveSet(s1,s2);
	}
	
	public Stone createRandomStone(Board b, Player player)
	{
		Stone s = null;
		while (s == null)
		{
			int x = rgen.nextInt(19);
			int y = rgen.nextInt(19);
			Stone temp = b.getStone(x,y);
			if (temp == null){
				s = new Stone(x,y,player);
				s.stoneNr = 10;
			}
		}
		return s;
	}
	
	
}
