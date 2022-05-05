package projet;

import java.io.File;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import contamination.ListChainOfContamination;
import person.Person;

public class Main {

	public static void main() {
		//Instantiate every components
				ListChainOfContamination inQueue = new ListChainOfContamination();
				int idToRead =0;
				Pair<Integer, Integer> idFR = new Pair<>(0,0) ;
				Pair<Integer, Integer> idIT = new Pair<>(0,0) ;
				Pair<Integer, Integer> idSP = new Pair<>(0,0) ;
				String currentPath = System.getProperty("user.dir");
				//String pathFR = currentPath + "\\src\\main\\resources\\France.csv";
				//String pathIT = currentPath + "\\src\\main\\resources\\Italy.csv";
				//String pathSP = currentPath + "\\src\\main\\resources\\Spain.csv";
				String pathFR = currentPath + "\\src\\main\\resources\\France5000.csv";
				String pathIT = currentPath + "\\src\\main\\resources\\Italy5000.csv";
				String pathSP = currentPath + "\\src\\main\\resources\\Spain5000.csv";
				File france=new File(pathFR);
				File italy=new File(pathIT);
				File spain=new File(pathSP);
				
				Person personne = null;
				Integer fin = 0;
				Pair pair = new Pair<Person,Integer>(personne, fin);
				
				//Start timer
				long startTime = System.nanoTime();
				
				System.out.println("Starting reading ...");
				
				//boucle à faire après
				while (fin.equals(0)) {
				pair =  Utils.getNewEntry(idToRead,idFR,idIT,idSP, france, italy, spain);
				idToRead++;
				personne = (Person) pair.getKey();
                fin = (Integer) pair.getValue();
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
