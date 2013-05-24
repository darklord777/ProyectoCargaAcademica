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

public class ActualizarDocenteActivity extends Activity {
	private ControlDB helper;
	private Spinner spnConsultarCodDocente;
	private Spinner spnConsultarCodContrato;
	private EditText nombre;
	private EditText apellido;
	private EditText grado;
	private EditText correo;
	private EditText telefono;
	private String contrato;
	private String docente;
	private List<String> idContrato;
	private ArrayAdapter<String> adapter;
	private List<String> iddocente;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actualizar_docente);
		helper = new ControlDB(this);
		nombre = (EditText) findViewById(R.id.editTxtActuNomDocente);
		apellido = (EditText) findViewById(R.id.editTxtActuApeDocente);
		grado = (EditText) findViewById(R.id.editTxtActuGraDocente);
		correo = (EditText) findViewById(R.id.editTxtActuEmailDocente);
		telefono = (EditText) findViewById(R.id.editTxtActuTelDocente);
		spnConsultarCodContrato = (Spinner) findViewById(R.id.spnActuCodTipContDocente);
		spnConsultarCodDocente = (Spinner) findViewById(R.id.spnActuIdDocente);
		carga1();
	}

	private void carga1() {
		// TODO Auto-generated method stub
		helper.abrir();
		iddocente = helper.getAllIdDocentes();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, iddocente);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnConsultarCodDocente.setAdapter(adapter);
		
		spnConsultarCodDocente.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				// guardo en el string año el valor selecionado del spinner
				docente = parentView.getItemAtPosition(position).toString();
				carga2();
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
	}

	protected void carga2() {
		// TODO Auto-generated method stub
		helper.abrir();
		idContrato = helper.getAllIdContratos();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idContrato);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnConsultarCodContrato.setAdapter(adapter);
		
		spnConsultarCodContrato.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				contrato = parentView.getItemAtPosition(position).toString();
				Docente Docente = new Docente();
				helper.abrir();
				Docente = helper.ConsultarDocente(docente);
				helper.cerrar();
				helper.abrir();
				
				nombre.setText(Docente.getNombre());
				apellido.setText(Docente.getApellido());
				grado.setText(Docente.getGradoAcademico());
				correo.setText(Docente.getCorreo());
				telefono.setText(Docente.getTelefono());
			}

			public void onNothingSelected(AdapterView<?> parentView) {
				
			}
		});
	}
	
	protected void consulta() {
		// TODO Auto-generated method stub
		Docente Docente = new Docente();
		helper.abrir();
		Docente = helper.ConsultarDocente(docente);
		helper.cerrar();
		helper.abrir();
		
		nombre.setText(Docente.getNombre());
		apellido.setText(Docente.getApellido());
		grado.setText(Docente.getGradoAcademico());
		correo.setText(Docente.getCorreo());
		telefono.setText(Docente.getTelefono());
	}
	
	public void Actualizar(View v) {
		String regInsertados;
		Docente docente = new Docente();
		docente.setIdDocente(spnConsultarCodDocente.getSelectedItem().toString());
		docente.setIdContrato(spnConsultarCodContrato.getSelectedItem().toString());
		docente.setNombre(nombre.getText().toString());
		docente.setApellido(apellido.getText().toString());
		docente.setGradoAcademico(grado.getText().toString());
		docente.setCorreo(correo.getText().toString());
		docente.setTelefono(telefono.getText().toString());
		
		helper.abrir();
		regInsertados = helper.ActualizarDocente(docente);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actualizar_docente, menu);
		return true;
	}

}
