import com.game.source.src.main.classes.AllyEntity;
import com.game.source.src.main.classes.EnemyEntity;
import com.game.source.src.main.classes.UpgradeEntity;

public class Physics {
	//This class handles collisions
	/** Update that occurs after an EntityA object collides with an EntityB object */
	public static boolean Collision(AllyEntity enta, EnemyEntity entb){
			if (enta.getBounds().intersects(entb.getBounds())){
				return true;
			}
		return false;
	}
	/** Update that occurs after an EntityA object collides with an EntityC object */
	public static boolean Collision(AllyEntity enta, UpgradeEntity entc){
		if (enta.getBounds().intersects(entc.getBounds())){
			return true;
		}
	return false;
}
	/** Update that occurs after an EntityB object collides with an EntityA object */
	public static boolean Collision(EnemyEntity entb, AllyEntity enta){
		if (entb.getBounds().intersects(enta.getBounds())){
			return true;
		}
		return false;
	}
}
