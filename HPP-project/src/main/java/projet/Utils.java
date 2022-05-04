package projet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

	/**
	 * 
	 * @param contamination_date the contamination date
	 * @param current_date the current date
	 * @return the score of your contamination
	 */
	public static int calculateScore(int contamination_date, int current_date) {
		
		//1 day = 86400
		//7 days = 604 800
		//14 days = 1 209 600
		
		int res = current_date - contamination_date;
		if(res < 604800) 
			res = 10;
		else if(res > 604800 && res < 1209600)
			res = 4;
		else if(res >1209600)
			res = 0;
		
		return res;
	}
	
	public static String getNewEntry(int idToRead,int idFR,int idIT,int idSP,String franceDataPath, String italyDataPath, String spainDataPath) {
		try  
		{  
			File france=new File(franceDataPath);
			File italy=new File(italyDataPath);
			File spain=new File(spainDataPath);
			
			FileReader fr=new FileReader(france);   //reads the file  
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
			StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters  
			String line;  
			while((line=br.readLine())!=null)  
			{  
				sb.append(line);      //appends line to string buffer  
				sb.append("\n");     //line feed   
			}  
			
			fr.close();    //closes the stream and release the resources  
			System.out.println("Contents of File: ");  
			System.out.println(sb.toString());   //returns a string that textually represents the object  
		}  
		catch(IOException e){  
			e.printStackTrace();  
		}  
		
		return "";
	}
	
	public void write(String top3) {
				//On �crit les r�sultats
				System.out.println(top3);
	}
	
	
}
