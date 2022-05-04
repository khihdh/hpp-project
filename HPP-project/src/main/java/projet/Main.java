package projet;

import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import contamination.ListChainOfContamination;
import person.Person;

public class Main {

	public static void main() {
		String path = "";
		//Instantiate every components
				ListChainOfContamination inQueue = new ListChainOfContamination();
				int idToRead =0;
				Pair<Integer, Integer> idFR = new Pair<>(0,0) ;
				Pair<Integer, Integer> idIT = new Pair<>(0,0) ;
				Pair<Integer, Integer> idSP = new Pair<>(0,0) ;
				String franceDataPath ="";
				String italyDataPath ="";
				String spainDataPath ="";
				Person personne = null;
				
				//Start timer
				long startTime = System.nanoTime();
				
				//boucle à faire après
				Person personne =  Utils.getNewEntry(idToRead,idFR,idIT,idSP,franceDataPath,italyDataPath,spainDataPath);
				idToRead++;
				
				

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
