import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import projet.Utils;

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
		String pathFR = "C:\\Users\\Utilisateur\\Desktop\\FISE2\\S8\\HPP\\projet\\hpp-project-group-4\\France.csv";
		String pathIT = "C:\\Users\\Utilisateur\\Desktop\\FISE2\\S8\\HPP\\projet\\hpp-project-group-4\\Italy.csv";
		String pathSP = "C:\\Users\\Utilisateur\\Desktop\\FISE2\\S8\\HPP\\projet\\hpp-project-group-4\\Spain.csv";
		
		int idFR = 0;
		int idIT = 0;
		int idSP = 0;
		
		int idToRead = 0;
		
		String test = Utils.getNewEntry(idToRead,idFR,idIT,idSP,pathFR,pathIT,pathSP);
	}

}
