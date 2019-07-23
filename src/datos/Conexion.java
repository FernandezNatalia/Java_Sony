package datos;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/afatse";
	private static String user = "root";
	private static String psw = "utn1";	
	public static Connection miCon;
	
	public static void getConnection() {				
		try {
			Class.forName(driver);
			
			miCon = DriverManager.getConnection(url,user,psw);
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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