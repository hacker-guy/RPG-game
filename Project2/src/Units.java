import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Justin Bugeja> <jbugeja> <758397>
 */

public abstract class Units extends BlockableUnits{

	/** The unit's sprite*/
	private Image SPRITE;
	/** The unit's coordinates*/
	private Double x, y;
	/** The unit's health*/
	private Integer health;
	/** draws the unit*/
	public abstract void draw(Graphics g);
	/** updates the unit*/
	public abstract void update(Player player) throws SlickException;
	

	/** Sets the y-coordinate of the monster
	 * @param y*/
	public void setY(double dy) {
		if (this.y == null) {
			this.y = dy;
		} else if(this.boundsCheck(0, dy) != BLOCKY) {
			this.y += dy;
		}
	}

	/** Sets the x-coordinate of the monster
	 * @param x*/
	public void setX(double dx) {
		if (this.x == null) {
			this.x = dx;
		} else if(this.boundsCheck(dx, 0) != BLOCKX) {
			this.x += dx;
		}
	}
	
	/** Draws the sprite as centered as well as the name and health
	 * @param g the Graphics*/
	public void drawCentered() {
		this.getSprite().draw((int)this.getX() - this.getWidth()/2, 
				(int)this.getY() - this.getHeight()/2);
	}
	
	/** Gets the x-coordinate of the monster
	 * @return x*/
	public int getX() {
		return (int) x.doubleValue();
	}

	/** Gets the y-coordinate of the monster
	 * @return y*/
	public int getY() {
		return (int) y.doubleValue();
	}
	
	/** Gets the hp of the unit 
	 * @return health*/
	public int getHp() {
		return this.health;
	}

	/** Sets the hp of the unit 
	 * @param hp*/
	public void setHp(int hp) {
		if (this.health == null) {
			this.health = hp;
		} else {
			this.health = this.health - hp;
		}
	}

	/** Set the Image of the unit
	 * @param image Units image*/
	public void setSprite(Image image) {
		this.SPRITE = image;
	}
	
	/** Get the Image of the unit
	 * @return image Units image*/
	public Image getSprite() {
		return this.SPRITE;
	}
	
	/** Get the Height of the unit
	 * @return height*/
	public int getHeight() {
		return this.SPRITE.getHeight();
	}

	/** Get the width of the unit
	 * @return width*/
	public int getWidth() {
		return this.SPRITE.getWidth();
	}
	
	/** whether or not the unit is within 50 pixels of the player
	 * @param player
	 * @return boolean whether within range*/
	public boolean within50(Player player) {
		double ydist = Math.pow(Math.abs(player.getX() - this.getX()), 2);
		double xdist = Math.pow(Math.abs(player.getY() - this.getY()), 2);
		if (Math.sqrt(xdist+ydist) < 50) {
			return true;
		}
		return false;
	}

	/** whether or not the unit is within 150 pixels of the player
	 * * @param player
	 * @return boolean whether within range*/
	public boolean within150(Player player) {
		double ydist = Math.pow(Math.abs(player.getX() - this.getX()), 2);
		double xdist = Math.pow(Math.abs(player.getY() - this.getY()), 2);
		if (Math.sqrt(xdist+ydist) < 150) {
			return true;
		}
		return false;
	}
}