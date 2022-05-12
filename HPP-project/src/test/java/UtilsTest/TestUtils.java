package UtilsTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import person.Person;

import static org.junit.Assert.assertEquals;

import projet.Utils;
import projet.mainMultithreading;
import projet.Main;
import projet.Pair;
import projet.Triplet;

/**
 * 
 * This test class tests the utils functions such as ReturnId() or calculateScore() but more importantly the file parsing methods
 *
 */
class TestUtils {

	/**
	 * this method tests the return score returned for different values of the current date ans the contamination date
	 */
	@Test
	public void testcalculateScore() {
		assertEquals(10, Utils.calculateScore(1584540000,1584712800));
		assertEquals(10, Utils.calculateScore(1587052800,1587312000));
		assertEquals(4, Utils.calculateScore(1584558000,1585324800));
		assertEquals(0, Utils.calculateScore(1584540000,1587312000));
	}
	
	/**
	 * this test verify the first file parsing method. It creates an ArrayLsit containing all
	 * the persons in the 3 files and then compare them to exemples to check if the file is well parsed
	 * and in chronological order
	 */
	@Test
	public void testGetNewEntry() {
		
		String currentPath = System.getProperty("user.dir");
		String pathFR = currentPath + "\\src\\main\\resources\\France.csv";
		String pathIT = currentPath + "\\src\\main\\resources\\Italy.csv";
		String pathSP = currentPath + "\\src\\main\\resources\\Spain.csv";
		
		File france=new File(pathFR);
		File italy=new File(pathIT);
		File spain=new File(pathSP);
		
		Pair<Integer, Integer> idFR = new Pair<Integer, Integer>(0, 0);
		Pair<Integer, Integer> idIT = new Pair<Integer, Integer>(0, 0);
		Pair<Integer, Integer> idSP = new Pair<Integer, Integer>(0, 0);
		
		String exemple1 = "4, \"Daniel\", \"ROBINSON\", 1995-08-21 00:00:00, 1582161158.5235808, unknown, \"course à pieds avec la grand-mère au marché\"";
		String exemple2 = "15, \"Daniel\", \"HERNANDEZ\", 1962-09-25 00:00:00, 1588098882.9528382, unknown, \"course à pieds avec une copine au super maché\"";
		String exemple3 = "19, \"Joseph\", \"JONES\", 2013-04-09 00:00:00, 1589238000.0, 13, \"vélo avec des copines à la campagne\"";
		List<Person> pers = new ArrayList<Person>();
		
		Pair<Person, Integer> test;
		
		for(int i =0; i<20; i++) {
			test = Utils.getNewEntry(i,idFR,idIT,idSP,france,italy,spain);
			pers.add(test.getKey());
		}
		
		assertEquals(new Person((short)0,exemple1).getPerson_id(), pers.get(4).getPerson_id());
		assertEquals(new Person((short)0,exemple3).getPerson_id(), pers.get(19).getPerson_id());
		assertEquals(new Person((short)1,exemple2).getPerson_id(), pers.get(15).getPerson_id());
	}
	
	/**
	 * this test verify the second file parsing method. It creates an ArrayLsit containing all
	 * the persons in the 3 files and then compare them to exemples to check if the file is well parsed
	 * and in chronological order
	 */
	@Test
	public void testGetNewEntry2() {
		
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
			
		String exemple1 = "4, \"Daniel\", \"ROBINSON\", 1995-08-21 00:00:00, 1582161158.5235808, unknown, \"course à pieds avec la grand-mère au marché\"";
		String exemple2 = "15, \"Daniel\", \"HERNANDEZ\", 1962-09-25 00:00:00, 1588098882.9528382, unknown, \"course à pieds avec une copine au super maché\"";
		String exemple3 = "19, \"Joseph\", \"JONES\", 2013-04-09 00:00:00, 1589238000.0, 13, \"vélo avec des copines à la campagne\"";
		List<Person> pers = new ArrayList<Person>();
		
		Person idFR=null;
		Person idIT=null;
		Person idSP=null;
		
		Triplet<Person,Integer,Triplet<Person,Person,Person>> res;
		
		
		for(int i =0; i<20; i++) {
			res = Utils.getNewEntry2(i,idFR,idIT,idSP,brFR,brIT,brSP);
			
			idFR = res.getVal3().getVal1();
			idIT = res.getVal3().getVal2();
			idSP = res.getVal3().getVal3();
			
			pers.add(res.getVal1());
		}
		
		assertEquals(new Person((short)0,exemple1).getPerson_id(), pers.get(4).getPerson_id());
		assertEquals(new Person((short)0,exemple3).getPerson_id(), pers.get(19).getPerson_id());
		assertEquals(new Person((short)1,exemple2).getPerson_id(), pers.get(15).getPerson_id());
	}
	
	/**
	 * this test check if the raw data of a case returns an int which is the id of the case
	 */
	@Test
	public void testReturnId() {
		String exemple1 = "4, "+"Daniel"+", "+"ROBINSON"+", 1995-08-21 00:00:00, 1582161158.5235808, unknown, "+"course �  pieds avec la grand-mère au marché"+"";
        //System.out.println(exemple1);
        //System.out.println(Utils.returnId(exemple1));
        assertEquals(4, Utils.returnId(exemple1));
	}
	
	/*@Test
    public void testMain() {
        Main.main();
    }*/
	
	@Test
    public void testMain2() {
		long startTime = System.nanoTime();
        mainMultithreading.main2();
        System.out.println("main 2 : ");
        System.out.println( System.nanoTime()-startTime);
    }

	
}
