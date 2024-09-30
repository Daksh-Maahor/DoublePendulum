package pendulum.utils;

import java.awt.Color;

public class Utils {
	
	public static Color getRandomColor(boolean light) {
		if (light) {
			return new Color((int) (Math.random() * 128) + 128, (int) (Math.random() * 128) + 128, (int) (Math.random() * 128) + 128);
		} else {
			return new Color((int) (Math.random() * 128), (int) (Math.random() * 128), (int) (Math.random() * 128));
		}
	}

}
