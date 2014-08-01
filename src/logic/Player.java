package logic;

public enum Player {
	BLACK, WHITE;
	
	public static Player getOpponent(Player myPlayer){
		if (myPlayer.equals(BLACK)){
			return WHITE;
		}
		else {
			return BLACK;
		}
	}
	
}
