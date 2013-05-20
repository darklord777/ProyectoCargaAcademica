package sv.ues.fia.cargaacademicaeisi;

public class Docente {
	public String IdDocente;
	public String IdContrato;
	public String Nombre;
	public String Apellido;
	public String GradoAcademico;
	public String Correo;
	public String Telefono;
	public int HorasAsignadas;
	
	public Docente() {
		// TODO Auto-generated constructor stub
	}

	public Docente(String idDocente, String idContrato, String nombre,String apellido, 
			String gradoAcademico, String correo, String telefono) {
		this.IdDocente = idDocente;
		this.IdContrato = idContrato;
		this.Nombre = nombre;
		this.Apellido = apellido;
		this.GradoAcademico = gradoAcademico;
		this.Correo = correo;
		this.Telefono = telefono;
	}

	public String getIdDocente() {
		return IdDocente;
	}

	public void setIdDocente(String idDocente) {
		this.IdDocente = idDocente;
	}

	public String getIdContrato() {
		return IdContrato;
	}

	public void setIdContrato(String idContrato) {
		this.IdContrato = idContrato;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		this.Apellido = apellido;
	}

	public String getGradoAcademico() {
		return GradoAcademico;
	}

	public void setGradoAcademico(String gradoAcademico) {
		this.GradoAcademico = gradoAcademico;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		this.Correo = correo;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		this.Telefono = telefono;
	}

	public int getHorasAsignadas() {
		return HorasAsignadas;
	}

	public void setHorasAsignadas(int horasAsignadas) {
		this.HorasAsignadas = horasAsignadas;
	}
}
