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
import android.widget.AdapterView.OnItemSelectedListener;

public class ConsultarMateriasImpartirActivity extends Activity {
	private ControlDB helper;
	private Spinner spnConsultarDocente;
	private Spinner spnConsultarMatArea;
	private EditText nombre;
	private EditText apellido;
	private EditText MatArea;
	private String docente;
	private String idmatarea;
	private List<String> idareamateria;
	private ArrayAdapter<String> adapter;
	private List<String> iddocente;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultar_materias_impartir);
		helper = new ControlDB(this);
		nombre = (EditText) findViewById(R.id.editTxtConsulNomMatImp);
		apellido = (EditText) findViewById(R.id.editTxtConsultApellidoMatImp);
		MatArea = (EditText) findViewById(R.id.editTxtConsulNomDepMatImp);
		spnConsultarMatArea = (Spinner) findViewById(R.id.spnConsulIdDepMatImp);
		spnConsultarDocente = (Spinner) findViewById(R.id.spnConsulIdDocMatImp);
		carga1();
	}

	private void carga1() {
		// TODO Auto-generated method stub
		helper.abrir();
		iddocente = helper.getAllIdDocMatImp();
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
		idareamateria = helper.getAllIdMatArea2(docente);
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idareamateria);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnConsultarMatArea.setAdapter(adapter);
		
		spnConsultarMatArea.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				idmatarea = parentView.getItemAtPosition(position).toString();
				Docente Docente = new Docente();
				AreaMateria depto = new AreaMateria();
				helper.abrir();
				Docente = helper.ConsultarDocente2(docente);
				depto = helper.ConsultarAreaMat(idmatarea);
				helper.cerrar();
				
				nombre.setText(Docente.getNombre());
				apellido.setText(Docente.getApellido());
				MatArea.setText(depto.getIdareamat());
			}

			public void onNothingSelected(AdapterView<?> parentView) {
				
			}
		});
	}

	protected void consulta() {
		// TODO Auto-generated method stub
		Docente Docente = new Docente();
		AreaMateria depto = new AreaMateria();
		helper.abrir();
		Docente = helper.ConsultarDocente2(docente);
		depto = helper.ConsultarAreaMat(idmatarea);
		helper.cerrar();
		
		nombre.setText(Docente.getNombre());
		apellido.setText(Docente.getApellido());
		MatArea.setText(depto.getIdareamat());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.consultar_materias_impartir, menu);
		return true;
	}

}
