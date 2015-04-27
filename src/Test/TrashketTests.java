package Test;




import static org.junit.Assert.*;

import java.io.FileNotFoundException;






import org.junit.BeforeClass;
import org.junit.Test;

import Game.Physics;
import Game.TrashketballGame;
import Game.Level;
import Game.Quiz;

public class TrashketTests {
	private static TrashketballGame game;
	
	@BeforeClass
	public static void setups() throws FileNotFoundException{
		game = new TrashketballGame("LevelConfig.txt", "Quiz.txt");
	}
	
	@Test (expected = FileNotFoundException.class)
	public void badConfigTest() throws FileNotFoundException{
		TrashketballGame game = new TrashketballGame("configFileBad.txt","badQuiz.txt");
	}
	
	@Test
	public void configTest() throws FileNotFoundException{
		int testHeight = game.getLevels().get(0).getTrashHeight();
		int testDistance = game.getLevels().get(0).getTrashDistance();
		assertEquals(testHeight, 2);
		assertEquals(testDistance, 15);
		testHeight = game.getLevels().get(2).getTrashHeight();
		testDistance = game.getLevels().get(2).getTrashDistance();
		assertEquals(testHeight, 6);
		assertEquals(testDistance, 9);
		testHeight = game.getLevels().get(3).getTrashHeight();
		testDistance = game.getLevels().get(3).getTrashDistance();
		assertEquals(testHeight, 6);
		assertEquals(testDistance, 3);
	}
	
	@Test
	public void targetTest(){
		assertEquals(Physics.calcTarget(game.getLevels().get(0)), 45);
		assertEquals(Physics.calcTarget(game.getLevels().get(2)), 60, 3);
		assertEquals(Physics.calcTarget(game.getLevels().get(3)), 70, 3);
	}
	
	@Test
	public void pointsTest(){
		int testDistance = game.getLevels().get(0).getTrashDistance();
		Physics.calcPoints(game.getLevels().get(0), 51);
		assertEquals(Physics.getPoints().size(), testDistance+1);
		assertEquals(Physics.getPoints().get(0).getX(),0,0);
		assertEquals(Physics.getPoints().get(Physics.getPoints().size()-1).getX(),testDistance,0);
	}
	
	@Test
	public void solutionTest(){
		game.setCurrentLevel(game.getLevels().get(0));
		assertTrue(game.checkSolution(45));
		game.setCurrentLevel(game.getLevels().get(2));
		assertTrue(game.checkSolution(57));
	}

}
