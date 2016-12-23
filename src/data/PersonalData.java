package data;

import java.sql.*;
import java.util.ArrayList;

import business.entities.Personal;
import util.exception.ConsultaSQLException;
import util.exception.DataBaseConnectionException;

public class PersonalData {
	
	public ArrayList<Personal> getAll() throws ClassNotFoundException, DataBaseConnectionException,ConsultaSQLException, Exception {
		
		
		ArrayList<Personal> personal = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
		
			conn = FactoryConnection.getInstancia().getConn();
			stmt = conn.prepareStatement("SELECT personal.legajo, "
					+ "personal.dni, "
					+ "personal.apellido, "
					+ "personal.nombre, "
					+ "personal.direccion, "
					+ "personal.telefono, "
					+ "personal.fecha_incorporacion, "
					+ "personal.tipo, "
					+ "personal.usuario, "
					+ "personal.contrasenia "
					+ "FROM ges_remis.personal");
			
			
			ResultSet rs = stmt.executeQuery();
			personal = new ArrayList<Personal>();
			
			while(rs.next()){
				personal.add(this.mapToPersonalFromResultSet(rs));
			}
			stmt.close();
			conn.close();
			return personal;
		
		
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (DataBaseConnectionException ex) {
			throw ex;
		} catch (SQLException exc) {
			throw new ConsultaSQLException("Error en la consulta para traer a todo el personal: \n"+exc.getMessage(), exc);
		}
		catch (Exception exce) {
			throw exce;
		}
		finally{
			stmt.close();
			conn.close();
		}
	}
	
	private Personal mapToPersonalFromResultSet(ResultSet rs) throws SQLException {
		Personal per = new Personal();
		per.setLegajo(rs.getInt(1));
		per.setDni(rs.getString(2));
		per.setApellido(rs.getString(3));
		per.setNombre(rs.getString(4));
		per.setDireccion(rs.getString(5));
		per.setTelefono(rs.getString(6));
		per.setFechaIncorporacion(rs.getDate(7));
		per.setTipo(rs.getString(8));
		return per;
	}
	
	public Personal getOne(Personal buscado) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		Personal found = null;
		String query = "SELECT `personal`.`legajo`,`personal`.`dni`, "
					+ "`personal`.`apellido`, `personal`.`nombre`, `personal`.`direccion`, `personal`.`telefono`, "
					+ "`personal`.`fecha_incorporacion`, `personal`.`tipo` FROM personal where personal.legajo = ?";
		
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement(query);
			stmt.setInt(1,buscado.getLegajo());
			ResultSet rs = stmt.executeQuery();
			found = this.mapToPersonalFromResultSet(rs);
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			
		} catch (SQLException e) {
			
		} catch (DataBaseConnectionException e) {
			
		} catch (Exception e) {
			
		}
		return found;
	}
}
