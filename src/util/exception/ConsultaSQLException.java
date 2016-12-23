package util.exception;

import java.util.logging.Logger;

import org.apache.logging.log4j.Level;

import util.logger.SuperLogger;

public class ConsultaSQLException extends Exception {
	private static final long serialVersionUID = 1L;

	public ConsultaSQLException(){
		super("Error al ejecutar consulta SQL");
		SuperLogger.logger.log(Level.FATAL, "Error al ejecutar consulta SQL");
	}
	
	public ConsultaSQLException(String message) {
		super(message);
		SuperLogger.logger.log(Level.FATAL, message);
	}
	
	public ConsultaSQLException(String message, Exception e) {
		super(message, e);
		SuperLogger.logger.log(Level.FATAL, message, e);
	}
	
	public ConsultaSQLException(Exception e) {
		super("Error al ejecutar consulta SQL", e);
		SuperLogger.logger.log(Level.FATAL, "Error al ejecutar consulta SQL", e);
	}
}
