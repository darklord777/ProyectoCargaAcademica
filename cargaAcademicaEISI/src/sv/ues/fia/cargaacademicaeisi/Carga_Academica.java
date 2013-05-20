package sv.ues.fia.cargaacademicaeisi;

public class Carga_Academica {
	private String iddocente;
	private String anio;
	private String numero;

	public Carga_Academica() {
		// TODO Auto-generated constructor stub
	}

	public Carga_Academica(String iddocente, String anio, String numero) {
		this.iddocente = iddocente;
		this.anio = anio;
		this.numero = numero;
	}

	public String getIddocente() {
		return iddocente;
	}

	public void setIddocente(String iddocente) {
		this.iddocente = iddocente;
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

}// fin clase CARGA_ACADEMICA
