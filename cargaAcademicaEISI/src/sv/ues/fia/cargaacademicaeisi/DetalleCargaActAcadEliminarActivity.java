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

public class DetalleCargaActAcadEliminarActivity extends Activity {
	ControlDB helper;
	private Spinner spinner_iddocente;
	private Spinner spinner_anio;
	private Spinner spinner_ciclo;
	private Spinner spinner_idactividadacad;
	private String año;
	private String ciclo1;
	private String iddocente;
	private String idactividad_academica;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_carga_act_acad_eliminar);
		helper = new ControlDB(this);
		this.spinner_iddocente = (Spinner) findViewById(R.id.spinnerZ1_iddocente_eliminar);
		this.spinner_anio = (Spinner) findViewById(R.id.spinnerZ2_anioCiclo_eliminar);
		this.spinner_ciclo = (Spinner) findViewById(R.id.spinnerZ3_cicloCiclo_eliminar);
		this.spinner_idactividadacad = (Spinner) findViewById(R.id.spinnerZ4_idactividad_eliminar);
		loadSpinnerData3();
	}

	/**
	 * Función para cargar los datos de la base de datos SQLite spinner
	 * */
	private void loadSpinnerData() {
		String selectQuery;

		selectQuery = "SELECT distinct ANIO FROM DETALLE_CARGA_ACT_ACAD WHERE IDDOCENTE = '"+iddocente+"'";

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

	private void loadSpinnerData2() {
		String selectQuery = "SELECT distinct NUMERO FROM DETALLE_CARGA_ACT_ACAD WHERE IDDOCENTE='"+iddocente+"' AND ANIO = '"+ año + "'";
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
				loadSpinnerData4();
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
	}

	private void loadSpinnerData3() {
		String selectQuery = "SELECT distinct IDDOCENTE FROM DETALLE_CARGA_ACT_ACAD";
		// String selectQuery = "SELECT distinct ANIO FROM CICLO";
		List<String> lables = helper.getAllLabels(selectQuery, 0);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner_iddocente.setAdapter(dataAdapter);

		spinner_iddocente
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {
						iddocente = parentView.getItemAtPosition(position)
								.toString();
						loadSpinnerData();
					}

					public void onNothingSelected(AdapterView<?> parentView) {

					}
				});
	}
	
	private void loadSpinnerData4() {
		String selectQuery = "SELECT distinct IDACTACAD FROM DETALLE_CARGA_ACT_ACAD WHERE IDDOCENTE='"+iddocente+"' AND ANIO = '"+ año + "' AND NUMERO = '"+ciclo1+"'";
		List<String> lables = helper.getAllLabels(selectQuery, 0);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner_idactividadacad.setAdapter(dataAdapter);

		spinner_idactividadacad.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				idactividad_academica = parentView.getItemAtPosition(position).toString();
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
	}
	
	public void eliminar_actividades(View v) {
		String regEliminadas;
		Detalle_Carga_ActAcad cargact = new Detalle_Carga_ActAcad();
		cargact.setIddocente(iddocente);
		cargact.setAnio(año);
		cargact.setNumero(ciclo1);
		cargact.setIdactacad(idactividad_academica);
		helper.abrir();
		regEliminadas = helper.eliminar(cargact);
		helper.cerrar();
		Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
		
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle_carga_act_acad_eliminar, menu);
		return true;
	}

}
