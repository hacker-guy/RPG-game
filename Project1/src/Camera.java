/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Justin Bugeja> <jbugeja> <758397>
 */

import org.newdawn.slick.SlickException;

/** Represents the camera that controls our viewpoint.
 */
public class Camera
{
    
    /** The width and height of the screen */
    /** Screen width, in pixels. */
    public final int screenwidth = RPG.screenwidth;
    /** Screen height, in pixels. */
    public final int screenheight = RPG.screenheight;

    
    /** The camera's position in the world, in x and y coordinates. */
    private int xPos;
    private int yPos;

    /** Get the x position of the camera
     * @return xPos The x position*/
    public int getxPos() {
        return xPos;
    }
    
    /** Get the y position of the camera
     * @return yPos The y position*/
    public int getyPos() {
    	return yPos;
    }
    
    /** Create a new Camera object. 
     * @param player A player object*/
    public Camera(Player player) 
    throws SlickException
    {   
    	xPos = player.getX() - screenwidth/2;
    	yPos = player.getY() - screenheight/2;
    	
    }

    /** Update the game camera to recentre it's viewpoint around the player 
      * @param player A player object*/
    public void update(Player player)
    throws SlickException
    {
    	/* Set the camera to center around the player */
    	
    	xPos = player.getX() - screenwidth/2;
    	yPos = player.getY() - screenheight/2;
    	
    	/* If the player is off the map set the players position to inside 
    	  the map*/
    	if (xPos < 0) {
    		xPos = 0;
    	}
    	if (yPos < 0) {
    		yPos = 0;
    	}
    	if (xPos > World.maxWorldWidth - screenwidth) {
    		xPos = World.maxWorldWidth - screenwidth;
    	}
    	if (yPos > World.maxWorldHeight - screenheight) {
    		yPos = World.maxWorldHeight - screenheight;
    	} 
    	
    }
}
		