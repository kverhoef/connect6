package logic;

/**
 * Class used to hold the two stones from a move for placing purpose.
 * On the first turn only one stone is added, you have to set stone2 to null.
 */
public class MoveSet 
{
	public Stone stone1;
	public Stone stone2;
	
	public MoveSet(Stone stone1, Stone stone2)
	{
		this.stone1 = stone1;
		this.stone2 = stone2;
	}
	
}
