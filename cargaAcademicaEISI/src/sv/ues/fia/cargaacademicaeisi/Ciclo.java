package sv.ues.fia.cargaacademicaeisi;

public class Ciclo {
	private String anio;
	private String numero;
	private String fechaini;
	private String fechafin;

	public Ciclo() {
		// TODO Auto-generated constructor stub
	}

	public Ciclo(String anio, String numero, String fechaini, String fechafin) {
		this.anio = anio;
		this.numero = numero;
		this.fechaini = fechaini;
		this.fechafin = fechafin;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFechaini() {
		return fechaini;
	}

	public void setFechaini(String fechaini) {
		this.fechaini = fechaini;
	}

	public String getFechafin() {
		return fechafin;
	}

	public void setFechafin(String fechafin) {
		this.fechafin = fechafin;
	}

}// fin clase principal
