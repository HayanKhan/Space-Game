import java.awt.Canvas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import Audio.AudioPlayer;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	//Frame dimensions
	public static final int WIDTH = 600;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public static final int GAME_SIDE_PANEL_WIDTH = 305;

	public final String TITLE = "2D Space Game";
	
	private int enemy_count = 5;
	
	private Menu menu;
	private GameOver gameOver;
	
	public static STATE State = STATE.MENU; 
	//background image
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);

	private Thread thread;	
	private boolean running = false;
	private Controller controller;
	private Player player;
	private ScoreSystem scoreSystem;

	private AudioPlayer bgMusic;
	
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
		controller = new Controller();
		player = new Player(200, 200);
		
		menu = new Menu();
		gameOver = new GameOver();
		
		scoreSystem = new ScoreSystem();
		
		this.addKeyListener(player); 
		this.addMouseListener(new MouseInput());
		controller.createEnemy(enemy_count);
	
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
			player.tick();
			controller.tick();
			
			if (Enemy.getEnemyKilled() >= enemy_count){
				ScoreSystem.setWaveNum(ScoreSystem.getWaveNum() + 1);
				enemy_count +=2;
				Enemy.setEnemyKilled(0);
				controller.createEnemy(enemy_count);
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
		
		player.render(g);
		controller.render(g);
		scoreSystem.render(g); 
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
		boolean loopContinously = true;
		
		if (!AudioPlayer.getAudioState())
			bgMusic.stop();
		else if (AudioPlayer.getAudioState())
			bgMusic.play(loopContinously);
	}
}

