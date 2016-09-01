import java.awt.Color;
import java.awt.Graphics;

public class HealthBar {
	
	private int healthBarWidth;
	private int healthBarHeight;
	private int healthBarX;
	private int healthBarY;
	private int health;
	
	public HealthBar(Object character){
		if (character instanceof Player){
			healthBarWidth = 200;
			healthBarHeight = 50;
			healthBarX = Game.WIDTH * Game.SCALE - Game.GAME_SIDE_PANEL_WIDTH / 2 - healthBarWidth / 2;
			healthBarY = 50;
			health = 200;
		}
	}

	/** Renders the health bars onto the canvas */
	public void render(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(healthBarX,healthBarY,healthBarWidth,healthBarHeight);
		
		g.setColor(Color.RED);
		g.fillRect(healthBarX,healthBarY,health ,healthBarHeight);
		
		g.setColor(Color.WHITE);
		g.drawRect(healthBarX,healthBarY,healthBarWidth, healthBarHeight);
	}
	
	/**
	 * Sets the health of the player
	 * @param health The new health of the player
	 */
	public void setHealth(int health){
		this.health = health;
	}
	/** @return returns the current health of the player */
	public int getHealth(){
		return health;
	}
}
