package projet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
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
	                
	                System.out.println("Person_id : " + personne.getPerson_id() + " Person_ant :" + personne.getContaminated_by() + " - ts : " + personne.returnDate() );
	                
	                inQueue.addPerson(personne);
	                listTop3 = inQueue.top3();
	                
	                for(int i =0; i<listTop3.size(); i++) {
						listTop3.get(i).displayChain();
					}
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
