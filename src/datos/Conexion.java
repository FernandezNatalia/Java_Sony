package datos;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://db4free.net/pruebsql";
	private static String user = "juan9889";
	private static String psw = "juanjuan";	
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
		
		//return miCon;
	
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