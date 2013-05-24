package sv.ues.fia.cargaacademicaeisi;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PeriodoActualizarActivity extends Activity implements OnItemSelectedListener {
	private ControlDB helper;
	private Spinner spnActIdPeriodo;
	private EditText edtActFechaIni;
	private EditText edtActFechaFin;
	private List<String> idPeriodos;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_periodo_actualizar);
		helper = new ControlDB(this);
		spnActIdPeriodo = (Spinner) findViewById(R.id.spin_Act_Per);
		edtActFechaIni = (EditText) findViewById(R.id.edtFechaIniDate);
		edtActFechaFin= (EditText) findViewById(R.id.edtfechaFinDate);
		
		helper.abrir();
		idPeriodos = helper.getAllIdPeriodos();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idPeriodos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnActIdPeriodo.setAdapter(adapter);
		spnActIdPeriodo.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.periodo_actualizar, menu);
		return true;
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2 ,long arg3) {
		String idPeriodo = arg0.getItemAtPosition(arg2).toString();
		helper.abrir();
		PERIODO periodo = helper.consultarPeriodo(idPeriodo);
		helper.cerrar();
		if (periodo == null) {
			Toast.makeText(
					this,
					"Identificador de periodo: " + idPeriodo
							+ ". No existe.", Toast.LENGTH_LONG).show();
		} else {
			edtActFechaIni.setText(periodo.getFechaIni());
			edtActFechaFin.setText(periodo.getFechaFin());
		}
 }
	
    public void actualizarPeriodo(View v) {
		PERIODO periodo = new PERIODO();
		periodo.setIdPeriodo(spnActIdPeriodo.getSelectedItem().toString());
		periodo.setFechaIni(edtActFechaIni.getText().toString());
		periodo.setFechaFin(edtActFechaFin.getText().toString());
		helper.abrir();
		String estado = helper.actualizar(periodo);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
	}
    @Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}
	

}