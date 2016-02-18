package bg.uni.sofia.fmi.corejava.server_client.momchil.client;

public class ClientComponents {
	private String remoteHost;
	private int remotePort;
	private String name;

	public ClientComponents(String remoteHost, int remotePort) {
		this.remoteHost = remoteHost;
		this.remotePort = remotePort;
		this.name = GenerateName.getName();
	}

	public String getRemoteHost() {
		return this.remoteHost;
	}

	public int getRemotePort() {
		return this.remotePort;
	}

	public String getName() {
		return this.name;
	}
}
