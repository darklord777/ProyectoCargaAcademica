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

public class ModalidadCursoActualizarActivity extends Activity implements
			OnItemSelectedListener{
	private ControlDB helper;
	private Spinner spnActModCA;
	private EditText edtNomModCA;
	private EditText DesHrsModCA;
	private List<String> idModCA;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modalidad_curso_actualizar);
		
		helper = new ControlDB(this);
		edtNomModCA = (EditText) findViewById(R.id.ModCursoN_Act);
		DesHrsModCA = (EditText) findViewById(R.id.ModCursoDH_Act);
		spnActModCA = (Spinner) findViewById(R.id.spn_Select_MC);

		helper.abrir();
		idModCA = helper.getAllIdModCurso();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, idModCA);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnActModCA.setAdapter(adapter);
		spnActModCA.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modalidad_curso_actualizar, menu);
		return true;
	}
	
	public void ActualizarModCurso(View v) {
		if (!edtNomModCA.getText().toString().trim().equals("")) {
			String regInsertados;
			Modalidad_Curso ModCurso = new Modalidad_Curso();
			ModCurso.setIdmodalidadCurso(spnActModCA.getSelectedItem().toString());
			ModCurso.setNom_modalidad(edtNomModCA.getText().toString());
			ModCurso.setDescuento_horas(Integer.valueOf(DesHrsModCA.getText().toString()));
			helper.abrir();
			regInsertados = helper.actualizar(ModCurso);
			helper.cerrar();
			Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
		} else {
			edtNomModCA.setText("");
			Toast.makeText(this, "El nombre de la modalidad es obligatorio.",Toast.LENGTH_LONG).show();
			DesHrsModCA.setText("");
			Toast.makeText(this, "Las horas de descuento es obligatorio.",	Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		String idModCurso = arg0.getItemAtPosition(arg2).toString();
		Modalidad_Curso MCA = new Modalidad_Curso();
		helper.abrir();
		MCA = helper.consultarModCurso(idModCurso);
		helper.cerrar();
		edtNomModCA.setText(MCA.getNom_modalidad());
		DesHrsModCA.setText(String.valueOf(MCA.getDescuento_horas()));
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
