package contamination;

import java.util.ArrayList;

import person.Person;
import projet.Utils;

public class ChainOfContamination {
	
	private String country;
	private int rootId;
	private static int score;
	private ArrayList<Person> ListPerson;
	private int Index;
	
	public ChainOfContamination(String country, int rootId) {
		super();
		this.country = country;
		this.rootId = rootId;
		score = 0;
		ListPerson = new ArrayList<Person>();
		Index = 0;
	}
	public static int getScore() {
		return score;
	}
	/**
	 * 
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
	
	public void updateIndex(Person personToAdd) {
		int length = ListPerson.size();
		int staticIndex = Index;
		for (int i = staticIndex ; i<length; i++) {
			while((ListPerson.get(i).getDiagnosed_ts()-personToAdd.getDiagnosed_ts()) > 1209600 ) {
				Index = i;
			}
		}
	}
	
	public void addPerson(Person personToAdd) {
		ListPerson.add(personToAdd);
		updateScore(personToAdd);
		updateIndex(personToAdd);
	}
	
	// For the moment we don't delete person but just change his ParticipateToChain boolean
	/*public void deletePerson(int personId) {
		for (int i=0; i<ListPerson.size() ;i++) {
			if (ListPerson.get(i).getPerson_id() == personId) {
				ListPerson.remove(i);
			}
		}
			
	}*/
}
