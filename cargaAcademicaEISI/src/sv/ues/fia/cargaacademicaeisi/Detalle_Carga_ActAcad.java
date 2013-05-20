package sv.ues.fia.cargaacademicaeisi;

public class Detalle_Carga_ActAcad {
	private String iddocente;
	private String anio;
	private String numero;
	private String idactacad;

	public Detalle_Carga_ActAcad() {
		// TODO Auto-generated constructor stub
	}

	public Detalle_Carga_ActAcad(String iddocente, String anio, String numero,
			String idactacad) {
		this.iddocente = iddocente;
		this.anio = anio;
		this.numero = numero;
		this.idactacad = idactacad;
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

	public String getIdactacad() {
		return idactacad;
	}

	public void setIdactacad(String idactacad) {
		this.idactacad = idactacad;
	}

}