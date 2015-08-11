package org.mydomain.academy.db.h2;

import org.h2.tools.Server;

import java.sql.SQLException;

public class H2ConsoleServer {

	private int port;
	private int webPort;
	private String host;
	private String baseDir;

	public H2ConsoleServer(int port, int webPort, String host, String baseDir) {
		this.port = port;
		this.webPort = webPort;
		this.host = host;
		this.baseDir = baseDir;
	}

	public H2ConsoleServer() {
		this.port = 64000;
		this.webPort = 64001;
		this.host = "localhost";
		this.baseDir = "./H2_Base";
	}

	public boolean startEmbeddedDb() {
		return false;
	}

	public boolean startServer() {
		try {
			System.out.println("Starting H2 server...");
			System.out.println("DB: tcp://" + host + ":" + port + "/" + baseDir);
			System.out.println("Web port: " + webPort);
			Server.createTcpServer(
					"-tcpAllowOthers",
					"-webPort", Integer.toString(webPort),
					"-tcpPort", Integer.toString(port),
					"-baseDir", baseDir).start();
			System.out.println(H2ServerMessages.H2_SUCCESS_START_MESSAGE);
			return true;
		} catch (SQLException | IllegalArgumentException e) {
			if (e instanceof SQLException) {
				System.out.println(e);
				System.out.println(port + H2ServerMessages.H2_BUSY_PORT_MESSAGE);
			}
			if (e instanceof IllegalArgumentException) {
				System.err.println(H2ServerMessages.H2_PORT_OUT_OF_RANGE + port);
			}
			return false;
		}
	}

	public boolean stopServer(int port) {
		try {
			Server.shutdownTcpServer("tcp://" + host + ":" + Integer.toString(port), "", true, false);
			return true;
		} catch (SQLException ignored) {
			return false;
		}
	}

}