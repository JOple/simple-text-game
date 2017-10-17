package old.legacy2;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Utils {

	private Utils() { }
	
	private static Random random = new Random();
	
	public static <T> T pickRandomly(@SuppressWarnings("unchecked") T... items) {
		return items[random.nextInt(items.length)];
	}
	public static Image loadImage(String path) {
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			throw new RuntimeException("An error occured on loading the image", e);
		}
	}
	
	public static class Rx {
		
		public static final String MOVE = "(move|go|go to|transfer|transfer to)";
		public static final String LOOK = "(look( upon | at)?( the)?|look to|see|gaze|gaze upon|gaze to)";
		public static final String INSPECT = "(inspect|discover|examine|observe)";
	}

	public static class Img {

		private Img() { }

		public static final Image START = loadImage("res/S.png");
		public static final Image END_GOOD_1 = loadImage("res/EG1.png");
		public static final Image END_GOOD_2 = loadImage("res/EG2.png");
		public static final Image END_BAD_1 = loadImage("res/EB1.png");
		public static final Image END_BAD_2 = loadImage("res/EB2.png");
		public static final Image END_BAD_3 = loadImage("res/EB3.png");
		public static final Image QUADRANT_1_NORTH = loadImage("res/Q1N.png");
		public static final Image QUADRANT_1_SOUTH = loadImage("res/Q1S.png");
		public static final Image QUADRANT_1_EAST = loadImage("res/Q1E.png");
		public static final Image QUADRANT_1_WEST = loadImage("res/Q1W.png");
		public static final Image QUADRANT_2_NORTH = loadImage("res/Q2N.png");
		public static final Image QUADRANT_2_SOUTH = loadImage("res/Q2S.png");
		public static final Image QUADRANT_2_EAST = loadImage("res/Q2E.png");
		public static final Image QUADRANT_2_WEST = loadImage("res/Q2W.png");
		public static final Image QUADRANT_3_NORTH = loadImage("res/Q3N.png");
		public static final Image QUADRANT_3_SOUTH = loadImage("res/Q3S.png");
		public static final Image QUADRANT_3_EAST = loadImage("res/Q3E.png");
		public static final Image QUADRANT_3_WEST = loadImage("res/Q3W.png");
		public static final Image QUADRANT_4_NORTH = loadImage("res/Q4N.png");
		public static final Image QUADRANT_4_SOUTH = loadImage("res/Q4S.png");
		public static final Image QUADRANT_4_EAST = loadImage("res/Q4E.png");
		public static final Image QUADRANT_4_WEST = loadImage("res/Q4W.png");
	}
}
