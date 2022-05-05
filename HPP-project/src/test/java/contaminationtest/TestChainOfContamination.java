package contaminationtest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import contamination.ChainOfContamination;
import person.Person;

public class TestChainOfContamination {
	
	String initial = "1, "+"Daniel"+", "+"ROBINSON"+", 1995-08-21 00:00:00, 86400, unknown, "+"course �  pieds avec la grand-mère au marché"+"";
	
	ArrayList<Person> p;
	Person testPerson = new Person ((short)0,initial);
	
	ChainOfContamination chainTest;
	
	@Test
	public void testAddPerson() {
		chainTest = new ChainOfContamination(0, testPerson.getPerson_id(), testPerson);
		for(int i =2; i<5; i++) {
			String s = Integer.toString(i)+", "+"Daniel"+", "+"ROBINSON"+", 1995-08-21 00:00:00,"+Integer.toString(i*86400)+", 1, "+"course �  pieds avec la grand-mère au marché"+"";
			// date = i days 
			Person testp = new Person ((short)0,s);
			p.add(testp);	
			chainTest.addPerson(p.get(i));
		}
		assertEquals(chainTest.getIndex(),3); // There are 4 people or we begin at 0 so index = 3
		assertEquals(chainTest.getScore(),40);
	}
}
