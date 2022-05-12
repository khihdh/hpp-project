package projet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import contamination.ChainOfContamination;
import contamination.ListChainOfContamination;
import person.Person;

public class Main {
	
	static ArrayList<ChainOfContamination> listTop3 = new ArrayList();

	public static void main() {
		//Instantiate every components
				ListChainOfContamination inQueue = new ListChainOfContamination();
				
				int idToRead =0;
				String currentPath = System.getProperty("user.dir");
				String pathFR = currentPath + "\\src\\main\\resources\\France.csv";
				String pathIT = currentPath + "\\src\\main\\resources\\Italy.csv";
				String pathSP = currentPath + "\\src\\main\\resources\\Spain.csv";
				File france=new File(pathFR);
				File italy=new File(pathIT);
				File spain=new File(pathSP);
				
				FileReader frFR=null;
				FileReader frIT=null;
				FileReader frSP=null;
				BufferedReader brFR=null;
				BufferedReader brIT=null;
				BufferedReader brSP=null;
				
				try {
					frFR = new FileReader(france);
					frIT = new FileReader(italy); // java.io.FileReader
					frSP = new FileReader(spain); // java.io.FileReader
					
					brFR = new BufferedReader(frFR); // java.io.BufferedReader
					brIT = new BufferedReader(frIT); // java.io.BufferedReader
					brSP = new BufferedReader(frSP); // java.io.BufferedReader
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Person personne = null;
				Integer fin = 0;
				
				Person idFR=null;
				Person idIT=null;
				Person idSP=null;
				
				Triplet<Person,Integer,Triplet<Person,Person,Person>> res;
				
				//Start timer
				long startTime = System.nanoTime();
				List<String[]>  writer = new ArrayList<>();
				
				System.out.println("Starting reading ...");
				
				//boucle à faire après
				while (fin.equals(0)) {
					res = Utils.getNewEntry2(idToRead,idFR,idIT,idSP,brFR,brIT,brSP);
					
					idFR = res.getVal3().getVal1();
					idIT = res.getVal3().getVal2();
					idSP = res.getVal3().getVal3();
					
					
					idToRead++;
					personne = (Person) res.getVal1();
	                fin = (Integer) res.getVal2();
	                
	                //System.out.println("Person_id : " + personne.getPerson_id() + " Person_ant :" + personne.getContaminated_by() + " - ts : " + personne.returnDate() );
	                
	                inQueue.addPerson(personne);
	                
	                
	                listTop3 = inQueue.top3();
	                
						String header ="";
						String header1 ="";
						String header2 ="";
						String record1 ="";
						String score1 ="";
						String record2 ="";
						String score2 ="";

						if (Integer.valueOf(listTop3.get(0).getCountry()).equals(0)) {
							header = "France";
						}
						if (Integer.valueOf(listTop3.get(0).getCountry()).equals(1)) {
							header = "Italy";
						}
						if (Integer.valueOf(listTop3.get(0).getCountry()).equals(2)) {
							header = "Spain";
						}
						String record = Integer.toString(listTop3.get(0).getIndex());
						String score = Integer.toString(listTop3.get(0).getScore());
						
						if (listTop3.size()>1) {
							System.out.println(idToRead);
						if (Integer.valueOf(listTop3.get(1).getCountry()).equals(0)) {
							header1 = "France";
						
						}
						if (Integer.valueOf(listTop3.get(1).getCountry()).equals(1)) {
							header1 = "Italy";
						
						}
						if (Integer.valueOf(listTop3.get(1).getCountry()).equals(2)) {
							header1 = "Spain";
						
						}
						record1 = Integer.toString(listTop3.get(1).getIndex());
						score1 = Integer.toString(listTop3.get(1).getScore());
						}
						
						
						if (listTop3.size()>2) {
						if (Integer.valueOf(listTop3.get(2).getCountry()).equals(0)) {
							header2 = "France";
						
						}
						if (Integer.valueOf(listTop3.get(2).getCountry()).equals(1)) {
							header2= "Italy";
						
						}
						if (Integer.valueOf(listTop3.get(2).getCountry()).equals(2)) {
							header2 = "Spain";
						
						}
						record2 = Integer.toString(listTop3.get(2).getIndex());
						score2 = Integer.toString(listTop3.get(2).getScore());
						}
						String[] subWriter = {header,record,score,header1,record1,score1,header2,record2,score2};
						
						writer.add(subWriter);
				}
				
				try {
					CsvWriterSimple.main(writer);
				} catch (IOException e) {
					e.printStackTrace();
				}

				
					
				System.out.println("reading top 3");
				
				
				/*for(int i =0; i<listTop3.size(); i++) {
					listTop3.get(i).displayChain();
				}*/
				

				//Print execution time
				System.out.println("Reading dataSet and creating persons : " +(float)(System.nanoTime()-startTime)/1000000000 + "seconds");

	}

}
