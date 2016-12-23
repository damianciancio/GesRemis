package util.exception;

import org.apache.logging.log4j.Level;

import util.logger.SuperLogger;

public class DataBaseConnectionException extends Exception {

	private static final long serialVersionUID = 1L;

	public DataBaseConnectionException(){
		super("Error al conectar a la base de datos");
		SuperLogger.logger.log(Level.FATAL, "Error al conectar a la base de datos");
	}
	
	public DataBaseConnectionException(String message) {
		super(message);
		SuperLogger.logger.log(Level.FATAL, message);
	}
	
	public DataBaseConnectionException(String message, Exception e) {
		super(message, e);
		SuperLogger.logger.log(Level.FATAL, message, e);
	}
	
	public DataBaseConnectionException(Exception e) {
		super("Error al conectar a la base de datos", e);
		SuperLogger.logger.log(Level.FATAL, "Error al conectar a la base de datos", e);
	}
	
}
