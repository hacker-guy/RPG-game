import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
 
public class SlickFlyingGame extends BasicGame 
	{ 
        int count = 0;
        
        Image background;
        Image background2;
        Image currBackground;
        Image bullet;
        int backgroundcheck = 0;
        
        Image player;
        int x=400;
        int y=300; 
        double SPEED = 0.8;
        
        Random rand = new Random();
        
        Image target;
        int randx = rand.nextInt(800);
        int randy = rand.nextInt(600);
        
	
		public SlickFlyingGame() {
				super("SlickFlyingGame"); 
	    } 
		
		@Override 
	    public void init(GameContainer container) 
	    	throws SlickException {
			
			 background = new Image("assets/land.jpeg");
			 background2 = new Image("assets/Background2.jpg");
			 currBackground = background;
			 
			 player = new Image("assets/plane.png");
			 bullet = new Image("assets/plane.png");
			 
			 target = new Image("assets/balloon.png");

		} 
	    
	    @Override public void update(GameContainer gc, int delta) 
	    throws SlickException {
	    	
            Input input = gc.getInput();
          
            if(input.isKeyDown(Input.KEY_SPACE)) {
            	bullet.draw(x+40,y);
            }
            
	        if(input.isKeyDown(Input.KEY_UP))
	        {
	        	if (y > 0) {
	        		y -= delta*SPEED;
	        		player.setRotation(0);
	        	}
	        } else 
	 
	        if(input.isKeyDown(Input.KEY_DOWN))
	        {
	        	if (y < 600 - player.getHeight()) {
	        		y += delta*SPEED;
	        		player.setRotation(180);
	        	}
	        } else 
	        if(input.isKeyDown(Input.KEY_RIGHT))
	        {
	        	if (x < 800 - player.getWidth()) {
	        		x += delta*SPEED;
	        		player.setRotation(90);
	        	}
	        } else
	        if(input.isKeyDown(Input.KEY_LEFT))
	        {
	        	if (x > 0) {
	        		x -= delta*SPEED;
	        		player.setRotation(-90);
	        	}
	        }
	        //Change to modulus (distance formula)
	        if (x - randx < 50 && y - randy < 50) {
	        	randx = rand.nextInt(800);
	        	randy = rand.nextInt(600);
	        	count++;
	        }
	        
	        if(input.isKeyPressed(Input.KEY_ESCAPE))
	        {
	        	System.exit(0);
	        }
	        if(input.isKeyPressed(Input.KEY_C))
	        {
	        	
				if (backgroundcheck == 0) {
	        		currBackground = background2;
	        		backgroundcheck = 1;
	        	} else {
	        		currBackground = background;
	        		backgroundcheck = 0;
	        	}
	        }
	    } 
	    
	    @Override public void render(GameContainer container, Graphics g) 
	    throws SlickException { 
	    	
	    	currBackground.draw(0,0);
	    	target.draw(randx, randy);
	    	player.draw(x,y);
	    	g.drawString("Win = " + count, 100, 100); 
	    } 


	public static void main(String[] args) {
		try { 
    		AppGameContainer app = new AppGameContainer(new SlickFlyingGame()); 
    		app.setDisplayMode(800, 600, false);
            app.start();

    		
    	} catch (SlickException e) { 
    		e.printStackTrace(); 
    	} 
	}

}
