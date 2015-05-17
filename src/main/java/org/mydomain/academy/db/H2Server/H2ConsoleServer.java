package org.mydomain.academy.db.H2Server;

import org.h2.tools.Server;

import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static com.google.common.base.Preconditions.*;


/**
 * {@link H2ConsoleServer} launches tcp server for a H2 database.
 *
 * @author IgorZ
 */
public class H2ConsoleServer {

	private static final int DEFAULT_PORT = 64064;
	private static int availablePort;
	private static final int MAX_PORT_NUMBER = 65535;
	private static final int MIN_PORT_NUMBER = 2000;

	private static Scanner scanner = new Scanner(System.in);

	/**
	 * This is the main method that launches H2 server.
	 *
	 * @param args the command line arguments (no args are available at the moment)
	 */
	public static void main(String[] args) {
		try {
			System.out.println(H2ServerMessages.H2_WELCOME_MESSAGE + "\n");
			label_Main:
			while (true) {
				System.out.println(H2ServerMessages.H2_START_MESSAGE);
				System.out.println(H2ServerMessages.H2_EXIT_MESSAGE);
				String usersChoice = scanner.nextLine();
				switch (usersChoice) {
					case "1":
						label_Start:
						if (startServer(DEFAULT_PORT)) {
							while (true) {
								System.out.println(H2ServerMessages.H2_STOP_MESSAGE);
								usersChoice = scanner.nextLine();
								switch (usersChoice) {
									case "2":
										if (stopServer(availablePort)) {
											System.out.println(H2ServerMessages.H2_SUCCESS_STOP_MESSAGE);
										} else {
											System.err.println(H2ServerMessages.H2_ERROR_STOP_MESSAGE);
										}
										break label_Start;
									default:
										System.out.println(H2ServerMessages.H2_ERROR_MESSAGE);
								}
							}
						}
						break;
					case "0":
						break label_Main;
				}
			}
		} catch (NoSuchElementException e) {
			System.err.println(H2ServerMessages.H2_ABNORMAL_EXIT);
		}
	}

	/**
	 * Starts H2 server on the default port {@link #DEFAULT_PORT}. If default port is busy it will search for
	 * a first free port in range from {@link #MIN_PORT_NUMBER} to {@link #MAX_PORT_NUMBER}.
	 * Returns true if the server was launched successfully.
	 *
	 * @return <code>true</code> if the server was launched successfully;
	 * <code>false</code> otherwise.
	 */
	public static boolean startServer() {
		boolean switchToMinPort = false;
		availablePort = DEFAULT_PORT;
		while (true) {
			try {
				Server.createTcpServer(
						"-tcpPort", Integer.toString(availablePort),
						"-baseDir", "./H2db",
						"-webAllowOthers",
						"-tcpAllowOthers").start();
				System.out.println(H2ServerMessages.H2_SUCCESS_START_MESSAGE + availablePort);
				return true;
			} catch (SQLException e) {
				System.out.println(availablePort + H2ServerMessages.H2_BUSY_PORT_MESSAGE);
				if ((availablePort < MAX_PORT_NUMBER) && (!switchToMinPort)) {
					availablePort++;
				} else {
					if (!switchToMinPort) {
						availablePort = MIN_PORT_NUMBER;
						switchToMinPort = true;
					} else if (availablePort == DEFAULT_PORT) {
						System.out.println(H2ServerMessages.H2_NO_AVAILABLE_PORT_MESSAGE);
						return false;
					}
				}
			}
		}
	}

	/**
	 * Starts H2 server on the port
	 *
	 * @param port the port where the server is supposed to start;
	 * @return <code>true</code> if the server was launched successfully;
	 * <code>false</code> otherwise.
	 */
	public static boolean startServer(Integer port) {
		checkNotNull(port);
		availablePort = port;
		try {
			Server.createTcpServer(
					"-tcpPort", Integer.toString(availablePort),
					"-baseDir", "./H2db",
					"-webAllowOthers",
					"-tcpAllowOthers").start();
			System.out.println(H2ServerMessages.H2_SUCCESS_START_MESSAGE + availablePort);
			return true;
		} catch (SQLException | IllegalArgumentException e) {
			if (e instanceof SQLException) {
				System.out.println(availablePort + H2ServerMessages.H2_BUSY_PORT_MESSAGE);
			}
			if (e instanceof IllegalArgumentException) {
				System.err.println(H2ServerMessages.H2_PORT_OUT_OF_RANGE + availablePort);
			}
			return false;
		}
	}

	/**
	 * Stops H2 tcp server
	 *
	 * @param port {@link #availablePort} where server has been started.
	 * @return <code>true</code> if the server was stopped successfully;
	 * <code>false</code> otherwise.
	 */
	public static boolean stopServer(int port) {
		try {
			Server.shutdownTcpServer("tcp://localhost:" + Integer.toString(port), "", true, false);
			return true;
		} catch (SQLException ignored) {
			return false;
		}
	}

	/**
	 * availablePort getter
	 *
	 * @return availablePort value
	 */
	public static int getAvailablePort() {
		return availablePort;
	}
}