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
		this.listChainOfContamination = new ArrayList<ChainOfContamination>();
	}
	
	public ListChainOfContamination(ArrayList<ChainOfContamination> listChainOfContamination) {
		super();
		this.listChainOfContamination = listChainOfContamination;
	}
	
	public void add(ChainOfContamination chainToAdd) {
		this.listChainOfContamination.add(chainToAdd);
	}

	/**
	 * 
	 * @return the 3 first chain of contamination of the List sort by score
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
	 * 
	 * class the chain of contamination sort by descending order score 
	 */
	public void sortListChainOfContamination() {
		Collections.sort(this.listChainOfContamination,new ChainOfContaminationComparatore());;
	}
	
	public class ChainOfContaminationComparatore implements Comparator<Object>{
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
	
	public void updateListOfPerson(Person personToAdd) {
		int ListLength = this.listChainOfContamination.size();

		for (int i=0; i<ListLength; i++) {
			this.listChainOfContamination.get(i).updateIndex(personToAdd);
			this.listChainOfContamination.get(i).updateScore(personToAdd);
		}
	}
	
	public int getSize() {
		
		return this.listChainOfContamination.size();
	}
	
}
