package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Paciente;
import entidades.Plan;
import entidades.Usuario;

public class PacienteDatos extends Conexion{

	public Paciente getPaciente(int dni) throws SQLException {
		
		ObraSocialDatos ob = null;
		
		Plan pl = null;		
		Usuario pac = null;
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		String cadena = "select u.dni,u.nombre,u.apellido,u.fecha_nacimiento,u.tipo_usuario, u.password,u.email, "
				+ "nro_afiliado,p.id_plan, p.cuit_os, p.nombre as plan_nombre from usuarios u " + 
				"inner join planes p on p.id_plan = u.id_plan " + 
				"where dni= ?";
		
	    try {
	    	getConnection();
	        pst = miCon.prepareStatement(cadena);
	        pst.setInt(1, dni);
	        rs = pst.executeQuery();
	        
	        if(rs.next())
	        {
	        	pl = new Plan();
	        	ob = new ObraSocialDatos();
	        	
	        	UsuarioDatos udat = new UsuarioDatos();
		    	pac = new Paciente();
		    	udat.readUsuario(rs,pac);
	        	
	        	pl.setId(rs.getInt("id_plan"));
	        	pl.setObs(ob.getOne(rs.getString("cuit_os")));
	        	pl.setNomplan(rs.getString("plan_nombre")); 
	        	
	        	((Paciente)pac).setPlan(pl);
	        	((Paciente)pac).setNroAfiliado(rs.getString("nro_afiliado"));
	        }
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    finally {
	    	if(rs!=null) rs.close();
			if(pst!=null) pst.close();
			if(miCon!=null)closeConnection();
	    }
	    return ((Paciente)pac);
	}
	
	public void eliminarPlan(int dni) throws SQLException {
		
		PreparedStatement pst = null;
		String cadena = "UPDATE usuarios SET id_plan = NULL WHERE (dni = ?)";
		
		try {
			
			getConnection();
	        pst = miCon.prepareStatement(cadena);
	        pst.setInt(1,dni);
	        pst.executeUpdate();
	        
		}catch (Exception e) {e.printStackTrace();}
		 finally {
			 if(pst != null) pst.close();
		     if(miCon != null) closeConnection();
		 }
	}
	
	public void agregarPlan(int dni,int idplan,int nroafiliado) throws SQLException {
		
		PreparedStatement pst = null;
		String cadena = "UPDATE usuarios SET nro_afiliado = ?, id_plan = ? WHERE (dni = ?)";
		
		try {			
			getConnection();
	        pst = miCon.prepareStatement(cadena);
	        pst.setInt(1,nroafiliado);
	        pst.setInt(2,idplan);
	        pst.setInt(3,dni);
	        pst.executeUpdate();
	        
		}catch (Exception e) {e.printStackTrace();}
		 finally {
			 if(pst != null) pst.close();
		     if(miCon != null) closeConnection();
		 }
	}
	
}
