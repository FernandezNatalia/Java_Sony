package datos;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import entidades.*;

public class TurnoDatos extends Conexion {
	
	public void CancelarTurno(Turno tur) throws SQLException {
		
	    getConnection();
	    try {	    	
	        Statement stmt = miCon.createStatement();
	        stmt.executeUpdate("call Turnos.CancelarTurno(" + Integer.toString(tur.getIdturno()) + ");");
        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	public Turno getOne(int id) throws SQLException {
		Turno turno = new Turno();
		UsuarioDatos ud = new UsuarioDatos();
		PlanDatos pd = new PlanDatos();
		ConsultorioDatos cd = new ConsultorioDatos();
		getConnection();
    try {
	    	
	        Statement stmt = miCon.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Turnos WHERE idTurnos = " + id);
	        if(rs.next())
	        {
	        	turno.setEspecialista(ud.getOne(rs.getInt("dni_profesional")));
	        	turno.setFechahora(rs.getTimestamp("fecha"));
	        	turno.setIdturno(rs.getInt("idTurnos"));
	            turno.setPaciente(ud.getOne(rs.getInt("dni")));
	            turno.setObservacion(rs.getString("observacion"));
	            turno.setPlan(pd.getOne(rs.getInt("idPlan")));
	            turno.setConsultorio(cd.getOne(rs.getInt("idconsultorio")));
	            turno.setEstado(rs.getInt("estado"));
	            //turno.setEstado(rs.getString("desc"));
	            //Falta el consultorio?
	            
	        }
	        
	        rs.close();
	        stmt.close();
	        closeConnection();
	        
    } catch (SQLException ex) { ex.printStackTrace();} 
      catch (Exception e) {e.printStackTrace();}
	  
		return turno;
	}
	public ArrayList<Turno> getAll() throws SQLException{
		
		ArrayList<Turno> turnos = new ArrayList<Turno>();
		ConsultorioDatos cd = new ConsultorioDatos();
		getConnection();
		
		try {
			Statement stm = miCon.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM Turnos"); //Modificar para traer los turnos del dia actual.
			
			while(rs.next())
			{
				Turno tur = new Turno();
				UsuarioDatos ud = new UsuarioDatos();
				PlanDatos pd = new PlanDatos();
				tur.setEspecialista(ud.getOne(rs.getInt("dni")));
	        	tur.setFechahora(rs.getTimestamp("fecha"));
	        	tur.setIdturno(rs.getInt("idTurnos"));
	            tur.setPaciente(ud.getOne(rs.getInt("dni")));
	            tur.setObservacion(rs.getString("observacion"));
	            tur.setPlan(pd.getOne(rs.getInt("idPlan")));
	            tur.setConsultorio(cd.getOne(rs.getInt("idconsultorio")));
	            tur.setEstado(rs.getInt("estado"));
	            
	            turnos.add(tur);
			}
			
			rs.close();
			stm.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {closeConnection();}
		
		return turnos;
	}
	public ArrayList<Turno> getProximosDeEspecialista(Usuario especialista) throws SQLException{
		
		ArrayList<Turno> turnos = new ArrayList<Turno>();	
		ConsultorioDatos cd = new ConsultorioDatos();
		getConnection();
		
		try {
			java.sql.Statement stm;
			String sql = "SELECT * FROM Turnos WHERE (Turnos.estado = 1 OR Turnos.estado = 2) AND Turnos.dni_profesional = " + Integer.toString(especialista.getDni()) + " ORDER BY fecha";
			stm = miCon.prepareStatement(sql);
			//stm.setInt(1, Integer.valueOf(especialista.getDni()));
			
			
			
			ResultSet rs = stm.executeQuery(sql); //Modificar para traer los turnos del dia actual.
			
			while(rs.next())
			{
				Turno tur = new Turno();
				UsuarioDatos ud = new UsuarioDatos();
				PlanDatos pd = new PlanDatos();
				tur.setEspecialista(ud.getOne(rs.getInt("dni")));
	        	tur.setFechahora(rs.getTimestamp("fecha"));
	        	tur.setIdturno(rs.getInt("idTurnos"));
	            tur.setPaciente(ud.getOne(rs.getInt("dni")));
	            tur.setObservacion(rs.getString("observacion"));
	            tur.setPlan(pd.getOne(rs.getInt("idPlan")));
	            tur.setConsultorio(cd.getOne(rs.getInt("idconsultorio")));
	            tur.setEstado(rs.getInt("estado"));
	            turnos.add(tur);
			}
			
			rs.close();
			stm.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {closeConnection();}
		
		return turnos;
	}
	
	public void add(Turno turno) throws SQLException {
	 getConnection();
	java.sql.PreparedStatement st;
	try {
		
		Timestamp fechora = new Timestamp(turno.getFechahora().getTime()); 
		String datestring = fechora.toString();
		String sql = "insert into Turnos.Turnos(fecha, estado, idconsultorio, dni_profesional) VALUES( ?, ?, ? , ?)";
		st = miCon.prepareStatement(sql);
		
		
        st.setTimestamp(1, Timestamp.valueOf(datestring)); 
        st.setInt(2, Integer.valueOf(turno.getEstado()));
        st.setInt(3, Integer.valueOf(turno.getConsultorio().getIdconsultorio()));
        st.setInt(4, Integer.valueOf(turno.getEspecialista().getDni()));
       
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

	
	
	
	
	public ArrayList<Turno> getPacienteTurnos(int dni){
		ArrayList<Turno> turnos = new ArrayList<Turno>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String consulta = "SELECT * FROM turnos WHERE dni=?"; //INNER JOIN?
		try {
			getConnection();
			pst = miCon.prepareStatement(consulta);
			pst.setInt(1,dni);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				
				Turno t = new Turno();
				t.setIdturno(rs.getInt("idTurnos"));
				t.setFechahora(rs.getTimestamp("fecha"));
				//t.setDuracion(rs.getInt("duracion")); NO ESTA EN ENTIDADES
				t.setEstado(rs.getInt("estado"));
				//t.setPaciente(paciente);
				
						//t.setConsultorio(consultorio);				
						ConsultorioDatos cd = new ConsultorioDatos();
						t.setConsultorio(cd.getOne(rs.getInt("idconsultorio")));
				
						//t.setEspecialista(especialista);
						UsuarioDatos ud = new UsuarioDatos();
						t.setEspecialista(ud.getOne(rs.getInt("dni_profesional")));
						
				t.setObservacion(rs.getString("observacion"));
				
						//t.setPlan(plan);
						PlanDatos pd = new PlanDatos();
						t.setPlan(pd.getOne(rs.getInt("idPlan")));
				
				
				//BORRAR - ES DE PRUEBA
				/*Turno tur = new Turno();
				UsuarioDatos ud = new UsuarioDatos();
				PlanDatos pd = new PlanDatos();
				ConsultorioDatos cd = new ConsultorioDatos();				
				
				tur.setEspecialista(ud.getOne(rs.getInt("dni")));
				tur.setPaciente(ud.getOne(rs.getInt("dni")));
				
	        	tur.setFechahora(rs.getTimestamp("fecha"));
	        	tur.setIdturno(rs.getInt("idTurnos"));
	            
	            tur.setObservacion(rs.getString("observacion"));
	            tur.setPlan(pd.getOne(rs.getInt("idPlan")));
	            tur.setConsultorio(cd.getOne(rs.getInt("idconsultorio")));
	            tur.setEstado(rs.getInt("estado"));
	            */
	            turnos.add(t);
			}
		
			} catch (SQLException ex) {
	        ex.printStackTrace();
			}
	    	finally {
	    		try {
	    			if(rs!=null) rs.close();
	    			if(pst!=null) pst.close();
	    			if(miCon!=null)closeConnection();
	    			
	    			} catch (SQLException e) 
	    			 { e.printStackTrace();}	        
	    }		
		return turnos;
	}
	
}
