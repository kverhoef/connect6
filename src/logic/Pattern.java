package logic;
/**
 * Patterns are used for finding combinations. A 2 dimensional int represents the pattern. 
 * It uses the following codes to determine if a pattern matches:
 *	0 dont care
 *	1 must be my color
 *	2 must be open
 *	3 must be other color
 */
public class Pattern 
{
	// The pattern
	int[][] pattern;
	// Geeft aan of de uitkomst van de pattern true of false moet zijn voor een pattern collectie
	boolean resultMustbe = true;
	
	/**
	 * Constructor1
	 */
	public Pattern(int[][] pattern, boolean resultMustbe)
	{
		this.pattern = pattern;
		this.resultMustbe = resultMustbe;
	}
	
	/**
	 * Constructor2, resultMustbe = standard true.
	 */
	public Pattern(int[][] pattern)
	{
		this.pattern = pattern;
	}
	
	/**
	 * Mirrors the pattern horizontaly.
	 */
	public void mirrorPattern()
	{
		int[][] mirroredPattern = new int[pattern.length][pattern[0].length];
		int count = 0;
		for (int i= pattern.length - 1; i >= 0;i--)
		{
			mirroredPattern[i] = pattern[count].clone();
			count++;
		}
		this.pattern = mirroredPattern;
	}
	
	/**
	 * Rotates the pattern clockwise.
	 */
	public void rotatePattern()
    {
        int row = this.pattern.length;
        int column = this.pattern[0].length;
        
        int[][] newarray = new int[column][row];
        
        for (int i = 0; i < column; i++)
        {
            int x = row - 1;
            for (int j = 0; j < row; j++)
            {
                newarray[i][j] = this.pattern[x--][i];
            }
        }
        
        this.pattern = newarray;
    }
	
	/**
	 * Check if a pattern patches on a given position (patternX, patternY). 
	 * There is an option to check the pattern rotated 45 degrees (rotated45degrees), 
	 * this can only be used on patterns that consist of a single line horizontaly or verticaly. 
	 */
	public boolean checkPattern(Player[][] field, int patternX, int patternY, boolean rotated45degrees)
	{
		int yFrom = patternY - ((pattern.length - 1) / 2); 		// y startPosition in field
		int xFrom = patternX - ((pattern[0].length - 1) / 2); 	// x startPosition in field
		int yTo = yFrom + pattern.length;
		int xTo = xFrom + pattern[0].length;

		Player player = field[patternY][patternX];

		for (int y=yFrom;y < yTo;y++){

			for (int x=xFrom;x < xTo;x++)
			{
				int pX = x - xFrom; // pattern x
				int pY = y - yFrom;	// pattern y
			
				int fX = x; // field x
				int fY = y; // field y
				
				// When rotated fix x and y
				if (rotated45degrees)
				{
					// Normaly 1 row horizontaly
					if (pattern.length == 1)
					{
						// fix y
						fY += pX - ((pattern[0].length - 1) / 2);
					}
					// Normaly 1 row vertical
					else if (pattern[0].length == 1)
					{
						fX -= pY - ((pattern.length - 1) / 2);
					}
				}

                int patternValue = pattern[pY][pX];

				try
				{
					Player fieldValue = field[fY][fX];

					// 1 must be my color
					if (patternValue == 1 && fieldValue != player)
						return false;
					
					// 2 must be open
					if (patternValue == 2 && fieldValue != null)
						return false;
					
					// 3 must be other color
					if (patternValue == 3 && (fieldValue == player || fieldValue == null))
						return false;
				}
				catch (ArrayIndexOutOfBoundsException e)
				{
					// Pattern valt buiten het veld, 
					// als de pattern waarde geen 0 is of blocked
					// kan de pattern niet.
//					if (pattern[pY][pX] != 0)
//						return false;
                    // {0,0,0,3,2,1,1,2,2,2,0}

                    if (patternValue != 0 && patternValue != 3){
                        return false;
                    }
				}
			
			}
		}
		
		return true;
	}
	
	/**
	 * Prints a representation of the pattern for testing purposes.
	 */
	public void print()
	{
        for(int i = 0; i<pattern.length;i++)
        {
            for(int j = 0; j<pattern[0].length;j++)
            {
                System.out.print(pattern[i][j]+" ");
            }
            System.out.println("");
        }
	}
	
}
