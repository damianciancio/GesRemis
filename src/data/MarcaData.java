package data;

import java.sql.*;
import java.util.ArrayList;

import business.entities.Marca;

public class MarcaData {
	public ArrayList<Marca> getAll(){
		ArrayList<Marca> array = new ArrayList<Marca>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = FactoryConnection.getInstancia().getConn();
			ps = conn.prepareStatement("select * from marcas");
			rs = ps.executeQuery();
			while(rs.next()){
				Marca m = new Marca();
				m.setId(rs.getInt(1));
				m.setDescripcion(rs.getString(2));
				array.add(m);
			}
		} catch (Exception e){
			
		}
		return array;
	}
}
