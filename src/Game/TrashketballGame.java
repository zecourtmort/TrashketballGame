package Game;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class TrashketballGame extends JFrame{
	private ArrayList<Level> levels = new ArrayList<Level>();
	private int currentSolution;
	private ArrayList<Quiz> quizzes= new ArrayList<Quiz>();
	private Level currentLevel;
	private int enteredAngle;
	private String configFileName;
	private String quizFile;
	private int lifeCounter = 3;
	private JLabel life;
	private int currentLevelIndex;
	
	public TrashketballGame(String configFileName, String quizFile) throws FileNotFoundException{
		this.configFileName = configFileName;
		this.quizFile = quizFile;
		loadConfigFiles();
		currentLevelIndex = 0;
		currentLevel = levels.get(currentLevelIndex);
		currentSolution = Physics.calcTarget(currentLevel);
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
	public boolean checkSolution(int angle){
		setCurrentSolution(Physics.calcTarget(currentLevel));
		return (currentSolution == angle);
	}
	public ArrayList<Level> getLevels(){
		return levels;
	}
	public void setCurrentSolution(int angle){
		currentSolution = angle;
	}
	public void setCurrentLevel(Level level){
		currentLevel = level;
	}
	
	public boolean throwBall(int angle) {
		Physics.calcPoints(currentLevel, angle);
		
		List<Point> points = Physics.getPoints();
		
		//TO-DO use the points
		boolean success = checkSolution(angle);
		
		if (success) {
			nextLevel();
			
			//TO DO: draw new trashcan
	
			return true;
		}
		else {
			subLife();
			
			if (lifeCounter == 0) {
				//TO-DO implement a punishment for running out of lives
			}
			
			return false;
		}
	}
	
	private void nextLevel() {
		currentLevelIndex++;
		setCurrentLevel(levels.get(currentLevelIndex));
		
	}
	
	public int getLifeCounter() {
		return lifeCounter;
	}
	
	public void addComponents() {
		BackgroundPanel background = new BackgroundPanel();
		setLayout(new BorderLayout());
		add(background, BorderLayout.CENTER);
		
		JPanel control = new JPanel();
		JLabel lifeCount = new JLabel("Lives:");
		life = new JLabel(Integer.toString(getLifeCounter()));
		life.setEnabled(false);
		JLabel sliderLabel = new JLabel("Angle: ");
		
		JSlider angle = new JSlider(JSlider.HORIZONTAL, 0, 90, 0);
		JLabel angleField = new JLabel();
		JButton throwButton = new JButton("Throw!");
		
		
		angleField.setEnabled(false);
		angleField.setText(Integer.toString(angle.getValue()));

		
		angle.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				
		        if (!source.getValueIsAdjusting()) {
		        	int angle = source.getValue();
		        	
		            setEnteredAngle(angle);
		            angleField.setText(Integer.toString(angle));
		            
		        }    
			}
		});

		throwButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				throwBall(enteredAngle);
				
			}
			
		});
		
		
		control.add(lifeCount);
		control.add(life);
		control.add(sliderLabel);
		control.add(angle);
		control.add(angleField);
		control.add(throwButton);
		
		
		add(control, BorderLayout.NORTH);	

	}

	public static void main(String[] args) throws FileNotFoundException{
		TrashketballGame game = new TrashketballGame("LevelConfig.txt", "Quiz.txt");
		game.addComponents();
		game.setSize(700, 700);
		game.setVisible(true);
		
	}
	public void setEnteredAngle(int enteredAngle) {
		this.enteredAngle = enteredAngle;
	}
	private ArrayList<Quiz> getQuizzes() {
		return quizzes;
	}
	public Level getCurrentLevel() {
		return currentLevel;
	}
	public void addLife(){
		lifeCounter++;
		life.setText(Integer.toString(lifeCounter));
	}
	public void subLife(){
		lifeCounter--;
		life.setText(Integer.toString(lifeCounter));
	}
}
