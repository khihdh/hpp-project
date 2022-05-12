package contaminationtest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import contamination.ChainOfContamination;
import person.Person;
/**
 * 
 * @author Diane
 *
 * @description Class:
 * Creation of an initial string containing all informations as in a document 
 * Creation of a {@link contamination.Person} with the previous string.
 * We initialize a {@link contamination.ChainOfContamination}
 */
public class TestChainOfContamination {
	
	String initial = "1, "+"Daniel"+", "+"ROBINSON"+", 1995-08-21 00:00:00, 86400, unknown, "+"course �  pieds avec la grand-mère au marché"+"";
	
	Person testPerson = new Person ((short)0,initial);
	
	ChainOfContamination chainTest;
	
	/**
	 * Test of the {@link contamination.ChainOfContamination#addPerson(Person)}, {@link contamination.ChainOfContamination#updateIndex(Person)} and {@link contamination.ChainOfContamination#updateScore(Person)} functions.
	 * It create a new {@link contamination.ChainOfContamination} and add it some information. 
	 * Then it asserts that the number of {@link contamination.Person} in the {@link contamination.ChainOfContamination.listPerson} is added by 1, that the {@link contamination.ChainOfContamination.index} and the {@link contamination.ChainOfContamination.score}is update
	 *
	 */
	@Test
	public void testAddPerson() {
		chainTest = new ChainOfContamination(0, testPerson.getPerson_id(), testPerson);
		//System.out.println("befor adding, index = "+chainTest.getIndex());
		for(int i =0; i<3; i++) {
			String date = Integer.toString(((i+2)*86400));
			//System.out.println((i+1)+" adding, date = " + date);
			String s = Integer.toString(i+2)+", "+"Daniel"+", "+"ROBINSON"+", 1995-08-21 00:00:00,"+date+", 1, "+"course �  pieds avec la grand-mère au marché"+"";
			// date = i+2 days 
			Person testp = new Person ((short)0,s);
			//System.out.println((i+1)+" person date = " + testp.getDiagnosed_ts());
			chainTest.addPerson(testp);
			chainTest.updateIndex(testp);
			chainTest.updateScore(testp);
			//System.out.println((i+1)+" adding, index = "+ chainTest.getIndex());
			//System.out.println((i+1)+" adding, score = "+ chainTest.getScore());

		}
		assertEquals(0,chainTest.getIndex()); // There are 4 people but we begin at 0 so index = 3
		assertEquals(40,chainTest.getScore());
	}
}
