package sv.ues.fia.cargaacademicaeisi;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.DTDHandler;

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
	private static final String[] camposAreaMat = new String[] { "IDAREAMAT",
			"IDDEPARTAMENTO", "CODIGOMATERIA" };
	private static final String[] camposDetGpoAsig = new String[] {
			"IDDETALLECURSO", "CODIGOMATERIA", "IDMODALIDAD", "IDLOCAL" };

	private static final String[] camposLocal = new String[] { "IDLOCAL",
			"CAPACIDAD" };
	private static final String[] camposModalidadAA = new String[] {
			"IDMODALIDAD", "NOM_MODALIDAD", "DESCUENTO_HORAS" };

	private static final String[] camposModCurso = new String[] {
			"IDMODALIDAD", "NOM_MODALIDAD", "DESCUENTO_HORAS" };

	private static final String[] camposActAcademica = new String[] {
			"IDACTACAD", "IDMODALIDAD", "NOM_ACT_ACAD", "CARGO" };

	private static final String[] camposCargaAcademica = new String[] {
			"IDDOCENTE", "ANIO", "NUMERO" };

	private static final String[] camposCiclo = new String[] { "ANIO",
			"NUMERO", "FECHAINI", "FECHAFIN" };

	/* Yo */
	private static final String[] camposContrato = new String[] { "IDCONTRATO",
			"TIPO", "HORAS" };
	/* Fin YO */
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public ControlDB(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		private static final String BASE_DATOS = "databasecargaacademica.s3db";
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
				db.execSQL("CREATE TRIGGER fk_area_departam BEFORE INSERT ON AREA_MATERIA FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDDEPARTAMENTO FROM DEPARTAMENTO WHERE IDDEPARTAMENTO = NEW.IDDEPARTAMENTO) IS NULL) THEN RAISE(ABORT, 'No existe el Departamento') END; END;");
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

	/** TODO EL CODIGO DE CONTROL DE DCONTROLD DE BD ASIGNACION alexis */

	// Conseguir todas las etiquetas lista retornos de etiquetas!! IMPORTANTE
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

	// TABLA CICLO**************************************************
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

	public String eliminar(Ciclo ciclo) {
		String regAfectados = "Registro de Ciclo afectados= ";
		String where = "ANIO='" + ciclo.getAnio() + "'" + " AND NUMERO='"
				+ ciclo.getNumero() + "'";
		int contador = 0;
		if (verificarIntegridad(ciclo, 1)) {
			// contador += db.delete("ciclo",where, null);
			regAfectados = "No es posible eliminar, Existe carga academica asignada a este ciclo.";
		} else {
			contador += db.delete("CICLO", where, null);
			regAfectados += contador;
		}

		return regAfectados;
	}

	public String actualizar(Ciclo ciclo) {

		String[] id = { ciclo.getAnio(), ciclo.getNumero() };
		ContentValues cv = new ContentValues();
		cv.put("FECHAINI", ciclo.getFechaini());
		cv.put("FECHAFIN", ciclo.getFechafin());
		db.update("CICLO", cv, "ANIO = ? AND NUMERO = ?", id);
		return "Registro Actualizado Correctamente";
	}

	// TABLA CARGA ACADEMICA********************************************
	public String insertar(Carga_Academica carga) {
		String regInsertados = "Registro Carga Academica Insertado Nº= ";
		long contador = 0;

		if (verificarIntegridad(carga, 2)) {
			ContentValues carga_acad = new ContentValues();
			carga_acad.put("IDDOCENTE", carga.getIddocente());
			carga_acad.put("ANIO", carga.getAnio());
			carga_acad.put("NUMERO", carga.getNumero());
			contador = db.insert("CARGA_ACADEMICA", null, carga_acad);
		}

		if (contador == -1 || contador == 0) {
			regInsertados = "Error al Insertar el Carga Academica, Registro Duplicado. Verificar inserción";
		} else {
			regInsertados = regInsertados + contador;
		}

		return regInsertados;
	}

	public String eliminar(Carga_Academica carga) {
		// verificando si tiene REGISTROS hijos
		String regAfectados = "Registro Carga Academica Eliminados= ";
		String where = "IDDOCENTE ='" + carga.getIddocente() + "' AND ANIO = '"
				+ carga.getAnio() + "' AND NUMERO='" + carga.getNumero() + "'";
		int contador = 0;
		if (verificarIntegridad(carga, 3)) {
			// contador += db.delete("ciclo",where, null);
			regAfectados = "No es posible eliminar, Existe Carga de Materias o Actividades Academicas Asignadas a este Docente.";
		} else {
			contador += db.delete("CARGA_ACADEMICA", where, null);
			regAfectados += contador;
		}

		return regAfectados;
	}

	// TABLA DETALLE_CARGA_MAT**************************************************
	// VERIFICAR INTEGRIDAD?????????????????????????????????????????????????
	public String insertar(Detalle_Carga_Mat cargamat) {
		String regInsertados = "Registro Insertado de Carga de Materias  Nº= ";
		long contador = 0;
		ContentValues cargamaterias = new ContentValues();
		cargamaterias.put("IDDOCENTE", cargamat.getIddocente());
		cargamaterias.put("ANIO", cargamat.getAnio());
		cargamaterias.put("NUMERO", cargamat.getNumero());
		cargamaterias.put("IDDETALLECURSO", cargamat.getIddetallecurso());

		if (verificarIntegridad(cargamat, 4)) {
			// contador += db.delete("ciclo",where, null);
			regInsertados = "Error al Insertar Carga de Materias, Registro Duplicado. Verificar inserción";
		} else {
			contador = db.insert("DETALLE_CARGA_MAT", null, cargamaterias);
			regInsertados = regInsertados + contador;
		}

		/*
		 * if (contador == -1 || contador == 0) { regInsertados =
		 * "Error al Insertar el registro Ciclo, Ciclo Duplicado. Verificar Inserción"
		 * ; } else { regInsertados = regInsertados + contador; }
		 */
		return regInsertados;
	}

	public String eliminar(Detalle_Carga_Mat cargamat) {
		// verificando si tiene REGISTROS hijos
		String regAfectados = "Registro Carga Materia Eliminados= ";
		String where = "IDDOCENTE ='" + cargamat.getIddocente()
				+ "' AND ANIO = '" + cargamat.getAnio() + "' AND NUMERO='"
				+ cargamat.getNumero() + "' AND IDDETALLECURSO='"
				+ cargamat.getIddetallecurso() + "'";
		int contador = 0;
		contador += db.delete("DETALLE_CARGA_MAT", where, null);
		regAfectados += contador;
		return regAfectados;
	}

	// TABLA DETALLE_ACT_ACAD**************************************************

	// VERIFICAR INTEGRIDAD?????????????????????????????????????????????????
	public String insertar(Detalle_Carga_ActAcad cargamat) {
		String regInsertados = "Registro Insertado de Detalle Carga de Academica  Nº= ";
		long contador = 0;
		ContentValues cargaactividad = new ContentValues();
		cargaactividad.put("IDDOCENTE", cargamat.getIddocente());
		cargaactividad.put("ANIO", cargamat.getAnio());
		cargaactividad.put("NUMERO", cargamat.getNumero());
		cargaactividad.put("IDACTACAD", cargamat.getIdactacad());

		if (verificarIntegridad(cargamat, 5)) {
			regInsertados = "Error al Insertar el Detalle de Carga Academica, Registro Duplicado. Verificar inserción";
		} else {
			contador = db
					.insert("DETALLE_CARGA_ACT_ACAD", null, cargaactividad);
			regInsertados = regInsertados + contador;
		}
		return regInsertados;
	}

	public String eliminar(Detalle_Carga_ActAcad cargaactiv) {
		// verificando si tiene REGISTROS hijos
		String regAfectados = "Registro Carga Materia Eliminados= ";
		String where = "IDDOCENTE ='" + cargaactiv.getIddocente()
				+ "' AND ANIO = '" + cargaactiv.getAnio() + "' AND NUMERO='"
				+ cargaactiv.getNumero() + "' AND IDACTACAD='"
				+ cargaactiv.getIdactacad() + "'";
		int contador = 0;
		contador += db.delete("DETALLE_CARGA_ACT_ACAD", where, null);
		regAfectados += contador;
		return regAfectados;
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

	public String insertar(AreaMateria areaMateria) {
		String regInsertados = "Registro insertado en la fila No.=";
		long contador = 0;
		ContentValues areamat = new ContentValues();
		areamat.put("IDAREAMAT", areaMateria.getIdareamat());
		areamat.put("IDDEPARTAMENTO", areaMateria.getIddepartamento());
		areamat.put("CODIGOMATERIA", areaMateria.getCodigomateria());
		contador = db.insert("AREA_MATERIA", null, areamat);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error, registro duplicado. Verificar Insercion";
		} else {
			regInsertados += contador;
		}
		return regInsertados;
	}

	public String insertar(DetalleGrupoAsignado grupoAsignado) {
		String regInsertados = "Registro insertado en la fila No.=";
		long contador = 0;
		ContentValues gpoasig = new ContentValues();
		gpoasig.put("IDDETALLECURSO", grupoAsignado.getIddetallecurso());
		gpoasig.put("CODIGOMATERIA", grupoAsignado.getCodigomateria());
		gpoasig.put("IDMODALIDAD", grupoAsignado.getIdmodalidad());
		gpoasig.put("IDLOCAL", grupoAsignado.getIdlocal());
		contador = db.insert("DETALLE_GRUPO_ASIGNADO", null, gpoasig);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error. Verificar Insercion";
		} else {
			regInsertados += contador;
		}
		return regInsertados;
	}

	public List<String> getAllIdModCurso() {
		List<String> idMaterias = new ArrayList<String>();
		Cursor cursor = db
				.rawQuery(
						"select IDMODALIDAD from MODALIDAD_CURSO order by IDMODALIDAD;",
						null);
		if (cursor.moveToFirst()) {
			do {
				idMaterias.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return idMaterias;
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

	public AreaMateria consultarAreaMateria(String idaremat) {
		String[] id = { idaremat };

		Cursor cursor = db.query("AREA_MATERIA", camposAreaMat,
				"IDAREAMAT = ?", id, null, null, null);

		/*
		 * String sql =
		 * "select idareamat,(select nom_depto from departamento) departamento, "
		 * + "(select nom_materia from materia) materia " +
		 * "from area_materia where idareamat='" + idaremat + "';";
		 * 
		 * Cursor cursor = db.rawQuery(sql, null);
		 */

		if (cursor.moveToFirst()) {
			AreaMateria areaMateria = new AreaMateria();
			areaMateria.setIdareamat(cursor.getString(0));
			areaMateria.setIddepartamento(cursor.getString(1));
			areaMateria.setCodigomateria(cursor.getString(2));
			return areaMateria;
		} else {
			return null;
		}
	}

	public DetalleGrupoAsignado consultarDetGpoAsig(String idetcurso) {
		String[] id = { idetcurso };
		Cursor cursor = db.query("DETALLE_GRUPO_ASIGNADO", camposDetGpoAsig,
				"IDDETALLECURSO = ?", id, null, null, null);
		if (cursor.moveToFirst()) {
			DetalleGrupoAsignado grupoAsignado = new DetalleGrupoAsignado();
			grupoAsignado.setIddetallecurso(cursor.getString(0));
			grupoAsignado.setCodigomateria(cursor.getString(1));
			grupoAsignado.setIdmodalidad(cursor.getString(2));
			grupoAsignado.setIdlocal(cursor.getString(3));
			return grupoAsignado;
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

	public List<String> getAllIdAreaMats() {
		List<String> idMaterias = new ArrayList<String>();
		Cursor cursor = db.rawQuery(
				"select IDAREAMAT from AREA_MATERIA order by IDAREAMAT;", null);
		if (cursor.moveToFirst()) {
			do {
				idMaterias.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return idMaterias;
	}

	public List<String> getAllIdDetGpoAsig() {
		List<String> idMaterias = new ArrayList<String>();
		Cursor cursor = db
				.rawQuery(
						"select IDDETALLECURSO from DETALLE_GRUPO_ASIGNADO order by IDDETALLECURSO;",
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

	public String actualizar(Materia materia) {
		String[] id = { materia.getCodigomateria() };
		ContentValues values = new ContentValues();
		values.put("NOM_MATERIA", materia.getNom_materia());
		db.update("MATERIA", values, "CODIGOMATERIA = ?", id);
		return "Registro actualizado correctamente";
	}

	public String eliminar(Departamento departamento) {
		String regAfectados = "";
		int contador = 0;
		if (verificarIntegridad(departamento, 7)
				|| verificarIntegridad(departamento, 8)) {
			regAfectados += "Tiene registros hijos\nNo se puede borrar,";
			if (verificarIntegridad(departamento, 7))
				regAfectados += " DOCENTE_DPTO tiene registros.";
			if (verificarIntegridad(departamento, 8))
				regAfectados += " AREA_MATERIA tiene registros.";
			return regAfectados;
		}

		regAfectados = "No tiene registros hijos\nFilas afectadas=";
		/*
		 * contador += db.delete("DOCENTE_DPTO", "IDDEPARTAMENTO='" +
		 * departamento.getIddepartamento() + "'", null);
		 * 
		 * contador += db.delete("AREA_MATERIA", "IDDEPARTAMENTO='" +
		 * departamento.getIddepartamento() + "'", null);
		 */
		contador += db.delete("DEPARTAMENTO",
				"IDDEPARTAMENTO='" + departamento.getIddepartamento() + "'",
				null);
		regAfectados += contador;
		return regAfectados;
	}

	public String eliminar(Materia materia) {
		String regAfectados = "";
		int contador = 0;
		if (verificarIntegridad(materia, 9) || verificarIntegridad(materia, 10)) {
			regAfectados += "Tiene registros hijos\nNo se puede borrar,";
			if (verificarIntegridad(materia, 9))
				regAfectados += " DETALLE_GRUPO_ASIGNADO tiene registros.";
			if (verificarIntegridad(materia, 10))
				regAfectados += " AREA_MATERIA tiene registros.";
			return regAfectados;
		}
		regAfectados = "No tiene registros hijos\nFilas afectadas=";
		contador += db.delete("MATERIA",
				"CODIGOMATERIA='" + materia.getCodigomateria() + "'", null);
		regAfectados += contador;
		return regAfectados;
	}

	public String eliminar(AreaMateria areaMateria) {
		String regAfectados = "";
		int contador = 0;
		regAfectados = "No tiene registros hijos\nFilas afectadas=";
		contador += db.delete("AREA_MATERIA",
				"IDAREAMAT='" + areaMateria.getIdareamat() + "'", null);
		regAfectados += contador;
		return regAfectados;
	}

	public String eliminar(DetalleGrupoAsignado grupoAsignado) {
		String regAfectados = "";
		int contador = 0;
		if (verificarIntegridad(grupoAsignado, 11)) {
			regAfectados += "No se puede borrar\nTiene registros hijos en DETALLE_CARGA_MAT.";
			return regAfectados;
		}
		regAfectados = "No tiene registros hijos\nFilas afectadas=";
		contador += db.delete("DETALLE_GRUPO_ASIGNADO", "IDDETALLECURSO='"
				+ grupoAsignado.getIddetallecurso() + "'", null);
		regAfectados += contador;
		return regAfectados;
	}

	public String getAsociado(String tabla, String campo, String clave,
			String valor) {
		String sql = "select " + campo + " from " + tabla + " where " + clave
				+ "='" + valor + "';";
		String asocido = "no hay dato";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToFirst()) {
			asocido = cursor.getString(0);
		}

		return asocido;
	}

	/** METODOS EMERSON */
	public String InsertarContrato(TipoContrato tipocontrato) {
		String regInsertados = "Registro insertado en la fila No.= ";
		long contador = 0;
		ContentValues contrato = new ContentValues();
		contrato.put("IDCONTRATO", tipocontrato.getIdContrato());
		contrato.put("TIPO", tipocontrato.getTipo());
		contrato.put("HORAS", tipocontrato.getHoras());
		contador = db.insert("TIPO_CONTRATO", null, contrato);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error. Verificar Insercion";
		} else {
			regInsertados += contador;
		}

		return regInsertados;
	}

	public TipoContrato ConsultarContrato(String idcontrato) {
		String[] id = { idcontrato };
		Cursor cursor = db.query("TIPO_CONTRATO", camposContrato,
				"IDCONTRATO = ?", id, null, null, null);
		if (cursor.moveToFirst()) {
			TipoContrato contrato = new TipoContrato();
			contrato.setIdContrato(cursor.getString(0));
			contrato.setTipo(cursor.getString(1));
			contrato.setHoras(cursor.getInt(2));
			return contrato;
		} else {
			return null;
		}
	}

	public List<String> getAllIdContratos() {
		List<String> idContratos = new ArrayList<String>();
		Cursor cursor = db.rawQuery(
				"select IDCONTRATO from TIPO_CONTRATO order by IDCONTRATO;",
				null);
		if (cursor.moveToFirst()) {
			do {
				idContratos.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return idContratos;
	}

	public String InsertarDocDepto(DocenteDepto docdepto) {
		String regInsertados = "Registro insertado en la fila No.= ";
		long contador = 0;
		ContentValues relacion = new ContentValues();
		relacion.put("IDDEPARTAMENTO", docdepto.getIdDepartamento());
		relacion.put("IDDOCENTE", docdepto.getIdDocente());
		contador = db.insert("DOCENTE_DEPTO", null, relacion);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error. Verificar Insercion";
		} else {
			regInsertados += contador;
		}

		return regInsertados;
	}

	public String InsertarMatImpart(MateriasImpartir mateimpart) {
		String regInsertados = "Registro insertado en la fila No.= ";
		long contador = 0;
		ContentValues relacion = new ContentValues();
		relacion.put("IDDOCENTE", mateimpart.getIdDocente());
		relacion.put("IDAREAMAT", mateimpart.getIdAreaMat());
		contador = db.insert("MAT_AREA_PUEDE_IMPARTIR", null, relacion);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error. Verificar Insercion";
		} else {
			regInsertados += contador;
		}

		return regInsertados;
	}

	public String InsertarDocentes(Docente docente) {
		String regInsertados = "Registro insertado en la fila No.= ";
		long contador = 0;
		ContentValues doc = new ContentValues();
		doc.put("IDDEPARTAMENTO", docente.getIdDocente());
		doc.put("IDDOCENTE", docente.getIdContrato());
		doc.put("NOMBRE", docente.getNombre());
		doc.put("APELLIDO", docente.getApellido());
		doc.put("GRADO_ACAD", docente.getGradoAcademico());
		doc.put("CORREO", docente.getCorreo());
		doc.put("TELEFONO", docente.getTelefono());
		doc.put("HORAS_ASIG", docente.getHorasAsignadas());
		contador = db.insert("DOCENTE_DEPTO", null, doc);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error. Verificar Insercion";
		} else {
			regInsertados += contador;
		}

		return regInsertados;
	}

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

	public String insertar(Modalidad_Curso modalidadcurso) {
		String regInsertados = "Registro insertado en la fila No.=";
		long contador = 0;
		ContentValues modcurso = new ContentValues();
		modcurso.put("IDMODALIDAD", modalidadcurso.getIdmodalidadCurso());
		modcurso.put("NOM_MODALIDAD", modalidadcurso.getNom_modalidad());
		modcurso.put("DESCUENTO_HORAS", modalidadcurso.getDescuento_horas());
		contador = db.insert("MODALIDAD_CURSO", null, modcurso);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error. Verificar Insercion";
		} else {
			regInsertados += contador;
		}
		return regInsertados;
	}

	public String insertar(Actividad_Academica ActAcademica) {
		String regInsertados = "Registro insertado en la fila No.=";
		long contador = 0;
		ContentValues ActiviAcademica = new ContentValues();
		ActiviAcademica.put("IDACTACAD", ActAcademica.getIdactacad());
		ActiviAcademica.put("IDMODALIDAD", ActAcademica.getIdmodalidad());
		ActiviAcademica.put("NOM_ACT_ACAD", ActAcademica.getNom_act_acad());
		ActiviAcademica.put(" CARGO", ActAcademica.getCargo());
		contador = db.insert("ACTIVIDAD_ACADEMICA", null, ActiviAcademica);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error. Verificar Insercion";
		} else {
			regInsertados += contador;
		}
		return regInsertados;
		
	}

	public List<String> getAll_IdLocales() {
		List<String> idLocales = new ArrayList<String>();
		Cursor cursor = db.rawQuery(
				"select IDLOCAL from LOCALES order by IDLOCAL;", null);
		if (cursor.moveToFirst()) {
			do {
				idLocales.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return idLocales;
	}

	public Locales consultarLocal(String idLocal) {
		String[] id = { idLocal };
		Cursor cursor = db.query("LOCALES", camposLocal, "IDLOCAL = ?", id,
				null, null, null);
		if (cursor.moveToFirst()) {
			Locales local = new Locales();
			local.setIdlocal(cursor.getString(0));
			local.setCapacidad(cursor.getString(1));
			return local;
		} else {
			return null;
		}

	}

	public Modalidad_Curso consultarModCurso(String idModCurso) {
		String[] id = { idModCurso };
		Cursor cursor = db.query("MODALIDAD_CURSO", camposModCurso,
				"IDMODALIDAD = ?", id, null, null, null);
		if (cursor.moveToFirst()) {
			Modalidad_Curso ModalCurso = new Modalidad_Curso();
			ModalCurso.setIdmodalidadCurso(cursor.getString(0));
			ModalCurso.setNom_modalidad(cursor.getString(1));
			ModalCurso.setDescuento_horas(cursor.getInt(2));
			return ModalCurso;
		} else {
			return null;
		}

	}

	public Modalidad_Act_Acad consultarModActAcad(String idModActA) {
		String[] id = { idModActA };
		Cursor cursor = db.query("MODALIDAD_ACT_ACAD", camposModalidadAA,
				"IDMODALIDAD = ?", id, null, null, null);
		if (cursor.moveToFirst()) {
			Modalidad_Act_Acad ModalAA = new Modalidad_Act_Acad();
			ModalAA.setIdmodalidad(cursor.getString(0));
			ModalAA.setNom_modalidad(cursor.getString(1));
			ModalAA.setDescuento_horas(cursor.getInt(2));
			return ModalAA;
		} else {
			return null;
		}
	}

	public List<String> getAll_IdModAA() {
		List<String> idModAA = new ArrayList<String>();
		Cursor cursor = db
				.rawQuery(
						"select IDMODALIDAD from MODALIDAD_ACT_ACAD order by IDMODALIDAD;",
						null);
		if (cursor.moveToFirst()) {
			do {
				idModAA.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return idModAA;
	}

	public Actividad_Academica consultarActAcademica(String idActAcademica) {
		String[] id = { idActAcademica };
		Cursor cursor = db.query("ACTIVIDAD_ACADEMICA", camposActAcademica,
				"IDACTACADD = ?", id, null, null, null);
		if (cursor.moveToFirst()) {
			Actividad_Academica ActAcad = new Actividad_Academica();
			ActAcad.setIdactacad(cursor.getString(0));
			ActAcad.setIdmodalidad(cursor.getString(1));
			ActAcad.setNom_act_acad(cursor.getString(2));
			ActAcad.setCargo(cursor.getString(3));
			return ActAcad;
		} else {
			return null;
		}
	}

	public List<String> getAll_IdActA() {
		List<String> idActA = new ArrayList<String>();
		Cursor cursor = db
				.rawQuery(
						"select IDACTACADD from ACTIVIDAD_ACADEMICA order by IDACTACADD;",
						null);
		if (cursor.moveToFirst()) {
			do {
				idActA.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return idActA;
	}

	public String eliminarLocales(Locales local) {
		String regAfectados = "";
		int contador = 0;
		if (verificarIntegridad(local, 19)) {
			regAfectados += "Tiene registros hijos\nNo se puede borrar,";
			return regAfectados;
		}
		regAfectados = "No tiene registros hijos\nFilas afectadas=";

		contador += db.delete("LOCALES","IDLOCAL='" + local.getIdlocal() + "'", null);
		regAfectados += contador;
		return regAfectados;
	}
	
	public String eliminarModCurso(Modalidad_Curso ModCurso) {
		String regAfectados = "";
		int contador = 0;
		if (verificarIntegridad(ModCurso, 22)) {
			regAfectados += "Tiene registros hijos\nNo se puede borrar,";
			return regAfectados;
		}
		regAfectados = "No tiene registros hijos\nFilas afectadas=";

		contador += db.delete("MODALIDAD_CURSO","IDMODALIDAD='" + ModCurso.getIdmodalidadCurso() + "'", null);
		regAfectados += contador;
		return regAfectados;
	}
	
	public String eliminarModalActAcad(Modalidad_Act_Acad ModalAA) {
		String regAfectados = "";
		int contador = 0;
		if (verificarIntegridad(ModalAA, 21)) {
			regAfectados += "Tiene registros hijos\nNo se puede borrar,";
			return regAfectados;
		}
		regAfectados = "No tiene registros hijos\nFilas afectadas=";

		contador += db.delete("MODALIDAD_ACT_ACAD","IDMODALIDAD='" + ModalAA.getIdmodalidad() + "'", null);
		regAfectados += contador;
		return regAfectados;
	}
	
	public String eliminarActAcad(Actividad_Academica ActAcademica) {
		String regAfectados = "";
		int contador = 0;
		if (verificarIntegridad(ActAcademica, 20)) {
			regAfectados += "Tiene registros hijos\nNo se puede borrar,";
			return regAfectados;
		}
		regAfectados = "No tiene registros hijos\nFilas afectadas=";

		contador += db.delete("ACTIVIDAD_ACADEMICA","IDACTACAD='" + ActAcademica.getIdactacad() + "'", null);
		regAfectados += contador;
		return regAfectados;		
	}
	
	public String actualizar(Locales local) {
		String[] id = { local.getIdlocal() };
		ContentValues values = new ContentValues();
		values.put("CAPACIDAD", local.getCapacidad());
		db.update("LOCALES", values, "IDLOCAL = ?", id);
		return "Registro actualizado correctamente";
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
			// VERIFICAR QUE AL ELIMINAR CICLONO EXISTA REGISTROS HIJOS EN TABLA
			// CARGA_ACADEMICA
			Ciclo ciclo2 = (Ciclo) dato;
			String[] id0 = { "ANIO", "NUMERO" };
			String[] id4 = { ciclo2.getAnio(), ciclo2.getNumero() };
			Cursor c = db.query("CARGA_ACADEMICA", id0,
					"ANIO = ? AND NUMERO = ?", id4, null, null, null);
			if (c.moveToFirst())
				return true;
			else
				return false;

		}

		case 2: {
			// verificar que al insertar CARGA_aCADEMICA exista IDDOCENTE, AÑIO
			// y el
			// CICLO
			Carga_Academica carga = (Carga_Academica) dato;
			String[] id1 = { carga.getIddocente() };
			String[] id2 = { carga.getAnio() };
			String[] id0 = { "ANIO", "NUMERO" };
			String[] id4 = { carga.getAnio(), carga.getNumero() };
			// abrir();
			Cursor cursor1 = db.query("DOCENTE", null, "IDDOCENTE = ?", id1,
					null, null, null);
			Cursor cursor2 = db.query("CICLO", id0, "ANIO = ? AND NUMERO = ?",
					id4, null, null, null);
			// Cursor cursor3 = db.query("CICLO", null, "codmateria = ?",
			// id3,null, null, null);
			if (cursor1.moveToFirst() && cursor2.moveToFirst()) {
				// Se encontraron datos
				return true;
			}
			return false;

		}

		case 3: {
			// VERIFICAR QUE AL ELIMICAR CARGA_ACADEMICA EL REGISTRO NO TENGA
			// HIJOS
			// VERIFICAR QUE AL ELIMINAR CICLONO EXISTA REGISTROS HIJOS EN TABLA
			// CARGA_ACADEMICA
			Carga_Academica carga2 = (Carga_Academica) dato;
			String[] id0 = { "IDDOCENTE", "ANIO", "NUMERO" };
			String[] id4 = { carga2.getIddocente(), carga2.getAnio(),
					carga2.getNumero() };
			Cursor c = db.query("DETALLE_CARGA_ACT_ACAD", id0,
					"IDDOCENTE = ? AND ANIO = ? AND NUMERO = ?", id4, null,
					null, null);
			Cursor d = db.query("DETALLE_CARGA_MAT", id0,
					"IDDOCENTE = ? AND ANIO = ? AND NUMERO = ?", id4, null,
					null, null);
			if (c.moveToFirst() && d.moveToFirst())
				return true;// SE ENCONTRARON REGISTROS HIJOS
			else
				return false;

		}

		case 4: {
			// VERIFICA DUPLICIDAD DE DETALLE_CARGA_MAT AL INSERTAR
			Detalle_Carga_Mat cargamat = (Detalle_Carga_Mat) dato;
			String[] id0 = { "IDDOCENTE", "ANIO", "NUMERO", "IDDETALLECURSO" };
			String[] id4 = { cargamat.getIddocente(), cargamat.getAnio(),
					cargamat.getNumero(), cargamat.getIddetallecurso() };
			// Cursor c = db.query("DETALLE_CARGA_ACT_ACAD", id0,
			// "IDDOCENTE = ? AND ANIO = ? AND NUMERO = ?",id4,null, null,
			// null);
			Cursor d = db
					.query("DETALLE_CARGA_MAT",
							id0,
							"IDDOCENTE = ? AND ANIO = ? AND NUMERO = ? AND IDDETALLECURSO = ?",
							id4, null, null, null);
			if (d.moveToFirst())
				return true;// SE ENCONTRARON REGISTROS = DUPLICIDAD
			else
				return false;

		}

		case 5: {
			// VERIFICA DUPLICIDAD DE DETALLE_CARGA_ACT_ACAD AL INSERTAR!
			Detalle_Carga_ActAcad cargamat = (Detalle_Carga_ActAcad) dato;
			String[] id0 = { "IDDOCENTE", "ANIO", "NUMERO", "IDACTACAD" };
			String[] id4 = { cargamat.getIddocente(), cargamat.getAnio(),
					cargamat.getNumero(), cargamat.getIdactacad() };
			Cursor d = db
					.query("DETALLE_CARGA_ACT_ACAD",
							id0,
							"IDDOCENTE = ? AND ANIO = ? AND NUMERO = ? AND IDACTACAD = ?",
							id4, null, null, null);
			if (d.moveToFirst())
				return true;// SE ENCONTRARON REGISTROS = DUPLICIDAD
			else
				return false;
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
			Materia materia = (Materia) dato;
			Cursor cursor = db.query(true, "DETALLE_GRUPO_ASIGNADO",
					new String[] { "CODIGOMATERIA" }, "CODIGOMATERIA='"
							+ materia.getCodigomateria() + "'", null, null,
					null, null, null);
			if (cursor.moveToFirst())
				return true;
			else
				return false;
		}
		case 10: {
			Materia materia = (Materia) dato;
			Cursor cursor = db.query(true, "AREA_MATERIA",
					new String[] { "CODIGOMATERIA" }, "CODIGOMATERIA='"
							+ materia.getCodigomateria() + "'", null, null,
					null, null, null);
			if (cursor.moveToFirst())
				return true;
			else
				return false;
		}
		case 11: {
			DetalleGrupoAsignado grupoAsignado = (DetalleGrupoAsignado) dato;
			Cursor cursor = db.query(true, "DETALLE_CARGA_MAT",
					new String[] { "IDDETALLECURSO" }, "IDDETALLECURSO='"
							+ grupoAsignado.getIddetallecurso() + "'", null,
					null, null, null, null);
			if (cursor.moveToFirst())
				return true;
			else
				return false;
		}
		case 12: {
			return true;
		}
		case 13: {
			return true;
		}
		
		case 14: {
			return true;
		}
		
		case 15: {
			return true;
		}
		
		case 16: {
			return true;
		}
		
		case 17: {
			return true;
		}
		
		case 18: {
			return true;
		}
		
		case 19: {
			Locales local = (Locales) dato;			
			Cursor cursor = db.query(true, "DETALLE_GRUPO_ASIGNADO",
					new String[] { "IDLOCAL" }, "IDLOCAL='"
							+ local.getIdlocal() + "'", null, null,
					null, null, null);
			if (cursor.moveToFirst())
				return true;
			else
				return false;
		}

		case 20: {			
				return true;
		}
		case 21: {			
			Modalidad_Act_Acad ModActAcademica = (Modalidad_Act_Acad) dato;
			Cursor cursor = db.query(true, "ACTIVIDAD_ACADEMICA",
					new String[] { "IDACTACAD " }, "IDACTACAD ='"
							+ ModActAcademica.getIdmodalidad() + "'", null, null,
					null, null, null);
			if (cursor.moveToFirst())
				return true;
			else
				return false;
		}
		
		case 22: {
			Modalidad_Curso ModCurso = (Modalidad_Curso) dato;
			Cursor cursor = db.query(true, "DETALLE_GRUPO_ASIGNADO",
					new String[] { "IDMODALIDAD" }, "IDMODALIDAD='"
							+ ModCurso.getIdmodalidadCurso() + "'", null, null,
					null, null, null);
			if (cursor.moveToFirst())
				return true;
			else
				return false;
		}
		
		case 23: {
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
