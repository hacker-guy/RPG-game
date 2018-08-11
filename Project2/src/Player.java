/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Justin Bugeja> <jbugeja> <758397>
 */

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** Represents the player that explores the world.
 */
public class Player extends BlockableUnits{

	/** The player's inventory*/
	private ArrayList <Items> ItemsL;
	/** The Player's sprite image */
	private Image sprite;
	/** The Player's x,y coordinates */
	private float x, y, centerX, centerY;
	/** The stats of the player */
	private int cooldown = 600, timer = cooldown, health = 100, maxHp = 100, damage = 26;
	/** Whether the player is facing left or right */
	private boolean flipped = false;
	/** Random number initializer*/
	private Random rn;
	/** Whether the player is talking or attacking*/
	private boolean talking = false, attack = false;
	/** Dimensions of the player sprite */
	private int playerwidth, playerheight;
	/** The starting point of the Player */
	final static int STARTX = 756, STARTY = 684;
	
	/** Creates a new Player object 
	 * @param y2 
	 * @param x2 */
	public Player(int x2, int y2) 
		throws SlickException {
		//Create a new sprite and get its dimensions
		 ItemsL = new ArrayList<Items>();
		 sprite = new Image("assets/units/player.png");
		 playerheight = sprite.getHeight();
		 playerwidth = sprite.getWidth();
		 x = x2;
		 y = y2;
		 centerX = x-playerwidth/5;
		 centerY = y-playerheight/5;
		 rn = new Random();
	}
	
	/** Get the player's Height 
	 * @return playerheight The height of the player*/
	public int getHeight() {
		return playerheight;
	}
	
	/** Get the player's Width 
	 * @return playerwidth The width of the player*/
	public int getWidth() {
		return playerwidth;
	}
	
	/** Get the player's sprite image 
	 * @return sprite The players sprite*/
	public Image getSprite() {
		return sprite;
	}

	/** Update the player's position 
	 * @param dir_x the movement amount in x
	 * @param dir_y the movement amount in y*/
	public void update(double dir_x, double dir_y) {
		if (health <= 0) {
			health = maxHp;
			x = STARTX;
			y = STARTY;
		}
		x += dir_x;
		y += dir_y;
		centerX = x-playerwidth/5;
		centerY = y-playerheight/5;
		//To flip the player's sprite
		if (dir_x < 0 && !flipped) {
			sprite = sprite.getFlippedCopy(true, false);
			flipped = true;
		} else if (dir_x > 0 && flipped) {
			sprite = sprite.getFlippedCopy(true, false);
			flipped = false;
		}
	}
	
	/** Sends the item to the player's inventory
	 * @param item*/
	public void sendToInv(Items item) {
		ItemsL.add(item);
	}
	
	/** Get the x position of the player
	 * @return x The players x position*/
	public int getX() {
		
		return ((int)(x));
	}

	/** Get the y position of the player
	 * @return y The players y position*/
	public int getY() {
		
		return ((int)(y));
	}

	/** Draw the player sprite*/
	public void draw() {
		sprite.draw(centerX, centerY);
	}
	
	/** Whether the player is attacking */
	public boolean isAttacking() {
		return attack;
	}
	
	/** Sets whether the player should attack or not
	 * @param att
	 * @param delta*/
	public void setAttacking(boolean att, int delta) {
		if (timer < cooldown) {
			timer += delta;
			attack = false;
		} else if (att) {
				attack = att;
				timer = 0;
		} else {
			attack = att;
		}
	}

	/** Set whether the player is attacking*/
	public void setTalking(boolean t) {
		talking = t;
	}
	
	/** Whether the player is talking*/
	public boolean isTalking() {
		return talking;
	}

	/** Get how much damage the player can do*/
	public int getDamage() {
		return damage;
	}

	/** Get the cooldown stat*/
	public int getCooldown() {
		return cooldown;
	}

	/** Make the player attack a specific unit
	 * @param unit*/
	public void Attack(Units unit) {
		if (attack) {
			unit.setHp(rn.nextInt(damage)+1);
		}
		
	}

	/** Set the player's hp
	 * @param damage*/
	public void setHp(int damage) {
		health += damage;
	}

	/** Get the current hp of the player
	 * @return health*/
	public int getHp() {
		return health;
	}

	/** Get the max hp of the player
	 * @return maxHp*/
	public int getMaxHp() {
		return maxHp;
	}

	/** Get the player's inventory 
	 * @return ItemsL*/
	public ArrayList<Items> getItems() {
		return ItemsL;
	}

	/** Whether the player's inventory contains a certain object
	 * @param itemC
	 * @param boolean*/
	public boolean containsInstance(String itemC) {
		if (itemC.equals("LifeItem")) {
			for (Items item : ItemsL) {
				if (item instanceof LifeItem) {
					return true;
				}
			}
		} else if (itemC.equals("VitalityItem")) {
			for (Items item : ItemsL) {
				if (item instanceof VitalityItem) {
					return true;
				}
			}
			
		} else if (itemC.equals("StrengthItem")) {
			for (Items item : ItemsL) {
				if (item instanceof StrengthItem) {
					return true;
				}
			}
			
		} else if (itemC.equals("AgilityItem")) {
			for (Items item : ItemsL) {
				if (item instanceof AgilityItem) {
					return true;
				}
			}
			
		}
		return false;
	}

	/** Set the player's cooldown
	 * @param cooldownset*/
	public void setCooldown(int cooldownset) {
		cooldown = cooldownset;
		
	}

	/** Set the damage the player can do
	 * @param damageset*/
	public void setDamage(int damageset) {
		damage += damageset;
	}

	/** Set the player's max hp
	 * @param hpset*/
	public void setMaxHp(int hpset) {
		maxHp += hpset;
	}
	 
}
