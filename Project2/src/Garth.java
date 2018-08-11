/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Justin Bugeja> <jbugeja> <758397>
 */

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Garth extends Units {

	/** Garth's static coordinates and int constants*/
	private final Integer HEALTHMAX=1, AMULETS = 1, SWORDS = 2, TOMES = 3;
	/** The health of the unit*/
	private Integer health;
	/** Garth's constant strings*/
	private final String NAME, TEXT1 = "Find the Amulet of Vitality, across the river to the west.",
			TEXT2 = "Find the Sword of Strength - cross the river and back, on the east side.",
			TEXT3 = "Find the Tome of Agility, in the Land of Shadows.",
			TEXT4 = "You have found all the treasure I know of.";
	/** Which text to display*/
	private Integer action = -1;
	
	/** Constructor for Garth*/
	public Garth(double x, double y) 
			throws SlickException {
		this.setSprite(new Image("assets/units/garth.png"));
		this.setX(x);
		this.setY(y);
		health = 1;
		NAME = "Garth";
	}
	
	/** Draws Garth 
	 * @param Graphics g*/
	@Override
	public void draw(Graphics g) {
		this.drawCentered();
		int yOffset = 40, xOffset = 35, textOffset = 70,  hpLength = 75, hpHeight = 15;
		g.setColor(Color.black);
        g.fillRect(this.getX()-xOffset, this.getY()-yOffset, hpLength, hpHeight);
        g.setColor(Color.red);
        g.fillRect(this.getX()-xOffset, this.getY()-yOffset, health / HEALTHMAX * hpLength, hpHeight);
        g.setColor(Color.white);
        g.drawString(NAME, this.getX()-xOffset, this.getY()-yOffset);
        //Decides which text to display
        if (action == AMULETS) {
        	int actionX = (this.getX() - 8*TEXT1.length()/2);
			g.drawString(TEXT1, actionX, this.getY()-textOffset);
		} else if (action == SWORDS) {
			int actionX = (this.getX() - 8*TEXT2.length()/2);
			g.drawString(TEXT2, actionX, this.getY()-textOffset);
		} else if (action == TOMES) {
			int actionX = (this.getX() - 8*TEXT3.length()/2);
			g.drawString(TEXT3, actionX, this.getY()-textOffset);
		} else if (action == 0) {
			int actionX = (this.getX() - 8*TEXT4.length()/2);
			g.drawString(TEXT4, actionX, this.getY()-textOffset);    
		}
	}

	/** Updates Garth when the player is nearby
	 * @param player */
	@Override
	public void update(Player player) 
			throws SlickException {
		if (this.within50(player) && player.isTalking()) {
			if (!player.containsInstance("VitalityItem")) {
				action = AMULETS;
			} else if (!player.containsInstance("StrengthItem")) {
				action = SWORDS;
			} else if (!player.containsInstance("AgilityItem")) {
				action = TOMES;
			} else {
				action = 0;
			}
		} else {
			action = -1;
		}
	}

	/** Sets the health of Garth
	 * @param damage*/
	@Override
	public void setHp(int damage) {
		// Garth is invincible
	}
	/** Gets the hp of the unit 
	 * @return health*/
	@Override
	public int getHp() {
		return health;
	}


}

