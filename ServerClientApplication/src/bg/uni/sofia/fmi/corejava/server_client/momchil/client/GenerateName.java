package bg.uni.sofia.fmi.corejava.server_client.momchil.client;

import java.util.Random;

public class GenerateName {
	private final static String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
	private final static Random rand = new Random();

	public static String getName() {
		StringBuilder builder = new StringBuilder();

		int length = rand.nextInt(5) + 5;
		for (int i = 0; i < length; i++) {
			builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
		}

		return builder.toString();
	}
}