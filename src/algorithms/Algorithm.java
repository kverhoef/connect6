package algorithms;

import logic.Board;
import logic.MoveSet;
import logic.Player;

public abstract class Algorithm 
{
	public abstract MoveSet doMove(Board board, Player myPlayer, int turn);
	
}