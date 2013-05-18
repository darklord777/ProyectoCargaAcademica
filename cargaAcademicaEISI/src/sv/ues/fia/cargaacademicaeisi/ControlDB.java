package sv.ues.fia.cargaacademicaeisi;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControlDB {
	/* Mario */
	private static final String[] camposDepto = new String[] {
			"IDDEPARTAMENTO", "NOM_DEPTO" };
	private static final String[] camposCiclo = new String[] { "ANIO",
			"NUMERO", "FECHAINI", "FECHAFIN" };

	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public ControlDB(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		private static final String BASE_DATOS = "cargacademica.s3db";
		private static final int VERSION = 1;

		public DatabaseHelper(Context context) {
			super(context, BASE_DATOS, null, VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				// Mario
				db.execSQL("CREATE TABLE DEPARTAMENTO ( IDDEPARTAMENTO  VARCHAR(6)  NOT NULL PRIMARY KEY, NOM_DEPTO VARCHAR(20));");
				db.execSQL("CREATE TABLE AREA_MATERIA ( IDAREAMAT VARCHAR(6) NOT NULL PRIMARY KEY, IDDEPARTAMENTO VARCHAR(6), CODIGOMATERIA VARCHAR(6));");
				// Mario
				db.execSQL("CREATE TABLE DOCENTE_DPTO ( IDDEPARTAMENTO  VARCHAR(6) NOT NULL, IDDOCENTE VARCHAR(8) NOT NULL, PRIMARY KEY (IDDEPARTAMENTO, IDDOCENTE));");
				/** Alexis */
				db.execSQL("CREATE TABLE CICLO ( ANIO VARCHAR(4) NOT NULL, NUMERO VARCHAR(2) NOT NULL, FECHAINI DATE DEFAULT CURRENT_DATE NULL, FECHAFIN DATE NULL, PRIMARY KEY (ANIO,NUMERO) );");
				db.execSQL("CREATE TABLE CARGA_ACADEMICA ( IDDOCENTE VARCHAR(8) NOT NULL, ANIO VARCHAR(4) NOT NULL, NUMERO VARCHAR(2) NOT NULL, PRIMARY KEY (IDDOCENTE,ANIO,NUMERO)); ");
				db.execSQL("CREATE TABLE DETALLE_CARGA_ACT_ACAD ( IDDOCENTE VARCHAR(8) NULL, ANIO VARCHAR(4) NULL, NUMERO VARCHAR(2) NULL,IDACTACAD VARCHAR(6) NULL );");
				db.execSQL("CREATE TABLE DETALLE_CARGA_MAT ( IDDOCENTE VARCHAR(8) NULL, ANIO VARCHAR(4) NULL, NUMERO VARCHAR(2) NULL, IDDETALLECURSO VARCHAR(6) NULL );");
				/** Alexis */
				// Michael
				db.execSQL("CREATE TABLE ACTIVIDAD_ACADEMICA (IDACTACAD VARCHAR(6)  NOT NULL PRIMARY KEY, IDMODALIDAD   VARCHAR(6), NOM_ACT_ACAD  VARCHAR(30), CARGO VARCHAR(20));");
				db.execSQL("CREATE TABLE [LOCALES] ([IDLOCAL] VARCHAR(6)  PRIMARY KEY NOT NULL, [CAPACIDAD] INTEGER  NULL);");
				db.execSQL("CREATE TABLE [MODALIDAD_ACT_ACAD] ([IDMODALIDAD] VARCHAR(6)  PRIMARY KEY NOT NULL,[NOM_MODALIDAD] VARCHAR(25)  NULL,[DESCUENTO_HORAS] INTEGER  NULL);");
				db.execSQL("CREATE TABLE MODALIDAD_CURSO (IDMODALIDAD VARCHAR(6)  NOT NULL PRIMARY KEY, NOM_MODALIDAD VARCHAR(20), DESCUENTO_HORAS  INTEGER);");
				// Michael

				// emersson

				// emersson
				// sergio

				// sergio

				/*
				 * DE AQUI EN ADELANTE COLOCAR TRIGUERS DE INTEGRIDAD QUE
				 * APARECEN EN LA BD QUE MANDO Jr
				 */
				/** TRIGGER alexis */
				db.execSQL("CREATE TRIGGER fk_carga_ciclo BEFORE INSERT ON CARGA_ACADEMICA FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT anio FROM CICLO WHERE (anio = NEW.anio AND numero = NEW.numero)) IS NULL) THEN RAISE(ABORT, 'No existe el Ciclo') END; END;");
				db.execSQL("CREATE TRIGGER fk_carga_docente BEFORE INSERT ON CARGA_ACADEMICA FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDDOCENTE FROM DOCENTE WHERE IDDOCENTE = NEW.IDDOCENTE) IS NULL) THEN RAISE(ABORT, 'No existe el Docente') END; END;");
				db.execSQL("CREATE TRIGGER fk_detalle_carga_mat BEFORE INSERT ON DETALLE_CARGA_MAT FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDDOCENTE FROM CARGA_ACADEMICA WHERE IDDOCENTE = NEW.IDDOCENTE AND ANIO = NEW.ANIO AND NUMERO = NEW.NUMERO) IS NULL) THEN RAISE(ABORT, 'No existe esta informacion de Carga Academica') END; END;");
				db.execSQL("CREATE TRIGGER fk_detalle_curso BEFORE INSERT ON DETALLE_CARGA_MAT FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDDETALLECURSO FROM DETALLE_GRUPO_ASIGNADO WHERE IDDETALLECURSO = NEW.IDDETALLECURSO) IS NULL) THEN RAISE(ABORT, 'No existe esta informacion de este Curso') END; END;");
				db.execSQL("CREATE TRIGGER fk_detalle_carga_acad BEFORE INSERT ON DETALLE_CARGA_ACT_ACAD FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDDOCENTE FROM CARGA_ACADEMICA WHERE IDDOCENTE = NEW.IDDOCENTE AND ANIO = NEW.ANIO AND NUMERO = NEW.NUMERO) IS NULL) THEN RAISE(ABORT, 'No existe esta informacion de Carga Academica') END; END;");
				db.execSQL("CREATE TRIGGER fk_detalle_actividad BEFORE INSERT ON DETALLE_CARGA_ACT_ACAD FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDACTACAD FROM ACTIVIDAD_ACADEMICA WHERE IDACTACAD = NEW.IDACTACAD) IS NULL) THEN RAISE(ABORT, 'No existe esta Actividad Academica') END; END;");
				/** FIN TRIGGER alexis */

				// Mario
				db.execSQL("CREATE TRIGGER fk_area_materia BEFORE INSERT ON AREA_MATERIA FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT CODIGOMATERIA FROM MATERIA WHERE CODIGOMATERIA = NEW.CODIGOMATERIA) IS NULL) THEN RAISE(ABORT, 'No existe la Materia') END; END;");
				db.execSQL("CREATE TRIGGER [fk_docente_departamento] BEFORE INSERT ON [DOCENTE_DPTO] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT IDDEPARTAMENTO FROM DEPARTAMENTO WHERE IDDEPARTAMENTO = NEW.IDDEPARTAMENTO) IS NULL) THEN RAISE(ABORT, 'No existe este Departamento') END; END;");
				// Mario
				// Michael
				// Michael
				// emersson
				// emersson
				// sergio
				// sergio

				/* AQUI TERMINAN LOS TRIGUERS DE INTEGRIDAD */

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
