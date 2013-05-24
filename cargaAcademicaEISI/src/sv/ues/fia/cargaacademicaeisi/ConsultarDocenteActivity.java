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

public class ConsultarDocenteActivity extends Activity implements OnItemSelectedListener {
	private ControlDB helper;
	private Spinner spnConsultarCodDocente;
	private EditText tipocont;
	private EditText nombre;
	private EditText apellido;
	private EditText grado;
	private EditText correo;
	private EditText telefono;
	private EditText horas;
	private String contrato;
	private List<String> idContrato;
	private ArrayAdapter<String> adapter;
	private List<String> iddocente;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultar_docente);
		helper = new ControlDB(this);
		tipocont = (EditText) findViewById(R.id.editTxtConsulTipoContDocente);
		nombre = (EditText) findViewById(R.id.editTxtConsulNomDocente);
		apellido = (EditText) findViewById(R.id.editTxtCosulApeDocente);
		grado = (EditText) findViewById(R.id.editTxtConsulGraDocente);
		correo = (EditText) findViewById(R.id.editTxtConsulEmailDocente);
		telefono = (EditText) findViewById(R.id.editTxtConsulTelDocente);
		horas = (EditText) findViewById(R.id.editTxtConsulHorasDocente);
		spnConsultarCodDocente = (Spinner) findViewById(R.id.spnConsulIdDocente);
		spnConsultarCodDocente.setOnItemSelectedListener(this);
		
		helper.abrir();
		iddocente = helper.getAllIdDocentes();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, iddocente);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnConsultarCodDocente.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.consultar_docente, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		String idDocente = arg0.getItemAtPosition(arg2).toString();
		Docente docente = new Docente();
		helper.abrir();
		docente = helper.ConsultarDocente(idDocente);
		helper.cerrar();
		
		tipocont.setText(docente.getIdContrato());
		nombre.setText(docente.getNombre());
		apellido.setText(docente.getApellido());
		grado.setText(docente.getGradoAcademico());
		correo.setText(docente.getCorreo());
		telefono.setText(docente.getTelefono());
		horas.setText(String.valueOf(docente.getHorasAsignadas()));
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
