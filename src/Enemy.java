import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.source.src.main.classes.AllyEntity;
import com.game.source.src.main.classes.EnemyEntity;


public class Enemy extends GameObject implements EnemyEntity {
	
	private Random r = new Random();
	private Animation anim;
	
	private double ySpeed = r.nextDouble() * ScoreSystem.getWaveNum() * 0.25 + 1; 
	private double xSpeed = 1.5;
	private double originalX;
	private double sway = 50;
	private static int enemyKilled = 0;
	
	/**
	 * Main constructor for the enemy class, and produces enemies on the screen with specific images at certain positions.
	 * @param x Horizontal position of enemy plane
	 * @param y	Vertical position of enemy plane
	 * @param tex Used to retrieve enemy plane textures
	 * @param game Used for access to enemy plane linked list
	 * @param c Controller to remove bullets, enemies and create upgrades
	 */
	public Enemy(double x, double y){
		super(x,y);
		originalX = x;
		xSpeed = r.nextDouble() / 2 + 1;
		anim = new Animation(5, Textures.getEnemyImage(0),Textures.getEnemyImage(1),Textures.getEnemyImage(2));
	}
	
	/** Updates the enemy plane */
	public void tick(){
		y += ySpeed;
		x += xSpeed;
		
		if (x - originalX > sway || x - originalX < -sway || x < 0|| x > Game.WIDTH * Game.SCALE - Game.GAME_SIDE_PANEL_WIDTH - Textures.SPRITE_WIDTH)
			xSpeed = -xSpeed;
		
		if (y> (Game.HEIGHT * Game.SCALE)){
			int nextXPosition = r.nextInt((Game.WIDTH * Game.SCALE - Game.GAME_SIDE_PANEL_WIDTH - Textures.SPRITE_WIDTH));
			this.setY(-10);
			this.setX(nextXPosition);
			originalX = nextXPosition;
			ySpeed = r.nextDouble() * ScoreSystem.getWaveNum() * 0.25 + 1; 
		}
		
		for (int i = 0 ; i < Controller.getEntityA().size(); i++){
			AllyEntity tempEnt = Controller.getEntityA().get(i);
			Bullet currBullet = (Bullet)tempEnt;
			
			if (Physics.Collision(this, tempEnt)){
				
				if (!currBullet.isPiercingBullet())
					Controller.removeEntity(tempEnt);
				
				Controller.removeEntity(this);
				setEnemyKilled(getEnemyKilled() + 1);
				Controller.createUpgrade(x, y);
				
				ScoreSystem.updateScoreSystem("Unit Destroyed");
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
