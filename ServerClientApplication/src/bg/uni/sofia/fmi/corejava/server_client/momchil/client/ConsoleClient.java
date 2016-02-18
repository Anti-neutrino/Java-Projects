package bg.uni.sofia.fmi.corejava.server_client.momchil.client;

public class ConsoleClient {
	private ConsoleClientThread client;
	private Thread clientThread;

	public ConsoleClient(String remoteHost, int remotePort) {
		this.client = new ConsoleClientThread(remoteHost, remotePort);
		this.clientThread = new Thread(client);
	}

	public void connect() {
		this.client.connect();
	}
	
	public boolean isConnected() {
		return this.client.getSocket().isConnected();
	}

	public void logMessage() {
		this.clientThread.start();
	}
}