package sv.ues.fia.cargaacademicaeisi;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class ControlDB {
	/* Mario */
	private static final String[] camposDepto = new String[] {
			"IDDEPARTAMENTO", "NOM_DEPTO" };
	private static final String[] camposMat = new String[] { "CODIGOMATERIA",
			"NOM_MATERIA" };

	private static final String[] camposCiclo = new String[] { "ANIO",
			"NUMERO", "FECHAINI", "FECHAFIN" };

	private static final String[] camposLocal = new String[] { "IDLOCAL",
			"CAPACIDAD" };
	private static final String[] camposModalidadAA = new String[] {
			"IDMODALIDAD", "NOM_MODALIDAD", "DESCUENTO_HORAS" };

	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public ControlDB(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		private static final String BASE_DATOS = "dbcargaacademica.s3db";
		private static final int VERSION = 1;

		public DatabaseHelper(Context context) {
			super(context, BASE_DATOS, null, VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL("CREATE TABLE ACTIVIDAD_ACADEMICA ( IDACTACAD VARCHAR(6) NOT NULL PRIMARY KEY,IDMODALIDAD VARCHAR(6), NOM_ACT_ACAD VARCHAR(30), CARGO VARCHAR(20), CONSTRAINT fk_activida_modalida FOREIGN KEY (IDMODALIDAD) REFERENCES MODALIDAD_ACT_ACAD (IDMODALIDAD) ON DELETE RESTRICT);");
				db.execSQL("CREATE TABLE AREA_MATERIA ( IDAREAMAT VARCHAR(6) NOT NULL PRIMARY KEY, IDDEPARTAMENTO VARCHAR(6), CODIGOMATERIA VARCHAR(6), CONSTRAINT fk_area_departam FOREIGN KEY (IDDEPARTAMENTO) REFERENCES DEPARTAMENTO (IDDEPARTAMENTO) ON DELETE RESTRICT, CONSTRAINT fk_area_materia FOREIGN KEY (CODIGOMATERIA) REFERENCES MATERIA (CODIGOMATERIA) ON DELETE RESTRICT);");
				db.execSQL("CREATE TABLE [CARGA_ACADEMICA] ( [IDDOCENTE] VARCHAR(8)  NOT NULL, [ANIO] VARCHAR(4)  NOT NULL, [NUMERO] VARCHAR(2) NOT NULL, PRIMARY KEY ([IDDOCENTE],[ANIO],[NUMERO]));");
				db.execSQL("CREATE TABLE CARGO ( IDCARGO VARCHAR(5) NOT NULL PRIMARY KEY, NOM_CARGO  VARCHAR(20));");
				db.execSQL("CREATE TABLE [CICLO] ( [ANIO] VARCHAR(4)  NOT NULL, [NUMERO] VARCHAR(2)  NOT NULL, [FECHAINI] DATE DEFAULT CURRENT_DATE NULL, [FECHAFIN] DATE  NULL, PRIMARY KEY ([ANIO],[NUMERO]));");
				db.execSQL("CREATE TABLE DEPARTAMENTO ( IDDEPARTAMENTO  VARCHAR(6)  NOT NULL PRIMARY KEY, NOM_DEPTO VARCHAR(20));");
				db.execSQL("CREATE TABLE [DETALLE_CARGA_ACT_ACAD] ( [IDDOCENTE] VARCHAR(8) NULL, [ANIO] VARCHAR(4) NULL, [NUMERO] VARCHAR(2) NULL, [IDACTACAD] VARCHAR(6) NULL);");
				db.execSQL("CREATE TABLE [DETALLE_CARGA_MAT] ( [IDDOCENTE] VARCHAR(8) NULL, [ANIO] VARCHAR(4) NULL, [NUMERO] VARCHAR(2) NULL, [IDDETALLECURSO] VARCHAR(6) NULL);");
				db.execSQL("CREATE TABLE DETALLE_GRUPO_ASIGNADO ( IDDETALLECURSO  VARCHAR(6)  NOT NULL PRIMARY KEY, CODIGOMATERIA VARCHAR(6), IDMODALIDAD VARCHAR(6), IDLOCAL VARCHAR(6), constraint fk_detalle_materia FOREIGN KEY (CODIGOMATERIA) REFERENCES MATERIA (CODIGOMATERIA) ON DELETE RESTRICT, constraint fk_detalle_modalidad FOREIGN KEY (IDMODALIDAD) REFERENCES MODALIDAD_CURSO (IDMODALIDAD) ON DELETE RESTRICT, constraint fk_detalle_local FOREIGN KEY (IDLOCAL) REFERENCES LOCALES (IDLOCAL) ON DELETE RESTRICT);");
				db.execSQL("CREATE TABLE [DOCENTE] ( [IDDOCENTE] VARCHAR(8) PRIMARY KEY NOT NULL, [IDCONTRATO] VARCHAR(5) NULL, [NOMBRE] VARCHAR(50) NULL, [APELLIDO] VARCHAR(50) NULL, [GRADO_ACAD] VARCHAR(25) NULL, [CORREO] VARCHAR(20) NULL, [TELEFONO] VARCHAR(15) NULL, [HORAS_ASIG] INTEGER NULL);");
				db.execSQL("CREATE TABLE DOCENTE_CARGO ( IDDOCCAR VARCHAR(6) NOT NULL PRIMARY KEY, IDDOCENTE VARCHAR(8), IDPERIODO VARCHAR(6), IDCARGO VARCHAR(5), CONSTRAINT fk_docente_periodo FOREIGN KEY (IDPERIODO) REFERENCES PERIODO (IDPERIODO) ON DELETE RESTRICT, CONSTRAINT fk_docente_cargo FOREIGN KEY (IDCARGO) REFERENCES CARGO (IDCARGO) ON DELETE RESTRICT, CONSTRAINT fk_docente_docente FOREIGN KEY (IDDOCENTE) REFERENCES DOCENTE (IDDOCENTE) ON DELETE RESTRICT);");
				db.execSQL("CREATE TABLE DOCENTE_DPTO ( IDDEPARTAMENTO VARCHAR(6) NOT NULL, IDDOCENTE VARCHAR(8) NOT NULL, PRIMARY KEY (IDDEPARTAMENTO, IDDOCENTE), CONSTRAINT fk_docent_departam FOREIGN KEY (IDDEPARTAMENTO) REFERENCES DEPARTAMENTO (IDDEPARTAMENTO) ON DELETE RESTRICT, CONSTRAINT fk_docente_pertenece FOREIGN KEY (IDDOCENTE) REFERENCES DOCENTE (IDDOCENTE) ON DELETE RESTRICT);");
				db.execSQL("CREATE TABLE [LOCALES] ( [IDLOCAL] VARCHAR(6)  PRIMARY KEY NOT NULL,[CAPACIDAD] INTEGER  NULL);");
				db.execSQL("CREATE TABLE [MATERIA] ( [CODIGOMATERIA] VARCHAR(6)  PRIMARY KEY NOT NULL,[NOM_MATERIA] VARCHAR(20)  NULL);");
				db.execSQL("CREATE TABLE MAT_AREA_PUEDE_IMPARTIR ( IDDOCENTE  VARCHAR(8)  NOT NULL, IDAREAMAT  VARCHAR(6)  NOT NULL, PRIMARY KEY (IDDOCENTE, IDAREAMAT), CONSTRAINT fk_mat_area FOREIGN KEY (IDAREAMAT) REFERENCES AREA_MATERIA (IDAREAMAT) ON DELETE RESTRICT, CONSTRAINT fk_mat_docente FOREIGN KEY (IDDOCENTE) REFERENCES DOCENTE (IDDOCENTE) ON DELETE RESTRICT);");
				db.execSQL("CREATE TABLE [MODALIDAD_ACT_ACAD] ( [IDMODALIDAD] VARCHAR(6) PRIMARY KEY NOT NULL, [NOM_MODALIDAD] VARCHAR(25) NULL, [DESCUENTO_HORAS] INTEGER  NULL);");
				db.execSQL("CREATE TABLE MODALIDAD_CURSO ( IDMODALIDAD VARCHAR(6) NOT NULL PRIMARY KEY, NOM_MODALIDAD VARCHAR(20), DESCUENTO_HORAS  INTEGER);");
				db.execSQL("CREATE TABLE PERIODO ( IDPERIODO VARCHAR(6) NOT NULL PRIMARY KEY, FECHA_INI DATE, FECHA_FIN DATE);");
				db.execSQL("CREATE TABLE TIPO_CONTRATO ( IDCONTRATO VARCHAR(5) NOT NULL PRIMARY KEY, TIPO VARCHAR(25), HORAS INTEGER);");
				db.execSQL("CREATE TRIGGER fk_activida_modalida BEFORE INSERT ON ACTIVIDAD_ACADEMICA FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDMODALIDAD FROM MODALIDAD_ACT_ACAD WHERE IDMODALIDAD = NEW.IDMODALIDAD) IS NULL) THEN RAISE(ABORT, 'No existe esta Modalidad') END; END;");
				db.execSQL("CREATE TRIGGER fk_area_departam BEFORE INSERT ON AREA_MATERIA FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDDEPERTAMENTO FROM DEPARTAMENTO WHERE IDDEPARTAMENTO = NEW.IDDEPARTAMENTO) IS NULL) THEN RAISE(ABORT, 'No existe el Departamento') END; END;");
				db.execSQL("CREATE TRIGGER fk_area_materia BEFORE INSERT ON AREA_MATERIA FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT CODIGOMATERIA FROM MATERIA WHERE CODIGOMATERIA = NEW.CODIGOMATERIA) IS NULL) THEN RAISE(ABORT, 'No existe la Materia') END; END;");
				db.execSQL("CREATE TRIGGER [fk_carga_ciclo] BEFORE INSERT ON [CARGA_ACADEMICA] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT ANIO FROM CICLO WHERE (ANIO = NEW.ANIO AND NUMERO = NEW.NUMERO)) IS NULL) THEN RAISE(ABORT, 'No existe el Ciclo') END; END;");
				db.execSQL("CREATE TRIGGER [fk_carga_docente] BEFORE INSERT ON [CARGA_ACADEMICA] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDDOCENTE FROM DOCENTE WHERE IDDOCENTE = NEW.IDDOCENTE) IS NULL) THEN RAISE(ABORT, 'No existe el Docente') END; END;");
				db.execSQL("CREATE TRIGGER [fk_detalle_actividad] BEFORE INSERT ON [DETALLE_CARGA_ACT_ACAD] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDACTACAD FROM ACTIVIDAD_ACADEMICA WHERE IDACTACAD = NEW.IDACTACAD) IS NULL) THEN RAISE(ABORT, 'No existe esta Actividad Academica') END; END;");
				db.execSQL("CREATE TRIGGER [fk_detalle_carga_acad] BEFORE INSERT ON [DETALLE_CARGA_ACT_ACAD] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDDOCENTE FROM CARGA_ACADEMICA WHERE IDDOCENTE = NEW.IDDOCENTE AND ANIO = NEW.ANIO AND NUMERO = NEW.NUMERO) IS NULL) THEN RAISE(ABORT, 'No existe esta informacion de Carga Academica') END;END;");
				db.execSQL("CREATE TRIGGER [updateHorasActAcadMas] AFTER INSERT ON [DETALLE_CARGA_ACT_ACAD] FOR EACH ROW BEGIN UPDATE DOCENTE SET HORAS_ASIG = HORAS_ASIG + (SELECT DESCUENTO_HORAS FROM MODALIDAD_ACT_ACAD WHERE IDMODALIDAD = (SELECT IDMODALIDAD FROM ACTIVIDAD_ACADEMICA WHERE IDACTACAD = NEW.IDACTACAD)) WHERE DOCENTE.IDDOCENTE = NEW.IDDOCENTE; END;");
				db.execSQL("CREATE TRIGGER [updateHorasActAcadMenos] AFTER INSERT ON [DETALLE_CARGA_ACT_ACAD] FOR EACH ROW BEGIN UPDATE DOCENTE SET HORAS_ASIG = HORAS_ASIG - (SELECT DESCUENTO_HORAS FROM MODALIDAD_ACT_ACAD WHERE IDMODALIDAD = (SELECT IDMODALIDAD FROM ACTIVIDAD_ACADEMICA WHERE IDACTACAD = NEW.IDACTACAD)) WHERE DOCENTE.IDDOCENTE = NEW.IDDOCENTE;END;");
				db.execSQL("CREATE TRIGGER StopCargaActAcad BEFORE INSERT ON DETALLE_CARGA_ACT_ACAD FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT HORAS_ASIG FROM DOCENTE WHERE IDDOCENTE = NEW.IDDOCENTE)>=(SELECT HORAS FROM TIPO_CONTRATO WHERE IDCONTRATO = (SELECT IDCONTRATO FROM DOCENTE WHERE IDDOCENTE = NEW.IDDOCENTE))) THEN RAISE(ABORT, 'Ya NO se le puede asiganar mas carga') END;END;");
				db.execSQL("CREATE TRIGGER [fk_detalle_curso] BEFORE INSERT ON [DETALLE_CARGA_MAT] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDDETALLECURSO FROM DETALLE_GRUPO_ASIGNADO WHERE IDDETALLECURSO = NEW.IDDETALLECURSO) IS NULL) THEN RAISE(ABORT, 'No existe esta informacion de este Curso') END;END;");
				db.execSQL("CREATE TRIGGER [fk_detalle_carga_mat] BEFORE INSERT ON [DETALLE_CARGA_MAT] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDDOCENTE FROM CARGA_ACADEMICA WHERE IDDOCENTE = NEW.IDDOCENTE AND ANIO = NEW.ANIO AND NUMERO = NEW.NUMERO) IS NULL) THEN RAISE(ABORT, 'No existe esta informacion de Carga Academica') END; END;");
				db.execSQL("CREATE TRIGGER [updateHorasMatMas] AFTER INSERT ON [DETALLE_CARGA_MAT] FOR EACH ROW BEGIN UPDATE DOCENTE SET HORAS_ASIG = HORAS_ASIG + (SELECT DESCUENTO_HORAS FROM MODALIDAD_CURSO WHERE IDMODALIDAD = (SELECT IDMODALIDAD FROM DETALLE_GRUPO_ASIGNADO WHERE IDDETALLECURSO = NEW.IDDETALLECURSO)) WHERE DOCENTE.IDDOCENTE = NEW.IDDOCENTE; END;");
				db.execSQL("CREATE TRIGGER [updateHorasMatMenos] AFTER DELETE ON [DETALLE_CARGA_MAT] FOR EACH ROW BEGIN UPDATE DOCENTE SET HORAS_ASIG = HORAS_ASIG - (SELECT DESCUENTO_HORAS FROM MODALIDAD_CURSO WHERE IDMODALIDAD = (SELECT IDMODALIDAD FROM DETALLE_GRUPO_ASIGNADO WHERE IDDETALLECURSO = OLD.IDDETALLECURSO)) WHERE DOCENTE.IDDOCENTE = OLD.IDDOCENTE; END;");
				db.execSQL("CREATE TRIGGER [StopCargaMat] BEFORE INSERT ON [DETALLE_CARGA_MAT] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT HORAS_ASIG FROM DOCENTE WHERE IDDOCENTE = NEW.IDDOCENTE)>=(SELECT HORAS FROM TIPO_CONTRATO WHERE IDCONTRATO = (SELECT IDCONTRATO FROM DOCENTE WHERE IDDOCENTE = NEW.IDDOCENTE))) THEN RAISE(ABORT, 'Ya NO se le puede asiganar mas carga') END;END;");
				db.execSQL("CREATE TRIGGER fk_detalle_materia BEFORE INSERT ON DETALLE_GRUPO_ASIGNADO FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT CODIGOMATERIA FROM MATERIA WHERE CODIGOMATERIA = NEW.CODIGOMATERIA) IS NULL) THEN RAISE(ABORT, 'No existe esta Materia') END;END;");
				db.execSQL("CREATE TRIGGER fk_detalle_modalidad BEFORE INSERT ON DETALLE_GRUPO_ASIGNADO FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDMODALIDAD FROM MODALIDAD_CURSO WHERE IDMODALIDAD = NEW.IDMODALIDAD) IS NULL) THEN RAISE(ABORT, 'No existe esta Modalidad') END; END;");
				db.execSQL("CREATE TRIGGER fk_detalle_local BEFORE INSERT ON DETALLE_GRUPO_ASIGNADO FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDLOCAL FROM LOCALES WHERE IDLOCAL = NEW.IDLOCAL) IS NULL) THEN RAISE(ABORT, 'No existe este Local') END; END;");
				db.execSQL("CREATE TRIGGER [fk_docente_contrato] BEFORE INSERT ON [DOCENTE] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDCONTRATO FROM TIPO_CONTRATO WHERE IDCONTRATO = NEW.IDCONTRATO) IS NULL) THEN RAISE(ABORT, 'No existe este Tipo de Contrato') END;END;");
				db.execSQL("CREATE TRIGGER fk_docente_periodo BEFORE INSERT ON DOCENTE_CARGO FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDPERIODO FROM PERIODO WHERE IDPERIODO = NEW.IDPERIODO) IS NULL) THEN RAISE(ABORT, 'No existe este Periodo') END; END;");
				db.execSQL("CREATE TRIGGER fk_docente_cargo BEFORE INSERT ON DOCENTE_CARGO FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDCARGO FROM CARGO WHERE IDCARGO = NEW.IDCARGO) IS NULL) THEN RAISE(ABORT, 'No existe este Cargo') END; END;");
				db.execSQL("CREATE TRIGGER fk_docente_docente BEFORE INSERT ON DOCENTE_CARGO FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDDOCENTE FROM DOCENTE WHERE IDDOCENTE = NEW.IDDOCENTE) IS NULL) THEN RAISE(ABORT, 'No existe este Docente') END;END;");
				db.execSQL("CREATE TRIGGER fk_docente_pertenece BEFORE INSERT ON DOCENTE_DPTO FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDDOCENTE FROM DOCENTE WHERE IDDOCENTE = NEW.IDDOCENTE) IS NULL) THEN RAISE(ABORT, 'No existe este Docente') END;END;");
				db.execSQL("CREATE TRIGGER [fk_docente_departamento] BEFORE INSERT ON [DOCENTE_DPTO] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDDEPARTAMENTO FROM DEPARTAMENTO WHERE IDDEPARTAMENTO = NEW.IDDEPARTAMENTO) IS NULL) THEN RAISE(ABORT, 'No existe este Departamento') END;END;");
				db.execSQL("CREATE TRIGGER fk_mat_area BEFORE INSERT ON MAT_AREA_PUEDE_IMPARTIR FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDAREAMAT FROM AREA_MATERIA WHERE IDAREAMAT = NEW.IDAREAMAT) IS NULL) THEN RAISE(ABORT, 'No existe esta Materia') END; END;");
				db.execSQL("CREATE TRIGGER fk_mat_docente BEFORE INSERT ON MAT_AREA_PUEDE_IMPARTIR FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDDOCENTE FROM DOCENTE WHERE IDDOCENTE = NEW.IDDOCENTE) IS NULL) THEN RAISE(ABORT, 'No existe este Docente') END; END;");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}
	}

	public void abrir() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return;
	}

	public void cerrar() {
		DBHelper.close();
	}

	/** TODO EL CODIGO DE CONTROL DE DCONTROLD DE BD ASIGNACION Alexis */
	/**
	 * Conseguir todas las etiquetas lista retornos de etiquetas!! IMPORTANTE
	 * */
	public List<String> getAllLabels(String selectQuery, int posicion) {
		List<String> labels = new ArrayList<String>();

		// Select All Query
		// String selectQuery = "SELECT  * FROM PAIS order by nom_pais" ;

		db = DBHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				labels.add(cursor.getString(posicion));
			} while (cursor.moveToNext());
		}

		// closing connection
		cursor.close();
		db.close();

		// returning lables
		return labels;
	}

	public String insertarCiclo(Ciclo ciclo) {
		String regInsertados = "Registro de Ciclo Insertado Nº= ";
		long contador = 0;
		ContentValues ciclo1 = new ContentValues();
		ciclo1.put("ANIO", ciclo.getAnio());
		ciclo1.put("NUMERO", ciclo.getNumero());
		ciclo1.put("FECHAINI", ciclo.getFechaini());
		ciclo1.put("FECHAFIN", ciclo.getFechafin());

		contador = db.insert("CICLO", null, ciclo1);

		if (contador == -1 || contador == 0) {
			regInsertados = "Error al Insertar el registro Ciclo, Ciclo Duplicado. Verificar Inserción";
		} else {
			regInsertados = regInsertados + contador;
		}
		return regInsertados;
	}

	public Ciclo consultarCiclo(String anio, String numciclo) {
		String[] id = { anio, numciclo };
		Cursor cursor = db.query("CICLO", camposCiclo,
				"ANIO = ? AND NUMERO = ?", id, null, null, null);
		if (cursor.moveToFirst()) {
			Ciclo ciclo = new Ciclo();
			ciclo.setAnio(cursor.getString(0));
			ciclo.setNumero(cursor.getString(1));
			ciclo.setFechaini(cursor.getString(2));
			ciclo.setFechafin(cursor.getString(3));
			return ciclo;
		} else {
			return null;
		}
	}

	/** METODOS MARIO */
	public String insertar(Departamento departamento) {
		String regInsertados = "Registro insertado en la fila No.=";
		long contador = 0;
		ContentValues depto = new ContentValues();
		depto.put("IDDEPARTAMENTO", departamento.getIddepartamento());
		depto.put("NOM_DEPTO", departamento.getNom_depto());
		contador = db.insert("DEPARTAMENTO", null, depto);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error, registro duplicado. Verificar Insercion";
		} else {
			regInsertados += contador;
		}
		return regInsertados;
	}

	public String insertar(Materia materia) {
		String regInsertados = "Registro insertado en la fila No.=";
		long contador = 0;
		ContentValues mat = new ContentValues();
		mat.put("CODIGOMATERIA", materia.getCodigomateria());
		mat.put("NOM_MATERIA", materia.getNom_materia());
		contador = db.insert("MATERIA", null, mat);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error, registro duplicado. Verificar Insercion";
		} else {
			regInsertados += contador;
		}
		return regInsertados;
	}

	public Departamento consultarDepto(String idepto) {
		String[] id = { idepto };
		Cursor cursor = db.query("DEPARTAMENTO", camposDepto,
				"IDDEPARTAMENTO = ?", id, null, null, null);
		if (cursor.moveToFirst()) {
			Departamento departamento = new Departamento();
			departamento.setIddepartamento(cursor.getString(0));
			departamento.setNom_depto(cursor.getString(1));
			return departamento;
		} else {
			return null;
		}
	}

	public String consultarTablas() {
		String tablas = "Tablas:";
		String[] id = { "table" };
		Cursor cursor = db.query("sqlite_master", new String[] { "name" },
				"type = ?", id, null, null, null);
		if (cursor.moveToFirst()) {
			tablas += "\n" + cursor.getString(0);
		} else {
			tablas = "No hay tablas";
		}
		return tablas;
	}

	public List<String> getAllIdDeptos() {
		List<String> idDeptos = new ArrayList<String>();
		Cursor cursor = db
				.rawQuery(
						"select IDDEPARTAMENTO from DEPARTAMENTO order by IDDEPARTAMENTO;",
						null);
		if (cursor.moveToFirst()) {
			do {
				idDeptos.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return idDeptos;
	}

	public List<String> getAllIdMaterias() {
		List<String> idMaterias = new ArrayList<String>();
		Cursor cursor = db.rawQuery(
				"select codigomateria from materia order by codigomateria;",
				null);
		if (cursor.moveToFirst()) {
			do {
				idMaterias.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return idMaterias;
	}

	public String actualizar(Departamento departamento) {
		String[] id = { departamento.getIddepartamento() };
		ContentValues values = new ContentValues();
		values.put("NOM_DEPTO", departamento.getNom_depto());
		db.update("DEPARTAMENTO", values, "IDDEPARTAMENTO = ?", id);
		return "Registro actualizado correctamente";
	}

	public String eliminar(Departamento departamento) {
		String regAfectados = "";
		int contador = 0;
		if (verificarIntegridad(departamento, 7)
				|| verificarIntegridad(departamento, 8)) {
			regAfectados += "No se puede borrar,";
			if (verificarIntegridad(departamento, 7))
				regAfectados += " DOCENTE_DPTO tiene registros.";
			if (verificarIntegridad(departamento, 8))
				regAfectados += " AREA_MATERIA tiene registros.";
			return regAfectados;
		}

		regAfectados = "Filas afectadas=";
		contador += db.delete("DOCENTE_DPTO",
				"IDDEPARTAMENTO='" + departamento.getIddepartamento() + "'",
				null);

		contador += db.delete("AREA_MATERIA",
				"IDDEPARTAMENTO='" + departamento.getIddepartamento() + "'",
				null);

		contador += db.delete("DEPARTAMENTO",
				"IDDEPARTAMENTO='" + departamento.getIddepartamento() + "'",
				null);
		regAfectados += contador;
		return regAfectados;
	}

	public Materia consultarMateria(String codmat) {
		String[] id = { codmat };
		Cursor cursor = db.query("MATERIA", camposMat, "CODIGOMATERIA = ?", id,
				null, null, null);
		if (cursor.moveToFirst()) {
			Materia materia = new Materia();
			materia.setCodigomateria(cursor.getString(0));
			materia.setNom_materia(cursor.getString(1));
			return materia;
		} else {
			return null;
		}
	}

	/** METODOS EMERSON */

	/** METODOS AGUSTIN */
	public String insertar(Locales local) {
		String regInsertados = "Registro insertado en la fila No.=";
		long contador = 0;
		ContentValues loc = new ContentValues();
		loc.put("IDLOCAL", local.getIdlocal());
		loc.put("CAPACIDAD", local.getCapacidad());
		contador = db.insert("LOCALES", null, loc);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error. Verificar Insercion";
		} else {
			regInsertados += contador;
		}
		return regInsertados;
	}

	public String insertar(Modalidad_Act_Acad modalidadAA) {
		String regInsertados = "Registro insertado en la fila No.=";
		long contador = 0;
		ContentValues modalidad = new ContentValues();
		modalidad.put("IDMODALIDAD", modalidadAA.getIdmodalidad());
		modalidad.put("NOM_MODALIDAD", modalidadAA.getNom_modalidad());
		modalidad.put("DESCUENTO_HORAS", modalidadAA.getDescuento_horas());
		contador = db.insert("MODALIDAD_ACT_ACAD", null, modalidad);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error. Verificar Insercion";
		} else {
			regInsertados += contador;
		}
		return regInsertados;
	}

	/** METODOS SERGIO */

	/* Verificacion de integridad */
	// FUNCION DE VERIFICACION DE INTEGRIDAD
	// 1 AL 6 aLEXIS
	// 7 AL 12 MARIO
	// 13 AÑ 18 EMERSON
	// 19 AL 24 AGUSTIN
	// 25 AL 31 SERGIO
	private boolean verificarIntegridad(Object dato, int relacion)
			throws SQLException {

		switch (relacion) {

		case 1: {
			Ciclo ciclo = (Ciclo) dato;
			Cursor c = db.query(
					true,
					"CICLO",
					new String[] { "carnet," },
					"carnet ='" + ciclo.getAnio() + "' AND numero ='"
							+ ciclo.getNumero() + "'", null, null, null, null,
					null);
			if (c.moveToFirst())
				return true;
			else
				return false;
		}

		case 2: {

			return true;
		}

		case 3: {

			return true;

		}

		case 4: {
			return true;
		}

		case 5: {
			return true;
		}

		case 6: {
			return true;
		}
		case 7: {
			Departamento departamento = (Departamento) dato;
			Cursor cursor = db.query(true, "DOCENTE_DPTO",
					new String[] { "IDDEPARTAMENTO" }, "IDDEPARTAMENTO='"
							+ departamento.getIddepartamento() + "'", null,
					null, null, null, null);
			if (cursor.moveToFirst())
				return true;
			else
				return false;
		}
		case 8: {
			Departamento departamento = (Departamento) dato;
			Cursor cursor = db.query(true, "AREA_MATERIA",
					new String[] { "IDDEPARTAMENTO" }, "IDDEPARTAMENTO='"
							+ departamento.getIddepartamento() + "'", null,
					null, null, null, null);
			if (cursor.moveToFirst())
				return true;
			else
				return false;
		}
		case 9: {
			return true;
		}
		case 10: {
			return true;
		}
		case 11: {
			return true;
		}
		case 12: {
			return true;
		}
		case 13: {
			return true;
		}

		default:
			return false;

		}

	}

	public String llenarBDCarnet() {

		return "Guardo Correctamente";
	}

}
