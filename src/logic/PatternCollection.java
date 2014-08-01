package logic;
import java.util.ArrayList;

/**
 * Used to hold a collection of patterns.
 * The serie of patterns can then all be checked calling 1 method.
 */
public class PatternCollection 
{
	ArrayList<Pattern> patterns = new ArrayList<Pattern>();

	/**
	 * Add a pattern to the collection
	 * @param pattern
	 */
	public void addPatern(Pattern pattern)
	{
		patterns.add(pattern);
	}
	
	/**
	 * Returns the number of the failed pattern when a pattern fails.
	 * If every pattern succeeds, -1 is returned.
	 */
	public int checkPatterns(Player[][] field, int patternX, int patternY, boolean rotated45degrees)
	{
		for (int i = 0;i<patterns.size();i++)
		{
			Pattern p = patterns.get(i);
			if (p.resultMustbe == p.checkPattern(field, patternX, patternY,rotated45degrees))
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Rotates all paters in the collection.
	 */
	public void rotatePatterns()
	{
		for (int i = 0;i<patterns.size();i++)
		{
			Pattern p = patterns.get(i);
			p.rotatePattern();
		}
	}
}
