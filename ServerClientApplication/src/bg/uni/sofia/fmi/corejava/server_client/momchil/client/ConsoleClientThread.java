package bg.uni.sofia.fmi.corejava.server_client.momchil.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import java.util.Scanner;

public class ConsoleClientThread extends Client implements Runnable {
	private static final String EXIT = "Exit";
	private static final String ERROR_HAS_OCCURED = "An error has ocured. ";

	public ConsoleClientThread(String remoteHost, int remotePort) {
		super(remoteHost, remotePort);
	}

	private void sendMessages(Scanner sc, PrintWriter out) throws IOException {
		String line;

		System.out.print(this.getName() + ": ");
		while ((line = sc.nextLine()) != null) {
			if (line.equals(EXIT)) {
				break;
			}

			this.sendRequest(out, line);
			out.flush();

			System.out.print(this.getName() + ": ");
		}
	}

	@Override
	public void run() {
		try (PrintWriter out = new PrintWriter(this.getSocket().getOutputStream());
				Scanner sc = new Scanner(System.in)) {
			// Send the name
			this.sendRequest(out, this.getName());

			sendMessages(sc, out);

		} catch (SocketException e) {
			while (!this.getSocket().isConnected()) {
				this.connect();
			}
		} catch (IOException e) {
			System.err.println(ERROR_HAS_OCCURED + e.getMessage());
		}

		this.stopClient();
	}
}