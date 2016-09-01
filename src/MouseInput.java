import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Audio.AudioPlayer;

public class MouseInput implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		Rectangle cursorRectangle = new Rectangle(mx, my , 1, 1);
		
		if (Game.State == Game.STATE.MENU){
			mouseEventsForMenu(cursorRectangle);
		}else if (Game.State == Game.STATE.GAMEOVER){
			mouseEventsForGameOver(cursorRectangle);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	private void mouseEventsForMenu(Rectangle cursorRectangle){
		
		//Play Button
		if (cursorRectangle.intersects(Menu.playButton)){
			Game.State = Game.STATE.GAME;
		}
		
		//Quit Button
		if (cursorRectangle.intersects(Menu.quitButton)){
			System.exit(1);
		}
		
		//Volume Button
		if (cursorRectangle.intersects(Menu.volumeButton)){
			AudioPlayer.changeAudioState();
		}
	}
	
	private void mouseEventsForGameOver(Rectangle cursorRectangle){
		
		//Quit Button
		if (cursorRectangle.intersects(GameOver.exitButton)){
			System.exit(1);
		}
	}
}
