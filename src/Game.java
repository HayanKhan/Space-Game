import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import Audio.AudioPlayer;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	//Constant application window dimensions
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12*9;
	public static final int SCALE = 2;
	//Game title
	public final String TITLE = "2D Space Game";
	//Player properties
	private boolean isShooting = false;
	private boolean running = false;
	private Thread thread;	
	//Enemy statistics
	private int enemy_count = 5;
	//Game states
	private Menu menu;
	private GameOver gameOver;
	//Shows the current state of the game
	public static STATE State = STATE.MENU; //do this using a class getters and setters enum class
	//Types of images used in game
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	//Class properties
	private Controller c;
	private Player p;
	private static ScoreSystem scoreSystem;
	//Sound player and features
	private AudioPlayer bgMusic;
	private boolean loopContinously = true;
	
	/** Types of states */
	public static enum STATE{
		
		MENU,
		GAME,
		GAMEOVER
	};
	
	/**
	 * Main function that occurs when application is ran.
	 * The game starts and the frame is made
	 */
	public static void main (String []args){
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH* SCALE, HEIGHT*SCALE));
		game.setMaximumSize(new Dimension(WIDTH* SCALE, HEIGHT*SCALE));
		game.setMinimumSize(new Dimension(WIDTH* SCALE, HEIGHT*SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
	/** A thread of this class is made if the game is not already running */ 
	private synchronized void start(){ 
		if (running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start(); 
	}
	
	/** Implementation of main game loop **/
	public void run(){
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running){ //main game loop
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1){
				tick(); //makes it so it ticks 60times/s
				updates++;
				delta --;
			}
			render();
			frames++; // FPS
			
			if(System.currentTimeMillis() - timer > 1000){
				timer +=1000;
				System.out.println(updates + " Ticks, Fps :" + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	/**
	 * Initializes features in the game
	 * Loads the textures, sprites, controller, player and game states
	 * Enables score system, sound system and mouse/key events 
	 */
	public void init(){
		Textures.init();
		c = new Controller();
		p = new Player(200, 200);
		
		menu = new Menu();
		scoreSystem = new ScoreSystem();
		gameOver = new GameOver(scoreSystem);
		
		this.addKeyListener(new KeyInput(this)); 
		this.addMouseListener(new MouseInput());
		c.createEnemy(enemy_count);
	
		bgMusic = new AudioPlayer("/Music/Once-Around-the-Solar-System.mp3");
	}
	
	
	/** Processes after the game loop is terminated */
	private synchronized void stop(){
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	/** Updates the whole game */
	private void tick(){
		if (State == STATE.GAME){
			p.tick();
			c.tick();
			if (Enemy.getEnemyKilled() >= enemy_count){
				enemy_count +=2;
				Enemy.setEnemyKilled(0);
				c.createEnemy(enemy_count);
			}
		}
	}
	/** Renders the menu, play screen, game over screen, monsters, player, the score system and etc. */
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
			createBufferStrategy(3); //3 buffers
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		////////////////////Draws stuff onto canvas between these two series of lines
		g.drawImage(image, 0, 0, getWidth(),getHeight(),this); //background
		//Checks what the current state is, and renders that state
		if (State == STATE.GAME){
			renderGameObjects(g);
		} else if (State == STATE.MENU){
			renderMenuObjects(g);
		} else if (State == STATE.GAMEOVER){
			renderGameOverObjects(g);
		}
		///////////////////
		
		g.dispose();
		bs.show();	
	}
	
	/** Game updates after certain keys are pressed */
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if (State == STATE.GAME){
			if (key == KeyEvent.VK_RIGHT){
				p.setVelX(5);
			}else if(key == KeyEvent.VK_LEFT){
				p.setVelX(-5);
			}else if(key == KeyEvent.VK_UP){
				p.setVelY(-5);
			}else if(key == KeyEvent.VK_DOWN){
				p.setVelY(5);
			}else if (key == KeyEvent.VK_SPACE &&!isShooting){
				isShooting = true;
				Controller.addEntity(new Bullet(p.getX(),p.getY()));
			}
		}
	}
	/**
	 * Game updates after certain keys are released
	 * @param e Key pressed by the user.
	 */
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_RIGHT){
			p.setVelX(0);
		}else if(key == KeyEvent.VK_LEFT){
			p.setVelX(0);
		}else if(key == KeyEvent.VK_UP){
			p.setVelY(0);
		}else if(key == KeyEvent.VK_DOWN){
			p.setVelY(0);
		}else if (key == KeyEvent.VK_SPACE){
			isShooting = false;
		}
	}
	/**
	 * This renders all the objects in the menu state.
	 * @param g The graphics program, that includes graphics applications such as drawing, coloring etc.
	 */
	private void renderMenuObjects(Graphics g){
		menu.render(g);
		setAudioButton();
	}
	/**
	 * This renders all the objects in the game play state.
	 * @param g The graphics program, that includes graphics applications such as drawing, coloring etc.
	 */
	private void renderGameObjects(Graphics g){
		g.drawImage(Textures.getBackground(),0,0,null);
		
		p.render(g);
		c.render(g);
		
		scoreSystem.render(g);
		
		g.setColor(Color.GRAY);
		g.fillRect(5,5,200,50);
		
		g.setColor(Color.RED);
		g.fillRect(5,5,p.getHealth(),50);
		
		g.setColor(Color.WHITE);
		g.drawRect(5,5,200,50);
	}
	/**
	 * This renders all the objects in the game over state.
	 * @param g The graphics program, that includes graphics applications such as drawing, coloring etc.
	 */
	private void renderGameOverObjects(Graphics g){
		gameOver.render(g);
	}

	/** The audio begins playing or stops depending on the previous state of the audio player */
	private void setAudioButton(){
		if (!AudioPlayer.getAudioState())
			bgMusic.stop();
		else if (AudioPlayer.getAudioState())
			bgMusic.play(loopContinously);
	}

	/** Returns the score system */
	public static ScoreSystem getScoreSystem(){
		return scoreSystem;
	}

}

