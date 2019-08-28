package entidades;

import java.util.Date;
import datos.*;


public class Usuario {
	
	public static final int paciente = 1;
	public static final int especialista = 2;
	public static final int admin = 3;
	
	private int dni;
	private String nombre;
	private String apellido;
	private Date fechanacimiento;
	private int tipousuario;
	private String nombre_usuario; //Ver si va
	private String password;
	private String email;
	
	
	public int getDni() { return dni;}
	public void setDni(int dni) { this.dni = dni;}

	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	
	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public Date getFechanacimiento() {
		return fechanacimiento;
	}



	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}



	public int getTipousuario() {
		return tipousuario;
	}



	public void setTipousuario(int tipousuario) {
		this.tipousuario = tipousuario;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}




	
	
	

}
