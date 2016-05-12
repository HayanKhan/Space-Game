import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.source.src.main.classes.EntityA;
import com.game.source.src.main.classes.EntityB;

public class Enemy extends GameObject implements EntityB {
	
	//Class features
	Random r = new Random();
	Animation anim;
	private Game game;
	private Controller c;
	
	//Enemy plane speed
	private double speed = r.nextDouble() * 3 + 1; 
	private static int enemyKilled = 0;
	
	/**
	 * Main constructor for the enemy class, and produces enemies on the screen with specific images at certain positions.
	 * @param x Horizontal position of enemy plane
	 * @param y	Vertical position of enemy plane
	 * @param tex Used to retrieve enemy plane textures
	 * @param game Used for access to enemy plane linked list
	 * @param c Controller to remove bullets, enemies and create upgrades
	 */
	public Enemy(double x, double y, Textures tex, Game game, Controller c){
		super(x,y);
		this.game = game;
		this.c = c;
		
		anim = new Animation(5,tex.enemy[0],tex.enemy[1],tex.enemy[2]);
	}
	/** Updates the enemy plane */
	public void tick(){
		y+=speed;
		if (y> (Game.HEIGHT * Game.SCALE)){
			this.setY(-10); // -10 is offset
			this.setX(r.nextInt((Game.WIDTH* Game.SCALE))); //make a variablee somewhere to replace the 32 constant
			speed = r.nextDouble() * 3 + 1; 
		}
		for (int i = 0 ; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			
			if (Physics.Collision(this, tempEnt)){
				c.removeEntity(tempEnt);
				c.removeEntity(this);
				setEnemyKilled(getEnemyKilled() + 1);
				game.getScoreSystem().setScore(game.getScoreSystem().getScore() + 10);
				c.createUpgrade(x, y);
			}
		}
		anim.runAnimation();
	}
	/** Renders the enemy */
	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
	}
	/** Sets collision boundaries of the enemy plane*/
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, Textures.SPRITE_WIDTH, Textures.SPRITE_HEIGHT);
	}
	/** Returns x position of the enemy */
	public double getX(){
		return x;
	}
	/** Returns y position of the enemy */
	public double getY(){
		return y;
	}
	/**
	 * Sets y position of the enemy
	 * @param y Y position to be set at
	 */
	public void setY(double y){
		this.y =y;
	}
	/**
	 * Sets y position of the enemy
	 * @param x X position to be set at
	 */
	public void setX(double x){
		this.x =x;
	}
	
	/** Returns the number of enemies killed */
	public static int getEnemyKilled() {
		return enemyKilled;
	}
	
	/** Sets the number of enemies killed */
	public static void setEnemyKilled(int enemyKilled) {
		Enemy.enemyKilled = enemyKilled;
	}
}