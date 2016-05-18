import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.game.source.src.main.classes.AllyEntity;
import com.game.source.src.main.classes.EnemyEntity;
import com.game.source.src.main.classes.UpgradeEntity;

public class Controller {
	//Linked lists made to group different type of entities in seperate linked lists.
	private static LinkedList<AllyEntity> ea = new LinkedList<AllyEntity>();
	private static LinkedList<EnemyEntity> eb = new LinkedList<EnemyEntity>();
	private static LinkedList<UpgradeEntity> ec = new LinkedList<UpgradeEntity>();
	private AllyEntity enta;
	private EnemyEntity entb;
	private UpgradeEntity entc;
	//Required classes/variables
	Random r = new Random();
	private static int type;

	/**
	 * Enemies alive are stored in the EntityB linked list
	 * @param enemy_count The number of enemies alive in the current wave
	 */
	public void createEnemy(int enemy_count){
		for (int i =0 ; i <enemy_count ; i++){
			addEntity(new Enemy(r.nextInt(Game.HEIGHT * Game.SCALE) - Textures.SPRITE_HEIGHT, -10));
		}
	}
	
	/**
	 * Creates an upgrade after an enemy entity is removed
	 * @param x X position of where upgrade is created
	 * @param y Y position of where upgrade is created
	 */
	public static void createUpgrade(double x, double y){
		Random rgen = new Random();
		if (rgen.nextInt(4) == 0){
			if (rgen.nextInt(2) == 0){
				type = 1;
			}else{
				type = 2;
			}
			addEntity(new Upgrades(type, x, y));
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
	public static void addEntity(AllyEntity block){
		ea.add(block);
	}
	/** Removes an object of class EntityA from an EntityA linked list */
	public static void removeEntity(AllyEntity block){
		ea.remove(block);
	}
	/** Adds an object of class EntityB to an EntityB linked list */
	public static void addEntity(EnemyEntity block){
		eb.add(block);
	}
	/** Removes an object of class EntityB from an EntityB linked list */
	public static void removeEntity(EnemyEntity block){
		eb.remove(block);
	}
	/** Adds an object of class EntityC to an EntityC linked list */
	public static void addEntity(UpgradeEntity block){
		ec.add(block);
	}
	/** Removes an object of class EntityC from an EntityC linked list */
	public static void removeEntity(UpgradeEntity block){
		ec.remove(block);
	}
	/** Returns an EntityA linked list */
	public static LinkedList<AllyEntity> getEntityA(){
		return ea;
	}
	/** Returns an EntityB linked list */
	public static LinkedList<EnemyEntity> getEntityB(){
		return eb;
	}
	/** Returns an EntityC linked list */
	public static LinkedList<UpgradeEntity> getEntityC(){
		return ec;
	}
}
