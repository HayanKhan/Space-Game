import java.awt.Rectangle;

public class GameObject {
	
	public double x, y;
	/**
	 * Main constructor of every GameObject object
	 * @param x Horizontal position of object
	 * @param y Vertical position of object
	 */
	public 	GameObject(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/**Returns the bounds of the GameObject object */
	public Rectangle getBounds(int width, int height){
		return new Rectangle((int)x, (int)y, width, height);
	}
}
