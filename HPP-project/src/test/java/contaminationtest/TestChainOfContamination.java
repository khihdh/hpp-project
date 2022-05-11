package contaminationtest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import contamination.ChainOfContamination;
import person.Person;
/**
 * 
 * @author madidina
 *
 *
 */
public class TestChainOfContamination {
	
	String initial = "1, "+"Daniel"+", "+"ROBINSON"+", 1995-08-21 00:00:00, 86400, unknown, "+"course �  pieds avec la grand-mère au marché"+"";
	
	ArrayList<Person> p;
	Person testPerson = new Person ((short)0,initial);
	
	ChainOfContamination chainTest;
	
	@Test
	public void testAddPerson() {
		chainTest = new ChainOfContamination(0, testPerson.getPerson_id(), testPerson);
		System.out.println("befor adding, index = "+chainTest.getIndex());
		for(int i =0; i<3; i++) {
			String date = Integer.toString(((i+2)*86400));
			System.out.println((i+1)+" adding, date = " + date);
			String s = Integer.toString(i+2)+", "+"Daniel"+", "+"ROBINSON"+", 1995-08-21 00:00:00,"+date+", 1, "+"course �  pieds avec la grand-mère au marché"+"";
			// date = i+2 days 
			Person testp = new Person ((short)0,s);
			System.out.println((i+1)+" person date = " + testp.getDiagnosed_ts());
			chainTest.addPerson(testp);
			chainTest.updateIndex(testp);
			chainTest.updateScore(testp);
			System.out.println((i+1)+" adding, index = "+ chainTest.getIndex());
			System.out.println((i+1)+" adding, score = "+ chainTest.getScore());

		}
		assertEquals(0,chainTest.getIndex()); // There are 4 people but we begin at 0 so index = 3
		assertEquals(40,chainTest.getScore());
	}
}
