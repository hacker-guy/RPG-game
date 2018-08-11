/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Justin Bugeja> <jbugeja> <758397>
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** The strength item */ 
public class StrengthItem extends Items {

	/** The damage to set the player*/
	private static final int damageset = 30;
	/** The coordinates of the item*/
	private final int X,Y;
	
	/** The constructor for the strength item
	 * @param i
	 * @param j*/
	public StrengthItem(int i, int j) 
			throws SlickException {
		setSprite(new Image("assets/items/sword.png"));
		X = i;
		Y = j;
	}

	/** The action to set the player's max damage
	 * @param player*/
	@Override
	public void action(Player player) {
		player.setDamage(damageset);
	}

	/** Gets the x-coordinate of the item
	 * @return x*/
	@Override
	public int getX() {
		return X;
	}
	/** Gets the y-coordinate of the item
	 * @return y*/
	@Override
	public int getY() {
		return Y;
	}

}
