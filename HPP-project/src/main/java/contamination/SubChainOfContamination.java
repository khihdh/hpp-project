package contamination;

import person.Person;
/**
 * 
 * @author madidina
 * the first person of the subchain will be the person in commun with the parent chain
 */

public class SubChainOfContamination extends ChainOfContamination{

	private ChainOfContamination parentChain;
	
	public SubChainOfContamination(int country, int rootId, Person p, ChainOfContamination c) {
		super(country, rootId, p);
		parentChain = c;
	}
	
	public ChainOfContamination getParentChain() {
		return parentChain;
	}

	public void setParentChain(ChainOfContamination parentChain) {
		this.parentChain = parentChain;
	}
}
