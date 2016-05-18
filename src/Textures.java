import java.awt.image.BufferedImage;
import java.io.IOException;

public class Textures {
	//Class image properties
	private static SpriteSheet ss;
	private static BufferedImage spriteSheetImage = null;
    private static BufferedImage background = null;
	private static BufferedImage[] playerImageArray = new BufferedImage[3];
	private static BufferedImage[] bulletImageArray = new BufferedImage[3];
	private static BufferedImage[] enemyImageArray = new BufferedImage[3];
	private static BufferedImage powerUpImage;
	private static BufferedImage healthUpImage;
	public static BufferedImage volumeButtonOn;
	public static BufferedImage volumeButtonOff;
	public static final int SPRITE_WIDTH = 32;
	public static final int SPRITE_HEIGHT = 32;
	

	/** Loads in the images for the player, enemies and bullets */
	public static void init(){
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			spriteSheetImage = loader.loadImage("/spriteSheet.png");
			background = loader.loadImage("/background.png");
		}catch(IOException e){
			e.printStackTrace();
		}
		
		ss = new SpriteSheet(spriteSheetImage);
		playerImageArray[0] = ss.grabImage(1, 1, SPRITE_WIDTH, SPRITE_HEIGHT);
		playerImageArray[1] = ss.grabImage(1, 2, SPRITE_WIDTH, SPRITE_HEIGHT);
		playerImageArray[2] = ss.grabImage(1, 3, SPRITE_WIDTH, SPRITE_HEIGHT);
		
		bulletImageArray[0] = ss.grabImage(2, 1, SPRITE_WIDTH, SPRITE_HEIGHT);
		bulletImageArray[1] = ss.grabImage(2, 2, SPRITE_WIDTH, SPRITE_HEIGHT);
		bulletImageArray[2] = ss.grabImage(2, 3, SPRITE_WIDTH, SPRITE_HEIGHT);
		
		enemyImageArray[0] = ss.grabImage(3, 1, SPRITE_WIDTH, SPRITE_HEIGHT);
		enemyImageArray[1] = ss.grabImage(3, 2, SPRITE_WIDTH, SPRITE_HEIGHT);
		enemyImageArray[2] = ss.grabImage(3, 3, SPRITE_WIDTH, SPRITE_HEIGHT);
		
		powerUpImage = ss.grabImage(4, 1, SPRITE_WIDTH, SPRITE_HEIGHT);
		healthUpImage = ss.grabImage(4, 2, SPRITE_WIDTH, SPRITE_HEIGHT);
		
		volumeButtonOn = ss.grabImage(5, 1,SPRITE_WIDTH, SPRITE_HEIGHT);
		volumeButtonOff = ss.grabImage(5, 2, SPRITE_WIDTH, SPRITE_HEIGHT);
		
	}
	/**
	 * Returns a specific player image from the player image array
	 * @param i The player image you would like to return
	 * @return Returns the player image
	 */
	public static BufferedImage getPlayerImage(int i){
		return playerImageArray[i];
	}
	/**
	 * Returns a specific bullet image from the bullet image array.
	 * @param i The bullet image you would like to return
	 * @return Returns the bullet image
	 */
	public static BufferedImage getMissleImage(int i){
		return bulletImageArray[i];
	}
	/**
	 * Returns a specific enemy image from the bullet image array.
	 * @param i The enemy image you would like to return
	 * @return Returns the enemy image
	 */
	public static BufferedImage getEnemyImage(int i){
		return enemyImageArray[i];
	}
	/** @return The power up image*/
	public static BufferedImage getPowerUpImage(){
		return powerUpImage;
	}
	/** @return The health up image*/
	public static BufferedImage getHealthUpImage(){
		return healthUpImage;
	}
	/** @return The background used in the game	 */
	public static BufferedImage getBackground(){
		return background;
	}
	
}
