/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Justin Bugeja> <jbugeja> <758397>
 */

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** The status panel shown on the screen*/
public class StatusPanel {

	/** The image of the panel*/
	private Image sprite;
	/** The values to be shown on the panel*/
	private int health, damage, rate, maxHp;
	/** The items to be shown*/
	private ArrayList <Items> ItemsL;
	private final int BARHEIGHT = 85;
	
	/** Constructor for the status panel
	 * @param player*/
	public StatusPanel(Player player) 
			throws SlickException {
		sprite = new Image("assets/panel.png");
		health = player.getHp();
		maxHp = player.getMaxHp();
		damage = player.getDamage();
		rate = player.getCooldown();
		ItemsL = player.getItems();
	}

	/** Updates the status panel with the player's stats
	 * @param player*/
	public void update(Player player) {
		health = player.getHp();
		maxHp = player.getMaxHp();
		damage = player.getDamage();
		rate = player.getCooldown();
		ItemsL = player.getItems();
		
	}
	
	/** Draws the panel, stats, and items
	 * @param x
	 * @param y
	 * @param Graphics g*/
	public void draw(int x, int y, Graphics g) {
		float yPos = y + 30, xPos = x+10, xPadding = 85, xBreak = 95, 
				barPadding = xPadding - 10;
		g.setColor(Color.yellow);
		sprite.draw(x, y);
		g.drawString( "Health:", xPos, yPos);
		float damageP;
		g.drawString( "Damage:", damageP = xPos + 2*xBreak, yPos);
		float rateP;
		g.drawString( "Rate:", rateP = xPos + 3*xBreak, yPos);
		float itemP;
		g.drawString( "Items:", itemP = xPos + 5*xBreak, yPos);
		
		g.setColor(Color.darkGray);
        g.fillRect(xPos + barPadding, yPos-5, 85, 30);
        g.fillRect(itemP + barPadding, yPos-20, 230, 65);
        g.setColor(Color.red);
        g.fillRect(xPos + barPadding, yPos-5, (float) (1.0*health / maxHp * 85), 30);
		
		g.setColor(Color.white);
		g.drawString("" + health +"/"+ maxHp, xPos + xPadding, yPos);
		g.drawString("" + damage  , damageP + 4*xPadding/5, yPos);
		g.drawString("" + rate, rateP + 2*xPadding/3, yPos);

		float itemXPos = itemP + barPadding;
		for (Items item : ItemsL) {
			item.drawInv((int)itemXPos, (int)yPos-25);
			itemXPos += 2*xPadding/3;
		}
	}
	
	/** Gets the height of the status panel
	 * @return imgH*/
	public int getHeight() {
		return BARHEIGHT;
	}

}
