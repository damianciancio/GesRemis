package util.exception;

public class ConsultaSQLException extends Exception {
	private static final long serialVersionUID = 1L;

	public ConsultaSQLException(){
		super("Error al ejecutar consulta SQL");
	}
	
	public ConsultaSQLException(String message) {
		super(message);
	}
	
	public ConsultaSQLException(String message, Exception e) {
		super(message, e);
	}
	
	public ConsultaSQLException(Exception e) {
		super("Error al ejecutar consulta SQL", e);
	}
}
