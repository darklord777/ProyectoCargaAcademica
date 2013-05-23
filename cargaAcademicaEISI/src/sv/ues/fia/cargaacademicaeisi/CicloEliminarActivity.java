package sv.ues.fia.cargaacademicaeisi;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class CicloEliminarActivity extends Activity {
	ControlDB helper;
	private Spinner spinner_anio;
	private Spinner spinner_ciclo;
	private String ciclo1;
	private String año;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ciclo_eliminar);
		helper = new ControlDB(this);
		this.spinner_anio = (Spinner) findViewById(R.id.spinnera3_anio_eliminar);
		this.spinner_ciclo = (Spinner) findViewById(R.id.spinnera3_ciclo_eliminar);
		loadSpinnerData(); // 1 HACE CONSULTA Y CARGA LOS LABELS EN EL SPINNER
							// AÑOS

	}// fin oncreate

	/**
	 * Función para cargar los datos de la base de datos SQLite spinner
	 * */
	private void loadSpinnerData() {
		// COPIAN A SU ACTIVITY SOLO CAMBIAN LA CONSULTA !!!!!
		String selectQuery = "SELECT distinct ANIO FROM CICLO";
		// LLAMA A getAllLabels en ControlDB !!! importante
		List<String> lables = helper.getAllLabels(selectQuery, 0);

		// creando ADAPTER para el Spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);

		// vista de lista con el botón
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// adaptador de datos para spinner
		spinner_anio.setAdapter(dataAdapter);

		// Guardando valor selecionado en variable para consulta posterior
		spinner_anio.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				// guardo en el string año el valor selecionado del spinner
				año = parentView.getItemAtPosition(position).toString();
				loadSpinnerData2();
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
	}

	/**
	 * Función para cargar los datos de la base de datos SQLite spinner
	 * */
	private void loadSpinnerData2() {
		String selectQuery = "SELECT distinct NUMERO FROM CICLO WHERE ANIO='"
				+ año + "'";
		List<String> lables = helper.getAllLabels(selectQuery, 0);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner_ciclo.setAdapter(dataAdapter);

		spinner_ciclo.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				ciclo1 = parentView.getItemAtPosition(position).toString();
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
	}

	public void eliminarAlumno(View v) {
		String regEliminadas;
		Ciclo ciclo = new Ciclo();
		ciclo.setAnio(año);
		ciclo.setNumero(ciclo1);
		helper.abrir();
		regEliminadas = helper.eliminar(ciclo);
		helper.cerrar();
		Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
		//valores por defecto spinner
		spinner_anio.setSelection(0);
		spinner_ciclo.setSelection(0);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ciclo_eliminar, menu);
		return true;
	}

}