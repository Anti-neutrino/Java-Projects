package bg.uni.sofia.fmi.corejava.server_client.momchil.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends ClientComponents {
	private static final String CONNECTED_TO_SERVER = " connect to server.";
	protected static final String CLIENT = "Client ";
	private static final String STOPPED = " stopped.";
	private static final String UNKNOWN_HOST = "Unknown host.";
	private static final String CONNECTION_FAILED = "Connection failed.";
	private static final String SOCKET_CANNOT_BE_CLOSED = "Socket can not be closed.";

	private Socket socket;

	public Socket getSocket() {
		return this.socket;
	}

	public Client(String remoteHost, int remotePort) {
		super(remoteHost, remotePort);
	}

	public void connect() {
		try {
			this.socket = new Socket(this.getRemoteHost(), this.getRemotePort());
		} catch (UnknownHostException e) {
			System.err.println(UNKNOWN_HOST);
		} catch (IOException e) {
			System.err.println(CONNECTION_FAILED);
		}

		System.out.println(CLIENT + this.getName() + CONNECTED_TO_SERVER);
	}

	public void sendRequest(PrintWriter writer, String message) {
		writer.println(message);
	}

	public boolean isConnected() {
		return this.socket.isConnected();
	}

	public void stopClient() {
		try {
			this.socket.close();
			System.out.println(CLIENT + this.getName() + STOPPED);
		} catch (IOException e) {
			System.err.println(SOCKET_CANNOT_BE_CLOSED);
		}
	}
}