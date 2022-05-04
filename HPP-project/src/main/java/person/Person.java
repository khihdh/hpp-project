package person;

import java.sql.Timestamp;

public class Person {

	private final int person_id;
	//on ne met pas le pr�nom car jug� inutile
    //private final String person;
    private final int diagnosed_ts;
    private final int contaminated_by;
    private final short country_id;
    private final boolean ParticipateToChain;
    //person stay in a chain even if his diagnosed_ts is too hold
    
    

	public Person(short country,String[] initial) {
    	person_id = Integer.parseInt(initial[0]);
    	diagnosed_ts = (int)(Double.parseDouble(initial[4])*1000.0);
        //diagnosed_ts = new Timestamp((long)(Double.parseDouble(initial[4])*1000.0));
        contaminated_by = initial[5].equals("unknown") ? -1 : Integer.parseInt(initial[5]);
        country_id = country;
        ParticipateToChain = true;
    }
    
    /* Getters */
    public int getPerson_id() {
        return person_id;
    }
    
    public int getDiagnosed_ts() {
        return diagnosed_ts;
    }

    public int getContaminated_by() {
        return contaminated_by;
    }

    public short getCountry_id() {
    	return country_id; 
    }
    
    public boolean isParticipateToChain() {
		return ParticipateToChain;
	}
}
