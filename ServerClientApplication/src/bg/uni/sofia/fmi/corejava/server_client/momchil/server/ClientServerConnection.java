package bg.uni.sofia.fmi.corejava.server_client.momchil.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientServerConnection extends Thread {
	private static final String UNEXISTING_NAME = "Client was not send his/her name.";
	private static final String CLIENT = "Client ";
	private static final String CONNECTED = " connected";
	private static final String DISCONNECTED = " disconnected.";
	private static final String PATH_TO_THE_LOCAL_FILE = "resources\\LocalServerFile\\clientsDB.txt";
	private static final String PATH_TO_THE_SEPARATE_FILES = "resources\\SeparateClientFiles\\";
	private static final String FILE_EXTENSION = ".txt";
	private static final String CANNOT_READ_THE_MESSAGE = "Can not read the message.";

	private Socket socket;
	private volatile boolean run;
	private BufferedReader reader;

	public ClientServerConnection(Socket socket) throws IOException {
		this.socket = socket;
		this.run = true;
		this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
	}

	private String readMessage() {
		String request = null;
		try {
			request = this.reader.readLine();
		} catch (IOException e) {
			System.err.println(UNEXISTING_NAME);
		}

		return request;
	}

	private void logMessageInFile(String fileName, String message) throws IOException {
		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}

		// Mode to append messages to the end of the file.
		FileWriter fileWriter = new FileWriter(file, true);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		synchronized (bufferedWriter) {
			bufferedWriter.write(message + "\n");
		}
		bufferedWriter.close();
	}

	@Override
	public void run() {
		try {
			// Read name
			String name = this.readMessage();

			System.out.println(CLIENT + name + CONNECTED);

			while (run) {
				String request = this.readMessage();

				if (request == null) {
					// The connection is broken. Exit the thread.
					break;
				}

				// Format the message
				String message = MessageFormater.formatMessage(name, request);

				// Log message in local file to the server
				this.logMessageInFile(PATH_TO_THE_LOCAL_FILE, message);

				// Log message in file for every client
				this.logMessageInFile(PATH_TO_THE_SEPARATE_FILES + name + FILE_EXTENSION, message);
			}

			System.out.println(CLIENT + name + DISCONNECTED);
		} catch (IOException e) {
			System.err.println(CANNOT_READ_THE_MESSAGE);
		}
	}

	public void stopThread() throws IOException {
		run = false;
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				// Nothing that we can do
			}
		}
	}
}