/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Justin Bugeja> <jbugeja> <758397>
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** Represents the player that explores the world.
 */
public class Player {

	/** The Player's sprite image */
	private Image sprite;
	/** The Player's x,y coordinates */
	private float x, y;
	/** Whether the player is facing left or right */
	private boolean flipped = false;
	/** Dimensions of the player sprite */
	private int playerwidth, playerheight;
	/** The starting point of the Player */
	final static int startX = 756, startY = 684;
	
	/** Creates a new Player object */
	public Player() 
		throws SlickException {
		//Create a new sprite and get its dimensions
		 sprite = new Image("assets/units/player.png");
		 playerheight = sprite.getHeight();
		 playerwidth = sprite.getWidth();
		 x = startX;
		 y = startY;
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
		x += dir_x;
		y += dir_y;
		//To flip the player's sprite
		if (dir_x < 0 && !flipped) {
			sprite = sprite.getFlippedCopy(true, false);
			flipped = true;
		} else if (dir_x > 0 && flipped) {
			sprite = sprite.getFlippedCopy(true, false);
			flipped = false;
		}
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
		sprite.draw(x - playerwidth/2, y - playerheight/2);
	}

	/** Get the tile coordinates in the y-direction that the 
	 * player is moving
	 * @param dir_y the direction the player is moving in y
	 * @return The tile in the y direction that the player is moving*/
	public int getBoundsY(double dir_y) {
		if (dir_y > 0) {
			return (int)((y + playerheight/4)/World.tilesize);
		} else if (dir_y < 0){
			return (int)((y - playerheight/4)/World.tilesize);
		}
		
		return (int)y/World.tilesize;
	}

	/** Get the tile coordinates in the x-direction that the 
	 * player is moving
	 * @param dir_x the direction the player is moving in x
	 * @return The tile in the x direction that the player is moving*/
	public int getBoundsX(double dir_x) {
		if (dir_x > 0) {
			return (int)((x + playerwidth/4)/World.tilesize);
		} else if (dir_x < 0 ){
			return (int)((x - playerwidth/4)/World.tilesize);
		}
		
		return (int)x/World.tilesize;
	}
}
