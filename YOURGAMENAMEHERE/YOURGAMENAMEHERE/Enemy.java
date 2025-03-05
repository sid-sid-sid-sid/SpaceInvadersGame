package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Polygon {
	private Color color = Color.GREEN;
	private static Random rand = new Random();
	private boolean alive = true;
	private static double speed = 0.5;
	
	public Enemy() {
		super(new Point[] {new Point(0, 0), new Point(30, 0), new Point(30, 30),
				new Point(0, 30)}, new Point(rand.nextDouble(800 - 20), 0), 0);
	}
	
	public void paint(Graphics brush) {
		if (!this.alive) {
			return;
		}
		brush.setColor(color);
		Point[] points = this.getPoints();
		int[] xPoints = new int[points.length];
		int[] yPoints = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			xPoints[i] = (int) points[i].getX();
			yPoints[i] = (int) points[i].getY();
		}
		brush.fillPolygon(xPoints, yPoints, points.length);
	}
	
	public void moveOneAtATime() {
		if(!this.alive) {
			return;
		}
		position.y += speed;
		
		if(SpaceInvadersGame.counter >= 300) {
			if(speed < 5) {
				speed = speed + 0.1;
			}
			SpaceInvadersGame.counter = 0;
		}
	}
	
	public boolean checkCollision(Ship ship) {
		if(!this.alive) {
			return false;
		}
		for(Point p : this.getPoints()) {
			if(ship.contains(p)) {
				this.alive = false;
				Ship.setHealth(Ship.getHealth()-1);
				return true;
			}
		}
		return false;
	}
	
	public boolean isOutOfBounds() {
		if(!this.alive) {
			return false;
		}
		double bottom = position.getY() + 30;
		if(bottom >= 600) {
			alive = false;
			Ship.setHealth(Ship.getHealth()-1);
			return true;
		}
		return false;
	}
	
	public boolean isAlive() {
		return this.alive;
	}
}
