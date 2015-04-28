package Game;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class LoseScreen extends JPanel{
	private Image lose;
	public LoseScreen(){
		MediaTracker tracker = new MediaTracker(this);
		URL url = getClass().getResource("/Files/stern.jpg");
		lose = Toolkit.getDefaultToolkit().getImage(url);
		tracker.addImage(lose, 0);
		//this.game = game;
		try{
			tracker.waitForID(0);
		} catch (InterruptedException e){ return;}
	}
	public void paintComponent(Graphics g){
		int PADDING = 20;
		g.drawImage(lose, PADDING, PADDING, 600, 600, null);
	}
}
