package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.Toast;

public class CicloInsertarActivity extends Activity {
	ControlDB helper;
	Spinner anio;
	Spinner ciclo;
	EditText fecha_ini;
	EditText fecha_fin;
	private String año;
	private String ciclo1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ciclo_insertar);
		helper = new ControlDB(this);
		anio = (Spinner) findViewById(R.id.spinner_anio_ciclo);
		ciclo = (Spinner) findViewById(R.id.spinner_ciclo);
		fecha_ini = (EditText) findViewById(R.id.editText1_fechainicio);
		fecha_fin = (EditText) findViewById(R.id.editText2_fechafin);

		/** codigo llenado de spinner años */
		ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
				R.array.anios_ciclo, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		anio.setAdapter(adapter);

		anio.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				// guardo en el string año el valor selecionado del spinner
				año = parentView.getItemAtPosition(position).toString();
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
		/** codigo llenado de spinner ciclos */
		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
				R.array.ciclos, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ciclo.setAdapter(adapter2);

		ciclo.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				// guardo en el string año el valor selecionado del spinner
				ciclo1 = parentView.getItemAtPosition(position).toString();
				/*
				 * Toast.makeText( parentView.getContext(), "Has seleccionado "
				 * + parentView.getItemAtPosition(position) .toString(),
				 * Toast.LENGTH_LONG).show();
				 */
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});

	}// fin metod oncreate

	public void insertarCiclo(View v) {

		String fecha_inicio = fecha_ini.getText().toString();
		String fecha_final = fecha_fin.getText().toString();
		String regInsertados;
		Ciclo ciclo = new Ciclo();
		ciclo.setAnio(año);
		ciclo.setNumero(ciclo1);
		ciclo.setFechaini(fecha_inicio);
		ciclo.setFechafin(fecha_final);
		helper.abrir();
		regInsertados = helper.insertarCiclo(ciclo);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
	}

	public void limpiarTexto(View v) {
		
		fecha_ini.setText("");
		fecha_fin.setText("");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ciclo_insertar, menu);
		return true;
	}

}
