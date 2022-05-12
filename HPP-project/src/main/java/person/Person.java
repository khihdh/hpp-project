package person;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * 
 * Class containing :
	 * the {@link person_id} is the id of the person.
	 * the {@link diagnosed_ts} is the date of contamination.
	 * the {@link contaminated_by} is the {@link person_id} of the person who contaminate him/here. It is set to -1 if it is unknown.
	 * the {@link country_id} is the id of his/here country. It's 0 for France, 1 for Italy and 2 for Spane.
	 * the {@link ParticipateToChain} is true if the person participate to a {@link contamination.ChainOfContamination} or false if the person does not participate.
	 * A {@link person.Person} stay in a {@link contamination.ChainOfContamination} even if his/here {@link diagnosed_ts} is too hold 
 *
 * This class permit to stock and have access only to the wanted informations of a person.
 */

public class Person {

	private  int person_id;
    private  int diagnosed_ts;
    private int contaminated_by;
    private short country_id;
    private boolean ParticipateToChain;
    
    /**
     * Class Constructor :
     * Create a {@link person.Person}, with a default {@link ParticipateToChain} set to true.
     * The other objects are initialized with the informations giving in the documents 
     * @param country , {@link country_id} set manually in function of the upload fill
     * @param initial , String containing all informations on the person
     */
    public Person(short country,String initial) {
    	
    	String[] split = null;
    	split = initial.replaceAll("\"","").replaceAll(" ", "").split(",");
    	
    	//System.out.println(split[0]);
    	//System.out.println(split[4]);
    	//System.out.println(split[5]);
    	
    	person_id = Integer.parseInt(split[0]);
    	diagnosed_ts = (int)(Double.parseDouble(split[4]));
        contaminated_by = split[5].equals("unknown") ? -1 : Integer.parseInt(split[5]);
        country_id = country;
        ParticipateToChain = true;
    }
    
    /**
     * Function calculating the {@link diagnosed_ts} in comprehensible date 
     * @return {@link java.sql.Timestamp.Timestamp(long time)} comprehensible by human 
     */
    
    public Timestamp returnDate() {
		long mille = 1000;
		return new Timestamp(diagnosed_ts*mille);
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
