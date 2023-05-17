/*//Christopher Liebsch, Jan Baudler
 * ToDo: import standard java libraries you need e.g. java.io, java.utils, ... */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;

public class MonsterLoader 
{
  // --------------------------------------------------------------- //
  /**
   * Load the monsters from the given file. If something is wrong with a monster, it is not loaded.
   * The loading process continues with the next monster.
   */
  public static List<Monster> loadMonsterFile(String file_path) 
  {
	  List<Monster> ret = new ArrayList<Monster>();
	  String actualLine;
	  int lineNumber = 0;
	  String name = "";
	  int maxHP = 1;
	  int attack = 1;
	  float weight = 1;
	  float multi = 1;
	  String substring = "";
	  try
	  {
		  BufferedReader reader = new BufferedReader(new FileReader(file_path));
		  try 
		  {
			  
			  while((actualLine = reader.readLine()) != null)
			  {
				  switch (lineNumber) 
				  {
				  	case 0:
				  		if(actualLine.equals("Monster"))
				  			++lineNumber;
				  		break;
				  	case 1:
				  		if(actualLine.startsWith("name ")) 
				  		{
				  			name = actualLine.substring(5);						  				

				  			++lineNumber;
				  		}
				  		else lineNumber = 0;
				  		break;
				  	case 2:
				  		if(actualLine.startsWith("maxHP "))
				  		{
				  			try 
				  			{
				  				maxHP = Integer.parseInt(actualLine.substring(6));
				  				++lineNumber;
				  			}
				  			catch(NumberFormatException e)
				  			{
				  				e.printStackTrace();
				  			}
				  		}
				  		else lineNumber = 0;
				  		break;
				  	case 3:
				  		if(actualLine.startsWith("attack "))
				  		{
				  			try 
				  			{
				  				
				  				attack = Integer.parseInt(actualLine.substring(7));
				  				++lineNumber;
				  			}
				  			catch(NumberFormatException e)
				  			{
				  				e.printStackTrace();
				  			}
				  		}
				  		else lineNumber = 0;
				  		break;
				  	case 4: 
				  		if(actualLine.startsWith("weight "))
				  		{
				  			try
				  			{
				  				weight = Float.parseFloat(actualLine.substring(7));
				  				++lineNumber;
				  			}
				  			catch(NumberFormatException e)
				  			{
				  				e.printStackTrace();
				  			}
				  		}
				  		else lineNumber = 0;
				  		break;
				  	case 5:
				  		if(actualLine.startsWith("multi "))
				  		{
				  			try
				  			{				  				
				  				multi = Float.parseFloat(actualLine.substring(6));
				  				++lineNumber;
				  			}
				  			catch(NumberFormatException e)
				  			{
				  				e.printStackTrace();
				  			}
				  			
				  			ret.add(new Monster(name, maxHP, weight, attack, multi));
				  			
				  		}
				  		lineNumber = 0;
				  		break;		
				  }
			  }
		  }
		  catch(IOException e)
		  {
			  e.printStackTrace();
		  }
	  }
	  catch(IOException e)
	  {
		  e.printStackTrace();
	  }
	  return ret;
  }

  // --------------------------------------------------------------- //
  public static void main(String[] args)
  {
	  
  }
}
