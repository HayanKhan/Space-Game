import com.game.source.src.main.classes.EntityA;
import com.game.source.src.main.classes.EntityB;
import com.game.source.src.main.classes.EntityC;

public class Physics {
	//This class handles collisions
	/** Update that occurs after an EntityA object collides with an EntityB object */
	public static boolean Collision(EntityA enta, EntityB entb){
			if (enta.getBounds().intersects(entb.getBounds())){
				return true;
			}
		return false;
	}
	/** Update that occurs after an EntityA object collides with an EntityC object */
	public static boolean Collision(EntityA enta, EntityC entc){
		if (enta.getBounds().intersects(entc.getBounds())){
			return true;
		}
	return false;
}
	/** Update that occurs after an EntityB object collides with an EntityA object */
	public static boolean Collision(EntityB entb, EntityA enta){
		if (entb.getBounds().intersects(enta.getBounds())){
			return true;
		}
		return false;
	}
}
