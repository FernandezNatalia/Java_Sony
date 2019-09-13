package datos;

import entidades.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;


public class UsuarioDatos extends Conexion {
	
	public Usuario getOne(int dni){
		Usuario us = null;	
		PreparedStatement pst = null;
		ResultSet rs = null;
		String consulta = "SELECT * FROM usuarios WHERE dni = ?";
		
	    try {	    	
	    	getConnection();
	    	pst = miCon.prepareStatement(consulta);
	    	pst.setInt(1,dni);
	    	rs = pst.executeQuery();
	        
	        if(rs.next())
	        {
	        	us = new Usuario();
	            us.setDni(rs.getInt("dni"));
	            us.setNombre(rs.getString("nombre"));
	            us.setApellido(rs.getString("apellido"));	            	
	            us.setFechanacimiento(rs.getDate("fecha_nacimiento"));
	            us.setTipousuario(rs.getInt("tipo_usuario"));	
	            us.setPassword(rs.getString("password"));
	            us.setEmail(rs.getString("email"));
	            //us.setCodEspecialidad(rs.getInt("cod_especialidad"));
	                        
	        }	 
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    finally {
	    	try {
				if(rs!=null) rs.close();
				if(pst!=null) pst.close();
				if(miCon!=null)closeConnection();
			} catch (SQLException e) { e.printStackTrace();}	        
	    }
	    return us;
	}	
	public boolean Autenticacion(int dni,String contraseña){
		PreparedStatement pst = null;
		ResultSet rs = null;		
		String consulta = "SELECT * FROM Usuarios WHERE dni = ? AND password = ?";
		
		try {	
			getConnection();			
			pst = miCon.prepareStatement(consulta);
			
			pst.setInt(1, dni);
			pst.setString(2, contraseña);			
			rs = pst.executeQuery();
			
	        if(rs.next()) return true;	       
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		finally{
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(miCon != null) closeConnection();
				} catch (SQLException e) {e.printStackTrace();}					     
		}
	    return false;
	}	
	
	public void RegistrarNuevoUsuario(Usuario us){
		
		PreparedStatement pst = null;
		String consulta ="INSERT INTO usuarios(dni, nombre, apellido, fecha_nacimiento, tipo_usuario, password, email) VALUES (?,?,?,?,?,?,?)";		
		try {
			getConnection();
			pst = miCon.prepareStatement(consulta);
			pst.setInt(1,us.getDni());
			pst.setString(2,us.getNombre());
			pst.setString(3,us.getApellido());
			SimpleDateFormat formatoddmmyy = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = formatoddmmyy.format(us.getFechanacimiento());
			pst.setString(4,fecha);
			pst.setInt(5,1);			
			pst.setString(6,us.getPassword());
			pst.setString(7,us.getEmail());
			//pst.setInt(8,us.getCodEspecialidad());			
			pst.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(pst!=null)pst.close();
				if(miCon!= null) miCon.close();
				
				} catch (SQLException e) { e.printStackTrace();}			
		}
	}
	
}
