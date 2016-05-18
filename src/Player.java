import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.source.src.main.classes.EntityA;
import com.game.source.src.main.classes.EntityB;
import com.game.source.src.main.classes.EntityC;

public class Player extends GameObject implements EntityA {
	
	//Class properties
	private double velX=0;
	private double velY=0;
	private int health = 100 * 2;
	Animation anim;
	
	/** 
	 * Constructor that sets the properties for the player in this class
	 * @param x Horizontal position of player
	 * @param y	Vertical position of player
	 * @param tex Textures of the player
	 * @param game The game itself
	 * @param c The controller used
	 */
	public Player(double x, double y){
		super(x,y);
		
		anim = new Animation(100,Textures.getPlayerImage(0),Textures.getPlayerImage(1), Textures.getPlayerImage(2));
	}
	
	/** Update that occurs to the player */
	public void tick(){
		x+=velX;
		y+=velY;
		
		if (x <= 0)
			x = 0;
		if (x >= (640 - 18))
			x=640 - 18;
		if (y <0)
			y = 0;
		if (y >= (480 - 32))
			y = 480 - 32;
		
		for (int i = 0 ; i < Game.eb.size() ; i++){
			EntityB tempEnt = Game.eb.get(i);
			
			// the following body occurs when EntityA collides with EntityB
			if(Physics.Collision(this, tempEnt)){
				Controller.removeEntity(tempEnt);
				this.health -= 10;
				Enemy.setEnemyKilled(Enemy.getEnemyKilled()+1);
				if (this.health == 0){
					Game.State = Game.STATE.GAMEOVER;
				}
			}
		}
		for (int i = 0 ; i < Game.ec.size() ; i++){
			EntityC tempEnt = Game.ec.get(i);
			
			//removes the upgrade from the program as it leaves the screen	
			if (tempEnt.getY()> Game.HEIGHT * Game.SCALE){
				Controller.removeEntity(tempEnt);
			}
			// the following body occurs when entity a collides with entity c
			if(Physics.Collision(this, tempEnt)){
				Controller.removeEntity(tempEnt);
				Upgrades u = (Upgrades) tempEnt;
				if (u.getUpgradeType()==Textures.getHealthUpImage()){
					healthUp();
				} else if (u.getUpgradeType() == Textures.getPowerUpImage()){
					powerUp();
				}
			}
		}
	
		anim.runAnimation();
	}
	/** Renders the player onto the canvas */
	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
	}
	
	/** Increases the player's health */
	public void healthUp(){
		if (health <=190){
			health +=10;
		}else if (health >190 && health <200){
			health = 200;
		}
	}
	/** Increases the player's bullet speed */ 
	private void powerUp(){
		if (Bullet.bulletSpeed != 40)
			Bullet.bulletSpeed = Bullet.bulletSpeed + 10;
	}
	/** Returns the collision boundary of the player */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, Textures.SPRITE_WIDTH, Textures.SPRITE_HEIGHT);
	}
	/** Returns the horizontal position of the player */
	public double getX(){
		return x;
	}
	/** Returns the vertical position of the player */
	public double getY(){
		return y;
	}
	/** Sets the horizontal position of the player */
	public void setX(double x){
		this.x = x;
	}
	/** Sets the vertical position of the player */
	public void setY(double y){
		this.y = y;
	}
	/** Sets the horizontal velocity of the player */
	public void setVelX(double velX){
		this.velX = velX;
	}
	/** Sets the vertical velocity of the player */
	public void setVelY(double velY){
		this.velY = velY;
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
