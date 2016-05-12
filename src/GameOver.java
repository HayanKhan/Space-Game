import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GameOver {
	//Class properties
	public Rectangle r = new Rectangle(Game.WIDTH/2 + 90 ,350,150,50);
	private ScoreSystem scoreSystem;
	
	/** 
	 * The constructor of the game over state.
	 * @param scoreSystem The score system.
	 */
	public GameOver(ScoreSystem scoreSystem){
		this.scoreSystem = scoreSystem;
	}
	/**Renders the game over state */
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		Font f0 = new Font("Arial",Font.BOLD,50);
		g.setFont(f0);
		g.setColor(Color.RED);
		g.drawString("GAME OVER", Game.WIDTH/2, Game.HEIGHT/2);
		g2d.draw(r);
		
		Font f1 = new Font("Arial",Font.BOLD,30);
		g.setFont(f1);
		g.setColor(Color.BLUE);
		g.drawString("Try Again",r.x + 5, r.y + (int) r.getHeight() - 5);
		g.setColor(Color.WHITE);
		g.drawString("Score: " + scoreSystem.getScore() , r.x + 5, r.y - 80);
	
	}
}
