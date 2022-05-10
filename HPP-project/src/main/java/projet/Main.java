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
				String pathFR = currentPath + "\\src\\main\\resources\\France5000.csv";
				String pathIT = currentPath + "\\src\\main\\resources\\Italy5000.csv";
				String pathSP = currentPath + "\\src\\main\\resources\\Spain5000.csv";
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
	                
	                inQueue.addPerson(personne); // first person must have an unkown contaminatore 
					
	                System.out.println(personne);
					listTop3 = inQueue.top3();
					System.out.println(personne);
					List<String[]>  writer = new ArrayList<>();
					for (int i = 0; i<2;i++) {
						if (listTop3.get(3*i).getCountry()==0) {
							String[] header = {"France"};
							writer.add(header);
						
						}
						if (listTop3.get(3*i).getCountry()==1) {
							String[] header = {"Italy"};
							writer.add(header);
						
						}
						if (listTop3.get(3*i).getCountry()==2) {
							String[] header = {"Spain"};
							writer.add(header);
						
						}
						String[] record1 = {Integer.toString(listTop3.get(1+3*i).getIndex())};
						writer.add(record1);
						String[] record2 = {Integer.toString(listTop3.get(2+3*i).getScore())};
						writer.add(record2);
					}
					try {
						CsvWriterSimple.main(writer);
					} catch (IOException e) {
						e.printStackTrace();
					}
					//System.out.println(personne);
				}
				

				//ExecutorService service = Executors.newFixedThreadPool(5); //5 threads is the limit

//				Scanner scanners = new Scanner(System.in);
//				int i = scanners.nextInt();

				
				//service.execute(reader);
				//service.execute(processing);
				//service.execute(writer);

				//Wait for the threads to end
				//ThreadUtils.shutdownAndAwaitTermination(service);

				//Print execution time
				System.out.println("Reading dataSet and creating persons : " +(float)(System.nanoTime()-startTime)/1000000000 + "seconds");

	}

}
