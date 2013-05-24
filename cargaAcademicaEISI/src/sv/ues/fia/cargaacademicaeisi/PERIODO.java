package sv.ues.fia.cargaacademicaeisi;

public class PERIODO {
	private String IdPeriodo;
	private String FechaIni;
	private String FechaFin;

	public PERIODO() {
	}

	public PERIODO(String IdPeriodo, String FechaIni, String FechaFin) {
		this.IdPeriodo = IdPeriodo;
		this.FechaIni = FechaIni;
		this.FechaFin = FechaFin;
	}

	public String getIdPeriodo() {
		return IdPeriodo;
	}

	public void setIdPeriodo(String IdPeriodo) {
		this.IdPeriodo = IdPeriodo;
	}

	public String getFechaIni() {
		return FechaIni;
	}

	public void setFechaIni(String FechaIni) {
		this.FechaIni = FechaIni;
	}

	public String getFechaFin() {
		return FechaFin;
	}

	public void setFechaFin(String FechaFin) {
		this.FechaFin = FechaFin;
	}
}
