package entidades;

import java.util.ArrayList;

public class Especialista extends Usuario {

	private String especialidad;
	private int codEspecialidad;
	
	private ArrayList<Practica> practicas;
	
	
	
	//Ver si queda
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

	//Hasta aca

	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
}
