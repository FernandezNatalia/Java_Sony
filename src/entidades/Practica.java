package entidades;
import datos.PracticaDatos;

public class Practica {

	private int id;
	private String desc;
	
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
	
	/*public Double getValor(Plan pl) {
		double valor = 0;
		int pl_id=0;
		PracticaDatos pd = new PracticaDatos();
		valor = pd.getValorPlan(this.id, pl_id);
		
		return valor;
	}*/
	
}
