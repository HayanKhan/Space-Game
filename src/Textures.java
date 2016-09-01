import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class Textures {

	private static SpriteSheet ss;
	private static BufferedImage spriteSheetImage = null;
    private static BufferedImage background = null;
	private static BufferedImage[] playerImageArray = new BufferedImage[3];
	private static BufferedImage[] bulletImageArray = new BufferedImage[3];
	private static BufferedImage[] enemyImageArray = new BufferedImage[3];
	private static HashMap <Character, BufferedImage> abilityImageMap = new HashMap<>();
	private static BufferedImage bulletSpeedUpImage;
	private static BufferedImage healthUpImage;
	private static BufferedImage playerSpeedUpImage;
	public static BufferedImage volumeButtonOn;
	public static BufferedImage volumeButtonOff;
	public static final int SPRITE_WIDTH = 32;
	public static final int SPRITE_HEIGHT = 32;
	public static final int NUM_SPRITE_IN_COLUMN = 3;
	

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
		
		for (int i = 0 ; i < NUM_SPRITE_IN_COLUMN ; i++){
			playerImageArray[i] = ss.grabImage(1, i + 1, SPRITE_WIDTH, SPRITE_HEIGHT);
			bulletImageArray[i] = ss.grabImage(2, i + 1, SPRITE_WIDTH, SPRITE_HEIGHT);
			enemyImageArray[i] = ss.grabImage(3, i + 1, SPRITE_WIDTH, SPRITE_HEIGHT);
		}
	
		bulletSpeedUpImage = ss.grabImage(4, 1, SPRITE_WIDTH, SPRITE_HEIGHT);
		healthUpImage = ss.grabImage(4, 2, SPRITE_WIDTH, SPRITE_HEIGHT);
		playerSpeedUpImage = ss.grabImage(4, 3, SPRITE_WIDTH, SPRITE_HEIGHT);
		
		volumeButtonOn = ss.grabImage(5, 1,SPRITE_WIDTH, SPRITE_HEIGHT);
		volumeButtonOff = ss.grabImage(5, 2, SPRITE_WIDTH, SPRITE_HEIGHT);
		
		abilityImageMap.put('q', ss.grabImage(6, 1, SPRITE_WIDTH, SPRITE_HEIGHT));
		abilityImageMap.put('w', ss.grabImage(6, 2, SPRITE_WIDTH, SPRITE_HEIGHT));
		abilityImageMap.put('e', ss.grabImage(6, 3, SPRITE_WIDTH, SPRITE_HEIGHT));
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
	
	/** @return The bullet speed up image*/
	public static BufferedImage getBulletSpeedUpImage(){
		return bulletSpeedUpImage;
	}
	
	/** @return The health up image*/
	public static BufferedImage getHealthUpImage(){
		return healthUpImage;
	}
	
	/** @return The player speed up image*/
	public static BufferedImage getPlayerSpeedUpImage(){
		return playerSpeedUpImage;
	}
	
	/** @return The image of the player's ability*/
	public static BufferedImage getAbilityImage(char ability){
		return abilityImageMap.get(ability);
	}
	
	/** @return The background used in the game	 */
	public static BufferedImage getBackground(){
		return background;
	}
}
