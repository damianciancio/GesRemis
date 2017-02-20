package data;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import util.exception.DataBaseConnectionException;
public class FactoryConnection {
	
	private String dbDriver="com.mysql.jdbc.Driver";
	private String host="mysql3972-gesremis.jelastic.saveincloud.net";
	private String pass="CKLtip35191";
	private boolean developmentStatus = true;
	private String port="3306";
	private String user="root";
	private String db="ges_remis";
	private String dbType="mysql";
	
	private Connection conn;
	private int cantConn=0;
	
	
	
	private
	 FactoryConnection() throws ClassNotFoundException, Exception {
			try {
				Class.forName(dbDriver);
				if(developmentStatus){
					host = "localhost";
					pass = "root";
				}
				
				//Properties propiedades = new Properties();
			    //InputStream entrada = null;
			    
			    //entrada = new FileInputStream("configs/config.properties");
			    //propiedades.load(entrada);
				
			    //host = propiedades.getProperty("host");
			    //user = propiedades.getProperty("user");
			    //pass = propiedades.getProperty("pass");
			    
			    //host = "localhost";
			    //user = "root";
	    		//pass = "";
	    		//pass = "root";
			    
				
			} catch (ClassNotFoundException e) {
				throw e;
			} catch (Exception ex) {
				throw new Exception("Error en rchivo de configuracion.", ex);
			}
		}
		
		private static FactoryConnection instancia;
		
		public static FactoryConnection getInstancia() throws ClassNotFoundException, Exception{
			if (instancia == null){
				instancia = new FactoryConnection();
			}
			return instancia;
		}
		
		public Connection getConn() throws DataBaseConnectionException{
			try {
				if(conn==null || conn.isClosed()){
					conn = DriverManager.getConnection(
							"jdbc:"+dbType+"://"+host+":"+port+"/"+
							db+"?user="+user+"&password="+pass+"&useSSL=false");
					cantConn++;
				}
			} catch (SQLException e) {
				throw new DataBaseConnectionException("Error al conectar a la DB", e);

			}
			return conn;
		}
		
		public void releaseConn() throws DataBaseConnectionException{
			try {
				cantConn--;
				if(cantConn==0){
					conn.close();
				}
			} catch (SQLException e) {
				throw new DataBaseConnectionException("Error al cerrar conexión", e);
			}
			
		}
}
