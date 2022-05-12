package contamination;

import java.util.ArrayList;

import person.Person;
import projet.Pair;
import projet.Utils;

/**
 * 
 * @author Diane
 * 
 * @description Class containing : 
	 * the list of persons of a chain of contamination,
	 * the country and the ID of the original person,
	 * the index of the last person who could be contact (less than 14 days),
	 * the score of the chain.
 * 
 * There is as much as we need object of this class.
 * 
 */


public class ChainOfContamination {

	private int country;
	private int rootId;
	private int index;
	private ArrayList<Person> listPerson;

	protected int score;

	/**
	 * Class Constructor:
	 * Create a {@link contamination.ChainOfContamination}, with a default {@link #index} & {@link #score} of 0.
	 * 
	 * @param country
	 * @param rootId
	 * @param p
	 */
	public ChainOfContamination(int country, int rootId, Person p) {
		super();
		this.country = country;
		this.rootId = rootId;
		this.index = 0;
		score = 0;
		
		listPerson = new ArrayList<Person>();
		listPerson.add(p);

	}
	
	/**
	 * Class Constructor:
	 * Create a new {@link contamination.ChainOfContamination} based on a previous one, named parentChain.
	 * 
	 * @param parentChain
	 */
	public ChainOfContamination(ChainOfContamination parentChain) {
		super();
		this.country = parentChain.getCountry();
		this.rootId = parentChain.getRootId();
		this.index = parentChain.getIndex();
		score = parentChain.getScore();
		
		listPerson = parentChain.getListPerson();

	}
	
	/**
	 * Function printing the {@link #score} of the {@link contamination.ChainOfContamination}, 
	 * the {@link person.Person#person_id} and covid contact - {@link person.Person#getcontaminated_by()} - 
	 * of each persons of the {@link #listPerson}
	 */
	public void displayChain() {
		System.out.println("");
		System.out.println("Chain of score : " + score);
		System.out.println("   ||   ");
		System.out.println("Person Id : " +listPerson.get(0).getPerson_id());
		System.out.println("contaminated by : " +listPerson.get(0).getContaminated_by());
		for(int i=1; i<listPerson.size(); i++) {
			
			System.out.println("   ||   ");
			System.out.println("Person Id : " +listPerson.get(i).getPerson_id());
			System.out.println("contaminated by : " +listPerson.get(i).getContaminated_by());
		}
	}

	/**
	 * Function that add a {@link person.Person} to the {@link #listPerson} of this class.   
	 * It' used in {@link contamination.ListChainOfContamination#updateListOfPerson(Person)}
	 * 
	 * @param personToAdd
	 */
	public void addPerson(Person personToAdd) {
		listPerson.add(personToAdd);
	}
	
	/**
	 * Function that update the {@link #index} of this class 
	 * by looking at the {@link person.Person#getDiagnosed_ts()} of the last {@link person.Person} pick up 
	 * and that we want to add to a chain (but not necessarily to this chain)
	 * It' used in {@link contamination.ListChainOfContamination#updateListOfPerson(Person)}
	 * 
	 * @param personToAdd
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
	 * Function updating the {@link #score} of this class 
	 * by looking at the {@link person.Person#diagnosed_ts()} of the last {@link person.Person} pick up 
	 * and that we want to add to a chain (but not necessarily to this chain) 
	 * It' used in {@link contamination.ListChainOfContamination#updateListOfPerson(Person)}
	 * Please {@link #updateIndex(Person)} before
	 * 
	 * @param personToAdd
	 * 
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

	/**
	 * Getters function
	 * @return private member
	 */
	
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
	
	/**
	 * Setter function 
	 * only use for the Unit Test
	 * 
	 * @param newScore
	 */
	public void setScore(int newScore) {
		this.score = newScore;
		return;
	}

}
