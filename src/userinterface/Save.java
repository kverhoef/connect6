package userinterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import logic.Player;
import logic.Stone;

class Save
{
	public static void setFile(File file, UI applet) 
	{
	    Writer output = null;
	    String gameMatch = "(;GM[7]FF[4]";
	    
	    ArrayList<Stone> stones = applet.controller.stones;
	    
	    for(int i = 0; i<stones.size();i++)
	    {
	    	Stone stone = stones.get(i);
	    	
	    	if(stone.player == Player.WHITE)		//Wit
	    	{
	    		gameMatch += ";W";
	    	}
	    	else if(stone.player == Player.BLACK) //Zwart
	    	{
	    		gameMatch += ";B";
	    	}
	    	
	    	char x = (char)(stone.x+65);
	    	char y = (char)(stone.y+65);
	    	
	    	gameMatch += "["+x+y+"]";
	    }
	    gameMatch += "))";
		
	    try
		{
	    	output = new BufferedWriter(new FileWriter(file));
	    	
	    	output.write(gameMatch);
			output.close();
			
			File newFile = new File(file.getPath()+".sgf");
	        file.renameTo(newFile);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		catch (NullPointerException e2)
		{
			
		}
	    
        
    }  
}