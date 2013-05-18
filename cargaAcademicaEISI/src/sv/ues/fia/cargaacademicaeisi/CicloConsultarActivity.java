package sv.ues.fia.cargaacademicaeisi;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class CicloConsultarActivity extends Activity {
	ControlDB helper;
	private Spinner spinner_anio;
	private Spinner spinner_ciclo;
	private EditText iniciofecha;
	private EditText finfecha;
	private String año;
	private String ciclo1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ciclo_consultar);
		helper = new ControlDB(this);
		this.spinner_anio = (Spinner) findViewById(R.id.spinner_anio_consultar);
		this.spinner_ciclo = (Spinner) findViewById(R.id.spinner_ciclo_consultr);
		loadSpinnerData(); // 1 HACE CONSULTA Y CARGA LOS LABELS EN EL SPINNER AÑOS
		this.iniciofecha = (EditText) findViewById(R.id.editText1_consult_inicio);
		this.finfecha = (EditText) findViewById(R.id.editText2_consult_fin);
		//deshabilitando edicion de editText
		iniciofecha.setFocusable(false);
		finfecha.setFocusable(false);
		
		/** codigo llenado de spinner ciclos FIJOS */
		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
				R.array.ciclos, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_ciclo.setAdapter(adapter2);

		spinner_ciclo.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				// guardo en el string año el valor selecionado del spinner
				ciclo1 = parentView.getItemAtPosition(position).toString();
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
	}//fin metodo oncreate

	/**
	 * Función para cargar los datos de la base de datos SQLite spinner
	 * */
	private void loadSpinnerData() {
		// database handler
		// ControlBD db = new ControlBD(this);
		//helper2 = new ControlDB(getApplicationContext());

		// Select All Query
		//COPIAN A SU ACTIVITY SOLO CAMBIAN LA CONSULTA !!!!!
        String selectQuery = "SELECT distinct ANIO FROM CICLO" ; 
		// Spinner Drop down elements
        // LLAMA A getAllLabels en ControlDB !!! importante
		List<String> lables = helper.getAllLabels(selectQuery,0); 

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);

		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		spinner_anio.setAdapter(dataAdapter);
		//Guardando valor selecionado en variable para consulta posterior
		spinner_anio.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				// guardo en el string año el valor selecionado del spinner
				año = parentView.getItemAtPosition(position).toString();
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
	}
	
	public void consultarCiclo(View v) {

		helper.abrir();
		Ciclo ciclo = helper.consultarCiclo(año,ciclo1);
		helper.cerrar();
		if (ciclo == null)
			Toast.makeText(this, "Ciclo No Encontrado", Toast.LENGTH_LONG)
					.show();
		else {
			iniciofecha.setText(String.valueOf(ciclo.getFechaini()));
			finfecha.setText(String.valueOf(ciclo.getFechafin()));
		}
	}
	
	public void limpiarTexto(View v) {

		iniciofecha.setText("");
		finfecha.setText("");
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ciclo_consultar, menu);
		return true;
	}

}