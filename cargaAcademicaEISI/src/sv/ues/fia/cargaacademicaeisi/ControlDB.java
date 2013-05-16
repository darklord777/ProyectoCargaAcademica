package sv.ues.fia.cargaacademicaeisi;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControlDB {
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
				// Mario
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

	// Mario
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
	// Mario
}
