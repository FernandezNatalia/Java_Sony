package datos;

import entidades.Paciente;
import entidades.Plan;
import entidades.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class UsuarioDatos extends Conexion {
	
	public Usuario readUsuario(ResultSet rs,Usuario us) throws SQLException {
		us.setDni(rs.getInt("dni"));
		us.setNombre(rs.getString("nombre"));
		us.setApellido(rs.getString("apellido"));	            	
		us.setFechanacimiento(rs.getDate("fecha_nacimiento"));
		us.setTipousuario(rs.getInt("tipo_usuario"));	
		us.setPassword(rs.getString("password"));
		us.setEmail(rs.getString("email"));
		return us;
	}		
	
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
		    	readUsuario(rs,us);
	                        
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
	
	public boolean Autenticacion(int dni,String pass){
		PreparedStatement pst = null;
		ResultSet rs = null;		
		String consulta = "SELECT * FROM Usuarios WHERE dni = ? AND password = ?";
		
		try {	
			getConnection();			
			pst = miCon.prepareStatement(consulta);
			
			pst.setInt(1, dni);
			pst.setString(2, pass);			
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
            pst.setTimestamp(4, new java.sql.Timestamp(us.getFechanacimiento().getTime()));		
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
	
	public void CambioMail(String mail, int dni) throws SQLException {
		
		PreparedStatement pst = null;
		String consulta = "UPDATE usuarios SET email = ? WHERE (dni = ?)";
		try {
			
			getConnection();
			pst = miCon.prepareStatement(consulta);
			pst.setString(1,mail);
			pst.setInt(2, dni);
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			if(pst!=null)pst.close();
			if(miCon!= null) miCon.close();
			
		}		
	}
	
	
	public void CambioClave(String newpass,int dni) throws SQLException {
		
		PreparedStatement pst = null;
		String consulta = "UPDATE usuarios SET password = ? WHERE (dni = ?)";
		try {
			
			getConnection();
			pst = miCon.prepareStatement(consulta);
			pst.setString(1,newpass);
			pst.setInt(2, dni);
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			if(pst!=null)pst.close();
			if(miCon!= null) miCon.close();
			
		}
		
	}
	public ArrayList<Usuario> getPacientesEspecialista(int dniEsp){
		ArrayList<Usuario> pac = new ArrayList<Usuario>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String consulta ="Select distinct usuarios.dni from usuarios,turnos where turnos.dni_especialista = ? and tipo_usuario=1";		
		try {
			getConnection();
			pst = miCon.prepareStatement(consulta);
			pst.setInt(1,dniEsp);
			rs = pst.executeQuery();
	        
	        while(rs.next())
	        {
	        	Usuario us = this.getOne(rs.getInt("dni"));
		    	pac.add(us);
	                        
	        }	 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(pst!=null)pst.close();
				if(miCon!= null) miCon.close();
				
				} catch (SQLException e) { e.printStackTrace();}			
		}
		return pac;
		
	}
	
	
	
	
}
