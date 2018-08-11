/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Justin Bugeja> <jbugeja> <758397>
 */

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Elvira extends Units {


	/** The position and health*/
	private final int HEALTHMAX=1;
	/** Elvira's health*/
	private Integer health;
	/** Elvira's constant strings*/
	private final String NAME, text1 = "You're looking much healthier now.",
			text2 = "Return to me if you ever need healing.";
	/** What action Elvira should say*/
	private int action = 0;
	
	/** Constructor class for Elvira*/
	public Elvira(double x, double y) 
			throws SlickException {
		setSprite(new Image("assets/units/elvira.png"));
		setX(x);
		setY(y);
		health = 1;
		NAME = "Elvira";

	}
	
	/** Draws Elvira
	 * @param Graphics g*/
	@Override
	public void draw(Graphics g) {
		this.drawCentered();
		int yOffset = 40, xOffset = 35, textOffset = 70, hpLength = 75, hpHeight = 15;
		g.setColor(Color.black);
        g.fillRect(this.getX()-xOffset, this.getY()-yOffset, hpLength, hpHeight);
        g.setColor(Color.red);
        g.fillRect(this.getX()-xOffset, this.getY()-yOffset, health / HEALTHMAX * hpLength, hpHeight);
        g.setColor(Color.white);
        g.drawString(NAME, this.getX()-xOffset, this.getY()-yOffset);
        //Decides what string to display
        if (action==1) {
			int actionX = (this.getX() - 8*text1.length()/2);
			g.drawString(text1, actionX, this.getY()-textOffset);
		} else if (action == 2){
			int actionX = (this.getX() - 8*text2.length()/2);
			g.drawString(text2, actionX, this.getY()-textOffset);
		}
	}

	/** Updates Elvira if player is within 50 pixels*/
	@Override
	public void update(Player player) {
		if (this.within50(player) && player.isTalking()) {
			//Sets players health to max if health is low
			if (player.getHp() < player.getMaxHp()) {
				action = 1;
				int lostHp = player.getMaxHp() - player.getHp();
				player.setHp(-lostHp);
			} else {
				action = 2;
			}
		} else {
			action = 0;
		}
	}

	/** Sets the hp of the unit when attacked
	 * @param damage*/
	@Override
	public void setHp(int damage) {
		// Elvira is invincible
	}

	/** Gets the hp of the unit 
	 * @return health*/
	@Override
	public int getHp() {
		return health;
	}



}
