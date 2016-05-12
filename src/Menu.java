import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Audio.AudioPlayer;

public class Menu {
	
	//Class properties
	public Rectangle playButton = new Rectangle(Game.WIDTH/2 + 120,150,100,50);
	public Rectangle helpButton = new Rectangle(Game.WIDTH/2 + 120,250,100,50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH/2 + 120,350,100,50);
	
	private static BufferedImage volumeButtonOn = Textures.volumeButtonOn;
	private static BufferedImage volumeButtonOff = Textures.volumeButtonOff;
	private static int volumeXPos = (Game.WIDTH * Game.SCALE) - volumeButtonOn.getWidth();
	private static int volumeYPos = Game.HEIGHT * Game.SCALE -32;
	
	/** Renders the menu state and the audio button */
	public void render(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;	
		
		Font fnt0 = new Font("arial",Font.BOLD,50);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("SPACE GAME", Game.WIDTH /2,50);
		
		Font fnt1 = new Font("Arial",Font.BOLD,15);
		g.setFont(fnt1);
		g.drawString("Made By: Hayan Khan", Game.WIDTH - 65, 75);
	
		Font fnt2 = new Font("arial",Font.BOLD,30);
		g.setFont(fnt2);
		g.drawString("PLAY",playButton.x + 10,playButton.y + 35);
		g2d.draw(playButton);
		g.drawString("HELP",helpButton.x + 10,helpButton.y + 35);
		g2d.draw(helpButton);
		g.drawString("QUIT",quitButton.x + 10,quitButton.y + 35);
		g2d.draw(quitButton);
		
		if (AudioPlayer.getAudioState()){
			g.drawImage(volumeButtonOn, volumeXPos, volumeYPos, null);
		} else if (!AudioPlayer.getAudioState()){
			g.drawImage(volumeButtonOff, volumeXPos, volumeYPos, null);
			
		}
		
	}
	/** @return The volume button's horizontal position */
	public static int getVolumeXPos(){
		return volumeXPos;
	}
	/** @return The volume button's vertical position */
	public static int getVolumeYPos(){
		return volumeYPos;
	}
}
