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

public class ModActAcadActualizarActivity extends Activity implements OnItemSelectedListener {

	private ControlDB helper;
	private Spinner spnActModAA;
	private EditText edtNomModAA;
	private EditText DesHrsModAA;
	private List<String> idModAA;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mod_act_acad_actualizar);
		
		helper = new ControlDB(this);
		edtNomModAA = (EditText) findViewById(R.id.NombreModalAA_Act);
		DesHrsModAA = (EditText) findViewById(R.id.DesHrsModalAA_Act);
		spnActModAA = (Spinner) findViewById(R.id.spnActIdModalAA);

		helper.abrir();
		idModAA = helper.getAll_IdModAA();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, idModAA);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnActModAA.setAdapter(adapter);
		spnActModAA.setOnItemSelectedListener(this);
	}
	
	public void actualizarModAA(View v) {
		if (!edtNomModAA.getText().toString().trim().equals("") || !DesHrsModAA.getText().toString().trim().equals("")) {
			String regInsertados;
			Modalidad_Act_Acad ModActAcad = new Modalidad_Act_Acad();
			ModActAcad.setIdmodalidad(spnActModAA.getSelectedItem().toString());
			ModActAcad.setNom_modalidad(edtNomModAA.getText().toString());
			ModActAcad.setDescuento_horas(Integer.valueOf(DesHrsModAA.getText().toString()));
			helper.abrir();
			regInsertados = helper.actualizar(ModActAcad);
			helper.cerrar();
			Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
		} else {
			edtNomModAA.setText("");
			Toast.makeText(this, "El nombre de la modalidad es obligatorio.",Toast.LENGTH_LONG).show();
			DesHrsModAA.setText("");
			Toast.makeText(this, "Las horas de descuento es obligatorio.", Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mod_act_acad_actualizar, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		String idModalAA = arg0.getItemAtPosition(arg2).toString();
		Modalidad_Act_Acad MAA = new Modalidad_Act_Acad();
		helper.abrir();
		MAA = helper.consultarModActAcad(idModalAA);
		helper.cerrar();
		edtNomModAA.setText(MAA.getNom_modalidad());
		DesHrsModAA.setText(String.valueOf(MAA.getDescuento_horas()));
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
