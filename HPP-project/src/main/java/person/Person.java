package person;

import java.sql.Timestamp;
import java.util.Objects;

public class Person {

	private  int person_id;
	
	//on ne met pas le pr�nom car jug� inutile
    //private final String person;
    private  int diagnosed_ts;
    
    // -1 if it is unknown 
    private int contaminated_by;
    
    //france 0 italie 1 espagne 2
    private short country_id;
    private boolean ParticipateToChain;
    //person stay in a chain even if his diagnosed_ts is too hold
    
    public Person(short country,String initial) {
    	
    	String[] split = null;
    	
    	split = initial.replaceAll("\"","").replaceAll(" ", "").split(",");
    	//System.out.println(split[0]);
    	//System.out.println(split[4]);
    	//System.out.println(split[5]);
    	person_id = Integer.parseInt(split[0]);
    	diagnosed_ts = (int)(Double.parseDouble(split[4]));
    	//diagnosed_ts = new Timestamp((long)(Double.parseDouble(split[4])*1000.0));
        contaminated_by = split[5].equals("unknown") ? -1 : Integer.parseInt(split[5]);
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

	@Override
	public int hashCode() {
		return Objects.hash(ParticipateToChain, contaminated_by, country_id, diagnosed_ts, person_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return ParticipateToChain == other.ParticipateToChain && contaminated_by == other.contaminated_by
				&& country_id == other.country_id && diagnosed_ts == other.diagnosed_ts && person_id == other.person_id;
	}
    
    
}
