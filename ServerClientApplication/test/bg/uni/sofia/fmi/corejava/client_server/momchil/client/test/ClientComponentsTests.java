package bg.uni.sofia.fmi.corejava.client_server.momchil.client.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import bg.uni.sofia.fmi.corejava.server_client.momchil.client.ClientComponents;

public class ClientComponentsTests {
	private ClientComponents client;

	@Before
	public void setup() throws IOException, InterruptedException {
		this.client = new ClientComponents("localhost", 4444);
	}

	@Test
	public void test_getRemoteHost() {
		assertEquals("localhost", this.client.getRemoteHost());
	}

	@Test
	public void test_getRemotePort() {
		assertEquals(4444, this.client.getRemotePort());
	}

	@Test
	public void test_getName() {
		assertNotEquals(null, this.client.getName());
	}
}
