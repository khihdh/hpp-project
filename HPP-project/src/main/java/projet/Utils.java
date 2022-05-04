package projet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import person.Person;

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
	
	public static String getNewEntry(int idToRead,Pair<Integer, Integer> idFR,Pair<Integer, Integer> idIT,Pair<Integer, Integer> idSP,String franceDataPath, String italyDataPath, String spainDataPath) {
		try  
		{  
			File france=new File(franceDataPath);
			File italy=new File(italyDataPath);
			File spain=new File(spainDataPath);
			
			Person personToReturn;
			
			//if it is the first reading of the files, we open the three of them to get the ids of the contaminated people
			if(idToRead == 0) {
				
				Person pf = null,pi = null,ps = null;
				
				FileReader fr=new FileReader(france);   //reads the file  
				BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
				String line;  
				if((line=br.readLine())!=null)  
				{  
					System.out.println(line);
					//france countryid = 0
					pf = new Person((short)0,line);
					idFR.setValue(pf.getPerson_id());
				}  
				fr.close();    //closes the stream and release the resources*
				
				
				fr=new FileReader(italy);   //reads the file  
				br=new BufferedReader(fr);  //creates a buffering character input stream  
				if((line=br.readLine())!=null)  
				{  
					System.out.println(line);
					//italy countryid = 1
					pi = new Person((short)1,line);
					idIT.setValue(pi.getPerson_id());
				}  
				fr.close();    //closes the stream and release the resources
				
				fr=new FileReader(spain);   //reads the file  
				br=new BufferedReader(fr);  //creates a buffering character input stream  
				if((line=br.readLine())!=null)  
				{  
					System.out.println(line);
					//spain countryid = 2
					ps = new Person((short)2,line);
					idSP.setValue(ps.getPerson_id());
				}  
				fr.close();    //closes the stream and release the resources
				
				if(idToRead == pf.getPerson_id()) {
					idFR.setKey(idFR.getKey() + 1);
					personToReturn = pf;
				}else if(idToRead == pi.getPerson_id()) {
					idIT.setKey(idIT.getKey() + 1);
					personToReturn = pi;
				}else if(idToRead == pi.getPerson_id()) {
					idSP.setKey(idSP.getKey() + 1);
					personToReturn = ps;
				}
			}else {
				
			}
			
			   
		}  
		catch(IOException e ){  
			e.printStackTrace();  
		}
		
		return "";
	}
	
	
	public static int returnId(String line) {
		String[] listeDeux = (line.split(",", 2));
		return Integer.parseInt(listeDeux[0]);
	}
	
	public void write(String top3) {
				//On �crit les r�sultats
				System.out.println(top3);
	}
	
}
