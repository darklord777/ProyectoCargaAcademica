package sv.ues.fia.cargaacademicaeisi;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EliminarContratoActivity extends Activity implements OnItemSelectedListener {
	private ControlDB helper;
	private Spinner spnConsultarContrato;
	private EditText edtNomContrato;
	private List<String> idContrato;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eliminar_contrato);
		helper = new ControlDB(this);
		edtNomContrato = (EditText) findViewById(R.id.editTxtEliminarNomTipoCont);
		spnConsultarContrato = (Spinner) findViewById(R.id.spnEliminarCodTipoCont);
		spnConsultarContrato.setOnItemSelectedListener(this);
		
		helper.abrir();
		idContrato = helper.getAllIdContratos();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idContrato);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnConsultarContrato.setAdapter(adapter);
	}
	
	public void EliminarContrato(View v) {
		TipoContrato contrato = new TipoContrato();
		contrato.setIdContrato(spnConsultarContrato.getSelectedItem().toString());
		contrato.setTipo(edtNomContrato.getText().toString());
		helper.abrir();
		String estado = helper.EliminarContrato(contrato);
		helper.cerrar();
		edtNomContrato.setText("");
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.eliminar_contrato, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		String idContrato = arg0.getItemAtPosition(arg2).toString();
		TipoContrato contrato = new TipoContrato();
		helper.abrir();
		contrato = helper.ConsultarContrato(idContrato);
		helper.cerrar();
		edtNomContrato.setText(contrato.getTipo());
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
