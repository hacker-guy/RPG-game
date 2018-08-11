/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Justin Bugeja> <jbugeja> <758397>
 */

import org.newdawn.slick.Graphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */
public class World
{
	/** Initialising an array of units and items*/
	private ArrayList <Units> UnitsL;
	private ArrayList <Items> ItemsL;
	/** The map of the background */
	public static TiledMap background;
	/** The camera class */
	private Camera camera;
	private Player player;
	/** Constant numbers based on background */
	static int maxWorldWidth, maxWorldHeight, tilesize;
	/** Which direction to block unit movement*/
	private int block;
	/** The speed at which the unit is to move */
	private final static double SPEED = 0.28;
	/** Whether to block x or y */
	private final static int blockX = 1, blockY = 2;
	
	
    /** Create a new World object. 
     * @throws IOException */
    public World()
    throws SlickException, IOException
    {
    	//Initiating the item array
    	ItemsL = new ArrayList<Items>();
    	
    	//Initiating the items
    	LifeItem lifeitem = new LifeItem(1976, 402);
    	StrengthItem strengthitem = new StrengthItem(4791, 1253);
    	VitalityItem vitalityitem = new VitalityItem(965, 3563);
    	AgilityItem agilityitem = new AgilityItem(546, 6707);
    	
    	//Add the items to the array
    	ItemsL.add(lifeitem);
    	ItemsL.add(strengthitem);
    	ItemsL.add(vitalityitem);
    	ItemsL.add(agilityitem);
    	
    	UnitsL = ReadIn.readUnits();
    	player = new Player(756, 684);
    	
    	/* Creating a new map object*/
    	background = new TiledMap("assets/map.tmx","assets/");
    	/* Creating a new camera object*/
    	camera = new Camera(player);
    	/* Set the tilesize to the tile height, assuming tiles are square*/
    	tilesize = background.getTileHeight();
    	
    	/* Set the max height and width of the map*/
    	World.maxWorldWidth = 
    			background.getWidth()*background.getTileWidth();
    	World.maxWorldHeight = 
    			background.getHeight()*background.getTileHeight();
    }

    /** Update the game state for a frame.
     * @param dir_x The unit's movement in the x axis (-1, 0 or 1).
     * @param dir_y The unit's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds)
     * @param a whether to attack
     * @param t whether to talk.
     */
    public void update(double dir_x, double dir_y, int delta, boolean a, boolean t)
    throws SlickException
    {
    	//Set whether player is talking or attacking
		player.setAttacking(a, delta);
		player.setTalking(t);
		
		//Create an iterator to iterate through the unit list
		Iterator<Units> itU = UnitsL.iterator();
		while (itU.hasNext()) {
			Units unit = itU.next();
			unit.update(player);
			if (unit.within50(player)) {
				/*If the unit is an Aggressive Monster and near the player
				 *  attack the player*/
				if(unit instanceof AggressiveMonsters) {
					AggressiveMonsters unitAM = (AggressiveMonsters) unit;
					unitAM.Attack(player);
				}
			}
			/*If the unit is a Passive Monster, then wander the map*/
			if(unit instanceof PassiveMonsters) {
				PassiveMonsters unitPM = (PassiveMonsters) unit;
				unitPM.wander(delta);
			}
			//If the unit is dead, remove from the game
			if (unit.getHp() <= 0) {
				itU.remove();
			}
		}
		//Iterate through the items and check if within player
		for (Items item : ItemsL) {
			if (item.withinRange(player)) {
				//Make the item affect the player
				item.action(player);
				player.sendToInv(item);
				ItemsL.remove(item);
				break;
			}
		}
    	/* If the unit is moving */
    	if ((int)Math.abs(dir_x) > 0  || (int)Math.abs(dir_y) > 0) {
    		/* Check whether to block the player from moving */
    		block = player.boundsCheck(dir_x,  dir_y);
    		/* If player isn't blocked */
    		if (block == 0) {
    			player.update(dir_x*SPEED*delta, dir_y*SPEED*delta);
    			/* If unit is blocked in x direction, move only in y*/
    		} else if (block == blockX) {
    			player.update(0, dir_y*SPEED*delta);
    			/* If unit is blocked in y direction, move only in x*/
    		} else if (block == blockY) {
    			player.update(dir_x*SPEED*delta, 0);
    		}
    	} else {
    		player.update(0, 0);
    	}
		/* Update the camera to follow the unit */
		camera.update(player);
    }

    
    /** Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(Graphics g)
    throws SlickException
    {
    	/* Offset the render by the camera position minus an extra tile*/
    	int OffsetX =  camera.getxPos() - (camera.getxPos() % tilesize);
        int OffsetY =  camera.getyPos() - (camera.getyPos() % tilesize);
    	
        /* Get the index of the tile where the camera begins*/
        int tileIndexX =  (camera.getxPos() / tilesize);
        int tileIndexY =  (camera.getyPos() / tilesize);

        /* Translate the graphics to where the camera position is in the 
         * world */
        g.translate(-camera.getxPos(), -camera.getyPos());
        
        /* Render function */
        background.render(
        	OffsetX, 
        	OffsetY, 
            tileIndexX,
            tileIndexY,
            RPG.screenwidth/tilesize + 2, 
            RPG.screenheight/tilesize + 2
        );    
      
        //Draw function here
		player.draw();
		
		for (Units unit : UnitsL) {
			unit.draw(g);
		}
		for (Items item : ItemsL) {
			item.draw();
		}
		camera.renderStatusP(player, g);
		
    }
	
}



