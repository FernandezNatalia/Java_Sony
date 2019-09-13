package datos;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/java_turnos";
	private static String user = "root";
	private static String psw = "utn1";	
	public static Connection miCon;
	
	public static void getConnection() throws SQLException {				
		try {
			Class.forName(driver);
			
			miCon = DriverManager.getConnection(url,user,psw);			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}	
	}
	
	public static void closeConnection()
	{
		try {
			miCon.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	

}