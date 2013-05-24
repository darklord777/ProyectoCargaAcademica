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

public class ActividadAcademicaActualizarActivity extends Activity implements
			OnItemSelectedListener {
	
	private ControlDB helper;
	private Spinner spnActIdActAcad;
	private EditText edtActNomAct;
	private EditText edtActCargo;
	private EditText edtidModActA;
	private List<String> idActAcad;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actividad_academica_actualizar);
		
		helper = new ControlDB(this);
		spnActIdActAcad = (Spinner) findViewById(R.id.spin_Act_ActAcademica);
		edtActNomAct = (EditText) findViewById(R.id.SelNombreActAcad);
		edtActNomAct = (EditText) findViewById(R.id.CargoActAcad);
		edtidModActA = (EditText) findViewById(R.id.idModActA);

		helper.abrir();
		idActAcad = helper.getAll_IdActA();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, idActAcad);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnActIdActAcad.setAdapter(adapter);
		spnActIdActAcad.setOnItemSelectedListener(this);
	}	

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		String idActA = arg0.getItemAtPosition(arg2).toString();
		Actividad_Academica ActA = new Actividad_Academica();
		helper.abrir();
		ActA = helper.consultarActAcademica(idActA);
		helper.cerrar();
		edtActNomAct.setText(ActA.getNom_act_acad());
		edtActCargo.setText(ActA.getCargo());	
	}
	
	public void ActualizarActAcademica(View v) {
		if (!edtActNomAct.getText().toString().trim().equals("")) {
			Actividad_Academica ActA = new Actividad_Academica();
			ActA.setIdactacad(spnActIdActAcad.getSelectedItem().toString());
			ActA.setIdmodalidad(edtidModActA.getText().toString());
			ActA.setNom_act_acad(edtActNomAct.getText().toString());
			ActA.setCargo(edtActCargo.getText().toString());
			helper.abrir();
			String estado = helper.actualizar(ActA);
			helper.cerrar();
			Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
		} else {
			edtActNomAct.setText("");
			edtidModActA.setText("");
			edtActCargo.setText("");
			Toast.makeText(this, "Debe llenarse todos los campos",
					Toast.LENGTH_LONG).show();
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
