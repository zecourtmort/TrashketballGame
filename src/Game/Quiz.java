package Game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Quiz extends JPanel{
	private String answer;
	private String question;
	private String a;
	private String b;
	private String c;
	private String d;
	private JTextField ques;
	private JTextField aa;
	private JTextField bb;
	private JTextField cc;
	private JTextField dd;
	public Quiz(String question, String a, String b, String c, String d,
			String answer2) {
		this.answer = answer2;
		this.question = question;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	public boolean checkSolution(String solution){
		return (answer.equals(solution));
	}
	public void drawQuiz(TrashketballGame game){
		JFrame quizPanel = new JFrame();
		quizPanel.setSize(200,200);
		quizPanel.setVisible(true);
		ques = new JTextField(question);
		ques.setEditable(false);
		quizPanel.add(ques, BorderLayout.NORTH);
		JPanel answerPanel = new JPanel();
		ButtonGroup group = new ButtonGroup();
		JRadioButton A = new JRadioButton(a);
		A.setActionCommand("A");
		JRadioButton B = new JRadioButton(b);
		B.setActionCommand("B");
		JRadioButton C = new JRadioButton(c);
		C.setActionCommand("C");
		JRadioButton D = new JRadioButton(d);
		D.setActionCommand("D");
		group.add(A);
		group.add(B);
		group.add(C);
		group.add(D);
		answerPanel.add(A);
		answerPanel.add(B);
		answerPanel.add(C);
		answerPanel.add(D);
		quizPanel.add(answerPanel, BorderLayout.CENTER);
		JButton ok = new JButton ("OK");
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					boolean check = checkSolution(group.getSelection().getActionCommand());
					System.out.println(group.getSelection().toString());
					if (check){
						game.addLife();
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, "You are correct!");
						quizPanel.setVisible(false);
					}
					else{
						game.subLife();
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, "You are incorrect!");
						quizPanel.setVisible(false);
					}
			}
		
		});
		quizPanel.add(ok, BorderLayout.SOUTH);
	}
	
}
