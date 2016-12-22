package util.exception;

public class DataBaseConnectionException extends Exception {

	private static final long serialVersionUID = 1L;

	public DataBaseConnectionException(){
		super("Error al conectar a la base de datos");
	}
	
	public DataBaseConnectionException(String message) {
		super(message);
	}
	
	public DataBaseConnectionException(String message, Exception e) {
		super(message, e);
	}
	
	public DataBaseConnectionException(Exception e) {
		super("Error al conectar a la base de datos", e);
	}
	
}
