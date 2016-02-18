package bg.uni.sofia.fmi.corejava.server_client.momchil.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server implements AutoCloseable {
	private static final String SERVER_PORT_MESSSAGE = "Server listening on port ";
	private static final String ERROR_COULD_NOT_CLOSE_THE_SERVER = "Could not close the server. ";
	public static final int SERVER_PORT = 4444;

	private ServerSocket serverSocket;
	private List<ClientServerConnection> clients = new LinkedList<ClientServerConnection>();

	public Server(int port) throws IOException {
		System.out.println(SERVER_PORT_MESSSAGE + port);
		// Open the port,if the port was not busy
		serverSocket = new ServerSocket(port);
	}

	public void start() throws IOException {
		while (true) {
			Socket socket = serverSocket.accept();
			
			ClientServerConnection clientThread = new ClientServerConnection(socket);
			clients.add(clientThread);

			clientThread.setDaemon(true);
			clientThread.start();
		}
	}

	@Override
	public void close() throws Exception {
		if (this.serverSocket != null) {
			try {
				this.serverSocket.close();
			} catch (IOException e) {
				System.err.println(ERROR_COULD_NOT_CLOSE_THE_SERVER + e.getMessage());
			}
		}

		for (ClientServerConnection client : this.clients) {
			client.stopThread();
		}

		this.clients.clear();
	}

	public static void main(String[] args) throws IOException {
		try (Server server = new Server(SERVER_PORT)) {
			server.start();
		} catch (Exception e) {
			System.err.println("An error has occured. " + e.getMessage());
		}
	}
}