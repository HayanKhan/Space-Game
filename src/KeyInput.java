import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
		private Game game;
		
		public KeyInput(Game game){
			this.game = game;
		}
		/** Update that occurs after pressing a key */
		public void keyPressed(KeyEvent e){ 
			game.keyPressed(e);
		}
		/** Update that occurs after releasing a key */
		public void keyReleased(KeyEvent e){
			game.keyReleased(e);
		}
}
