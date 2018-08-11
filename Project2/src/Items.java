import org.newdawn.slick.Image;

public abstract class Items {

	/** The monster's sprite image*/
	private Image sprite;
	
	public void setSprite(Image image) {
		this.sprite = image;
	}
	
	/** Gets the item sprite*/
	public Image getSprite() {
		return sprite;
	}
	
	/** The action the item has on the player*/
	public abstract void action(Player player);
	/** Whether the item is within range of the player*/
	public boolean withinRange(Player player) {
		double ydist = Math.pow(Math.abs(player.getX() - this.getX()), 2);
		double xdist = Math.pow(Math.abs(player.getY() - this.getY()), 2);
		if (Math.sqrt(xdist+ydist) < 50) {
			return true;
		}
		return false;
	}
	/** Gets the x coodinate*/
	public abstract int getX();
	/** Gets the y coodinate*/
	public abstract int getY();
	/** Draws the item*/
	public void draw() {
		Image sprite = this.getSprite();
		int x = this.getX() - sprite.getWidth()/2;
		int y = this.getY() - sprite.getHeight()/2;
		sprite.draw(x,y);
	}

	/** Draws the tem
	 * @return X the x-coordinate*/
	public void drawInv(int x, int y) {
		sprite.draw(x, y);
	}
}
