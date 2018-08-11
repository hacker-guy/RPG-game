/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Justin Bugeja> <jbugeja> <758397>
 */

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PrinceAldric extends Units {

	/** The constant attributes*/
	private final int healthMax=1;
	/** The health of the player*/
	private Integer health;
	/** The constant Strings and dialogue*/
	private final String NAME, TEXT1 = "The elixir! My father is cured! Thankyou!", 
			TEXT2 = "Please seek out the Elixir of Life to cure the king.";
	/** Which dialogue to display*/
	private int itemB = -1;
	private boolean takenItem = false;
	
	
	/** The constructor for PrinceAldric
	 * @param x
	 * @param y2*/
	public PrinceAldric(double x, double y) 
			throws SlickException {
		this.setSprite(new Image("assets/units/prince.png"));
		this.setX(x);
		this.setY(y);
		health = 1;
		NAME = "Prince";
	}
	
	/** Draws the sprite and dialogue
	 * @param Graphics g*/
	@Override
	public void draw(Graphics g) {
		this.drawCentered();
		int yOffset = 40, xOffset = 35, textOffset = 70, hpLength = 75, hpHeight = 15;
		g.setColor(Color.black);
        g.fillRect(this.getX()-xOffset, this.getY()-yOffset, hpLength, hpHeight);
        g.setColor(Color.red);
        g.fillRect(this.getX()-xOffset, this.getY()-yOffset, health / healthMax * hpLength, hpHeight);
        g.setColor(Color.white);
        g.drawString(NAME, this.getX()-xOffset, this.getY()-yOffset);
        //Choose which text to display
        if (itemB == 1) {
			int actionX = (this.getX() - 8*TEXT1.length()/2);
			g.drawString(TEXT1, actionX, this.getY()-textOffset);
		} else if (itemB == 0) {
			int actionX = (this.getX() - 8*TEXT2.length()/2);
			g.drawString(TEXT2, actionX, this.getY()-textOffset);
		}
	}
	
	/** Updates the unit if near player
	 * @param player*/
	@Override
	public void update(Player player) {
		if (this.within50(player) && player.isTalking()) {
			if (player.containsInstance("LifeItem") || takenItem) {
				itemB = 1;
				//Check if item has already been taken
				if (!takenItem) {
					takenItem = true;
				}
			} else {
				itemB = 0;
			}
		} else {
			itemB = -1;
		}
	}

	/** Sets the hp of the unit 
	 * @param damage*/
	@Override
	public void setHp(int damage) {
		// PrinceAldric is invincible
	}

	/** Gets the hp of the unit 
	 * @return health*/
	@Override
	public int getHp() {
		return health;
	}


}
