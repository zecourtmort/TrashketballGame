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
		assertEquals(testHeight, 2);
		assertEquals(testDistance, 15);
		testHeight = game.getLevels().get(1).getTrashHeight();
		testDistance = game.getLevels().get(1).getTrashDistance();
		assertEquals(testHeight, 22);
		assertEquals(testDistance, 33);
		testHeight = game.getLevels().get(2).getTrashHeight();
		testDistance = game.getLevels().get(2).getTrashDistance();
		assertEquals(testHeight, 8);
		assertEquals(testDistance, 3);
	}
	@Test
	public void targetTest(){
		assertEquals(Math.calcTarget(game.getLevels().get(0).getTrashHeight(), game.getLevels().get(0).getTrashDistance()), 45);
		assertEquals(Math.calcTarget(game.getLevels().get(1).getTrashHeight(), game.getLevels().get(0).getTrashDistance()), 60);
		assertEquals(Math.calcTarget(game.getLevels().get(2).getTrashHeight(), game.getLevels().get(0).getTrashDistance()), 70);
	}
	@Test
	public void pointsTest(){
		int testDistance = game.getLevels().get(0).getTrashDistance();
		assertEquals(Math.getPoints().size(), testDistance+1);
		assertEquals(Math.getPoints().get(0).getX(),0,0);
		assertEquals(Math.getPoints().get(0).getX(),testDistance+1,0);
	}
	@Test
	public void solutionTest(){
		assertTrue(game.checkSolution(45));
		assertTrue(game.checkSolution(60));
	}

}
