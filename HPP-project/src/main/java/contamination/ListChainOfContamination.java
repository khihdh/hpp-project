package contamination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import person.Person;
import projet.Utils;

/**
 * 
 * @author madidina
 *
 * Keep a track of all created chain of contamination
 * 
 */

public class ListChainOfContamination {
	
	private ArrayList<ChainOfContamination> listChainOfContamination;

	public ListChainOfContamination() {
		super();
		listChainOfContamination = new ArrayList<ChainOfContamination>();
	}

	public ArrayList<ChainOfContamination> top3() {
		
		ArrayList<ChainOfContamination> top3contamination = new ArrayList();
		
		for (int i=0; i<3; i++) {
			top3contamination.add(listChainOfContamination.get(i));
		}
		
		return top3contamination;
	}
	
	@SuppressWarnings("unchecked")
	public void sortListChainOfContamination() {
		Collections.sort(listChainOfContamination,new ChainOfContaminationComparatore());;
	}
	
	public class ChainOfContaminationComparatore implements Comparator{
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
	 * 
	 * @param personToAdd : we have to find what to to with this person
	 * 
	 * 4 solutions : 
	 * 	- the person contaminated by is unknown 
	 * 	- add at the end of a chain 
	 * 	- creation of another chain linked to one already existing 
	 * 	- creation of another chain because the date of contamination is to old
	 * 
	 */
	
	public void addPerson(Person personToAdd) {
				
		int ListLength = listChainOfContamination.size();
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
			listChainOfContamination.add(newChain);
			this.updateListOfPerson(personToAdd);
			return;
		}
		
		else {				
			// Get contaminated by the last person of a chain 
			for (int i = 0; i<ListLength; i++ ) {
				selectedChain = listChainOfContamination.get(i);
				ChainLength = selectedChain.getListPersonSize();
				
				if(TargetRootId == selectedChain.getListPerson().get(ChainLength).getPerson_id()) {
					selectedChain.addPerson(personToAdd);
					this.updateListOfPerson(personToAdd);
					return;
				}
			}
			
			// Get contaminated by a person of a chain within 14 days 
			// Creation of a new list containing the first one
			for (int i = 0; i<ListLength; i++ ) {
				selectedChain = listChainOfContamination.get(i);
				
				ChainLength = selectedChain.getListPersonSize();
				ChainIndex = selectedChain.getIndex();
				
				for (int j = ChainIndex; j<ChainLength; j++ ) {
					selectedPerson = selectedChain.getListPerson().get(i);
					
					if (TargetRootId == selectedPerson.getPerson_id()) {
						
						newChain = new ChainOfContamination(selectedChain);
						listChainOfContamination.add(newChain);
						this.updateListOfPerson(personToAdd);
						return;
					}
				}
				
				// Creation of a new independent list 
				newChain = new ChainOfContamination(personToAdd.getCountry_id(),personToAdd.getPerson_id(), personToAdd);
				listChainOfContamination.add(newChain);
				this.updateListOfPerson(personToAdd);
				return;
			}
		}		
	}
	
	public void updateListOfPerson(Person personToAdd) {
		int ListLength = listChainOfContamination.size();

		for (int i=0; i<ListLength; i++) {
			listChainOfContamination.get(i).updateIndex(personToAdd);
			listChainOfContamination.get(i).updateScore(personToAdd);
		}
	}
	
}
