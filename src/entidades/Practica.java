package entidades;
import datos.PracticaDatos;

public class Practica {

	private int id;
	private String desc;
	private double valor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	/*public Double getValor(Plan pl) {
		double valor = 0;
		int pl_id=0;
		PracticaDatos pd = new PracticaDatos();
		valor = pd.getValorPlan(this.id, pl_id);
		
		return valor;
	}*/
	
}
