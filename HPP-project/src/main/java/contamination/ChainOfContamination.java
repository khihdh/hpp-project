package contamination;

import java.util.ArrayList;

import person.Person;
import projet.Pair;
import projet.Utils;

/**
 * 
 * @author madidina
 * 
 * @description Class containing informations and the list of person of a chain of contamination 
 * 
 */


public class ChainOfContamination {

	private int country;
	private int rootId;
	private int Index;
	protected static int score;

	private ArrayList<Person> ListPerson;

	public ChainOfContamination(int country, int rootId, Person p) {
		super();
		this.country = country;
		this.rootId = rootId;
		this.Index = 0;
		score = 0;
		
		ListPerson = new ArrayList<Person>();
		ListPerson.add(p);

	}
	
	// Create a new list with an old one
	public ChainOfContamination(ChainOfContamination parentChain) {
		super();
		this.country = parentChain.getCountry();
		this.rootId = parentChain.getRootId();
		this.Index = parentChain.getIndex();
		score = parentChain.getScore();
		
		ListPerson = parentChain.getListPerson();

	}

	/**
	 * @param personToAdd but not yet add to the list
	 */
	public void updateScore(Person personToAdd) {
		int length = ListPerson.size();
		Person pers;
		for (int i= Index; i<length-1; i++) {
			pers = ListPerson.get(i);
			score = Utils.calculateScore(pers.getDiagnosed_ts(),personToAdd.getDiagnosed_ts());
		}
	}
	
	/**
	 * @param personToAdd but not yet add to the list
	 */
	public void updateIndex(Person personToAdd) {
		int length = ListPerson.size();
		int staticIndex = Index;
		for (int i = staticIndex ; i<length; i++) {
			while((ListPerson.get(i).getDiagnosed_ts()-personToAdd.getDiagnosed_ts()) > 1209600 ) {
				Index = i;
			}
		}
	}
	
	/**
	 * @param personToAdd
	 */
	public void addPerson(Person personToAdd) {
		ListPerson.add(personToAdd);
		updateScore(personToAdd);
		updateIndex(personToAdd);
	}

	// Getters and Setters
	
	public int getCountry() {
		return country;
	}
	
	public int getRootId() {
		return rootId;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getIndex() {
		return Index;
	}

	int getListPersonSize() {
		return ListPerson.size();
	}
	
	ArrayList<Person> getListPerson() {
		return ListPerson;
	}


}
