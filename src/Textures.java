import java.awt.image.BufferedImage;

public class Textures {
	//Class image properties
	private SpriteSheet ss;
	public BufferedImage[] player = new BufferedImage[3];
	public BufferedImage[] missle = new BufferedImage[3];
	public BufferedImage[] enemy = new BufferedImage[3]; //NOTICE PUBLIC,BAD CODING STYLE, CAN MAKE IT STATIC AND CHANGE THE PROGRAM OR USE GETTERS/SETTERS
	public BufferedImage powerUp;
	public BufferedImage healthUp;
	public static BufferedImage volumeButtonOn;
	public static BufferedImage volumeButtonOff;
	public static int SPRITE_WIDTH = 32;
	public static int SPRITE_HEIGHT = 32;
	
	/**
	 * Constructor that initializes the spritesheet and gets the textures
	 * @param game Reference of the main game class.
	 */
	public Textures(Game game){
		ss = new SpriteSheet(game.getSpriteSheet());
		getTextures();	
	}
	
	/** Loads in the images for the player, enemies and bullets */
	private void getTextures(){
		player[0] = ss.grabImage(1, 1, 32, 32);
		player[1] = ss.grabImage(1, 2, 32, 32);
		player[2] = ss.grabImage(1, 3, 32, 32);
		
		missle[0] = ss.grabImage(2, 1, 32, 32);
		missle[1] = ss.grabImage(2, 2, 32, 32);
		missle[2] = ss.grabImage(2, 3, 32, 32);
		
		enemy[0] = ss.grabImage(3, 1, 32, 32);
		enemy[1] = ss.grabImage(3, 2, 32, 32);
		enemy[2] = ss.grabImage(3, 3, 32, 32);
		
		powerUp = ss.grabImage(4, 1, 32, 32);
		healthUp = ss.grabImage(4, 2, 32, 32);
		
		volumeButtonOn = ss.grabImage(5, 1, 32, 32);
		volumeButtonOff = ss.grabImage(5, 2, 32, 32);
		
	}
}
