package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.util.ArrayList;

import business.entities.Personal;
import business.entities.Remis;
import util.exception.ConsultaSQLException;
import util.exception.DataBaseConnectionException;

public class RemisesData {
public ArrayList<Remis> getAll() throws ClassNotFoundException, DataBaseConnectionException,ConsultaSQLException, Exception {
		
		
		ArrayList<Remis> flota = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
		
			conn = FactoryConnection.getInstancia().getConn();
			stmt = conn.prepareStatement(""
					+ "select remises.id, remises.patente, remises.fecha_incorporacion, "
					+ "remises.fecha_baja, remises.anio_modelo, remises.id_marca, "
					+ "remises.desc_modelo, p.legajo, p.dni, p.apellido, p.nombre, "
					+ "p.direccion, p.telefono, p.fecha_incorporacion, p.usuario, p.contrasenia, "
					+ "p.is_habilitado, aux.fecha_desde, m.descripcion "
					+ "from remises "
					+ "left join ( "
					+ "select id_remis, max(fecha_desde) 'fecha_desde' "
					+ "from remises_choferes "
					+ "group by id_remis "
					+ ") as aux  on remises.id = aux.id_remis "
					+ "left join remises_choferes rc "
					+ "on rc.id_remis = remises.id and rc.fecha_desde = aux.fecha_desde "
					+ "left join personal p "
					+ "on p.legajo = rc.legajo "
					+ "left join marcas m "
					+ "on m.id = remises.id_marca");
			
			
			ResultSet rs = stmt.executeQuery();
			flota = new ArrayList<Remis>();
			
			while(rs.next()){
				Remis rem = new Remis();
				Personal chofer = new Personal();
				rem.setId(rs.getInt(1));
				rem.setPatente(rs.getString(2));
				rem.setFechaIncorporacion(rs.getDate(3));
				rem.setFechaBaja(rs.getDate(4));
				rem.setAnioModelo(rs.getInt(5));
				rem.setIdMarca(rs.getInt(6));
				rem.setDescModelo(rs.getString(7));
				chofer.setLegajo(rs.getInt(8));
				chofer.setDni(rs.getString(9));
				chofer.setApellido(rs.getString(10));
				chofer.setNombre(rs.getString(11));
				chofer.setDireccion(rs.getString(12));
				chofer.setTelefono(rs.getString(13));
				chofer.setFechaIncorporacion(rs.getDate(14));
				chofer.setUsuario(rs.getString(15));
				chofer.setContrasenia(rs.getString(16));
				chofer.setHabilitado(rs.getInt(17));
				rem.setFechaDesdeChoferActual(rs.getDate(18));
				rem.setDescMarca(rs.getString(19));
				rem.setChoferActual(chofer);
				flota.add(rem);
			}
			stmt.close();
			conn.close();
			return flota;
		
		
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (DataBaseConnectionException ex) {
			throw ex;
		} catch (SQLException exc) {
			throw new ConsultaSQLException("Error en la consulta para traer a la flota: \n"+exc.getMessage(), exc);
		}
		catch (Exception exce) {
			throw exce;
		}
		finally{
			stmt.close();
			conn.close();
		}
	}

