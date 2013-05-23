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

public class ModActAcadEliminarActivity extends Activity  implements OnItemSelectedListener{

	private ControlDB helper;
	private Spinner spnEliminarModAA;
	private EditText NombreModAA_Elim;
	private List<String> idModalAA;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mod_act_acad_eliminar);
		
		helper = new ControlDB(this);
		spnEliminarModAA = (Spinner) findViewById(R.id.spn_Elim_ModActA);
		NombreModAA_Elim = (EditText) findViewById(R.id.NombreModalAA);
		helper.abrir();
		idModalAA = helper.getAll_IdModAA();
		helper.cerrar();		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, idModalAA);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnEliminarModAA.setAdapter(adapter);
		spnEliminarModAA.setOnItemSelectedListener(this);
	}
	
	public void eliminarModActA(View v) {
		String estado;
		Modalidad_Act_Acad ModalAA = new Modalidad_Act_Acad();
		ModalAA.setIdmodalidad(spnEliminarModAA.getSelectedItem().toString());
		helper.abrir();
		estado = helper.eliminarModalActAcad(ModalAA);
		helper.cerrar();
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mod_act_acad_eliminar, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		String idModActA = arg0.getItemAtPosition(arg2).toString();
		Modalidad_Act_Acad MAA = new Modalidad_Act_Acad();
		helper.abrir();
		MAA = helper.consultarModActAcad(idModActA);
		helper.cerrar();
		NombreModAA_Elim.setText(MAA.getNom_modalidad());
	}
	
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
