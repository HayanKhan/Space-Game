import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Audio.AudioPlayer;

public class Menu {
	
	private static int rectangleWidth = 100;
	private static int rectangleHeight = 50;
	public static Rectangle playButton = new Rectangle(Game.WIDTH * Game.SCALE / 2 - rectangleWidth / 2, 250,rectangleWidth, rectangleHeight);
	public static Rectangle quitButton = new Rectangle(Game.WIDTH * Game.SCALE / 2 - rectangleWidth / 2, 350,rectangleWidth, rectangleHeight);
	
	private static BufferedImage volumeButtonOn = Textures.volumeButtonOn;
	private static BufferedImage volumeButtonOff = Textures.volumeButtonOff;
	private static int volumeXPos = (Game.WIDTH * Game.SCALE) - volumeButtonOn.getWidth();
	private static int volumeYPos = Game.HEIGHT * Game.SCALE - volumeButtonOn.getHeight();
	public static Rectangle volumeButton = new Rectangle(volumeXPos, volumeYPos, volumeButtonOn.getWidth(), volumeButtonOff.getHeight());
	
	/** Renders the menu state and the audio button */
	public void render(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;	
		
		createTextLabel(g, "SPACE GAME", 50, Color.WHITE, Game.WIDTH * Game.SCALE / 2, 50);
		createTextLabel(g, "Made By: Hayan Khan", 15, Color.WHITE, Game.WIDTH * Game.SCALE / 2, 75);
		createTextLabel(g, "PLAY", 30, Color.WHITE, Game.WIDTH * Game.SCALE / 2, playButton.y + 35);
		createTextLabel(g, "QUIT", 30, Color.WHITE, Game.WIDTH * Game.SCALE / 2, quitButton.y + 35);
		
		g2d.draw(playButton);
		g2d.draw(quitButton);
		
		if (AudioPlayer.getAudioState())
			g.drawImage(volumeButtonOn, volumeXPos, volumeYPos, null);
		else if (!AudioPlayer.getAudioState())
			g.drawImage(volumeButtonOff, volumeXPos, volumeYPos, null);	
	}
	
	/**
	 * Creates a text label, at its center position.
	 * @param g Referenced to the canvas the graphics are drawn on.
	 * @param text Text displayed to graphics canvas
	 * @param fontSize Size of the font
	 * @param color Color of the label
	 * @param x The center of the label
	 * @param y The top position of the label
	 */
	public void createTextLabel(Graphics g, String text, int fontSize, Color color, int x, int y ){
	
		Font font = new Font("Arial", Font.BOLD, fontSize);
		FontMetrics fontMetrics = g.getFontMetrics(font);
		int textWidth = fontMetrics.stringWidth(text);
		
		g.setFont(font);
		g.setColor(color);
		g.drawString(text, x - textWidth / 2, y);
	}
}
