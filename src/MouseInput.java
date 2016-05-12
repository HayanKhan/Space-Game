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
		if (Game.State == Game.STATE.MENU){
			mouseEventsForMenu(e);
		}else if (Game.State == Game.STATE.GAMEOVER){
			mouseEventsForGameOver(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	private void mouseEventsForMenu(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		//Play Button
		if (mx >= Game.WIDTH / 2 +120 && mx <= Game.WIDTH /2 +220){
			if (my>=150 && my<=200){
				Game.State = Game.STATE.GAME;
			}	
		}
		//Quit Button
		if (mx >= Game.WIDTH / 2 +120 && mx <= Game.WIDTH /2 +220){
			if (my>=350 && my<=400){
				System.exit(1);
			}	
		}
		
		//Volume Button
		if (mx>= Menu.getVolumeXPos()){
			if (my >=Menu.getVolumeYPos()){
				AudioPlayer.changeAudioState();
			}
		}
	}
	
	private void mouseEventsForGameOver(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		//fill here, must still implement
	}

}