	public Remis getOne(Remis buscado){
		Remis rem = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean instanceConnectionMaked = false;
		
		try {
			conn = FactoryConnection.getInstancia().getConn();
			instanceConnectionMaked = true;
			ps = conn.prepareStatement(""
					+ "select remises.id, remises.patente, remises.fecha_incorporacion, "
					+ "remises.fecha_baja, remises.anio_modelo, remises.id_marca, "
					+ "remises.desc_modelo, p.legajo, p.dni, p.apellido, p.nombre, "
					+ "p.direccion, p.telefono, p.fecha_incorporacion, p.usuario, p.contrasenia, "
					+ "p.is_habilitado, aux.fecha_desde, m.descripcion "
					+ "from remises "
					+ "left join ( "
					+ "select id_remis, max(fecha_desde) 'fecha_desde' "
					+ "from remises_choferes "
					+ "group by id_remis "
					+ ") as aux  on remises.id = aux.id_remis "
					+ "left join remises_choferes rc "
					+ "on rc.id_remis = remises.id and rc.fecha_desde = aux.fecha_desde "
					+ "left join personal p "
					+ "on p.legajo = rc.legajo "
					+ "left join marcas m "
					+ "on m.id = remises.id_marca "
					+ "where remises.id = ?");
			ps.setInt(1, buscado.getId());
			rs = ps.executeQuery();
			if (rs.next()) {
				Personal chofer = new Personal();
				rem = new Remis();
				rem.setId(rs.getInt(1));
				rem.setPatente(rs.getString(2));
				rem.setFechaIncorporacion(rs.getDate(3));
				rem.setFechaBaja(rs.getDate(4));
				rem.setAnioModelo(rs.getInt(5));
				rem.setIdMarca(rs.getInt(6));
				rem.setDescModelo(rs.getString(7));
				chofer.setLegajo(rs.getInt(8));
				chofer.setDni(rs.getString(9));
				chofer.setApellido(rs.getString(10));
				chofer.setNombre(rs.getString(11));
				chofer.setDireccion(rs.getString(12));
				chofer.setTelefono(rs.getString(13));
				chofer.setFechaIncorporacion(rs.getDate(14));
				chofer.setUsuario(rs.getString(15));
				chofer.setContrasenia(rs.getString(16));
				chofer.setHabilitado(rs.getInt(17));
				rem.setFechaDesdeChoferActual(rs.getDate(18));
				rem.setDescMarca(rs.getString(19));
				rem.setChoferActual(chofer);
			}
		} catch (Exception e) {
			
		}
		finally{
			try {
				rs.close();
				rs = null;
				ps.close();
				ps = null;
				if(instanceConnectionMaked)
					FactoryConnection.getInstancia().releaseConn();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataBaseConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rem;
	}
	
	public int save(Remis remis){
		int id = 0;
		switch (remis.getEstado()) {
		case NEW:
			id = this.insert(remis);
			break;
		case MODIFIED:
			id = this.update(remis);
			break;
		}
		return id;
	}
	
	public int insert(Remis remis){
		int id = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean instanceConnectionMaked = false;
		String query = "INSERT INTO `ges_remis`.`remises` "
				+ "(`patente`, "
				+ "`fecha_incorporacion`, "
				+ "`fecha_baja`, "
				+ "`anio_modelo`, "
				+ "`id_marca`, "
				+ "`desc_modelo`) "
				+ "VALUES "
				+ "(?, "
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?) ";
		try {
			conn = FactoryConnection.getInstancia().getConn();
			instanceConnectionMaked = true;
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, remis.getPatente());
			ps.setDate(2, new java.sql.Date(remis.getFechaIncorporacion().getTime()));
			if(remis.getFechaBaja() != null)
			ps.setDate(3, new java.sql.Date(remis.getFechaBaja().getTime()));
			else 
				ps.setNull(3, java.sql.Types.DATE);
			ps.setInt(4, remis.getAnioModelo());
			ps.setInt(5, remis.getIdMarca());
			ps.setString(6, remis.getDescModelo());
			id = ps.executeUpdate();
			if(remis.getChoferActual().getLegajo() != 0){
				//this.updateChoferActual(remis);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataBaseConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				rs.close();
				rs = null;
				ps.close();
				ps = null;
				if(instanceConnectionMaked)
					FactoryConnection.getInstancia().releaseConn();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataBaseConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return id;
	}
	
	private int update(Remis rem){
		int id = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		PreparedStatement psIfChangesRemis = null;
		boolean instanceConnectionMaked = false;
		String query = "UPDATE `ges_remis`.`remises` "
				+ "SET "
				+ "`patente` = ?, "
				+ "`fecha_incorporacion` = ?, "
				+ "`fecha_baja` = ?, "
				+ "`anio_modelo` = ?, "
				+ "`id_marca` = ?, "
				+ "`desc_modelo` = ? "
				+ "WHERE `id` = ?";
		String queryIfChangesChofer = "";
		if(rem.getCambiaChofer()){
			queryIfChangesChofer = " INSERT INTO `ges_remis`.`remises_choferes` "
				+ "(`legajo`, "
				+ "`id_remis`, "
				+ "`fecha_desde`) "
				+ "VALUES "
				+ "(?, "
				+ "?, "
				+ "?)";
		}
		try {
			conn = FactoryConnection.getInstancia().getConn();
			instanceConnectionMaked = true;
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(query);
			ps.setString(1, rem.getPatente());
			ps.setDate(2, new java.sql.Date(rem.getFechaIncorporacion().getTime()));
			if (rem.getFechaBaja() != null) {

				ps.setDate(3, new java.sql.Date(rem.getFechaBaja().getTime()));
			} else {
				ps.setNull(3, java.sql.Types.DATE);
			}
			ps.setInt(4, rem.getAnioModelo());
			ps.setInt(5, rem.getIdMarca());
			ps.setString(6, rem.getDescModelo());
			ps.setInt(7, rem.getId());
			ps.executeUpdate();
			if (rem.getCambiaChofer()) {
				psIfChangesRemis = conn.prepareStatement(queryIfChangesChofer);
				psIfChangesRemis.setInt(1, rem.getChoferActual().getLegajo());
				psIfChangesRemis.setInt(2, rem.getId());
				psIfChangesRemis.setDate(3, new java.sql.Date(rem.getFechaDesdeChoferActual().getTime()));

				id = psIfChangesRemis.executeUpdate();
			}
			conn.commit();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataBaseConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				conn.setAutoCommit(true);
				rs.close();
				rs = null;
				ps.close();
				ps = null;
				if(instanceConnectionMaked)
					FactoryConnection.getInstancia().releaseConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataBaseConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return id;
	}
}