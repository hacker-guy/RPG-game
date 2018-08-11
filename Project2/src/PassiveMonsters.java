import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Justin Bugeja> <jbugeja> <758397>
 */

public class PassiveMonsters extends Units {

	/** Random number initializer*/
	private Random rn;
	/** The unit's max health*/
	private final int MAXHP=40;
	/** The distance for the run algorithm*/
	private double distX, distY;
	/** The direction to move the monster and timers*/
	private int dirn = 1, timerR = 5000, timerA = 3000;
	/** The name of the monster*/
	private final String NAME;
	/** The speed of the Monster*/
	private final double SPEED = 0.2;
	/** Static constants for directions*/
	private static final int U = 1, D = 2, L = 3, R = 4, UR = 5, DR = 6, DL = 7, UL = 8;
	
	/** The constructor for the monster
	 * @param x
	 * @param y
	 * @param string*/
	public PassiveMonsters(double x, double y, String string) 
			throws SlickException {
		this.setSprite(new Image("assets/units/dreadbat.png"));
		this.setX(x);
		this.setY(y);
		this.setHp(40);
		NAME = string;
		rn = new Random();
	}
	
	/** The draw function for the monster
	 * @param Graphics g */
	@Override
	public void draw(Graphics g) {
		this.drawCentered();
		int yOffset = 40, xOffset = 35, hpLength = 75, hpHeight = 15;
		g.setColor(Color.black);
        g.fillRect((int)getX()-xOffset, (int)getY()-yOffset, hpLength, hpHeight);
        g.setColor(Color.red);
        g.fillRect((int)getX()-xOffset, (int)getY()-yOffset, 
        		(float) (1.0*this.getHp() / MAXHP * hpLength), hpHeight);
        g.setColor(Color.white);
        g.drawString(NAME, (int)getX()-xOffset, (int)getY()-yOffset);
	}

	/** The update function for the monster
	 * @param player */
	@Override
	public void update(Player player) {
		distX = player.getX() - this.getX();
		distY = player.getY() - this.getY();
		if (this.within50(player)) {
			player.Attack(this);
			if (player.isAttacking()) {
				run();
				timerR = 0;
			}
		}	
		if (timerR < 5000) {
			run();
			timerR+=RPG.delta;	
		} 
	}
	/** The automatic wander method for the monster
	 * @param delta */
	public void wander(int delta) {
		timerA += delta;
		if (timerR >= 5000) {
			action(dirn, delta);
			if (timerA > 3000) {
				timerA = 0;
				dirn = rn.nextInt(9);
			}
		}
	}
	
	/** Sets the direction to move
	 * @param dirn
	 * @param delta */
	public void action(int dirn, int delta) {
		if (dirn == U) {
			this.setY(-1.0*SPEED*delta);
		} else if (dirn == D) {
			this.setY(1.0*SPEED*delta);
		} else if (dirn == L) {
			this.setX(-1.0*SPEED*delta);
		} else if (dirn == R) {
			this.setX(1.0*SPEED*delta);
		} else if (dirn == UR) {
			this.setY(-1.0*SPEED*delta);
			this.setX(1.0*SPEED*delta);
		} else if (dirn == DR) {
			this.setY(1.0*SPEED*delta);
			this.setX(1.0*SPEED*delta);
		} else if (dirn == DL) {
			this.setY(1.0*SPEED*delta);
			this.setX(-1.0*SPEED*delta);
		} else if (dirn == UL) {
			this.setY(-SPEED*delta);
			this.setX(-SPEED*delta);
		} 
	}
	
	/** Makes the unit run from the player*/
	public void run() {
		
		double distT = Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
		double dx =  -distX/distT * SPEED * RPG.delta;
		double dy =  -distY/distT * SPEED * RPG.delta;
		this.setX(dx);
		this.setY(dy);
	}

}
