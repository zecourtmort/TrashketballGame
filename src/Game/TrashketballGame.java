package Game;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class TrashketballGame {
	private static ArrayList<Level> levels = new ArrayList<Level>();
	private int currentSolution;
	private Level currentLevel;
	private String configFileName;
	private int lifeCounter = 3;
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
	public boolean checkSolution(int angle){
		this.currentSolution = Physics.calcTarget(currentLevel);
		return (currentSolution == angle);
	}
	public ArrayList<Level> getLevels(){
		return levels;
	}
	public void setCurrentSolution(int angle){
		this.currentSolution = angle;
	}
	public void setCurrentLevel(Level level){
		this.currentLevel = level;
	}
	public static void main(String[] args){
		int i = 0;
		while ( i < levels.size()){
			
		}
	}
}
