package datos;

import entidades.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDatos extends Conexion {

	public Usuario getOne(String user) {
		Usuario us = new Usuario();
	    getConnection();
	    try {
	    	
	        Statement stmt = miCon.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Usuarios WHERE nombre_usuario = " + user);
	        if(rs.next())
	        {
	            us.setDni(rs.getInt("dni"));
	            us.setNombre(rs.getString("Nombre"));
	            us.setApellido(rs.getString("Apellido"));
	            us.setEmail(rs.getString("email"));
	            us.setFechanacimiento(rs.getDate("fecha_nacimiento"));
	            us.setNombre_usuario(rs.getString("nombre_usuario"));
	            us.setPassword(rs.getString("password"));
	            us.setTipousuario(rs.getInt("tipo_usuario"));	            
	        }
	        
	        rs.close();
	        stmt.close();
	        closeConnection();
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return us;
	}
	public Usuario Logear(String user, String pass) {
		Usuario us = new Usuario();
	    getConnection();
	    try {
	    	
	        Statement stmt = miCon.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Usuarios WHERE nombre_usuario = " + user + " AND password = '" + pass + "'");
	        if(rs.next())
	        {
	            us.setDni(rs.getInt("dni"));
	            us.setNombre(rs.getString("Nombre"));
	            us.setApellido(rs.getString("Apellido"));
	            us.setEmail(rs.getString("email"));
	            us.setFechanacimiento(rs.getDate("fecha_nacimiento"));
	            us.setNombre_usuario(rs.getString("nombre_usuario"));
	            us.setPassword(rs.getString("password"));
	            us.setTipousuario(rs.getInt("tipo_usuario"));
	            
	        }
	        rs.close();
	        stmt.close();
	        closeConnection();

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	    return us;
	}

}
