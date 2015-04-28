package Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Toolkit;


import java.net.URL;

import javax.swing.JPanel;


public class BackgroundPanel extends JPanel{
private Image background1;
private Image background2;
private TrashketballGame game;
private Point p = new Point(0, 0);
	public BackgroundPanel(TrashketballGame game){
		MediaTracker tracker = new MediaTracker(this);
		URL url = getClass().getResource("/Files/Background1.png");
		background1 = Toolkit.getDefaultToolkit().getImage(url);
		tracker.addImage(background1, 0);
		this.game = game;
		try{
			tracker.waitForID(0);
		} catch (InterruptedException e){ return;}
	}
	
	public void setPoint(Point p) {
		this.p = p;
	}
	public void paintComponent(Graphics g){
		int PADDING = 20;
		g.drawImage(background1, PADDING, PADDING, 600, 600, null);
		drawCan(g, game);
		drawPoint(g, p);
	}
	public void drawCan(Graphics g, TrashketballGame game){
		Level current = game.getCurrentLevel();
		g.setColor(Color.LIGHT_GRAY);
		//Rectangle trashCan = new Rectangle(current.getTrashHeight(), current.getTrashDistance(), 100, 100);
		g.fillRect(current.getTrashDistance()*  20 + 150, current.getTrashHeight() * 20 + 350, 100, 100);
		
		g.setColor(Color.BLACK);
		//Rectangle trashCan = new Rectangle(current.getTrashHeight(), current.getTrashDistance(), 100, 100);
		g.drawRect(current.getTrashDistance() * 20 + 150, current.getTrashHeight() * 20 + 350, 100, 100);
		game.repaint();
	}
	
	public void drawPoint(Graphics g, Point p) {
		g.setColor(Color.BLUE);
		g.fillOval((int) (p.getX() * 20 + 150), (int) (p.getY() * 20 + 400), 40, 40);
	}
}
