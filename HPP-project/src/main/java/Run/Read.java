package Run;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import person.Person;
import projet.Triplet;
import projet.Utils;


public class Read implements Runnable {
	 private final BlockingQueue<Person> queue;
	
	int idToRead =0;
	//we put the path to the files to read
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
	
	
	public Read(BlockingQueue<Person> queue) {
        this.queue = queue;
    }
	

	@Override
	public void run() {
		try {
			frFR = new FileReader(france);
			frIT = new FileReader(italy); // java.io.FileReader
			frSP = new FileReader(spain); // java.io.FileReader
			
			brFR = new BufferedReader(frFR); // java.io.BufferedReader
			brIT = new BufferedReader(frIT); // java.io.BufferedReader
			brSP = new BufferedReader(frSP); // java.io.BufferedReader
		
		Person personne = null;
		Integer fin = 0;
		
		Person idFR=null;
		Person idIT=null;
		Person idSP=null;
		
		Triplet<Person,Integer,Triplet<Person,Person,Person>> res;
		
		
		System.out.println("Starting reading ...");
		
		//boucle  faire aprs
		while (fin.equals(0)) {
			//we take the value that we need to create a person
			res = Utils.getNewEntry2(idToRead,idFR,idIT,idSP,brFR,brIT,brSP);
			
			idFR = res.getVal3().getVal1();
			idIT = res.getVal3().getVal2();
			idSP = res.getVal3().getVal3();
			
			
			idToRead++;
			//we create the person with the previous values
			personne = (Person) res.getVal1();
			//we add the person to the queue so we can process it
			queue.add(personne);
            fin = (Integer) res.getVal2();
	}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	finally {
		System.out.println("finish reading ...");
	}
	}
		
}
