import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	private BufferedImage image;
	/**
	 * Returns a BufferedImage object from a given directory
	 * @param path String containing the path of the directory
	 * @return	the image given at the desired path
	 */
	public BufferedImage loadImage(String path) throws IOException{
		image = ImageIO.read(getClass().getResource(path));
		return image;
	}
}
