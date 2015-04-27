package Game;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;


public class TrashketballGame extends JFrame{
	private static ArrayList<Level> levels = new ArrayList<Level>();
	private static int currentSolution;
	private static ArrayList<Quiz> quizzes= new ArrayList<Quiz>();
	private static Level currentLevel;
	private static int enteredAngle;
	private String configFileName;
	private String quizFile;
	private static int lifeCounter = 3;
	private static JTextField life;
	public TrashketballGame(String configFileName, String quizFile) throws FileNotFoundException{
		this.configFileName = configFileName;
		this.quizFile = quizFile;
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
		FileReader file1 = new FileReader(quizFile);
		Scanner in1 = new Scanner (file1);
		while(in1.hasNextLine()){
			String question = in1.nextLine();
			String A = in1.nextLine();
			String B = in1.nextLine();
			String C = in1.nextLine();
			String D = in1.nextLine();
			String answer = in1.nextLine();
			Quiz ques = new Quiz(question, A, B, C, D, answer);
			quizzes.add(ques);
		}
	}
	public static boolean checkSolution(int angle){
		setCurrentSolution(Physics.calcTarget(currentLevel));
		return (currentSolution == angle);
	}
	public ArrayList<Level> getLevels(){
		return levels;
	}
	public static void setCurrentSolution(int angle){
		currentSolution = angle;
	}
	public static void setCurrentLevel(Level level){
		currentLevel = level;
	}
	public static void main(String[] args) throws FileNotFoundException{
		TrashketballGame game = new TrashketballGame("LevelConfig.txt", "Quiz.txt");
		int i = 0;
		BackgroundPanel background = new BackgroundPanel();
		game.add(background);
		game.setSize(700, 700);
		game.setVisible(true);
		JPanel control = new JPanel();
		JLabel lifeCount = new JLabel("Lives:");
		life = new JTextField(Integer.toString(lifeCounter));
		JLabel sliderLabel = new JLabel("Angle: ");
		JSlider angle = new JSlider(JSlider.HORIZONTAL, 0, 90, 0);
		control.add(lifeCount);
		control.add(life);
		control.add(sliderLabel);
		control.add(angle);
	}
	private ArrayList<Quiz> getQuizzes() {
		// TODO Auto-generated method stub
		return quizzes;
	}
	public Level getCurrentLevel() {
		return currentLevel;
	}
	public void addLife(){
		lifeCounter++;
	}
	public void subLife(){
		lifeCounter--;
	}
}
