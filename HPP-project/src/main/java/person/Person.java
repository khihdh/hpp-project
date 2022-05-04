package person;

import java.sql.Timestamp;

public class Person {

	private final int person_id;
	//on ne met pas le prénom car jugé inutile
    //private final String person;
    private final Timestamp diagnosed_ts;
    private final int contaminated_by;
    private final short country_id;
    
    public Person(short country,String[] initial) {
    	person_id = Integer.parseInt(initial[0]);
        diagnosed_ts = new Timestamp((long)(Double.parseDouble(initial[4])*1000.0));
        contaminated_by = initial[5].equals("unknown") ? -1 : Integer.parseInt(initial[5]);
        country_id = country;
    }
    
    /* Getters */
    public int getPerson_id() {
        return person_id;
    }
    
    public Timestamp getDiagnosed_ts() {
        return diagnosed_ts;
    }

    public int getContaminated_by() {
        return contaminated_by;
    }

    public short getCountry_id() {
    	return country_id; 
    }
    
}
