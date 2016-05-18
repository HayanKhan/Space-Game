import java.awt.Graphics;
import java.awt.Rectangle;
import com.game.source.src.main.classes.AllyEntity;

import Audio.AudioPlayer;


public class Bullet extends GameObject implements AllyEntity{
	//Bullet properties
	public static int bulletSpeed = 10;
	//Game properties
	Animation anim;
	//Bullet sound properties
	private AudioPlayer bulletSound;
	private boolean loopContinously = false;
	/** 
	 * This constructor is used to create the bullet, given circuit characteristics.
	 * @param x Horizontal position of bullet on canvas
	 * @param y Vertical position of bullet on canvas
	 * @param tex Textures required to produce the bullet
	 */
	public Bullet(double x, double y){
		super(x,y);
		anim = new Animation(100, Textures.getMissleImage(0), Textures.getMissleImage(1), Textures.getMissleImage(2)); 
		bulletSound = new AudioPlayer("/SFX/bullet-sound.mp3");
		bulletSound.play(loopContinously);
	}
	/** Updates the bullet */
	public void tick(){
		y -= bulletSpeed;
		anim.runAnimation();
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


		
	
}
