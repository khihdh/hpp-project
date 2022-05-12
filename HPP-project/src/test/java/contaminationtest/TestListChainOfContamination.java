package contaminationtest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import contamination.ChainOfContamination;
import contamination.ListChainOfContamination;
import person.Person;

/**
 * 
 * @author Diane
 *
 * Class 
 * Creation of an initial string containing all informations as in a document 
 * Creation of a {@link contamination.Person} with the previous string.
 * 
 * Creation of a {@link contamination.listChainOfContamination} with the previous {@link contamination.Person} and tow new other.
 * 
 */
public class TestListChainOfContamination {
	
	String initial = "1, "+"Daniel"+", "+"ROBINSON"+", 1995-08-21 00:00:00, 86400, unknown, "+"course �  pieds avec la grand-mère au marché"+"";
	Person testPerson = new Person ((short)0,initial);
	
	ListChainOfContamination listChainOfContamination = new ListChainOfContamination();

	/**
	 * Function testing the {@link contamination.listChainOfContamination#top3()} by verifying that the size of the final list is 3. 
	 */
	@Test
	public void testTop3ListChainOfContamination() {
				
		for (int i =0; i<4; i++) 
		{
			ChainOfContamination chainToAdd;
			chainToAdd = new ChainOfContamination(i,testPerson.getPerson_id(), testPerson);
			chainToAdd.setScore(i);
			listChainOfContamination.add(chainToAdd);
		}
		ListChainOfContamination sortedList = new ListChainOfContamination(listChainOfContamination.top3());
		//System.out.println("liste : "+sortedList);
		//System.out.println("taille = "+sortedList.getSize());
		assertEquals(3,sortedList.getSize());
	}
	
	
	
	
	
	ListChainOfContamination SortedListChainOfContamination = new ListChainOfContamination();
	ListChainOfContamination UnsortedListChainOfContamination = new ListChainOfContamination();
	
	/**
	 * Function testing the {@link contamination.listChainOfContamination#sortListChainOfContamination()} by creating town {@link contamination.listChainOfContamination} of three {@link contamination.ChainOfContamination}.
	 * One is sorted manually and the other one is sorted by the function {@link contamination.listChainOfContamination#sortListChainOfContamination()}. The aim is to verify if the both are equal.
	 */
	@Test
	public void testSortListChainOfContamination() {

		ChainOfContamination chainToAdd1;
		chainToAdd1 = new ChainOfContamination(0,testPerson.getPerson_id(), testPerson);
		chainToAdd1.setScore(1);
		
		ChainOfContamination chainToAdd3;
		chainToAdd3 = new ChainOfContamination(1,testPerson.getPerson_id(), testPerson);
		chainToAdd3.setScore(2);
		
		ChainOfContamination chainToAdd2;
		chainToAdd2 = new ChainOfContamination(1,testPerson.getPerson_id(), testPerson);
		chainToAdd2.setScore(2);
		
		SortedListChainOfContamination.add(chainToAdd1);
		SortedListChainOfContamination.add(chainToAdd2);
		SortedListChainOfContamination.add(chainToAdd3);
		
		UnsortedListChainOfContamination.add(chainToAdd2);
		UnsortedListChainOfContamination.add(chainToAdd3);
		UnsortedListChainOfContamination.add(chainToAdd1);

		UnsortedListChainOfContamination.sortListChainOfContamination();
		
		//System.out.println(UnsortedListChainOfContamination.top3());
		//System.out.println(SortedListChainOfContamination.top3());

		assertEquals(SortedListChainOfContamination.top3(),UnsortedListChainOfContamination.top3());
				
	}
	

	String initialCerise = "1, "+"Cerise"+", "+"DUPOND"+", 1963-01-21 02:00:00, 1584540000, unknown, "+"course �  pieds avec la grand-mère au marché"+"";
	Person Cerise = new Person ((short)0,initialCerise);

	String initialHerve = "2, "+"Herve"+", "+"RENOIR"+", 1971-03-11 02:00:00, 1584712800, unknown, "+"course �  pieds avec la grand-mère au marché"+"";
	Person Herve = new Person ((short)0,initialHerve);
	
	String initialValentina = "3, "+"Valentina"+", "+"Rossi"+", 1963-01-21 07:00:00, 1584558000, 1, "+"course �  pieds avec la grand-mère au marché"+"";
	Person Valentina = new Person ((short)1,initialValentina);
	
	String initialMarco = "4, "+"Marco"+", "+"Guili"+", 1956-01-06 04:00:00, 1585324800, unknown, "+"course �  pieds avec la grand-mère au marché"+"";
	Person Marco = new Person ((short)1,initialMarco);
	
	String initialStella = "5, "+"Stella"+", "+"Capelli"+", 1949-01-21 04:00:00, 1587312000, 4, "+"course �  pieds avec la grand-mère au marché"+"";
	Person Stella = new Person ((short)1,initialStella);
	
	String initialRicardo = "6, "+"Ricardo"+", "+"Rodriguez"+", 1964-10-03 04:00:00, 1587052800, 4, "+"course �  pieds avec la grand-mère au marché"+"";
	Person Ricardo = new Person ((short)1,initialRicardo);

	/*@Test
	public void testAddPersonToListChainOfContamination() {
		listChainOfContamination.addPerson(Cerise);
		listChainOfContamination.addPerson(Herve);
		listChainOfContamination.addPerson(Valentina);
		listChainOfContamination.addPerson(Marco);
		listChainOfContamination.addPerson(Stella);
		listChainOfContamination.addPerson(Ricardo);
		System.out.println(listChainOfContamination.top3());
		//assertEquals(SortedListChainOfContamination.top3(),UnsortedListChainOfContamination.top3());
	}*/
	
}
