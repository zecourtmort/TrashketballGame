package Test;




import static org.junit.Assert.*;

import java.io.FileNotFoundException;





import org.junit.BeforeClass;
import org.junit.Test;

import Game.TrashketballGame;
import Game.Level;
import Game.Math;
import Game.Quiz;

public class TrashketTests {
	private static TrashketballGame game;
	@BeforeClass
	public void setups(){
		game = new TrashketballGame("configFile.txt");
	}
	@Test (expected = FileNotFoundException.class)
	public void badConfigTest() throws FileNotFoundException{
		TrashketballGame game = new TrashketballGame("configFileBad.txt");
	}
	@Test
	public void configTest() throws FileNotFoundException{
		int testHeight = game.getLevels().get(0).getTrashHeight();
		int testDistance = game.getLevels().get(0).getTrashDistance();
		assertEquals(testHeight, 0);
		assertEquals(testDistance, 15);
	}
	@Test
	public void targetTest(){
		assertEquals(Math.calcTarget(game.getLevels().get(0).getTrashHeight(), game.getLevels().get(0).getTrashDistance()), 45);
	}
	
	@Test
	public void solutionTest(){
		assertTrue(game.checkSolution(45));
	}

}
