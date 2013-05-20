package sv.ues.fia.cargaacademicaeisi;

public class TipoContrato {
	public String IdContrato;
	public String Tipo;
	public int Horas;
	
	public TipoContrato() {
		// TODO Auto-generated constructor stub
	}
	
	public TipoContrato(String idContrato, String tipo, int horas) {
		this.IdContrato = idContrato;
		this.Tipo = tipo;
		this.Horas = horas;
	}

	public String getIdContrato() {
		return IdContrato;
	}
	
	public void setIdContrato(String idContrato) {
		this.IdContrato = idContrato;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		this.Tipo = tipo;
	}
	public int getHoras() {
		return Horas;
	}
	public void setHoras(int horas) {
		this.Horas = horas;
	}
	
}
