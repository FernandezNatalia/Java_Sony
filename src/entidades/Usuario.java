package entidades;

import java.util.Date;
public class Usuario {
	
	public static final int paciente = 1;
	public static final int especialista = 2;
	public static final int admin = 3;
	
	private int dni;
	private String nombre;
	private String apellido;
	private Date fechanacimiento;
	private int tipousuario;
	private String password;
	private String email;
	
	public Usuario () {
		
	}
	public Usuario(int dni,String nombre,String apellido,String email,Date fechaNacimiento,String password,int tipo){		
		setDni(dni);
		setNombre(nombre);
		setApellido(apellido);
		setEmail(email);
		setFechanacimiento(fechaNacimiento);
		setPassword(password);
		setTipousuario(tipo);
	}	
	
	public int getDni() {
		return dni;
		}
	public void setDni(int dni) { 
		this.dni = dni;
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
