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
	private List<String> idMaterias;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mod_act_acad_actualizar);
	}
	
	public void guardarMateria(View v) {
		if (!edtNomModAA.getText().toString().trim().equals("")) {
			String regInsertados;
			Modalidad_Act_Acad ModActAcad = new Modalidad_Act_Acad();
			ModActAcad.setIdmodalidad(spnActModAA.getSelectedItem().toString());
			helper.abrir();
			regInsertados = helper.actualizar(ModActAcad);
			helper.cerrar();
			Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
		} else {
			edtNomModAA.setText("");
			Toast.makeText(this, "El nombre de la modalidad es obligatorio.",
					Toast.LENGTH_LONG).show();
			DesHrsModAA.setText("");
			Toast.makeText(this, "Las horas de descuento es obligatorio.",
					Toast.LENGTH_LONG).show();
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
		DesHrsModAA.setText(MAA.getDescuento_horas());
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
