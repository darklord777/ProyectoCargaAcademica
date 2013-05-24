package sv.ues.fia.cargaacademicaeisi;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PeriodoInsertarActivity extends Activity {
	ControlDB helper;
	EditText editIdPeriodo;
	EditText editFechaIni;
	EditText editFechaFin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_periodo_insertar);
		helper = new ControlDB(this);
		editIdPeriodo = (EditText) findViewById(R.id.idCArgo);
		editFechaIni = (EditText) findViewById(R.id.fechaIni);
		editFechaFin = (EditText) findViewById(R.id.fechaFin);
	}
	public void insertarPeriodo(View v) {
		String IdPeriodo=editIdPeriodo.getText().toString();
		String FechaIni=editFechaIni.getText().toString();
		String FechaFin=editFechaFin.getText().toString();
		String regInsertados;	
			PERIODO periodo=new PERIODO();
			periodo.setIdPeriodo(IdPeriodo);
			periodo.setFechaIni(FechaIni);
			periodo.setFechaFin(FechaFin);
		
			helper.abrir();
			regInsertados=helper.insertar(periodo);
			helper.cerrar();
			Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
	}
	public void limpiarPeriodo(View v) {
		editIdPeriodo.setText("");
		editFechaIni.setText("");
		editFechaFin.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.periodo_insertar, menu);
		return true;
	}

}