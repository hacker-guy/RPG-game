/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Justin Bugeja> <jbugeja> <758397>
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class LifeItem extends Items {

	/** The coordinates of the Item */
	private final int X,Y;
	
	/** Constructor for the item
	 * @param i x coordinate
	 * @param j y coordinates*/
	public LifeItem(int i, int j) 
			throws SlickException {
		setSprite(new Image("assets/items/elixir.png"));
		X = i;
		Y = j;
	}

	/** Action on the player
	 * @param player*/
	@Override
	public void action(Player player) {
		//No action on player
	}
	/** Gets the x-coordinate
	 * @return x*/
	@Override
	public int getX() {
		return X;
	}
	/** Gets the y-coordinate
	 * @return y*/
	@Override
	public int getY() {
		return Y;
	}


}
