import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.game.source.src.main.classes.EntityA;
import com.game.source.src.main.classes.EntityB;
import com.game.source.src.main.classes.EntityC;

public class Controller {
	//Linked lists made to group different type of entities in seperate linked lists.
	private LinkedList<EntityA> ea = new LinkedList<EntityA>();
	private LinkedList<EntityB> eb = new LinkedList<EntityB>();
	private LinkedList<EntityC> ec = new LinkedList<EntityC>();
	
	EntityA enta;
	EntityB entb;
	EntityC entc;
	//Required Classes
	private Game game;
	private Textures tex;
	Random r = new Random();
	
	private int type;
	
	/**
	 * This constructor is used to get the necessary imports to be able to use different class properties.
	 * @param tex Imports the textures, so images can be used
	 * @param game Imports the game class.
	 */
	public Controller(Textures tex, Game game){
		this.tex = tex;	
		this.game = game;
	}
	
	/**
	 * Enemies alive are stored in the EntityB linked list
	 * @param enemy_count The number of enemies alive in the current wave
	 */
	public void createEnemy(int enemy_count){
		for (int i =0 ; i <enemy_count ; i++){
			addEntity(new Enemy(r.nextInt(Game.HEIGHT * Game.SCALE) - Textures.SPRITE_HEIGHT, -10 , tex,game,this));
		}
	}
	
	/**
	 * Creates an upgrade after an enemy entity is removed
	 * @param x X position of where upgrade is created
	 * @param y Y position of where upgrade is created
	 */
	public void createUpgrade(double x, double y){
		Random rgen = new Random();
		if (rgen.nextInt(4) == 0){
			if (rgen.nextInt(2) == 0){
				type = 1;
			}else{
				type = 2;
			}
			addEntity(new Upgrades(type, x, y, tex, this));
		}
	}
	/** Updates for all the different entities in the game */
	public void tick(){
		//ENTITYA CLASS TICKING
		for (int i = 0 ; i <  ea.size(); i++){
			enta = ea.get(i);
			enta.tick();
		}
		//ENTITYB CLASS TICKING
		for (int i = 0 ; i <  eb.size(); i++){
			entb = eb.get(i);
			entb.tick();
		}
		//ENTITYC CLASS TICKING
		for (int i = 0 ; i <  ec.size(); i++){
			entc = ec.get(i);
			entc.tick();
		}
	}
	/** Renders for all the different entities in the game */
	public void render(Graphics g){
		//ENTITYA CLASS RENDERING
		for (int i = 0 ; i <  ea.size(); i++){
			enta = ea.get(i);
			enta.render(g);
		}
		//ENTITYB CLASS RENDERING
		for (int i = 0 ; i <  eb.size(); i++){
			entb = eb.get(i);
			entb.render(g);
		}
		//ENTITYC CLASS RENDERING
		for (int i = 0 ; i <  ec.size(); i++){
			entc = ec.get(i);
			entc.render(g);
		}
	}
	/** Adds an object of class EntityA to an EntityA linked list */
	public void addEntity(EntityA block){
		ea.add(block);
	}
	/** Removes an object of class EntityA from an EntityA linked list */
	public void removeEntity(EntityA block){
		ea.remove(block);
	}
	/** Adds an object of class EntityB to an EntityB linked list */
	public void addEntity(EntityB block){
		eb.add(block);
	}
	/** Removes an object of class EntityB from an EntityB linked list */
	public void removeEntity(EntityB block){
		eb.remove(block);
	}
	/** Adds an object of class EntityC to an EntityC linked list */
	public void addEntity(EntityC block){
		ec.add(block);
	}
	/** Removes an object of class EntityC from an EntityC linked list */
	public void removeEntity(EntityC block){
		ec.remove(block);
	}
	/** Returns an EntityA linked list */
	public LinkedList<EntityA> getEntityA(){
		return ea;
	}
	/** Returns an EntityB linked list */
	public LinkedList<EntityB> getEntityB(){
		return eb;
	}
	/** Returns an EntityC linked list */
	public LinkedList<EntityC> getEntityC(){
		return ec;
	}
}
