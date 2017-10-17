package old;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utils {

	@SafeVarargs
	public static <T> T pickRand(T... vals) {
		return vals[(int) (Math.random() * vals.length)];
	}
	
	public static Image loadImage(String path) {
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			throw new RuntimeException("An error occured on loading the image", e);
		}
	}
}
