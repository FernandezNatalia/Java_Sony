package entidades;

import java.util.ArrayList;

public class Especialista extends Usuario {

	private String especialidad;
	
	//Ver si queda
	private int codEspecialidad;
	private String nombre;
	private ArrayList<Practica> practicas;	
	
	public ArrayList<Practica> getPracticas() {
		return practicas;
	}
	public void setPracticas(ArrayList<Practica> practicas) {
		this.practicas = practicas;
	}
	public int getCodEspecialidad() {
		return codEspecialidad;
	}
	public void setCodEspecialidad(int codEspecialidad) {
		this.codEspecialidad = codEspecialidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	//Hasta aca
	
	
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
}
