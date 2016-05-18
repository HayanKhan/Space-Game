import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.source.src.main.classes.EntityC;

public class Upgrades implements EntityC {
	//Class properties
	private double x;
	private double y;
	BufferedImage upgradeImage;
	//Class types
	public static int powerUp = 1;
	public static int healthUp = 2;
	
	/**
	 * This constructor shows what type of upgrade is produced, the position it should be created at and the corresponding textures required
	 * @param type 1 for powerUp and 2 for healthUp
	 * @param x X position of where upgrade is produced
	 * @param y Y position of where upgrade is produced
	 * @param tex The textures used in the game
	 * @param c The controller used in the game
	 */
	public Upgrades(int type, double x, double y){ 
		this.x = x;
		this.y = y;
		
		if (type == powerUp){
			upgradeImage = Textures.getPowerUpImage();
		}else if (type == healthUp){
			upgradeImage = Textures.getHealthUpImage();
		}
	}
	
	/** Updates the upgrades */
	public void tick(){
		y += 1;
	}
	
	/** Renders the upgrades onto the frame*/
	public void render(Graphics g){
		g.drawImage(upgradeImage,(int) x, (int)y, null);
	}

	/** Creates the collision bounds around the upgrade*/
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y, Textures.SPRITE_WIDTH,Textures.SPRITE_HEIGHT);
	}
	/** Returns x position of the upgrade */
	public double getX() {
		return x;
	}
	/** Returns y position of the upgrade */
	public double getY() {
		return y;
	}
	/** Returns the upgrade image */
	public BufferedImage getUpgradeType(){
		return upgradeImage;
	}
}
