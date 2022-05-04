package contamination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListChainOfContamination {

	public ListChainOfContamination() {
		super();
		listChainOfContamination = new ArrayList<ChainOfContamination>();
	}

	private ArrayList<ChainOfContamination> listChainOfContamination;
	
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
	
}
