package datos;

import entidades.Usuario;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class UsuarioDatos extends Conexion {

	
	public Usuario getOne(int dni){
		Usuario us = null;	
		PreparedStatement pst = null;
		ResultSet rs = null;
		String consulta = "SELECT * FROM Usuarios WHERE dni = ?";
		
	    try {	    	
	    	getConnection();
	    	pst = miCon.prepareStatement(consulta);
	    	pst.setInt(1,dni);
	    	rs = pst.executeQuery();
	        
	        if(rs.next())
	        {
	        	us = new Usuario();
	            us.setDni(rs.getInt("dni"));
	            us.setNombre(rs.getString("Nombre"));
	            us.setApellido(rs.getString("Apellido"));
	            us.setEmail(rs.getString("email"));
	            us.setFechanacimiento(rs.getDate("fecha_nacimiento"));	            
	            us.setPassword(rs.getString("password"));
	            us.setTipousuario(rs.getInt("tipo_usuario"));	            
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

		try {	
			getConnection();
			String consulta = "SELECT * FROM Usuarios WHERE dni = ? AND password = ?";
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
	public void RegistrarNuevoUsuario(Usuario us) {
		PreparedStatement pst = null;
		String consulta ="INSERT INTO usuarios(dni, Nombre, Apellido, fecha_nacimiento, password, email, tipo_usuario) VALUES (?,?,?,?,?,?,?)";
		
		try {
			getConnection();
			pst = miCon.prepareStatement(consulta);
			
			pst.setInt(1,us.getDni());
			pst.setString(2,us.getNombre());
			pst.setString(3,us.getApellido());
			pst.setDate(4,(Date)us.getFechanacimiento());
			pst.setString(5,us.getPassword());
			pst.setString(6,us.getEmail());
			pst.setInt(7,us.getTipousuario());
			
			pst.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(pst!=null)pst.close();
				if(miCon!= null) closeConnection();
				
				} catch (SQLException e) { e.printStackTrace();}			
		}
}

	

	
	public Usuario Logear(int dni, String pass) throws SQLException {
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
	public void add(Usuario us) throws SQLException {
		 getConnection();
		 //String datestring = "2018-01-01";
		 
		java.sql.PreparedStatement st;
		try {
			String datestring = us.getFechanacimiento().toString();
			String sql = "insert into Turnos.Usuarios(dni, Nombre, Apellido, fecha_nacimiento, password, email, tipo_usuario) VALUES( ?, ?, ?, ? , ?, ? , 1)";
			st = miCon.prepareStatement(sql);
			
			st.setInt(1, Integer.valueOf(us.getDni()));
	         st.setString(2, String.valueOf(us.getNombre()));
	         st.setString(3, String.valueOf(us.getApellido()));
	         st.setDate(4, Date.valueOf(datestring)); 
	         st.setString(5, String.valueOf(us.getPassword()));
	         st.setString(6, String.valueOf(us.getEmail()));
	        
	        
	        
	        
	         

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
