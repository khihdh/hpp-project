package projet;

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
				Pair<Integer, Integer> idFR = new Pair<>(0,0) ;
				Pair<Integer, Integer> idIT = new Pair<>(0,0) ;
				Pair<Integer, Integer> idSP = new Pair<>(0,0) ;
				String currentPath = System.getProperty("user.dir");
				String pathFR = currentPath + "\\src\\main\\resources\\France.csv";
				String pathIT = currentPath + "\\src\\main\\resources\\Italy.csv";
				String pathSP = currentPath + "\\src\\main\\resources\\Spain.csv";
				
				Person personne = null;
				Integer fin = 0;
				Pair pair = new Pair<Person,Integer>(personne, fin);
				
				//Start timer
				long startTime = System.nanoTime();
				
				//boucle � faire apr�s
				while (fin.equals(0)) {
					pair =  Utils.getNewEntry(idToRead,idFR,idIT,idSP, pathFR, pathIT, pathSP);
					idToRead++;
					personne = (Person) pair.getKey();
	                fin = (Integer) pair.getValue();
					System.out.println(personne);
					
					inQueue.addPerson(personne); // first person have an unkown contaminatore 
					
					listTop3 = inQueue.top3();

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
				System.out.println(System.nanoTime()-startTime);

	}

}
