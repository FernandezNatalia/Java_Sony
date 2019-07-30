package datos;

import entidades.Usuario;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class UsuarioDatos extends Conexion {

	public Usuario getOne(int dni) {
		Usuario us = new Usuario();
	    getConnection();
	    try {
	    	
	        Statement stmt = miCon.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Usuarios WHERE dni = " + Integer.toString(dni));
	        if(rs.next())
	        {
	            us.setDni(rs.getInt("dni"));
	            us.setNombre(rs.getString("Nombre"));
	            us.setApellido(rs.getString("Apellido"));
	            us.setEmail(rs.getString("email"));
	            us.setFechanacimiento(rs.getDate("fecha_nacimiento"));
	            
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
	public Usuario Logear(int dni, String pass) {
		Usuario us = new Usuario();
	    getConnection();
	    try {
	    	
	        Statement stmt = miCon.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Usuarios WHERE dni = " + Integer.toString(dni) + " AND password = '" + pass + "'");
	        if(rs.next())
	        {
	            us.setDni(rs.getInt("dni"));
	            us.setNombre(rs.getString("Nombre"));
	            us.setApellido(rs.getString("Apellido"));
	            us.setEmail(rs.getString("email"));
	            us.setFechanacimiento(rs.getDate("fecha_nacimiento"));
	            
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
	public void add(Usuario us) {
		 getConnection();
		 String datestring = "2018-01-01";
		 
		java.sql.PreparedStatement st;
		try {
			String sql = "insert into Turnos.Usuarios(dni, Nombre, Apellido, fecha_nacimiento, password, email, tipo_usuario) VALUES( ?, ?, ?, ? , ?, ? , 1)";
			st = miCon.prepareStatement(sql);
			//st = miCon.PreparedStatement("INSERT INTO Usuarios VALUES(?, ?, ?, ?, ?, ?, ?)");
			st.setInt(1, Integer.valueOf(us.getDni()));
	         st.setString(2, String.valueOf(us.getNombre()));
	         st.setString(3, String.valueOf(us.getApellido()));
	         st.setDate(4, Date.valueOf(datestring)); 
	         st.setString(5, String.valueOf(us.getPassword()));
	         st.setString(6, String.valueOf(us.getEmail()));
	        // st.setInt(7, Integer.valueOf(us.getTipousuario()));
	        
	        
	        
	         

	         st.executeUpdate(); 
	         st.close();
		        st.close();
		        closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 e.printStackTrace();
			
		}
		
			
		
		
         
         

	}

}
