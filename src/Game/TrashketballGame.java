package Game;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class TrashketballGame {
	private static ArrayList<Level> levels = new ArrayList<Level>();
	private static int currentSolution;
	private static Level currentLevel;
	private static int enteredAngle;
	private String configFileName;
	private static int lifeCounter = 3;
	public TrashketballGame(String configFileName) throws FileNotFoundException{
		this.configFileName = configFileName;
		loadConfigFiles();
	}
	public void loadConfigFiles() throws FileNotFoundException{
		FileReader file = new FileReader(configFileName);
		Scanner in = new Scanner(file);
		while(in.hasNextLine()){
			int height = in.nextInt();
			int distance = in.nextInt();
			Level temp = new Level(height, distance);
			levels.add(temp);
		}
	}
	public static boolean checkSolution(int angle){
		currentSolution = Physics.calcTarget(currentLevel);
		return (currentSolution == angle);
	}
	public ArrayList<Level> getLevels(){
		return levels;
	}
	public void setCurrentSolution(int angle){
		this.currentSolution = angle;
	}
	public static void setCurrentLevel(Level level){
		currentLevel = level;
	}
	public static void main(String[] args){
		int i = 0;
		while ( i < levels.size()){
			setCurrentLevel(levels.get(i));
			if (checkSolution(enteredAngle)){
				i++;
			}
			else{
				lifeCounter--;
			}
		}
	}
}
