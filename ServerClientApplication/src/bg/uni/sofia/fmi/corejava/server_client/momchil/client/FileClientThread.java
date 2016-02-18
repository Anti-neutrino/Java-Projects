package bg.uni.sofia.fmi.corejava.server_client.momchil.client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;

public class FileClientThread extends Client implements Runnable {
	private static final String PATH_TO_FILE = "resources\\TextFile\\movies-mpaa.txt";
	private static final String FILE_WAS_NOT_FOUND = "File was not found!";
	private static final String ERROR_HAS_OCCURED = "An error has occured";

	private BufferedReader reader;

	public FileClientThread(String remoteHost, int remotePort) {
		super(remoteHost, remotePort);
		try {
			this.reader = new BufferedReader(new FileReader(PATH_TO_FILE));
		} catch (FileNotFoundException e) {
			System.err.println(FILE_WAS_NOT_FOUND);
		}
	}

	private String getLine() throws IOException {
		return this.reader.readLine();
	}

	public void run() {
		try (PrintWriter out = new PrintWriter(this.getSocket().getOutputStream())) {

			// Send name
			this.sendRequest(out, this.getName());

			// Send messages
			fileInput(out);
		} catch (SocketException e) {
			while (!this.getSocket().isConnected()) {
				this.connect();
			}
		} catch (IOException e) {
			System.err.println(ERROR_HAS_OCCURED + e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.stopClient();
	}

	private void fileInput(PrintWriter out) throws IOException, InterruptedException {
		String line = null;
		while ((line = this.getLine()) != null) {
			this.sendRequest(out, line);
		}

		this.reader.close();
	}
}