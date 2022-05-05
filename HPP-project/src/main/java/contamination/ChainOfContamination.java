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
	private static int score;
	private ArrayList<Person> ListPerson;
	private int Index;
	/**
	 * SubChainOfContamination is a sub chain from this main chain
	 * Integer is the index of the main chain linked with the sub chain 
	 */
	private ArrayList<Pair<SubChainOfContamination, Integer>> ListChildChain = null;


	public ChainOfContamination(int country, int rootId, Person p) {
		super();
		this.country = country;
		this.rootId = rootId;
		score = 0;
		ListPerson = new ArrayList<Person>();
		ListPerson.add(p);
		Index = 0;
	}
	public static int getScore() {
		return score;
	}
	
	public int getIndex() {
		return Index;
	}
	
	public ArrayList<Pair<SubChainOfContamination, Integer>> getListChildChain() {
		return ListChildChain;
	}
	/**
	 * 
	 * @param subchain to link to the main chain
	 * @param index in the main chain
	 */
	public void addChildChain(SubChainOfContamination subchain, Integer index) {
		Pair<SubChainOfContamination, Integer> childchain = new Pair<>(subchain, index);
		ListChildChain.add(childchain);
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
	
	int getListPersonSize() {
		return ListPerson.size();
	}
	
	ArrayList<Person> getListPerson() {
		return ListPerson;
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
