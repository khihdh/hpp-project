package contamination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import person.Person;

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
	
	public void addPerson(Person personToAdd) {
		
		int ListLength = listChainOfContamination.size();
		int TargetRootId = personToAdd.getContaminated_by();

		// Contaminated by unknown 
		// Creation of a new independent list 
		if (TargetRootId == -1) {
			ChainOfContamination newChain = new ChainOfContamination(personToAdd.getCountry_id(),personToAdd.getPerson_id(), personToAdd);
			return;
		}
		
		else {				
			// person get contaminated by the last person of a list 
			for (int i = 0; i<ListLength; i++ ) {
				ChainOfContamination chain = listChainOfContamination.get(i);
				int ChainLength = chain.getListPersonSize();
				if(TargetRootId == chain.getListPerson().get(ChainLength).getPerson_id()) {
						chain.addPerson(personToAdd);
						return;
					}
			}
			
			// person get contaminated by a person of a list within 14 days 
			// creation of a sublist
			for (int i = 0; i<ListLength; i++ ) {
				ChainOfContamination chain = listChainOfContamination.get(i);
				int ChainLength = chain.getListPersonSize();
				int ChainIndex = chain.getIndex();
				for (int j = ChainIndex; j<ChainLength; j++ ) {
					Person choosePers = chain.getListPerson().get(i);
					if (TargetRootId == choosePers.getPerson_id()) {
						SubChainOfContamination subchain = 
								new SubChainOfContamination(choosePers.getCountry_id(),choosePers.getPerson_id(),choosePers,chain);
						chain.addChildChain(subchain,j);
						return;
					}
				}
				// Creation of a new independent list 
				for (int j = 0; j<ChainIndex; j++ ) {
					ChainOfContamination newChain = new ChainOfContamination(personToAdd.getCountry_id(),personToAdd.getPerson_id(), personToAdd);
					return;
				}
			}
		}		
	}
	
}
