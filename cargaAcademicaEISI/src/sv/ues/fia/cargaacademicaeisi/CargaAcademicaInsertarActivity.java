package sv.ues.fia.cargaacademicaeisi;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class CargaAcademicaInsertarActivity extends Activity {
	ControlDB helper;
	private Spinner spinner_iddocente;
	private Spinner spinner_anio;
	private Spinner spinner_ciclo;

	private String año;
	private String ciclo1;
	private String iddocente;


	static final int DATE_DIALOG_ID = 999;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carga_academica_insertar);
		helper = new ControlDB(this);
		this.spinner_iddocente = (Spinner) findViewById(R.id.spinner1_cargaacademica_iddocente_insertar);
		this.spinner_anio = (Spinner) findViewById(R.id.spinner2_cargaacademica_anioCiclo_insertar);
		this.spinner_ciclo = (Spinner) findViewById(R.id.spinner3_cargaacademica_cicloCiclo_insertar);
		loadSpinnerData3();
		loadSpinnerData();
		
	}// FIN METODO ONCREATE

	/**
	 * Función para cargar los datos de la base de datos SQLite spinner
	 * */
	private void loadSpinnerData() {
		String selectQuery;

		selectQuery = "SELECT distinct ANIO FROM CICLO";

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

	/**
	 * Función para cargar los datos de la base de datos SQLite spinner
	 * */
	private void loadSpinnerData3() {
		String selectQuery = "SELECT distinct IDDOCENTE FROM DOCENTE";
		List<String> lables = helper.getAllLabels(selectQuery, 0);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner_iddocente.setAdapter(dataAdapter);

		spinner_iddocente.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				iddocente = parentView.getItemAtPosition(position).toString();
				
				
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
	}
	
	public void insertarCargaAcademica(View v) {
		 
		 String regInsertados;
		 //String carnet=editCarnet.getText().toString();
		 //String codmateria=editCodmateria.getText().toString();
		 //String ciclo=editCiclo.getText().toString();
		 //Float notafinal=Float.valueOf(editNotafinal.getText().toString()); 
		 Carga_Academica carga= new Carga_Academica();
		 carga.setIddocente(iddocente);
		 carga.setAnio(año);
		 carga.setNumero(ciclo1);
		 helper.abrir();
		 regInsertados=helper.insertar(carga);
		 helper.cerrar();
		 Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
		}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.carga_academica_insertar, menu);
		return true;
	}

}
