
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class ScoreSystem {

	private static int score = 0;
	private static double multiplier = 1;
	private static int waveNum = 1;
	private static String bulletSpeedLevel = "1";
	private static String playerSpeedLevel = "1";
	
	private final int labelXOffSet = 100;
	private final int labelX = Game.WIDTH * Game.SCALE - Game.GAME_SIDE_PANEL_WIDTH / 2 - labelXOffSet;
	private final int upgradesLabelY = 600;
	private final int upgradeLabelOffSet = 25;
	private final int statsLabelY = 750;
	private final int statsLabelOffSet = 50;
	
	private final int abilityLabelY = 400;
	private final int abilityImageOffSet = Textures.SPRITE_WIDTH + 20;
	private final int abilityLabelOffSet = abilityImageOffSet;
	
	
	/** Renders the score board */
	public void render(Graphics g){
		
		//ABILITIES TOOLBAR
		createButtonText(g, "Upgrades", 25, Color.WHITE, labelX, upgradesLabelY - 10);
		g.drawImage(Textures.getBulletSpeedUpImage(), labelX - Textures.SPRITE_WIDTH, upgradesLabelY + 3, null);
		createButtonText(g, "Bullet Speed Level: " + bulletSpeedLevel, 15, Color.WHITE, labelX, upgradesLabelY + upgradeLabelOffSet);
		g.drawImage(Textures.getPlayerSpeedUpImage(), labelX - Textures.SPRITE_WIDTH, upgradesLabelY + 30, null);
		createButtonText(g, "Player Speed Level: " + playerSpeedLevel, 15, Color.WHITE, labelX, upgradesLabelY + upgradeLabelOffSet * 2);
		
		//SCORE TOOLBAR
		createButtonText(g, "Wave Number: " + waveNum, 25, Color.WHITE, labelX, statsLabelY);
		createButtonText(g, "Score: " + score, 25, Color.WHITE, labelX, statsLabelY + statsLabelOffSet);
		createButtonText(g, "Multiplier: x" + multiplier, 25, Color.CYAN, labelX, statsLabelY + statsLabelOffSet * 2);
		
		
		//ABILITIES TOOLBAR
		createButtonText(g, "Abilities", 25, Color.WHITE, labelX, 350);
		
		g.drawImage(Textures.getAbilityImage('q'), labelX - Textures.SPRITE_WIDTH,  abilityLabelY, null);
		if (Player.getAbilityTimer('q').isOnCooldown())
			createButtonText(g, Integer.toString(Player.getAbilityTimer('q').getInterval()), 40, Color.CYAN, labelX - Textures.SPRITE_WIDTH + 5, abilityLabelY + Textures.SPRITE_HEIGHT);
		
		g.drawImage(Textures.getAbilityImage('w'), labelX - Textures.SPRITE_WIDTH + abilityImageOffSet,  abilityLabelY, null);
		if (Player.getAbilityTimer('w').isOnCooldown())
			createButtonText(g, Integer.toString(Player.getAbilityTimer('w').getInterval()), 40, Color.CYAN, labelX + abilityLabelOffSet / 2 , abilityLabelY + Textures.SPRITE_HEIGHT);
		
		g.drawImage(Textures.getAbilityImage('e'), labelX - Textures.SPRITE_WIDTH + abilityImageOffSet * 2, abilityLabelY, null);
		if (Player.getAbilityTimer('e').isOnCooldown())
			createButtonText(g, Integer.toString(Player.getAbilityTimer('e').getInterval()), 40, Color.CYAN, labelX + abilityLabelOffSet * 2 - abilityLabelOffSet / 2 , abilityLabelY + Textures.SPRITE_HEIGHT);
	}
	
	/**
	 * Creates the text for a button.
	 * @param g The graphics canvas from the main game.
	 * @param buttonText The text to be inserted into the button.
	 * @param fontSize The size of the text font.
	 * @param color The color of the text/
	 * @param x The x position of the string inserted, relative to the canvas.
	 * @param y The y position of the string inserted, relative to the canvas.
	 */
	public void createButtonText(Graphics g, String buttonText, int fontSize, Color color, int x, int y){
		g.setFont(new Font("Arial", Font.BOLD, fontSize));
		g.setColor(color);
		g.drawString(buttonText, x, y);
	}
	
	/** Updates the wave number */
	public static void setWaveNum(int waveNum){
		ScoreSystem.waveNum = waveNum;
	}
	
	/** Returns the wave number */
	public static int getWaveNum(){
		return waveNum;
	}
	
	/** Updates the wave number */
	public static void setMultiplier(double multiplier){
		ScoreSystem.multiplier = multiplier;
	}
	
	/** Returns the wave number */
	public static double getMultiplier(){
		return multiplier;
	}
	
	/** Updates the new score */ 
	public static void setScore(int score){
		ScoreSystem.score = score;
	}
	
	/** Returns the score */
	public static int getScore(){
		return score;
	}
	
	/**
	 * Sets the speed of the player's bullet
	 * @param bulletSpeed The speed of the player's bullet
	 */
	public static void setBulletSpeedLevel(String bulletSpeedLevel){
		ScoreSystem.bulletSpeedLevel = bulletSpeedLevel;
	}
	
	/**
	 * Sets the speed of the player
	 * @param playerSpeed The speed of the player
	 */
	public static void setPlayerSpeedLevel(String playerSpeedLevel){
		ScoreSystem.playerSpeedLevel= playerSpeedLevel;
	}
	
	/** Updates all the score and multiplier for the the enemy class  */
	public static void updateScoreSystem(String updateContext){
		
		if (updateContext.equals("Unit Destroyed")){
			score += (int) (10 * multiplier);
			multiplier += 0.25;
		}else if (updateContext.equals("Player Collision")){
			multiplier -=1;
		}else if (updateContext.equals("Upgrade Collision")){
			multiplier += 1;
		}else if (updateContext.equals("Upgrade Missed")){
			multiplier -= 1;
		}else if (updateContext.equals("Bullet Missed")){
			multiplier -=0.25;
		} 
		
		if (multiplier < 1)
			multiplier = 1;
	}
}
