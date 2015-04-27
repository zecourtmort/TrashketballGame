package Game;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;


import java.net.URL;

import javax.swing.JPanel;


public class BackgroundPanel extends JPanel{
private Image background1;
private Image background2;
	public BackgroundPanel(){
		MediaTracker tracker = new MediaTracker(this);
		URL url = getClass().getResource("/Files/Background1.png");
		background1 = Toolkit.getDefaultToolkit().getImage(url);
		tracker.addImage(background1, 0);
		try{
			tracker.waitForID(0);
		} catch (InterruptedException e){ return;}
	}
	public void paintComponent(Graphics g){
		int PADDING = 20;
		g.drawImage(background1, PADDING, PADDING, 600, 600, null);
	}
}
