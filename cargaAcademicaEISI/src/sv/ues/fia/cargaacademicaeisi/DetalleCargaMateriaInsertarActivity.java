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

public class DetalleCargaMateriaInsertarActivity extends Activity {
	ControlDB helper;
	private Spinner spinner_iddocente;
	private Spinner spinner_anio;
	private Spinner spinner_ciclo;
	private Spinner spinner_idgrupomateria;
	private String a�o;
	private String ciclo1;
	private String iddocente;
	private String iddetallecurso;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_carga_materia_insertar);
		helper = new ControlDB(this);
		this.spinner_iddocente = (Spinner) findViewById(R.id.spinner1_detallecargamatria_iddocente_insertar);
		this.spinner_anio = (Spinner) findViewById(R.id.spinner2_detallecargamatria_anioCiclo_insertar);
		this.spinner_ciclo = (Spinner) findViewById(R.id.spinner3_detallecargamatria_cicloCiclo_insertar);
		this.spinner_idgrupomateria = (Spinner) findViewById(R.id.spinner4_detallecargamatria_insert);
		loadSpinnerData3();
		loadSpinnerData4();
	}
	
	
	/**
	 * Funci�n para cargar los datos de la base de datos SQLite spinner
	 * */
	private void loadSpinnerData() {
		String selectQuery;

		selectQuery = "SELECT distinct ANIO FROM CARGA_ACADEMICA WHERE IDDOCENTE = '"+iddocente+"'";

		List<String> lables = helper.getAllLabels(selectQuery, 0);

		// creando ADAPTER para el Spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);

		// vista de lista con el bot�n
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// adaptador de datos para spinner
		spinner_anio.setAdapter(dataAdapter);

		// Guardando valor selecionado en variable para consulta posterior
		spinner_anio.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				// guardo en el string a�o el valor selecionado del spinner
				a�o = parentView.getItemAtPosition(position).toString();
				loadSpinnerData2();
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});

	}

	private void loadSpinnerData2() {
		String selectQuery = "SELECT distinct NUMERO FROM CARGA_ACADEMICA WHERE IDDOCENTE='"+iddocente+"' AND ANIO = '"+ a�o + "'";
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

	private void loadSpinnerData3() {
		String selectQuery = "SELECT distinct IDDOCENTE FROM CARGA_ACADEMICA";
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
		String selectQuery = "SELECT distinct IDDETALLECURSO FROM DETALLE_GRUPO_ASIGNADO";
		List<String> lables = helper.getAllLabels(selectQuery, 0);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner_idgrupomateria.setAdapter(dataAdapter);

		spinner_idgrupomateria.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				iddetallecurso = parentView.getItemAtPosition(position).toString();
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
	}
	
	public void insertarDetalleCargaMaterias(View v) {
		String regInsertados;
		 Detalle_Carga_Mat cargamat = new Detalle_Carga_Mat();
		 cargamat.setIddocente(iddocente);
		 cargamat.setAnio(a�o);
		 cargamat.setNumero(ciclo1);
		 cargamat.setIddetallecurso(iddetallecurso);
		 helper.abrir();
		 regInsertados=helper.insertar(cargamat);
		 helper.cerrar();
		 Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle_carga_materia_insertar, menu);
		return true;
	}

}