package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.util.*;

class YourGameName extends Game {
	 private static final long serialVersionUID = 1L;
	 private ArrayList<Bullets> bullets;
	 private ArrayList<Enemy> enemies;
	 private ArrayList<Enemy> enemiesToRemove;
	 private Random rand = new Random();
	 private int enemiesSize;
	 private boolean gameOver = false;
	 static int counter = 0;
	 private Ship ship;


  public YourGameName() {
    super("Space Invaders!",800,600);
    ship = new Ship(new Point(400, 550), this);
    bullets = new ArrayList<>();
    this.addKeyListener(ship);
    this.setFocusable(true);
	this.requestFocus();
	enemies = new ArrayList<>();
    enemiesToRemove = new ArrayList<>();
    spawnEnemies(5); 
    enemiesSize = enemies.size();
  }
  private void spawnEnemies(int count) {
      for (int i = 0; i < count; i++) {
          enemies.add(new Enemy()); // Enemies spawn at top of screen
      }
      enemiesSize = enemies.size();
  }
  public void paint(Graphics brush) {
    brush.setColor(Color.black);
    brush.fillRect(0,0,width,height);
    if(Ship.getHealth() <= 0) {
    	brush.setColor(Color.red);
    	brush.drawString("GAME OVER!\n Your Score: " + Ship.getScore(),
    			330, 290);
    	return;
    }
    ship.move();
    ship.paint(brush);
    for(Bullets b : new ArrayList<>(bullets)) {
        b.move();
        b.paint(brush);
        if (!b.isActive()) bullets.remove(b);
        for(Enemy enemy : enemies) {
        	if(b.checkCollision(enemy)) {
        		enemiesToRemove.add(enemy);
        	}
        }
        for(Enemy enemy : enemies) {
        	if(b.checkCollision(enemy)) {
        		enemiesToRemove.add(enemy);
        	}
        }
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
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
//    	counter++;
//    	brush.setColor(Color.white);
//    	brush.drawString("Counter is " + counter,10,10);
        
  }
  
	public static void main (String[] args) {
   		YourGameName a = new YourGameName();
		a.repaint();
	}
	public void addBullet(Bullets b) {
		bullets.add(b);
	}
	public void removeBullets() {
		bullets.remove(1);
	}
}