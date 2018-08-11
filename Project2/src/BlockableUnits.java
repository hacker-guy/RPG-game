
public abstract class BlockableUnits {
	
	/** Get the dimensions of the unit*/
	public abstract int getHeight();
	public abstract int getWidth();
	/** Whether to block x or y */
	final static int BLOCKX = 1;
	static final int BLOCKY = 2;
	/** get the x coordinate*/
	public abstract int getX();
	/** get the y coordinate*/
	public abstract int getY();


    /** Function to check whether the unit is out of bounds
     * @param dir_x The direction the unit is moving in x
     * @param dir_y The direction the unit is moving in y
     * @return The direction to block the unit in */
	public int boundsCheck(double dir_x, double dir_y) {
		
		int blockCheck = 0;
		
		/* If unit is moving out of bounds in y then block movement */
		if (this.getY()+dir_y < 0) {
			blockCheck += BLOCKY;
		} else if (this.getY()+dir_y > World.maxWorldHeight - 
				this.getHeight()/2) {
			blockCheck += BLOCKY;
		}
		/* If unit is moving out of bounds in x then block movement */
		if (this.getX()+dir_x < 0) {
			blockCheck += BLOCKX;
		} else if (this.getX()+dir_x > World.maxWorldWidth - 
				this.getWidth()/2) {
			blockCheck += BLOCKX;
		}
		/* If unit is moving out of bounds in both x and y directions 
		 * then return */
		if (blockCheck == BLOCKX + BLOCKY) {
			return blockCheck;
		} else {
			return barrierCheck( blockCheck,  dir_x,  dir_y);
		}
	}
		
	/** Function to check whether the unit should be blocked
     * @param dir_x The direction the unit is moving in x
     * @param dir_y The direction the unit is moving in y
     * @return The direction to block the unit in */
	private int barrierCheck(int blockCheck, double dir_x, double dir_y) {
		
		int tileIDx = -1, tileIDy = -1;
		/* If unit is not moving out of bounds in x direction then check 
		 * the tile in x direction*/
		if (blockCheck != BLOCKX) {
			tileIDx = World.background.getTileId(
					getBoundsX(dir_x), 
					this.getY()/World.tilesize, 0);
		}
		/* If unit is not moving out of bounds in y direction then check 
		 * the tile in y direction*/
		if (blockCheck != BLOCKY) {
			tileIDy = World.background.getTileId(
					this.getX()/World.tilesize, 
					getBoundsY(dir_y), 0);
		}
		/* If a tile in x-direction has been checked and the unit should 
		 * be blocked then block the unit from moving in x*/
		if (tileIDx != -1 && 
				World.background.getTileProperty(tileIDx, "block", "0") != "0") {
			blockCheck += BLOCKX;
		}
		/* If a tile in y-direction has been checked and the unit should 
		 * be blocked then block the unit from moving in y*/
		if (tileIDy != -1 && 
				World.background.getTileProperty(tileIDy, "block", "0") != "0") {
			blockCheck += BLOCKY;
		}
		/* Return where to block the unit movement */
		return blockCheck;
	}
	
	/** Get the tile coordinates in the y-direction that the 
	 * player is moving
	 * @param dir_y the direction the player is moving in y
	 * @return The tile in the y direction that the player is moving*/
	private int getBoundsY(double dir_y) {
		float offsetY = this.getHeight()/2;
		float offsetY2 = this.getHeight()/16;
		if (dir_y > 0) {
			return (int)((this.getY() + offsetY)/World.tilesize);
		} else if (dir_y < 0){
			return (int)((this.getY() - offsetY2)/World.tilesize);
		}
		
		return (int)this.getY()/World.tilesize;
	}

	/** Get the tile coordinates in the x-direction that the 
	 * player is moving
	 * @param dir_x the direction the player is moving in x
	 * @param unit 
	 * @return The tile in the x direction that the player is moving*/
	private int getBoundsX(double dir_x) { 
		float offsetX = this.getWidth()/2;
		float offsetX2 = this.getWidth()/16;
		if (dir_x > 0) {
			return (int)((this.getX() + offsetX)/World.tilesize);
		} else if (dir_x < 0 ){
			return (int)((this.getX() - offsetX2)/World.tilesize);
		}
		
		return (int)this.getX()/World.tilesize;
	}

}

