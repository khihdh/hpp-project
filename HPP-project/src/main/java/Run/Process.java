package Run;

import person.Person;
import contamination.ChainOfContamination;
import contamination.ListChainOfContamination;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;



public class Process implements Runnable {
	// 
	static ArrayList<ChainOfContamination> listTop3 = new ArrayList();
	//the list of person that we need to treat
	BlockingQueue<Person> inQueue_;
	//the list of top 3 that we will send to the last runnable class
	BlockingQueue<ArrayList<ChainOfContamination>> outQueue_;
	ListChainOfContamination inQueue2 = new ListChainOfContamination();
	Person onePerson;
	Integer max_;
	
	
	//the constructor
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
					//we take the person in the list one by one
				onePerson = inQueue_.take();
				i++;
			inQueue2.addPerson(onePerson);
			//we create the top 3 which correspond to this person
			listTop3 = inQueue2.top3();
			//we add the top 3 to the output
			outQueue_.add(listTop3);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			finally {
				System.out.println("finish processing...");
			}
		}
}
