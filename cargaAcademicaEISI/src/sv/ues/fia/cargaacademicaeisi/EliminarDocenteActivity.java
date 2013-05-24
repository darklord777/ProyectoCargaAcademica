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

public class EliminarDocenteActivity extends Activity {
	private ControlDB helper;
	private Spinner spnConsultarCodDocente;
	private EditText nombre;
	private EditText apellido;
	private String docente;
	private ArrayAdapter<String> adapter;
	private List<String> iddocente;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eliminar_docente);
		helper = new ControlDB(this);
		nombre = (EditText) findViewById(R.id.editTxtelimNomDocente);
		apellido = (EditText) findViewById(R.id.editTxtelimApeDocente);
		spnConsultarCodDocente = (Spinner) findViewById(R.id.spnelimIdDocente);
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
				Docente Docente = new Docente();
				helper.abrir();
				Docente = helper.ConsultarDocente(docente);
				helper.cerrar();

				nombre.setText(Docente.getNombre());
				apellido.setText(Docente.getApellido());
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

		nombre.setText(Docente.getNombre());
		apellido.setText(Docente.getApellido());
	}

	public void EliminarDocente(View v) {
		Docente Doc = new Docente();
		Doc.setIdDocente(spnConsultarCodDocente.getSelectedItem().toString());
		
		helper.abrir();
		String estado = helper.EliminarDocente(Doc);
		helper.cerrar();
		nombre.setText("");
		apellido.setText("");
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.eliminar_docente, menu);
		return true;
	}

}
