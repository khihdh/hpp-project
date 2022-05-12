package personTest;


import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.Test;

import person.Person;
import projet.Utils;

/**
 * 
 * this class tests the creation of Person objects from a String and check if the values on the line are parsed correctly
 *
 */
public class TestsPerson {
	
	@Test
    public void test () {
        String exemple1 = "\"4, "+"Daniel"+", "+"ROBINSON"+", 1995-08-21 00:00:00, 1582161158.5235808, unknown, "+"course �  pieds avec la grand-mère au marché"+"";
        System.out.println(exemple1);
        Person person = new Person((short)0, exemple1);

        assertEquals(4, person.getPerson_id());
        assertEquals((int)(1582161158.5235808*1000.0), person.getDiagnosed_ts());
        assertEquals(-1, person.getContaminated_by());
        assertEquals(0, person.getCountry_id());

        String exemple2 = "\"11, "+"Gary"+", "+"ADAMS"+", 2008-06-04 00:00:00, 1577483620.7382095, 7, "+"escalade avec mon fils au marché"+"";
        Person person2 = new Person((short)1,exemple2);

        assertEquals(11, person2.getPerson_id());
        assertEquals((int)(1577483620.7382095*1000.0), person2.getDiagnosed_ts());
        assertEquals(7, person2.getContaminated_by());
        assertEquals(1, person2.getCountry_id());
    }
}
