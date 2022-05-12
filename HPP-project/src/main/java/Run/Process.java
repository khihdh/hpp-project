package Run;

import person.Person;
import contamination.ChainOfContamination;
import contamination.ListChainOfContamination;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;



public class Process implements Runnable {
	static ArrayList<ChainOfContamination> listTop3 = new ArrayList();
	
	BlockingQueue<Person> inQueue_;
	BlockingQueue<ArrayList<ChainOfContamination>> outQueue_;
	ListChainOfContamination inQueue2 = new ListChainOfContamination();
	Person onePerson;
	Integer max_;
	
	
	
	public Process(BlockingQueue<Person> inQueue, BlockingQueue<ArrayList<ChainOfContamination>> outQueue,Integer max){
		inQueue_=inQueue;
		outQueue_=outQueue;
		max_=max;
	}
	
	/**
	    * Run the thread  
	    */
		@Override
		public void run() {
			try {
				Integer i =0;
				while (!(i.equals(max_)))
				{
				System.out.println("where?");
				onePerson = inQueue_.take();
				i++;
				System.out.println("where?");
			inQueue2.addPerson(onePerson);
			listTop3 = inQueue2.top3();
			outQueue_.add(listTop3);
			System.out.println("where?");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			finally {
				System.out.println("where?");
			}
		}
}
