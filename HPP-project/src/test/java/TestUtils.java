import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import projet.Utils;
import projet.Pair;

class TestUtils {

	@Test
	void testcalculateScore() {
		assertEquals(10, Utils.calculateScore(1584540000,1584712800));
		assertEquals(10, Utils.calculateScore(1587052800,1587312000));
		assertEquals(4, Utils.calculateScore(1584558000,1585324800));
		assertEquals(0, Utils.calculateScore(1584540000,1587312000));
	}
	
	@Test
	void testGetNewEntry() {
		System.out.println(System.getProperty("user.dir") );
		System.out.println("");
		
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

}
