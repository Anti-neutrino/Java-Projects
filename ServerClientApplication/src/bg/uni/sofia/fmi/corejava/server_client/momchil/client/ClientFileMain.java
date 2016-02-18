package bg.uni.sofia.fmi.corejava.server_client.momchil.client;

import java.io.IOException;
import java.util.Scanner;

public class ClientFileMain {
	private static final String NUMBER_OF_CLIENTS = "Enter number of clinets: ";
	private static final String LOCALHOST = "localhost";

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner console = new Scanner(System.in);
		System.out.print(NUMBER_OF_CLIENTS);
		int numberOfClients = console.nextInt();
		for (int i = 0; i < numberOfClients; i++) {
			ClientFile client = new ClientFile(LOCALHOST, 4444);
			client.connect();
			client.logMessage();
		}

		console.close();
	}
}