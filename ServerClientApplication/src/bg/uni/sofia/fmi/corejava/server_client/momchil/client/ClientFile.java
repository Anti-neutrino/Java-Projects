package bg.uni.sofia.fmi.corejava.server_client.momchil.client;

public class ClientFile {
	private FileClientThread client;
	private Thread thread;

	public ClientFile( String remoteHost, int remotePort) {
		this.client = new FileClientThread(remoteHost, remotePort);
		this.thread = new Thread(client);
	}

	public void connect() {
		this.client.connect();
	}

	public boolean isConnected() {
		return this.client.getSocket().isConnected();
	}

	public void logMessage() {
		this.thread.start();
	}
}
