package bg.uni.sofia.fmi.corejava.server_client.momchil.client;

import java.io.IOException;
import java.net.UnknownHostException;

public class ClientConsoleMain {
	public static void main(String[] args) throws UnknownHostException, IOException {
		ConsoleClient client = new ConsoleClient("localhost", 4444);
		client.connect();
		client.logMessage();
	}
}