package projet;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

import person.Person;

import java.io.BufferedWriter;
public class Utils {

	/**
	 * This function calculate the score between the current date and the date of contamination of the person passed in parameter
	 * It returns the score (0 - 4 - 10) 
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
	
	/**
	 * This function is the first naive function designed to return a Person object that is the next case to add to our chains of contamination.
	 * 
	 * if the function is called for the first time, it starts by reading all the 3 files to determine which case corresponds to the id 0.
	 * 
	 * Then, we check all the 3 values read in the different files to see wich one corresponds and we update the current reading status of each file
	 * 
	 * We finaly return a person created from the parsing of the line read.
	 * 
	 * @param idToRead the id of the case we want to read
	 * @param idFR a couple composed of the id of the last read case in the france file and its line number
	 * @param idIT a couple composed of the id of the last read case in the italy file and its line number
	 * @param idSP a couple composed of the id of the last read case in the spain file and its line number
	 * @param france the france file we want to read
	 * @param italy the italy file we want to read
	 * @param spain the spain file we want to read
	 * @return the person to add to a chain and a int indicating if the reading is over or not
	 */
	public static Pair<Person,Integer> getNewEntry(int idToRead,Pair<Integer, Integer> idFR,Pair<Integer, Integer> idIT,Pair<Integer, Integer> idSP,File france, File italy, File spain) {
		Person personToReturn = null;
		Integer fin = 0;
		try  
		{  
			//if it is the first reading of the files, we open the three of them to get the ids of the contaminated people
			if(idToRead == 0) {
				
				Person pf = null,pi = null,ps = null;
				
				FileReader fr=new FileReader(france);   //reads the file  
				BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
				String line;  
				if((line=br.readLine())!=null)  
				{  
					pf = new Person((short)0,line); //we initalize the first person from france file
					idFR.setValue(pf.getPerson_id());
				}  
				fr.close();    //closes the stream and release the resources*
				
				
				fr=new FileReader(italy);   //reads the file  
				br=new BufferedReader(fr);  //creates a buffering character input stream  
				if((line=br.readLine())!=null)  
				{  
					//System.out.println(line);
					//italy countryid = 1
					pi = new Person((short)1,line);//we initalize the first person from italy file
					idIT.setValue(pi.getPerson_id());
				}  
				fr.close();    //closes the stream and release the resources
				
				fr=new FileReader(spain);   //reads the file  
				br=new BufferedReader(fr);  //creates a buffering character input stream  
				if((line=br.readLine())!=null)  
				{  
					//System.out.println(line);
					//spain countryid = 2
					ps = new Person((short)2,line);//we initalize the first person from spain file
					idSP.setValue(ps.getPerson_id());
				}  
				fr.close();    //closes the stream and release the resources
				
				//we determine which person corresponds to the case we want to read
				if(idToRead == pf.getPerson_id()) {
					idFR.setKey(idFR.getKey() + 1);
					personToReturn = pf;
					if((line = FileUtils.readLines(france).get(1))!=null) {
						idFR.setValue(returnId(line));
					}
				}else if(idToRead == pi.getPerson_id()) {
					idIT.setKey(idIT.getKey() + 1);
					personToReturn = pi;
					if((line = FileUtils.readLines(italy).get(1))!=null) {
						idIT.setValue(returnId(line));
					}
				}else if(idToRead == ps.getPerson_id()) {
					idSP.setKey(idSP.getKey() + 1);
					personToReturn = ps;
					if((line = FileUtils.readLines(spain).get(1))!=null) {
						idSP.setValue(returnId(line));
					}
				}
				
				 
				
			}else {//if it is not the first reading of the 3 files
				short countryId = -1;
				
				//we determine from the already read values which person corresponds to the current case
				if(idToRead == idFR.getValue()) {
					countryId = 0;
				}else if(idToRead == idIT.getValue()) {
					countryId = 1;
				}else if(idToRead == idSP.getValue()) {
					countryId = 2;
				}
				
				
				String line;  
				
				switch(countryId) {
				case 0:
					
					//read the current line and creates a person with the data and increment line number id by 1
					if((line = FileUtils.readLines(france).get(idFR.getKey()))!=null)  
					{  
						//System.out.println(line);
						personToReturn = new Person(countryId,line);
						idFR.setKey(idFR.getKey()+1);
					}
					
					try {
						//read the next line to get the next person id
						if((line = FileUtils.readLines(france).get(idFR.getKey()))!=null)
							idFR.setValue(returnId(line));
					}catch(IndexOutOfBoundsException e) {
						//e.printStackTrace();
						idFR.setKey(-1);
						idFR.setValue(-1);
					}
					
					break;
				case 1:
					//read the current line and creates a person with the data and increment line number id by 1
					if((line = FileUtils.readLines(italy).get(idIT.getKey()))!=null)  
					{  
						//System.out.println(line);
						personToReturn = new Person(countryId,line);
						idIT.setKey(idIT.getKey()+1);
					}
					
					try {
						//read the next line to get the next person id
						if((line = FileUtils.readLines(italy).get(idIT.getKey()))!=null)
							idIT.setValue(returnId(line));
					}catch(IndexOutOfBoundsException e) {
						//e.printStackTrace();
						idIT.setKey(-1);
						idIT.setValue(-1);
					}
					break;
				case 2:
					//read the current line and creates a person with the data and increment line number id by 1
					if((line = FileUtils.readLines(spain).get(idSP.getKey()))!=null)  
					{  
						//System.out.println(line);
						personToReturn = new Person(countryId,line);
						idSP.setKey(idSP.getKey()+1);
					}
					
					try {
						//read the next line to get the next person id
						if((line = FileUtils.readLines(spain).get(idSP.getKey()))!=null)
							idSP.setValue(returnId(line));
					}catch(IndexOutOfBoundsException e) {
						//e.printStackTrace();
						idSP.setKey(-1);
						idSP.setValue(-1);
					}
					break;
				}
			}
			 
		}  
		catch(IOException e ){  
			e.printStackTrace();  
		}
		
		//check if we haven't finished reading the files
		if (idFR.getKey().equals(-1) && idIT.getKey().equals(-1) && idSP.getKey().equals(-1))
		{
			fin = 1;
		}
		
		//return our 2 variables.
		return new Pair(personToReturn,fin);
	}
	
	
	
	
	/**
	 * The final Version of our function for reading the 3 files. In this version, we pass in parameter 3 bufferd reader, which only allow one thread to read the files without being blocked.
	 * However this method is absolutely faster than the older one and only need 1 thread to be return a case fast enough.
	 * 
	 * As the old version, it starts by reading the first 3 values of each filess and create 3 persons. Then, determine which person corresponds
	 * to the case we want to read.
	 * 
	 * We finaly assign the person designed as the person to return and read the next person in the same file to update the status of each file
	 * 
	 * @param idToRead id of the case we want to read
	 * @param pf french person that will be read next
	 * @param pi italian person that will be read next
	 * @param ps spanish person that will be read next
	 * @param france the buffered reader of the france file
	 * @param italy the buffered reader of the italy file
	 * @param spain the buffered reader of the spain file
	 * 
	 * 
	 * @return 3 values : the person to return, the status of the reading and the last person of each file
	 */
	public static Triplet<Person,Integer,Triplet<Person,Person,Person>> getNewEntry2(int idToRead,Person pf,Person pi,Person ps,BufferedReader france, BufferedReader italy, BufferedReader spain) {
		Person personToReturn = null;
		Integer fin = 0;
		boolean finFR = false,finIT = false,finSP = false;
		
		
		//if it is the first reading of the files, we open the three of them to get the
		//data of the 3 first person of each file to initialise the reading
		if(idToRead == 0) {
			
			try {
			String line;  
			if((line=france.readLine())!=null)
				pf = new Person((short)0,line);
			if((line=italy.readLine())!=null)  
				pi = new Person((short)1,line);
			if((line=spain.readLine())!=null)  
				ps = new Person((short)1,line);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		//then we read their value and chose in which file is the case
		short countryId = -1;
		
		//System.out.println("id to read : " + idToRead + " in [" + pf.getPerson_id() + "," + pi.getPerson_id() + "," + ps.getPerson_id() + "]");
		
		if(pf != null && idToRead == pf.getPerson_id()) {
			countryId = 0;
			//System.out.println("person to read is in france file");
		}else if(pi != null && idToRead == pi.getPerson_id()) {
			countryId = 1;
			//System.out.println("person to read is in italy file");
		}else if(ps != null && idToRead == ps.getPerson_id() ) {
			countryId = 2;
			//System.out.println("person to read is in spain file");
		}
		
		
		
		String line;  
		
		//depending of the files where the case is, we assign the last person of the file as the person to return and read the next value
		// in order to update the status of each file and check if the file is not empty
		switch(countryId) {
		case 0:
			personToReturn = pf;
			try {
				if((line=france.readLine())!=null)
					pf = new Person((short)0,line);
				else{
					pf = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 1:
			personToReturn = pi;
			try {
				if((line=italy.readLine())!=null)
					pi = new Person((short)0,line);
				else {
					pi = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			personToReturn = ps;
			try {
				if((line=spain.readLine())!=null)
					ps = new Person((short)0,line);
				else{
					ps = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
		
		// we check if the reading is finished
		if(pf== null && pi == null && ps == null) 
			fin = 1;
		
		Triplet persons = new Triplet<Person, Person, Person>(pf,pi,ps);
		return new Triplet(personToReturn,fin,persons);
	}
	
	/**
	 * Parse a String containing the raw data of a case to return the id of a case
	 * 
	 * @param line a string containing the raw data of the case we want to extract only the id
	 * @return the id of the case
	 */
	public static int returnId(String line) {
		String[] listeDeux = (line.replaceAll("\"","").split(",", 2));
		return Integer.parseInt(listeDeux[0]);
	}
	
	
	public void write(String top3) {
				//On �crit les r�sultats
				System.out.println(top3);
	}
	
}
