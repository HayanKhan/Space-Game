import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ScoreSystem {
	//Class properties
	private int score = 0;
	private final int scoreLabelXOffSet = Game.WIDTH * Game.SCALE - 70;
	private final int scoreLabelYOffSet = 50;
	
	public Rectangle r = new Rectangle(Game.WIDTH/2 + 90 ,350,150,50);
	public ScoreSystem(){
		
	}
	/** Renders the scoreboard */
	public void render(Graphics g){
		Font f0 = new Font("Arial",Font.BOLD,10);
		g.setFont(f0);
		g.setColor(Color.WHITE);
		g.drawString("Score: " + score , scoreLabelXOffSet, scoreLabelYOffSet);
	}
	/** Updates the new score */ 
	public void setScore(int score){
		this.score = score;
	}
	/** Returns the score */
	public int getScore(){
		return score;
	}
}
