import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Justin Bugeja> <jbugeja> <758397>
 */

public class AggressiveMonsters extends Units {


	/** The Monster's constant stats*/
	private final Integer HEALTHMAX, DAMAGE, COOLDOWN;
	/** The Monster's variable stats*/
	private Integer timer = 0;
	/** Variables for movement algorithm*/
	private double distX, distY;
	/** The name of the monster*/
	private final String NAME;
	/** The SPEED the monster moves at =*/
	private final double SPEED = 0.25;
	/** Random number initializer*/
	private Random rn;

	
	/** The Aggressive Monster initializer
	 * @param y2 
	 * @param x2 */
	public AggressiveMonsters(double x, double y, String type) 
			throws SlickException {
		rn = new Random();
		NAME = type;
		/** Creates a different type for each monster*/
		if (type.equals("Zombie")) {
			this.setHp(60);
			HEALTHMAX = 60;
			DAMAGE = 10;
			COOLDOWN = 800;
			this.setSprite(new Image("assets/units/zombie.png"));
		} else if (type.equals("Bandit")) {
			this.setHp(40);
			HEALTHMAX = 40;
			DAMAGE = 8;
			COOLDOWN = 200;
			this.setSprite(new Image("assets/units/bandit.png"));
		} else if (type.equals("Skeleton")) {
			this.setHp(100);
			HEALTHMAX = 100;
			DAMAGE = 16;
			COOLDOWN = 500;
			this.setSprite(new Image("assets/units/skeleton.png"));
		} else {
			this.setHp(40);
			HEALTHMAX = 140;
			DAMAGE = 30;
			COOLDOWN = 400;
			this.setSprite(new Image("assets/units/necromancer.png"));
		}
		this.setX(x);
		this.setY(y);
	}

	/** Draws the monster with it's name and health
	 * @param g */
	@Override
	public void draw(Graphics g) {
		this.drawCentered();
		int yOffset = 40, xOffset = 35, hpLength = 75, hpHeight = 15;
		g.setColor(Color.black);
        g.fillRect((int)this.getX()-xOffset, (int)this.getY()-yOffset, hpLength, hpHeight);
        g.setColor(Color.red);
        g.fillRect((int)this.getX()-xOffset, (int)this.getY()-yOffset, 
        		(float) (1.0*this.getHp() / HEALTHMAX * hpLength), hpHeight);
        g.setColor(Color.white);
        g.drawString(NAME, (int)this.getX()-xOffset, (int)this.getY()-yOffset);
	}

	/** Updates the monster
	 * @param player */
	@Override
	public void update(Player player) {
		if (timer < COOLDOWN) {
			timer += RPG.delta;
		}
		distX = player.getX() - this.getX();
		distY = player.getY() - this.getY();
		if (this.within50(player)) {
			player.Attack(this);
		} else if (this.within150(player)) {
			action();
		} 
	}


	/** Run's from the player when the monster is within range*/
	public void action() {
		double distT = Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
		double dx =  distX/distT * SPEED * RPG.delta;
		double dy =  distY/distT * SPEED * RPG.delta;
		this.setX(dx);
		this.setY(dy);
	}
	
	/** Attack's the player and decreases its health
	 * @return health*/
	public void Attack(Player player) {
		if (timer >= COOLDOWN) {
			player.setHp(rn.nextInt(DAMAGE)+1);
			timer = 0;
		}
	}

	/** Gets the max damage the monster can do
	 * @return damage*/
	public int getDAMAGE() {
		return this.DAMAGE;
	}
}
