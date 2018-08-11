/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Justin Bugeja> <jbugeja> <758397>
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */
public class World
{
	/** The map of the background */
	TiledMap background;
	/** The camera class */
	Camera camera;
	/** The player class */
	Player player;
	/** Constant numbers based on background */
	static int maxWorldWidth, maxWorldHeight, tilesize;
	/** Which direction to block player movement*/
	private int block;
	/** Whether to block x or y */
	private final static int blockX = 1, blockY = 2;
	/** The speed at which the player is to move */
	private final static double speed = 0.25;
	
	
    /** Create a new World object. */
    public World()
    throws SlickException
    {
    	/* Creating a new player object*/
    	player = new Player();
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
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(double dir_x, double dir_y, int delta)
    throws SlickException
    {
    	/* If the player is moving */
    	if ((int)Math.abs(dir_x) > 0  || (int)Math.abs(dir_y) > 0) {
    		
    		/* Check whether to block the player from moving */
    		block = boundsCheck( dir_x,  dir_y);
    		
    		/* If player isn't blocked */
    		if (block == 0) {
    			player.update(dir_x*speed*delta, dir_y*speed*delta);
    			/* If player is blocked in x direction, move only in y*/
    		} else if (block == blockX) {
    			player.update(0, dir_y*speed*delta);
    			/* If player is blocked in y direction, move only in x*/
    		} else if (block == blockY) {
    			player.update(dir_x*speed*delta, 0);
    		}
    		
    		/* Update the camera to follow the player */
    		camera.update(player);
    	}
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
        
      //DEGBUG
        
        if (RPG.debug) {
        	g.drawString("Player (x,y) = " 
        			+ player.getX() + 
        			", " + player.getY(), 
        			camera.getxPos() + 100, 
        			camera.getyPos() + 100); 
        	g.drawString("Camera (x,y) = " 
        			+ camera.getxPos() + 
        			", " + camera.getyPos(), 
        			camera.getxPos() + 100, 
        			camera.getyPos() + 150); 
        	g.drawString("Tile (*block) =" 
        			+" *"+ block, 
        			camera.getxPos() + 100, 
        			camera.getyPos() + 200); 
        	g.drawOval(player.getX(), player.getY(), 5, 5);
        }
        
    	player.draw();
    	
    	
    }

    /** Function to check whether the player should be blocked from 
     * moving or not
     * @param dir_x The direction the player is moving in x
     * @param dir_y The direction the player is moving in y
     * @return The direction to block the player in */
	private int boundsCheck(double dir_x, double dir_y) {
		
		int blockCheck = 0;
		int tileIDx = -1, tileIDy = -1;
		
		/* If player is moving out of bounds in y then block movement */
		if (player.getY()+dir_y < 0) {
			blockCheck += blockY;
		} else if (player.getY()+dir_y > World.maxWorldHeight - 
				player.getHeight()/2) {
			blockCheck += blockY;
		}
		/* If player is moving out of bounds in x then block movement */
		if (player.getX()+dir_x < 0) {
			blockCheck += blockX;
		} else if (player.getX()+dir_x > World.maxWorldWidth - 
				player.getWidth()/2) {
			blockCheck += blockX;
		}
		/* If player is moving out of bounds in both x and y directions 
		 * then return */
		if (blockCheck == blockX + blockY) {
			return blockCheck;
		}
		/* If player is not moving out of bounds in x direction then check 
		 * the tile in x direction*/
		if (blockCheck != blockX) {
			tileIDx = background.getTileId(
					player.getBoundsX(dir_x), 
					player.getY()/World.tilesize, 0);
		}
		/* If player is not moving out of bounds in y direction then check 
		 * the tile in y direction*/
		if (blockCheck != blockY) {
			tileIDy = background.getTileId(
					player.getX()/World.tilesize, 
					player.getBoundsY(dir_y), 0);
		}
		/* If a tile in x-direction has been checked and the player should 
		 * be blocked then block the player from moving in x*/
		if (tileIDx != -1 && 
				background.getTileProperty(tileIDx, "block", "0") != "0") {
			blockCheck += blockX;
		}
		/* If a tile in y-direction has been checked and the player should 
		 * be blocked then block the player from moving in y*/
		if (tileIDy != -1 && 
				background.getTileProperty(tileIDy, "block", "0") != "0") {
			blockCheck += blockY;
		}
		/* Return where to block the player movement */
		return blockCheck;
	}
	
}



