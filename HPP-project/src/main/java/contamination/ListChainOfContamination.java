package contamination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import person.Person;
import projet.Utils;

/**
 * 
 * @author Diane
 *
 * @description Class containing:
	 * the list of {@link contamination.ChainOfContamination}.
 * Keep a track of all created {@link contamination.ChainOfContamination} even if their score reach 0.
 * At the end the first three {@link contamination.ChainOfContamination} will be printed.
 * 
 */

public class ListChainOfContamination {
	
	private ArrayList<ChainOfContamination> listChainOfContamination;

	/**
	 * Class constructor:
	 * Create a {@link contamination.ListChainOfContamination}.
	 */
	public ListChainOfContamination() {
		super();
		this.listChainOfContamination = new ArrayList<ChainOfContamination>();
	}
	
	/**
	 * Class constructor:
	 * Create a {@link contamination.ListChainOfContamination}, based on a previous one. 
	 * This constructor is used in the {@link contaminationtest.TestListChainOfContamination}
	 * 
	 * @param listChainOfContamination 
	 */
	public ListChainOfContamination(ArrayList<ChainOfContamination> listChainOfContamination) {
		super();
		this.listChainOfContamination = listChainOfContamination;
	}
	
	/**
	 * Function called the {@link contamination.ListChainOfContamination#sortListChainOfContamination()}, 
	 * then return the 3 firsts {@link contamination.ChainOfContamination} of the {@link contamination.ListChainOfContamination} sort by their {@link contamination.ChainOfContamination#score}
	 * 
	 * @return the 3 firsts {@link contamination.ChainOfContamination} of the {@link contamination.ListChainOfContamination} sort by their {@link contamination.ChainOfContamination#score}
	 */
	public ArrayList<ChainOfContamination> top3() {
		int size = this.listChainOfContamination.size();
		
		this.sortListChainOfContamination();
		ArrayList<ChainOfContamination> top3contamination = new ArrayList<ChainOfContamination>();
		
		for (int i=0;i<size && i<3; i++) {
			top3contamination.add(this.listChainOfContamination.get(size -i -1));
		}
		
		return top3contamination;
	}
	/**
	 * Function sorting {@link contamination.ListChainOfContamination} by their {@link contamination.ChainOfContamination#score}
	 * class the chain of contamination sort by descending order score.
	 * It's using the class {@link contamination.ListChainOfContamination.ChainOfContaminationComparatore} as comparator.
	 */
	public void sortListChainOfContamination() {
		Collections.sort(this.listChainOfContamination,new ChainOfContaminationComparatore());;
	}
	
	/**
	 * 
	 * @author Diane
	 * 
	 * @description Class implementing {@link java.util.Comparator<Object>}. 
	 * This class was created to compare tow {@link contamination.ChainOfContamination#score} based on their {@link contamination.ChainOfContamination#score}
	 *
	 */
	public class ChainOfContaminationComparatore implements Comparator<Object>{
		
		/**
		 * Function comparing tow {@link contamination.ChainOfContamination#score} based on their {@link contamination.ChainOfContamination#score}
		 * 
		 * @param o1 is the {@link contamination.ChainOfContamination#score} of the first {@link contamination.ChainOfContamination}
		 * @param o2 is the {@link contamination.ChainOfContamination#score} of the second {@link contamination.ChainOfContamination}
		 * 
		 * @return 0 if both {@link contamination.ChainOfContamination#score} are equal, 
		 * 1 if the first {@link contamination.ChainOfContamination#score} is superior than the second {@link contamination.ChainOfContamination#score},
		 * 1 if the second {@link contamination.ChainOfContamination#score} is superior than the first {@link contamination.ChainOfContamination#score}
		 */
		public int compare(Object o1,Object o2){  
			ChainOfContamination c1=(ChainOfContamination)o1;  
			ChainOfContamination c2=(ChainOfContamination)o2;  
			  
			if(c1.getScore()==c2.getScore())  
			return 0;  
			else if(c1.getScore()>c2.getScore())  
			return 1;  
			else  
			return -1;  
			} 
	}
	
