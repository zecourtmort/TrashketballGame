package Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
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
	private JLabel levelCount;
	private int currentLevelIndex;
	private BackgroundPanel background;
	private int pointIndex = 0;
	private Timer timer;
	private static final int ANGLE_TOLERANCE = 3;
	
	public TrashketballGame(String configFileName, String quizFile) throws FileNotFoundException{
		this.configFileName = configFileName;
		this.quizFile = quizFile;
		loadConfigFiles();
		currentLevelIndex = 0;
		currentLevel = levels.get(currentLevelIndex);
		currentSolution = Physics.calcTarget(currentLevel);
		//drawCan(getGraphics(), this);
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
		return (currentSolution - 3 <= angle) && (currentSolution + 3 >= angle);
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
	
	private boolean animationHelper(Point p) {
		background.setPoint(p);

		
		return false;	
	}
	
	private void startTimer() {
		Physics.calcPoints(currentLevel, enteredAngle);
		List<Point> points = Physics.getPoints();

		TrashketballGame game = this;
		timer = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				repaint();
				

				setEnabled(false);
				if (pointIndex < points.size()) {
					animationHelper(points.get(pointIndex));
					//System.out.println(points.toString());
					
				}
				
				pointIndex++;
				
				if (pointIndex == points.size()) {
					//System.out.println(pointIndex);
					timer.stop();
					
					setEnabled(true);
					
					//TO-DO use the points
					boolean success = checkSolution(enteredAngle);
					System.out.println(currentSolution);
					if (success && lifeCounter > 0) {
						
						//System.out.println("Here");
						
						if (currentLevelIndex != 0 && !(currentLevelIndex % 2 == 0)) {
							if(currentLevelIndex!=9){
							Quiz q = quizzes.get(currentLevelIndex/2);
							q.drawQuiz(game);
							game.setEnabled(true);
							}
						}
						
						life.setText(Integer.toString(lifeCounter));	
						if (currentLevelIndex == 9){
							JFrame frame = new JFrame();
							JOptionPane.showMessageDialog(frame, "Congratulations! You have completed the game! \n"
									+ "Class is over and you can go home and play more video games!");
							game.setVisible(false);
							System.exit(0);
						}
						nextLevel();
						background.setPoint(new Point(0, 0));
						pointIndex = 0;
					}
					else if (!success && lifeCounter > 0) {
						subLife();
						background.setPoint(new Point(0, 0));
						pointIndex = 0;
					}
					else{
						background.setVisible(false);
						game.add(new LoseScreen());
						game.setEnabled(true);
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, "You should pay more attention in class!");
						game.setVisible(false);
						System.exit(0);
					}
				}
			}
			
		});
		
		timer.start();
	}
	
	public boolean throwBall(int angle) {
		startTimer();
		List<Point> points = Physics.getPoints();

		
		return false;

	}
	
	private void nextLevel() {
		currentLevelIndex++;
		setCurrentLevel(levels.get(currentLevelIndex));
		levelCount.setText(Integer.toString(currentLevelIndex+1));
	}
	
	public int getLifeCounter() {
		return lifeCounter;
	}
	
	public BackgroundPanel getBackgroundPanel() {
		return background;
	}
	
	public void addComponents() {
		background = new BackgroundPanel(this);
		
		setLayout(new BorderLayout());
		add(background, BorderLayout.CENTER);
		
		JPanel control = new JPanel();
		JLabel level = new JLabel("Level: ");
		levelCount = new JLabel(Integer.toString(currentLevelIndex+1));
		JLabel lifeCount = new JLabel("Lives:");
		life = new JLabel(Integer.toString(getLifeCounter()));
		life.setEnabled(false);
		JLabel sliderLabel = new JLabel("Angle: ");
		
		JSlider angle = new JSlider(JSlider.HORIZONTAL, 0, 90, 0);
		JLabel angleField = new JLabel();
		JButton throwButton = new JButton("Throw!");
		
		
		angleField.setEnabled(false);
		angleField.setText(Integer.toString(angle.getValue()));
		angle.setMajorTickSpacing(10);
		angle.setMinorTickSpacing(1);
		angle.setPaintTicks(true);
		angle.setPaintLabels(true);
		
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
		
		control.add(level);
		control.add(levelCount);
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
		game.getBackgroundPanel().drawCan(game.getBackgroundPanel().getGraphics(), game);
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "You are a student in a school for gifted children. You happen to be able to move objects with your mind. \n "
											+ "Unfortunately you can only move objects by thinking of an angle at which to throw them. \n "
											+ "Select the angle above using the slider, but be careful! You only have 4 tries before your teacher catches \n"
											+ " you (you know this because you can also see the future).");
		game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
