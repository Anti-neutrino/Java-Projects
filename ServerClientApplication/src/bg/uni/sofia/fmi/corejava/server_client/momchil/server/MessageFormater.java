package bg.uni.sofia.fmi.corejava.server_client.momchil.server;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MessageFormater {
	private static final SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS");

	public static String formatMessage(String name, String message) {
		StringBuilder builder = new StringBuilder();

		Calendar now = Calendar.getInstance();
		String nowAsString = sdFormat.format(now.getTime());
		builder.append(nowAsString);
		builder.append(" " + name);
		builder.append(": " + message);
		
		return builder.toString();
	}

}
