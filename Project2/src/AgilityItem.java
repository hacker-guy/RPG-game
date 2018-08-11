/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Justin Bugeja> <jbugeja> <758397>
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AgilityItem extends Items {

	/** The cooldown to set the player*/
	private static final int COOLDOWNSET = 300;
	/** The x and y coordinates of the item*/
	private final int X,Y;
	
	/** The Agility Item constructor
	 * @param i the x-coordinate
	 * @param j the y-coordinate*/
	public AgilityItem(int i, int j) 
			throws SlickException {
		X = i;
		Y = j;
		setSprite(new Image("assets/items/book.png"));
	}

	/** The Agility Item constructor
	 * @param player*/
	@Override
	public void action(Player player) {
		player.setCooldown(COOLDOWNSET);
	}
	
	/** Get the item's x-coordinate
	 * @return X the x-coordinate*/
	@Override
	public int getX() {
		return X;
	} 
	/** Get the item's Y-coordinate
	 * @return Y the y-coordinate*/
	@Override
	public int getY() {
		return Y;
	}

}
