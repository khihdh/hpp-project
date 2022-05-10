package contaminationtest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import contamination.ChainOfContamination;
import contamination.ListChainOfContamination;
import person.Person;

public class TestListChainOfContamination {
	String initial = "1, "+"Daniel"+", "+"ROBINSON"+", 1995-08-21 00:00:00, 86400, unknown, "+"course �  pieds avec la grand-mère au marché"+"";
	
	ArrayList<Person> p;
	Person testPerson = new Person ((short)0,initial);
	
	ListChainOfContamination listChainOfContamination = new ListChainOfContamination();

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
		System.out.println("liste : "+sortedList);
		//System.out.println("taille = "+sortedList.getSize());
		assertEquals(3,sortedList.getSize());
				
	}
	
	ListChainOfContamination SortedListChainOfContamination = new ListChainOfContamination();
	ListChainOfContamination UnsortedListChainOfContamination = new ListChainOfContamination();
	
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
		
		System.out.println(UnsortedListChainOfContamination.top3());
		System.out.println(SortedListChainOfContamination.top3());

		assertEquals(SortedListChainOfContamination.top3(),UnsortedListChainOfContamination.top3());
				
	}
}
