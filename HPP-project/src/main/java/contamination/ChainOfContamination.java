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
	private int index;
	protected static int score;

	private ArrayList<Person> listPerson;

	public ChainOfContamination(int country, int rootId, Person p) {
		super();
		this.country = country;
		this.rootId = rootId;
		this.index = 0;
		score = 0;
		
		listPerson = new ArrayList<Person>();
		listPerson.add(p);

	}
	
	// Create a new list with an old one
	public ChainOfContamination(ChainOfContamination parentChain) {
		super();
		this.country = parentChain.getCountry();
		this.rootId = parentChain.getRootId();
		this.index = parentChain.getIndex();
		score = parentChain.getScore();
		
		listPerson = parentChain.getListPerson();

	}

	/**
	 * @param personToAdd
	 */
	public void addPerson(Person personToAdd) {
		listPerson.add(personToAdd);
	}
	
	/**
	 * @param personToAdd but not obligatory to this listOfPerson
	 */
	public void updateIndex(Person personToAdd) {
		int length = listPerson.size();
		int staticIndex = index;
		for (int i = staticIndex ; i<length; i++) {
			while((listPerson.get(i).getDiagnosed_ts()-personToAdd.getDiagnosed_ts()) > 1209600 ) {
				index = i;
			}
		}
	}
	
	/**
	 * @param personToAdd but not obligatory to this listOfPerson
	 * 
	 * Please update the index before, in order to gain time
	 */
	public void updateScore(Person personToAdd) {
		
		//int datePersonToAdd;
		//int dateSelectedPers;
		int length = listPerson.size();
		Person selectedPerson;
		int newScore = 0;
		for (int i = this.index; i<length; i++) {
			selectedPerson = listPerson.get(i);
			
			//datePersonToAdd = personToAdd.getDiagnosed_ts();
			//System.out.println("personToAdd score " + datePersonToAdd);
			//dateSelectedPers = selectedPerson.getDiagnosed_ts();
			//System.out.println("person origin of score " + dateSelectedPers);
			
			//int difference = datePersonToAdd - dateSelectedPers;
			//System.out.println("difference of score " +Integer.toString(difference));
			newScore += Utils.calculateScore(selectedPerson.getDiagnosed_ts(),personToAdd.getDiagnosed_ts());
			//System.out.println("update Score " + (personToAdd.getDiagnosed_ts() - pers.getDiagnosed_ts()) );

		}
		this.score = newScore;
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
		return index;
	}

	int getListPersonSize() {
		return listPerson.size();
	}
	
	ArrayList<Person> getListPerson() {
		return listPerson;
	}


}
