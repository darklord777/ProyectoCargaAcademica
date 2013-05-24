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

public class EliminarDocenteDeptoActivity extends Activity {
	private ControlDB helper;
	private Spinner spnConsultarDocente;
	private Spinner spnConsultarDepartamento;
	private EditText nombre;
	private EditText apellido;
	private EditText departamento;
	private String docente;
	private String iddepto;
	private List<String> iddepartamento;
	private ArrayAdapter<String> adapter;
	private List<String> iddocente;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eliminar_docente_depto);
		helper = new ControlDB(this);
		nombre = (EditText) findViewById(R.id.editTxtElimNomDocDepto);
		apellido = (EditText) findViewById(R.id.editTxtElimApeDocDepto);
		departamento = (EditText) findViewById(R.id.editTxtElimNomDepDocDepto);
		spnConsultarDepartamento = (Spinner) findViewById(R.id.spnElimIdDepDocDepto);
		spnConsultarDocente = (Spinner) findViewById(R.id.spnElimIdDocDocDepto);
		carga1();
	}
	
	private void carga1() {
		// TODO Auto-generated method stub
		helper.abrir();
		iddocente = helper.getAllIdDocDocDepto();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, iddocente);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnConsultarDocente.setAdapter(adapter);
		
		spnConsultarDocente.setOnItemSelectedListener(new OnItemSelectedListener() {
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
		iddepartamento = helper.getAllIdDeptoDocDepto(docente);
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, iddepartamento);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnConsultarDepartamento.setAdapter(adapter);
		
		spnConsultarDepartamento.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				iddepto = parentView.getItemAtPosition(position).toString();
				Docente Docente = new Docente();
				Departamento depto = new Departamento();
				helper.abrir();
				Docente = helper.ConsultarDocente2(docente);
				depto = helper.consultarDepto(iddepto);
				helper.cerrar();
				
				nombre.setText(Docente.getNombre());
				apellido.setText(Docente.getApellido());
				departamento.setText(depto.getNom_depto());
			}

			public void onNothingSelected(AdapterView<?> parentView) {
				
			}
		});
	}
	
	protected void consulta() {
		// TODO Auto-generated method stub
		Docente Docente = new Docente();
		Departamento depto = new Departamento();
		helper.abrir();
		Docente = helper.ConsultarDocente2(docente);
		depto = helper.consultarDepto(iddepto);
		helper.cerrar();
		
		nombre.setText(Docente.getNombre());
		apellido.setText(Docente.getApellido());
		departamento.setText(depto.getNom_depto());
	}
	
	public void EliminarDocenteDepto(View v) {
		DocenteDepto Doc = new DocenteDepto();
		Doc.setIdDocente(spnConsultarDocente.getSelectedItem().toString());
		Doc.setIdDepartamento(spnConsultarDepartamento.getSelectedItem().toString());
		helper.abrir();
		String estado = helper.EliminarDocenteDepto(Doc);
		helper.cerrar();
		nombre.setText("");
		apellido.setText("");
		departamento.setText("");
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.eliminar_docente_depto, menu);
		return true;
	}

}
