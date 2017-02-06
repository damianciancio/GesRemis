package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		
		try {
			conn = FactoryConnection.getInstancia().getConn();
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
		return rem;
	}
}
