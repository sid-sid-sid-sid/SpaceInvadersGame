package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class YourGameName extends Game {
    private Ship ship;
	static int counter = 0;
	private ArrayList<Bullets> bullets;

  public YourGameName() {
    super("Space Invaders!",800,600);
    ship = new Ship(new Point(400, 550), this);
    bullets = new ArrayList<>();
    this.addKeyListener(ship);
    this.setFocusable(true);
	this.requestFocus();
  }
  
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	ship.move();
        ship.paint(brush);
        for(Bullets b : new ArrayList<>(bullets)) {
        	b.move();
            b.paint(brush);
            if (!b.isActive()) bullets.remove(b);
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