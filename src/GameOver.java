import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class GameOver {
	
	private static int rectangleWidth = 150;
	private static int rectangleHeight = 50;

	public static final Rectangle exitButton = new Rectangle(Game.WIDTH * Game.SCALE / 2 - rectangleWidth / 2, 400, rectangleWidth, rectangleHeight);
	
	/**Renders the game over state */
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		createButtonText(g, "Game Over", 80, Color.RED, Game.WIDTH *Game.SCALE/2, 100);
		createButtonText(g, "Exit", 30, Color.BLUE, Game.WIDTH *Game.SCALE/2, exitButton.y + 35);
		createButtonText(g, "Score: " + ScoreSystem.getScore(), 50, Color.WHITE, Game.WIDTH *Game.SCALE/2, 700 );
	
		g2d.draw(exitButton);
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
	public void createButtonText(Graphics g, String text, int fontSize, Color color, int x, int y){

		Font font = new Font("Arial", Font.BOLD, fontSize);
		FontMetrics fontMetrics = g.getFontMetrics(font);
		int textWidth = fontMetrics.stringWidth(text);
		
		g.setFont(font);
		g.setColor(color);
		g.drawString(text, x - textWidth / 2, y);	

	}
}