	/**
	 * Function finding what to do with this {@link person.Person} that we want to add to a {@link contamination.ChainOfContamination}
	 * 4 solutions : 
	 * 	- the {@link person.Person#contaminated_by} is unknown 
	 * 	- add at the end of a {@link contamination.ChainOfContamination} because he was contaminated by the last {@link person.Person} of the {@link contamination.ListChainOfContamination}
	 * 	- creation of another {@link contamination.ChainOfContamination} based on the previous one because he was contaminated by a {@link person.Person} in the middle of the {@link contamination.ListChainOfContamination}
	 * 	- creation of another {@link contamination.ChainOfContamination} because the {@link person.Person#diagnosed_ts()} is to old regards to all {@link person.Person} of {@link contamination.ListChainOfContamination}
	 * 
	 * @param personToAdd {@link person.Person} that we want to add to one of the {@link contamination.ChainOfContamination} 
	 * 
	 */
	
	public void addPerson(Person personToAdd) {
				
		int ListLength = this.listChainOfContamination.size();
		int TargetRootId = personToAdd.getContaminated_by();
		int ChainLength; // of the selected one
		int ChainIndex; // of the selected one
		Person selectedPerson = null; // of the selected chain 
		ChainOfContamination selectedChain = null; // chain already existing
		ChainOfContamination newChain = null; // the chain just created
		
		// Contaminated by unknown 
		// Creation of a new chain
		if (TargetRootId == -1) {
			newChain = new ChainOfContamination(personToAdd.getCountry_id(),personToAdd.getPerson_id(), personToAdd);
			this.listChainOfContamination.add(newChain);
			this.updateListOfPerson(personToAdd);
			return;
		}
		
		else {				
			// Get contaminated by the last person of a chain 
			for (int i = 0; i<ListLength; i++ ) {
				selectedChain = this.listChainOfContamination.get(i);
				ChainLength = selectedChain.getListPersonSize();
				
				if(TargetRootId == selectedChain.getListPerson().get(ChainLength-1).getPerson_id()) {
					selectedChain.addPerson(personToAdd);
					this.updateListOfPerson(personToAdd);
					return;
				}
			}
			
			// Get contaminated by a person of a chain within 14 days 
			// Creation of a new list containing the first one
			for (int i = 0; i<ListLength; i++ ) {
				selectedChain = this.listChainOfContamination.get(i);
				
				ChainLength = selectedChain.getListPersonSize();
				ChainIndex = selectedChain.getIndex();
				
				for (int j = ChainIndex; j<ChainLength; j++ ) {
					selectedPerson = selectedChain.getListPerson().get(i);
					
					if (TargetRootId == selectedPerson.getPerson_id()) {
						
						newChain = new ChainOfContamination(selectedChain);
						this.listChainOfContamination.add(newChain);
						this.updateListOfPerson(personToAdd);
						return;
					}
				}
				
				// Creation of a new independent list 
				newChain = new ChainOfContamination(personToAdd.getCountry_id(),personToAdd.getPerson_id(), personToAdd);
				this.listChainOfContamination.add(newChain);
				this.updateListOfPerson(personToAdd);
				return;
			}
		}		
	}
	
	/**
	 * Update the Index then the Score of each {@link contamination.ChainOfContamination} in the {@link contamination.ListChainOfContamination#listChainOfContamination} 
	 * using the {@link contamination.ChainOfContamination#updateIndex(Person)} and the {@link contamination.ChainOfContamination#updateScore(Person)} functions.
	 * This function is used in {@link contamination.ListChainOfContamination#addPerson(Person)}.
	 * 
	 * @param personToAdd {@link person.Person} that we want to add to one of the {@link contamination.ChainOfContamination}
	 */
	public void updateListOfPerson(Person personToAdd) {
		int ListLength = this.listChainOfContamination.size();

		for (int i=0; i<ListLength; i++) {
			this.listChainOfContamination.get(i).updateIndex(personToAdd);
			this.listChainOfContamination.get(i).updateScore(personToAdd);
		}
	}
	
	/**
	 * Function adding a {@link contamination.ChainOfContamination} to the {@link contamination.ListChainOfContamination#listChainOfContamination}
	 * @param chainToAdd
	 */
	public void add(ChainOfContamination chainToAdd) {
		this.listChainOfContamination.add(chainToAdd);
	}
	
	/**
	 * Getter
	 * @return {@link contamination.ListChainOfContamination#listChainOfContamination.size()} 
	 */
	
	public int getSize() {
		
		return this.listChainOfContamination.size();
	}
	
}
