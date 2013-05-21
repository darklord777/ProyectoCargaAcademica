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

public class ModActAcadConsultarMainActivity extends Activity  implements OnItemSelectedListener {

	private ControlDB helper;
	private EditText NombreModAA;
	private EditText DesHrsModAA;
	private Spinner spnListaModalidadAA;
	private List<String> idModalidadAA;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mod_act_acad_consultar_main);
		
		helper = new ControlDB(this);
		NombreModAA = (EditText) findViewById(R.id.NombreModalAA);
		DesHrsModAA = (EditText) findViewById(R.id.DescHorasMAA);
		spnListaModalidadAA = (Spinner) findViewById(R.id.spn_Select_ModAA);
		
		helper.abrir();
		idModalidadAA = helper.getAll_IdModAA();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, idModalidadAA);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnListaModalidadAA.setAdapter(adapter);
		spnListaModalidadAA.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mod_act_acad_consultar_main, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
		// TODO Auto-generated method stub
		String idModAA = parent.getItemAtPosition(pos).toString();
		helper.abrir();		
		Modalidad_Act_Acad ModalAA = helper.consultarModActAcad(idModAA);
		helper.cerrar();
		if (ModalAA == null) {
			Toast.makeText(this,"Identificador de modalidad: " + idModAA
							+ ". No existe.", Toast.LENGTH_LONG).show();
		} else {
			NombreModAA.setText(ModalAA.getNom_modalidad());
			Toast.makeText(this, "Valor de item=" + idModAA, Toast.LENGTH_LONG).show();
			DesHrsModAA.setText(ModalAA.getDescuento_horas());
			Toast.makeText(this, "Valor de item=" + idModAA, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
