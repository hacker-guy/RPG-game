/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Justin Bugeja> <jbugeja> <758397>
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class VitalityItem extends Items {

	/** The hp to increase the player's by*/
	private static final int hpset = 80;
	/** The coordinates of the item*/
	private final int X, Y;
	 
	/** The constructor for the item
	 * @param i
	 * @param j */
	public VitalityItem(int i, int j) 
			throws SlickException {
		setSprite(new Image("assets/items/amulet.png"));
		X = i;
		Y = j;
	}

	/** To increase the players hp*/
	@Override
	public void action(Player player) {
		player.setMaxHp(hpset);
		player.setHp(-hpset);
	}

	/** Get the x-coordinate
	 * @return x*/
	@Override
	public int getX() {
		return X;
	}
	/** Get the y-coordinate
	 * @return y*/
	@Override
	public int getY() {
		return Y;
	}

}
