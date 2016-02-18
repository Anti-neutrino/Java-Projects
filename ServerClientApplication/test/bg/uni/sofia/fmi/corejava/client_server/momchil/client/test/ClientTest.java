package bg.uni.sofia.fmi.corejava.client_server.momchil.client.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bg.uni.sofia.fmi.corejava.server_client.momchil.client.Client;
import bg.uni.sofia.fmi.corejava.server_client.momchil.server.Server;

public class ClientTest {
	private Client client;
	private Server server ;
	
	public ClientTest() throws IOException{
		this.server=new Server(4444);
	}

	@Before
	public void setup() throws UnknownHostException, IOException {
		client = new Client("localhost", 4444);
		client.connect();
	}

	@Test
	public void test_socketPort() throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost",4444);
		assertEquals(socket.getPort(), client.getRemotePort());
	}
	
	@Test
	public void test_socketHost() throws UnknownHostException, IOException{
		Socket socket=new Socket("localhost",4444);
		assertEquals(socket.getInetAddress().getHostName(),client.getRemoteHost());
	}
	
	@Test
	public void test_Connect(){
		assertEquals(client.getSocket().isConnected(), true);
	}
	
	@Test 
	public void test_isConnected(){
		assertEquals(client.isConnected(),true);
	}
	
	@Test
	public void test_stopClient() throws IOException{
		client.stopClient();
		assertEquals(this.client.getSocket().isClosed(),true);
	}
	
	@After
	public void exit() throws Exception{
		server.close();
	}
}