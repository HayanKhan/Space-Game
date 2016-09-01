import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import com.game.source.src.main.classes.AllyEntity;
import com.game.source.src.main.classes.EnemyEntity;
import com.game.source.src.main.classes.UpgradeEntity;


public class Player extends GameObject implements AllyEntity, KeyListener {
	
	private boolean isShooting = false;
	private double velX=0;
	private double velY=0;
	private int playerSpeed = 5;
	private int initialPlayerSpeed = playerSpeed;
	private int maxPlayerSpeed  = 10;
	
	private HealthBar playerHealthBar;
	
	private Animation anim;
	
	private static HashMap<Character, AbilityTimer> abilityTimerMap = new HashMap<>();
	
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
		playerHealthBar = new HealthBar(this);
				
		abilityTimerMap.put('q', new AbilityTimer(3));
		abilityTimerMap.put('w', new AbilityTimer(5));
		abilityTimerMap.put('e', new AbilityTimer(10));
	}
	
	/** Update that occurs to the player */
	public void tick(){
		x+=velX;
		y+=velY;
		
		if (x <= 0)
			x = 0;
		if (x >= (Game.WIDTH * Game.SCALE - Game.GAME_SIDE_PANEL_WIDTH - Textures.SPRITE_WIDTH))
			x = Game.WIDTH * Game.SCALE - Game.GAME_SIDE_PANEL_WIDTH - Textures.SPRITE_WIDTH;
		if (y <0)
			y = 0;
		if (y >= (Game.HEIGHT * Game.SCALE - Textures.SPRITE_HEIGHT))
			y = Game.HEIGHT * Game.SCALE - Textures.SPRITE_HEIGHT;
		
		for (int i = 0 ; i < Controller.getEntityB().size() ; i++){	
			EnemyEntity tempEnt = Controller.getEntityB().get(i);
			// the following body occurs when EntityA collides with EntityB
			if(Physics.Collision(this, tempEnt)){
				Controller.removeEntity(tempEnt);
				playerHealthBar.setHealth(playerHealthBar.getHealth() - 10);
				Enemy.setEnemyKilled(Enemy.getEnemyKilled()+1);
				if (playerHealthBar.getHealth() == 0){
					Game.State = Game.STATE.GAMEOVER;
				}
				
				ScoreSystem.updateScoreSystem("Player Collision");
			}
		}
		for (int i = 0 ; i < Controller.getEntityC().size() ; i++){
			UpgradeEntity tempEnt = Controller.getEntityC().get(i);
		
			// the following body occurs when entity a collides with entity c
			if(Physics.Collision(this, tempEnt)){
				Controller.removeEntity(tempEnt);
				Upgrades u = (Upgrades) tempEnt;
				
				if (u.getUpgradeType()==Textures.getHealthUpImage())
					healthUp();
				else if (u.getUpgradeType() == Textures.getBulletSpeedUpImage())
					bulletSpeedUp();
				else if (u.getUpgradeType() == Textures.getPlayerSpeedUpImage())
					playerSpeedUp();
				
				ScoreSystem.updateScoreSystem("Upgrade Collision");
			}
		}
		anim.runAnimation();
	}
	
	/** Renders the player onto the canvas */
	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
		playerHealthBar.render(g);
	}
	
	/** Game updates after certain keys are pressed */
	@Override
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if (Game.State == Game.STATE.GAME){
			if (key == KeyEvent.VK_RIGHT){
				this.setVelX(playerSpeed);
			}else if (key == KeyEvent.VK_LEFT){
				this.setVelX(-playerSpeed);
			}else if (key == KeyEvent.VK_UP){
				this.setVelY(-playerSpeed);
			}else if (key == KeyEvent.VK_DOWN){
				this.setVelY(playerSpeed);
			}else if (key == KeyEvent.VK_SPACE &&!isShooting){
				isShooting = true;
				Controller.addEntity(new Bullet(this.getX(),this.getY(), null));
			}else if (key == KeyEvent.VK_Q && !abilityTimerMap.get('q').isOnCooldown()){
				Controller.addEntity(new Bullet(this.getX(),this.getY(), "Piercing Bullet"));
				abilityTimerMap.get('q').resetTimer();
			}else if (key == KeyEvent.VK_W && !abilityTimerMap.get('w').isOnCooldown()){
				Controller.addEntity(new Bullet(this.getX(),this.getY(), "Scatter Shot"));
				Controller.addEntity(new Bullet(this.getX() - 20 ,this.getY(), "Scatter Shot"));
				Controller.addEntity(new Bullet(this.getX() + 20 ,this.getY(), "Scatter Shot"));
				abilityTimerMap.get('w').resetTimer();
			}else if (key == KeyEvent.VK_E && !abilityTimerMap.get('e').isOnCooldown()){
				for (int i = 0 ; i < Game.WIDTH * Game.SCALE - Game.GAME_SIDE_PANEL_WIDTH ; i = i + 50){
					Controller.addEntity(new Bullet(i, Game.HEIGHT * Game.SCALE, "Bullet Barrage"));
				}
				abilityTimerMap.get('e').resetTimer();
			}
		}
	}
	
	/**
	 * Game updates after certain keys are released
	 * @param e Key pressed by the user.
	 */
	@Override
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_RIGHT){
			this.setVelX(0);
		}else if(key == KeyEvent.VK_LEFT){
			this.setVelX(0);
		}else if(key == KeyEvent.VK_UP){
			this.setVelY(0);
		}else if(key == KeyEvent.VK_DOWN){
			this.setVelY(0);
		}else if (key == KeyEvent.VK_SPACE){
			isShooting = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}	
	
	/** Increases the player's health */
	public void healthUp(){
		if (playerHealthBar.getHealth() <=190){
			playerHealthBar.setHealth(playerHealthBar.getHealth() + 10);
		}else if (playerHealthBar.getHealth() >190 && playerHealthBar.getHealth() <200){
			playerHealthBar.setHealth(200);
		}
	}
	
	/** Increases the player's bullet speed */ 
	private void bulletSpeedUp(){
		if (Bullet.bulletSpeed != 40){
			Bullet.bulletSpeed = Bullet.bulletSpeed + 10;
			ScoreSystem.setBulletSpeedLevel(Integer.toString(Bullet.bulletSpeed / 10));
		}else {
			ScoreSystem.setBulletSpeedLevel("MAX SPEED");
		}
	}
	
	private void playerSpeedUp(){
		if (playerSpeed != maxPlayerSpeed){
			playerSpeed++;
			ScoreSystem.setPlayerSpeedLevel(Integer.toString(playerSpeed - initialPlayerSpeed + 1));
		}else {
			ScoreSystem.setPlayerSpeedLevel("MAX LEVEL");
		}
		
	}
	
	/**	Returns ability timer*/
	public static  AbilityTimer getAbilityTimer(Character abilityTimer){
		return abilityTimerMap.get(abilityTimer);
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
}
