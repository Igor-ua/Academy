package org.mydomain.academy.db.h2;


/**
 * {@link H2ServerMessages} contains messages for using them in a {@link H2ConsoleServer}
 * in the same package.
 *
 * @author IgorZ
 */
public final class H2ServerMessages {

	protected static final String H2_WELCOME_MESSAGE = "Welcome to H2 Server setup";
	protected static final String H2_START_MESSAGE = "1. Start H2 TCP Server.";
	protected static final String H2_STOP_MESSAGE = "2. Stop H2 TCP Server.";
	protected static final String H2_EXIT_MESSAGE = "0. Exit.";
	protected static final String H2_ERROR_MESSAGE = "Wrong choice, try again";
	protected static final String H2_SUCCESS_START_MESSAGE = "H2 Server has launched successfully...";
	protected static final String H2_BUSY_PORT_MESSAGE = " Port is busy at this moment";
	protected static final String H2_PORT_OUT_OF_RANGE = "Port value is out of range: ";
	protected static final String H2_ABNORMAL_EXIT = "Abnormal server termination.";
	protected static final String H2_SUCCESS_STOP_MESSAGE = "H2 Server has been stopped successfully.";
	protected static final String H2_ERROR_STOP_MESSAGE = "Error in stopping H2 server.";
	protected static final String H2_NO_AVAILABLE_PORT_MESSAGE = "No available ports at this moment.";

}