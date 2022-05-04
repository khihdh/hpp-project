
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

import projet.Utils;
import projet.Pair;

class TestUtils {

	@Test
	public void testcalculateScore() {
		assertEquals(10, Utils.calculateScore(1584540000,1584712800));
		assertEquals(10, Utils.calculateScore(1587052800,1587312000));
		assertEquals(4, Utils.calculateScore(1584558000,1585324800));
		assertEquals(0, Utils.calculateScore(1584540000,1587312000));
	}
	
	@Test
	public void testGetNewEntry() {
		
		String currentPath = System.getProperty("user.dir");
		String pathFR = currentPath + "\\src\\main\\resources\\France.csv";
		String pathIT = currentPath + "\\src\\main\\resources\\Italy.csv";
		String pathSP = currentPath + "\\src\\main\\resources\\Spain.csv";
		
		Pair<Integer, Integer> idFR = new Pair<>(0, 0);
		Pair<Integer, Integer> idIT = new Pair<>(0, 0);
		Pair<Integer, Integer> idSP = new Pair<>(0, 0);
		
		int idToRead = 0;
		
		String test = Utils.getNewEntry(idToRead,idFR,idIT,idSP,pathFR,pathIT,pathSP);
	}
	
	@Test
	public void testReturnId() {
		String exemple1 = "4, "+"Daniel"+", "+"ROBINSON"+", 1995-08-21 00:00:00, 1582161158.5235808, unknown, "+"course �  pieds avec la grand-mère au marché"+"";
        //System.out.println(exemple1);
        //System.out.println(Utils.returnId(exemple1));
        assertEquals(4, Utils.returnId(exemple1));
	}

}
