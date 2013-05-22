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

public class LocalesEliminarActivity extends Activity implements OnItemSelectedListener {

	private ControlDB helper;
	private Spinner spnEliminarLocal;
	private EditText CapacidadLocal_Elim;
	private List<String> idLocal;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locales_eliminar);
		helper = new ControlDB(this);
		spnEliminarLocal = (Spinner) findViewById(R.id.spnEliminarLocal);
		CapacidadLocal_Elim = (EditText) findViewById(R.id.CapLocal_Elim);

		helper.abrir();
		idLocal = helper.getAll_IdLocales();
		helper.cerrar();
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, idLocal);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnEliminarLocal.setAdapter(adapter);
		spnEliminarLocal.setOnItemSelectedListener(this);
	}	
	
	public void eliminarLocal(View v) {
		String estado;
		Locales local = new Locales();
		local.setIdlocal(spnEliminarLocal.getSelectedItem().toString());
		helper.abrir();
		estado = helper.eliminarLocales(local);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.locales_eliminar, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		String idLocal = arg0.getItemAtPosition(arg2).toString();
		Locales local = new Locales();
		helper.abrir();
		local = helper.consultarLocal(idLocal);
		helper.cerrar();
		CapacidadLocal_Elim.setText(local.getCapacidad());
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
