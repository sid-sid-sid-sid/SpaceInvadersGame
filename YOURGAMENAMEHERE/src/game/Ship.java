package game;

import java.awt.*;
import java.awt.event.*;

public class Ship extends Polygon implements GameObject, KeyListener {
    private boolean left, right, up, down, shooting;
    public boolean rotateLeft, rotateRight;
    private static final int SPEED = 5;
    private static int health = 10;
    private static int score = 0;
    
    private YourGameName g;
    private WeaponSystem weaponSystem; // Inner class instance

    public Ship(Point position, YourGameName s) {
        super(new Point[]{
                new Point(-30, 15), new Point(30, 15), new Point(0, -15)
        }, position, 0);
        this.g = s;
        Ship.health = 10;
        this.weaponSystem = new WeaponSystem();
    }

    public void move() {
        if (left) position.x -= SPEED;
        if (right) position.x += SPEED;
        if (up) position.y -= SPEED;
        if (down) position.y += SPEED;
        if (shooting) {
            weaponSystem.shoot(); // Delegate shooting to inner class
        }
    }

    public void paint(Graphics brush) {
        brush.setColor(Color.RED);
        Point[] points = getPoints();
        int[] x = {(int) points[0].x, (int) points[1].x, (int) points[2].x};
        int[] y = {(int) points[0].y, (int) points[1].y, (int) points[2].y};
        brush.fillPolygon(x, y, 3);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> up = true;
            case KeyEvent.VK_S -> down = true;
            case KeyEvent.VK_A -> left = true;
            case KeyEvent.VK_D -> right = true;
            case KeyEvent.VK_LEFT -> rotation = -25;
            case KeyEvent.VK_RIGHT -> rotation = 25;
            case KeyEvent.VK_UP -> up = true;
            case KeyEvent.VK_DOWN -> down = true;
            case KeyEvent.VK_SPACE -> shooting = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> left = false;
            case KeyEvent.VK_D -> right = false;
            case KeyEvent.VK_W -> up = false;
            case KeyEvent.VK_S -> down = false;
            case KeyEvent.VK_LEFT -> rotation = 0;
            case KeyEvent.VK_RIGHT -> rotation = 0;
            case KeyEvent.VK_UP -> up = false;
            case KeyEvent.VK_DOWN -> down = false;
            case KeyEvent.VK_SPACE -> shooting = false;
        }
    }

    public static int getHealth() {
        return Math.max(health, 0);
    }

    public static void setHealth(int health) {
        if (Ship.health > 0) {
            Ship.health = health;
        }
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Ship.score = score;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    private class WeaponSystem {
        private int shotClock = 0;
        private final int delay = 10; 

        public void shoot() {
            if (shotClock == 0) {
                Point[] shipPoints = getPoints();
                Point tip = shipPoints[2]; 
                g.addBullet(new Bullets(new Point(tip.x, tip.y), rotation)); 
                shotClock = delay;
            }
            if (shotClock > 0) shotClock--;
        }
    }
}
