package projet;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import Run.*;
import Run.Process;
import contamination.*;
import person.Person;

public class mainMultithreading {

	public static void main2() {
		//we create the queue where the person to treat will be stocked
		BlockingQueue<Person> inQueue = new LinkedBlockingQueue<>();
		//we create the queue the the top 3 will be stocked before being put in the csv
		BlockingQueue<ArrayList<ChainOfContamination>> outQueue =new  LinkedBlockingQueue<ArrayList<ChainOfContamination>>();
		//max corresponds to the number of person in the csv ( need to be changed accordingly)
		Integer max = 5000;

		//we create the object that the thread will need to execute
		Read read = new Read(inQueue);
		Process process = new Process(inQueue, outQueue,max);
		Write write = new Write(outQueue,max);

		//the pool oh threads that we will use
		ExecutorService service = Executors.newFixedThreadPool(5); //5 threads is the limit


		//Start the thread
		service.execute(read);
		service.execute(process);
		service.execute(write);

		//Wait for the threads to end
		shutdownAndAwaitTermination(service);
	}
		
		public static void shutdownAndAwaitTermination(ExecutorService pool) {
	        pool.shutdown(); // Disable new tasks from being submitted
	        try {
	            // Wait a while for existing tasks to terminate
	            if (!pool.awaitTermination(6000, TimeUnit.SECONDS)) {
	                pool.shutdownNow(); // Cancel currently executing tasks
	                // Wait a while for tasks to respond to being cancelled
	                if (!pool.awaitTermination(6000, TimeUnit.SECONDS))
	                    System.err.println("Pool did not terminate");
	            }
	        } catch (InterruptedException ie) {
	            // (Re-)Cancel if current thread also interrupted
	            pool.shutdownNow();
	            // Preserve interrupt status
	            Thread.currentThread().interrupt();
	        }
	    }

}
