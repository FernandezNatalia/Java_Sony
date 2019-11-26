package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import entidades.Especialidad;

public class EspecialidadDatos extends Conexion{

	public ArrayList<Especialidad> getAll() throws SQLException{
		
		ArrayList<Especialidad> espdades = new ArrayList<Especialidad>();;		
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			getConnection();
			stm = miCon.createStatement();
			rs = stm.executeQuery("SELECT * FROM especialidades"); 			

			while(rs.next())
			{
				Especialidad e = new Especialidad();				
				e.setCodEspecialidad(rs.getInt("cod_especialidad"));
				e.setNombre(rs.getString("nombre"));
				espdades.add(e);
			}
			
			rs.close();
			stm.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			if(rs!=null) rs.close();
			if(stm!=null) stm.close();
			if(miCon!=null)closeConnection();
		}
		
		return espdades;
	}
}
