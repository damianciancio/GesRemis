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
					+ "personal.contrasenia, "
					+ "personal.is_habilitado "
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
		per.setUsuario(rs.getString(9));
		per.setContrasenia(rs.getString(10));
		per.setHabilitado(rs.getInt(11));
		return per;
	}
	
	public Personal getOne(Personal buscado) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		Personal found = null;
		String query = "SELECT `personal`.`legajo`,`personal`.`dni`, "
					+ "`personal`.`apellido`, `personal`.`nombre`, `personal`.`direccion`, `personal`.`telefono`, "
					+ "`personal`.`fecha_incorporacion`, `personal`.`tipo`, `personal`.`usuario`,`personal`.`contrasenia`, "
					+ "personal.is_habilitado "
					+ "FROM personal where personal.legajo = ? "
					+ "and personal.is_habilitado = 1";
		
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement(query);
			stmt.setInt(1,buscado.getLegajo());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				found = this.mapToPersonalFromResultSet(rs);				
			}
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			
		} catch (SQLException e) {
			System.out.println(e);
		} catch (DataBaseConnectionException e) {
			
		} catch (Exception e) {
			
		}
		return found;
	}
	public int save (Personal per) throws DataBaseConnectionException, ConsultaSQLException{
		int id = 0;
		switch (per.getEstado()) {
		case NEW: 
			 id = insert(per);
			break;
		case MODIFIED:
			id = update(per);
		default:
			break;
		}
		return id;
	}
	private int update(Personal per) throws DataBaseConnectionException, ConsultaSQLException{
		int id = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "UPDATE `ges_remis`.`personal` "
				+ "SET "
				+ "`dni` = ?, "
				+ "`apellido` = ?, "
				+ "`nombre` = ?, "
				+ "`direccion` = ?, "
				+ "`telefono` = ?, "
				+ "`tipo` = ?, "
				+ "WHERE `legajo` = ?";
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(query);
		}
		catch (Exception e){
			throw new DataBaseConnectionException();
		}
		try {
			stmt.setString(1, per.getDni());
			stmt.setString(2, per.getApellido());
			stmt.setString(3, per.getNombre());
			stmt.setString(4, per.getDireccion());
			stmt.setString(5, per.getTelefono());
			stmt.setString(6, per.getTipo().name());
			stmt.setInt(7, per.getLegajo());
			int affectedRows = stmt.executeUpdate();
			if (affectedRows == 1) {
				id = per.getLegajo();
			}
		} catch (Exception e) {
			throw new ConsultaSQLException();
		}
		finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
			}
		}
		return id;
	}
	
	private int insert(Personal per) throws DataBaseConnectionException, ConsultaSQLException{
		int id = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = "INSERT INTO `ges_remis`.`personal` "
				+ "(`dni`,`apellido`,`nombre`,`direccion`,`telefono`,"
				+ "`fecha_incorporacion`,`tipo`,`usuario`,`contrasenia`)"
				+ "VALUES (?,?,?,?,?,?,?,?,md5(?))";
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		}
		catch (Exception e){
			throw new DataBaseConnectionException();
		}
		try {
			stmt.setString(1, per.getDni());
			stmt.setString(2, per.getApellido());
			stmt.setString(3, per.getNombre());
			stmt.setString(4, per.getDireccion());
			stmt.setString(5, per.getTelefono());
			stmt.setDate(6, new java.sql.Date(per.getFechaIncorporacion().getTime()));
			stmt.setString(7, per.getTipo().name());
			stmt.setString(8, per.getUsuario());
			stmt.setString(9, per.getContrasenia());
			int affectedRows = stmt.executeUpdate();
			if (affectedRows == 1) {
				rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					id = (int)rs.getLong(1);
				}
			}
		} catch (Exception e) {
			throw new ConsultaSQLException();
		}
		finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
			}
		}
		return id;
	}

	public Personal login(Personal per) {
		Connection conn = null;
		PreparedStatement stmt = null;
		Personal found = null;
		String query = "SELECT `personal`.`legajo`,`personal`.`dni`, "
					+ "`personal`.`apellido`, `personal`.`nombre`, `personal`.`direccion`, `personal`.`telefono`, "
					+ "`personal`.`fecha_incorporacion`, `personal`.`tipo`, `personal`.`usuario`,`personal`.`contrasenia`, "
					+ "`personal`.`is_habilitado` "
					+ "FROM personal where personal.usuario = ? and personal.contrasenia = ? and personal.is_habilitado = 1";
		
		try {
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement(query);
			stmt.setString(1,per.getUsuario());
			stmt.setString(2,  per.getContrasenia());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				found = this.mapToPersonalFromResultSet(rs);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataBaseConnectionException e) {
			
		} catch (Exception e) {
			
		}
		return found;
	}
	
	public ArrayList<Personal> getAllChoferesWithoutRemis(){
		String query = "select `personal`.`legajo`,`personal`.`dni`, "
				+ "`personal`.`apellido`, `personal`.`nombre`, `personal`.`direccion`, `personal`.`telefono`, "
				+ "`personal`.`fecha_incorporacion`, `personal`.`tipo`, `personal`.`usuario`,`personal`.`contrasenia`, "
				+ "`personal`.`is_habilitado` "
				+ "from personal "
				+ "where legajo not in (select rc.legajo "
				+ "from remises_choferes rc "
				+ "where rc.fecha_desde = (select max(rc2.fecha_desde) "
				+ "from remises_choferes rc2 "
				+ "where rc2.id_remis = rc.id_remis)) and tipo = \"Chofer\"";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =  null;
		ArrayList<Personal> found = new ArrayList<Personal>();
		try {
			conn = FactoryConnection.getInstancia().getConn();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				System.out.println("hola");
				found.add(this.mapToPersonalFromResultSet(rs));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch(Exception e){
			
		}
		return found;
	}
}
