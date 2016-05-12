import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage ss;
	/**
	 * SpriteSheet constructor
	 * @param image Image of the spritesheet
	 */
	public SpriteSheet(BufferedImage ss){
		this.ss= ss;
	}
	/**
	 * Returns the image at a (col,row) point on the spritesheet extending a specific width and a height 
	 * @param col Horizontal cell 
	 * @param row Vertical cell
	 * @param width Width in pixels 
	 * @param height Height in pixels
	 * @return The image at the corresponding column and row 
	 */
	public BufferedImage grabImage(int col, int row, int width, int height){
		BufferedImage img = ss.getSubimage(col *32 -32, row* 32 - 32 , width, height); 
		return img;
	}
}
