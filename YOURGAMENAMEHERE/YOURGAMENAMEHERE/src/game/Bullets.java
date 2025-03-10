package game;

import java.awt.*;

public class Bullets extends Polygon implements GameObject{
    private static final int SPEED = 5;
    private boolean active = true;


	public Bullets(Point inPosition) {
		super(new Point[] { new Point(-2, -5), new Point(2, -5), 
				new Point(2, 5), new Point(-2, 5)}, inPosition, 
				0);
		
	}

	@Override
	public void paint(Graphics brush) {
		// TODO Auto-generated method stub
		brush.setColor(Color.YELLOW);
        Point[] points = getPoints();
        int[] x = {(int) points[0].x, (int) points[1].x, (int) points[2].x, 
        		(int) points[3].x};
        int[] y = {(int) points[0].y, (int) points[1].y, (int) points[2].y, 
        		(int) points[3].y};
        brush.fillPolygon(x, y, 4);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		position.y -= SPEED;
		if(position.y < 0) { active = false; }
	}
	 public boolean isActive() {
		 return active;
	 }

}
