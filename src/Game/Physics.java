package Game;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.lang.Math;

public class Physics {
	private int height;
	private int length;
	private static double GRAVITY = 9.81;
	private static double VELOCITY = 13;
	private static ArrayList<Point> points;
	private int angle;
	public static int calcTarget(double height, double distance){
		double v2 = VELOCITY*VELOCITY;
		double sqrtValue = (v2*v2)-(GRAVITY*((GRAVITY*distance*distance)+(2*height*v2)));
		double arctanValue = (v2-Math.sqrt(sqrtValue))/(GRAVITY*distance);
		//arctanValue = Math.toDegrees(arctanValue);
		double angle = Math.atan(arctanValue);
		System.out.println(angle);
		angle = Math.toDegrees(angle);
		return (int)angle;
	}
	public static void calcPoints(int distance, int angle){
		points = new ArrayList<Point>();
		for ( int i = 0; i < distance+1; i++){
		double y = (distance*Math.tan(angle))-((GRAVITY*distance*distance)/(2*((VELOCITY*Math.cos(angle))*(VELOCITY*Math.cos(angle)))));
		Point temp = new Point (i,(int) y);
		points.add(temp);
		}
	}
	public void drawAngle(Graphics g, int angle){
		
	}
	public static ArrayList<Point> getPoints() {
		return points;
	}
	
}
