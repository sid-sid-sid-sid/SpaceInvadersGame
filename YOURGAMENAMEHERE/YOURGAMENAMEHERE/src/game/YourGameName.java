package game;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

class SpaceInvadersGame extends Game {
    static int counter = 0;
    private Ship ship;
    private ArrayList<Bullets> bullets;
    private ArrayList<Bullets> bulletsToRemove;
    private ArrayList<Enemy> enemies;
    private ArrayList<Enemy> enemiesToRemove;
    private Random rand = new Random();
    private int enemiesSize;
    private boolean gameOver = false;

    public SpaceInvadersGame() {
        super("Space Invaders Game!", 800, 600);
        ship = new Ship(new Point(400, 550), this);
        bullets = new ArrayList<>();
        bulletsToRemove = new ArrayList<>();
        this.addKeyListener(ship);
        this.setFocusable(true);
        this.requestFocus();
        
        // Initialize enemies list
        enemies = new ArrayList<>();
        enemiesToRemove = new ArrayList<>();
        spawnEnemies(5); // Spawn 5 enemies at the start
        enemiesSize = enemies.size();
    }

    private void spawnEnemies(int count) {
        for (int i = 0; i < count; i++) {
            enemies.add(new Enemy()); // Enemies spawn at top of screen
        }
        enemiesSize = enemies.size();
    }

    public void paint(Graphics brush) {
        // Clear screen
        brush.setColor(Color.black);
        brush.fillRect(0, 0, width, height);
        if(Ship.getHealth() <= 0) {
        	brush.setColor(Color.red);
        	brush.drawString("GAME OVER!\nYour Score: " + Ship.getScore(),
        			330, 290);
        	return;
        }
        ship.move();
        ship.paint(brush);
        for(Bullets b : new ArrayList<>(bullets)) {
        	b.move();
            b.paint(brush);
            if (!b.isActive()) {
            	bulletsToRemove.add(b);
            }
            for(Enemy enemy : enemies) {
            	if(b.checkCollision(enemy)) {
            		enemiesToRemove.add(enemy);
            	}
            }
        }
        
        bullets.removeAll(bulletsToRemove);
        bulletsToRemove.clear();

        // Debug counter, also used to speed up enemies over time
        counter++;
        brush.setColor(Color.white);
        brush.drawString("Counter: " + counter, 10, 45);

        // Move and draw enemies
        for (Enemy enemy : enemies) {
        	if(enemy.checkCollision(ship)) {
        		enemiesToRemove.add(enemy);
        	}
        	if(enemy.isOutOfBounds()){
        		enemiesToRemove.add(enemy);
        	}
            enemy.moveOneAtATime();
            enemy.paint(brush);
        }
        
        
        enemies.removeAll(enemiesToRemove);
        enemiesToRemove.clear();
        
        if(enemies.size() < (enemiesSize/2) || (enemies.size() == 0 && 
        		!gameOver)) {
        	spawnEnemies(rand.nextInt(10)+1);
        }
        brush.setColor(Color.white);
        brush.drawString("Health: " + Ship.getHealth(), 10, 15);  
        brush.drawString("Score: " + Ship.getScore(), 10, 30);
    }

    public static void main(String[] args) {
        SpaceInvadersGame game = new SpaceInvadersGame();
        game.repaint();
    }
    public void addBullet(Bullets b) {
		bullets.add(b);
	}
	public void removeBullets() {
		bullets.remove(1);
	}
}
