import java.awt.Graphics;
import java.awt.Rectangle;
import com.game.source.src.main.classes.AllyEntity;

import Audio.AudioPlayer;

public class Bullet extends GameObject implements AllyEntity{
	
	public static int bulletSpeed = 10;
	private boolean isPiercingBullet = false;
	private String bulletAbility;
	
	private Animation anim;

	private AudioPlayer bulletSound;
	private boolean loopContinously = false;
	
	/** 
	 * This constructor is used to create the bullet, given circuit characteristics.
	 * @param x Horizontal position of bullet on canvas
	 * @param y Vertical position of bullet on canvas
	 * @param tex Textures required to produce the bullet
	 */
	public Bullet(double x, double y, String bulletAbility){ 
		super(x,y);
		this.bulletAbility = bulletAbility;
		
		if (this.bulletAbility != null)
			if (this.bulletAbility.equals("Piercing Bullet"))
				isPiercingBullet = true;
		
		anim = new Animation(100, Textures.getMissleImage(0), Textures.getMissleImage(1), Textures.getMissleImage(2)); 
		bulletSound = new AudioPlayer("/SFX/bullet-sound.mp3");
		bulletSound.play(loopContinously);
	}
	
	/** Updates the bullet */
	public void tick(){
		y -= bulletSpeed;
		anim.runAnimation();
		
		if (y < - Textures.SPRITE_HEIGHT){
			Controller.removeEntity(this);
			if (bulletAbility == null)
				ScoreSystem.updateScoreSystem("Bullet Missed");
		}
	}
	
	/** Renders the bullet */
	public void render(Graphics g){
		g.drawImage(Textures.getMissleImage(0), (int)x,(int)y,null);
		anim.drawAnimation(g, x, y, 0);
	}
	
	/** Returns the collision boundaries of the bullet */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, Textures.SPRITE_WIDTH, Textures.SPRITE_HEIGHT);
	}
	
	/** Returns the horizontal position of the bullet */
	public double getX(){
		return x;
	}
	
	/** Returns the vertical position of the bullet */
	public double getY(){
		return y;
	}
	
	/** Checks if the bullet is a piercing bullet */
	public boolean isPiercingBullet(){
		return isPiercingBullet;
	}

		
	
}
