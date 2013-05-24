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

public class InsertarDocenteActivity extends Activity implements OnItemSelectedListener {
	private ControlDB helper;
	private EditText IdDoc;
	private Spinner spnCodTipContDocente;
	private EditText NomDoc;
	private EditText ApeDoc;
	private EditText GraDoc;
	private EditText EmailDoc;
	private EditText TelDoc;
	private List<String> idContrato;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insertar_docente);
		helper = new ControlDB(this);
		IdDoc = (EditText) findViewById(R.id.editTxtInsertCodigoDocente);
		spnCodTipContDocente = (Spinner) findViewById(R.id.spnInsertCodTipContDocente);
		spnCodTipContDocente.setOnItemSelectedListener(this);
		NomDoc = (EditText) findViewById(R.id.editTxtInsertNomDocente);
		ApeDoc = (EditText) findViewById(R.id.editTxtInsertApeDocente);
		GraDoc = (EditText) findViewById(R.id.editTxtInsertGraDocente);
		EmailDoc = (EditText) findViewById(R.id.editTxtInsertEmailDocente);
		TelDoc = (EditText) findViewById(R.id.editTxtInsertTelDocente);
		
		helper.abrir();
		idContrato = helper.getAllIdContratos();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idContrato);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnCodTipContDocente.setAdapter(adapter);
	}

	public void insertDocente(View v) {
		String regInsertados;
		Docente docente = new Docente();
		docente.setIdDocente(IdDoc.getText().toString());
		docente.setIdContrato(spnCodTipContDocente.getSelectedItem().toString());
		docente.setNombre(NomDoc.getText().toString());
		docente.setApellido(ApeDoc.getText().toString());
		docente.setGradoAcademico(GraDoc.getText().toString());
		docente.setCorreo(EmailDoc.getText().toString());
		docente.setTelefono(TelDoc.getText().toString());
		docente.setHorasAsignadas(0);
		helper.abrir();
		regInsertados = helper.InsertarDocentes(docente);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
	}
	
	public void limpiarDocente(View v) {
		IdDoc.setText("");
		NomDoc.setText("");
		ApeDoc.setText("");
		GraDoc.setText("");
		EmailDoc.setText("");
		TelDoc.setText("");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insertar_docente, menu);
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
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
