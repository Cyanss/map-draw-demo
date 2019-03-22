package cn.bluethink.util;

import java.awt.Color;
import java.util.Random;

public class ColorUtil {
	private static Random random = new Random();
	public static Color generateRandomColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
	}
	
	public static String encode(Color color) {
		return colorToHexValue(color);
	}
	public static Color decode(String code) {
		StringBuffer stringBuffer =new StringBuffer();
		stringBuffer.append("#").append(code);
		return Color.decode(stringBuffer.toString());
	}
	
	private static String colorToHexValue(Color color) {
		return intToHexValue(color.getRed()) + intToHexValue(color.getGreen()) + intToHexValue(color.getBlue());
	}
	 
	private static String intToHexValue(int number) {
		String result = Integer.toHexString(number & 0xff);
		while (result.length() < 2) {
			result = "0" + result;
		}
		return result.toUpperCase();
	}
}
