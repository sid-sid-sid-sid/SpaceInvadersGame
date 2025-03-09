package game;

import java.awt.*;

public class Bullets extends Polygon {
    private static final int SPEED = 5;
    private boolean active = true;
    private final GameObject gameObject;
    public Bullets(Point inPosition, double rotation) {
        super(new Point[]{new Point(-2, -5), new Point(2, -5),
                new Point(2, 5), new Point(-2, 5)}, inPosition,
                rotation);

        // Anonymous inner class implementing GameObject
        gameObject = new GameObject() {
            @Override
            public void paint(Graphics brush) {
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
            	if (rotation == -25) position.x -= 5;
                if (rotation == 25) position.x += 5;
                position.y -= SPEED;
                if (position.y < 0) {
                    active = false;
                }
            }
        };
    }

    public boolean checkCollision(Enemy enemy) {
        if (!this.active) {
            return false;
        }
        for (Point p : this.getPoints()) {
            if (enemy.contains(p)) {
                this.active = false;
                Ship.setScore(Ship.getScore() + 1);
                return true;
            }
        }
        return false;
    }

    public void paint(Graphics brush) {
        gameObject.paint(brush);
    }

    public void move() {
        gameObject.move();
    }

    public boolean isActive() {
        return active;
    }
}