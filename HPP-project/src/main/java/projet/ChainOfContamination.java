package projet;

import java.util.ArrayList;

public class ChainOfContamination {

	public ChainOfContamination(String country, int rootId) {
		super();
		this.country = country;
		this.rootId = rootId;
		score = 0;
		ListPerson = new ArrayList<Integer>();
	}
	public static int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	private String country;
	private int rootId;
	private static int score;
	private ArrayList<Integer> ListPerson;
	
	public void updatScore() {
		// fait appel à updateListPerson à la fin
	}
	
	public void updateListPerson(int personId) {
		for (int i=0; i<ListPerson.size() ;i++) {
			if (ListPerson.get(i)==personId) {
				ListPerson.remove(i);
			}
		}
			
	}
}
