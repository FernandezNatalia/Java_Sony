package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entidades.Practica;

public class PracticaDatos extends Conexion{

	public Practica getOne(int id) throws SQLException {
		Practica pr = new Practica();
	    getConnection();
	    try {
	    	
	        Statement stmt = miCon.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Practicas WHERE codPractica = " + id);
	        if(rs.next())
	        {
	        	pr.setDesc(rs.getString("Descripcion"));
	        	pr.setId(rs.getInt("codPractica"));
	            
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return pr;
	}
	public double getValorPlan(int idpr, int idpl) {
		double valor = 0;
		//Aca hay que poner una consulta que traiga el precio de mayor fecha menor a la actual
		//correspondiente a la practica y plan enviados por parametro
		
		return valor;
	}

	
	
}
