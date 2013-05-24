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

public class PeriodoEliminarActivity extends Activity implements OnItemSelectedListener{
	private ControlDB helper;
	private Spinner spnEliminarPeriodo;
	private EditText FechaInicio_Elim;
	private EditText FechaFin_Elim;
	private List<String> idPeriodos;
	private ArrayAdapter<String> adapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_periodo_eliminar);
		helper = new ControlDB(this);
		spnEliminarPeriodo = (Spinner) findViewById(R.id.spiIdPeriodo_Elimminar);
		FechaInicio_Elim = (EditText) findViewById(R.id.editIniFecha);
		FechaFin_Elim = (EditText) findViewById(R.id.editFinalFecha);

		helper.abrir();
		idPeriodos = helper.getAllIdPeriodos();
		helper.cerrar();
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, idPeriodos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnEliminarPeriodo.setAdapter(adapter);
		spnEliminarPeriodo.setOnItemSelectedListener(this);
	}	
	public void eliminarPeriodo(View v) {
		PERIODO periodo = new PERIODO();
		periodo.setIdPeriodo(spnEliminarPeriodo.getSelectedItem().toString());
		helper.abrir();
		String estado = helper.eliminar(periodo);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.periodo_eliminar, menu);
		return true;
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		String idPeriodos = arg0.getItemAtPosition(arg2).toString();
		helper.abrir();
		PERIODO periodo = helper.consultarPeriodo(idPeriodos);
		helper.cerrar();
		if (periodo == null) {
			Toast.makeText(this,"Identificador de periodo: " 
					+ idPeriodos+ ". No existe.", Toast.LENGTH_LONG).show();
		} else {
			FechaInicio_Elim.setText(periodo.getFechaIni());
			FechaFin_Elim.setText(periodo.getFechaFin());
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
