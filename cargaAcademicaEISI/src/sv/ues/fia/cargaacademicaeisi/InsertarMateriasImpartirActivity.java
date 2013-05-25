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

public class InsertarMateriasImpartirActivity extends Activity {
	private ControlDB helper;
	private Spinner spnConsultarDocente;
	private Spinner spnConsultarMatArea;
	private EditText nombre;
	private EditText apellido;
	private EditText materia;
	private String docente;
	private String idMatArea;
	private List<String> idMateria;
	private ArrayAdapter<String> adapter;
	private List<String> iddocente;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insertar_materias_impartir);
		helper = new ControlDB(this);
		nombre = (EditText) findViewById(R.id.editTxtInsertNomMatImp);
		apellido = (EditText) findViewById(R.id.editTxtInsertApeMatImp);
		materia = (EditText) findViewById(R.id.editTxtInsertNomDepMatImp);
		spnConsultarMatArea = (Spinner) findViewById(R.id.spnInsertIdDepMatImp);
		spnConsultarDocente = (Spinner) findViewById(R.id.spnInsertIdDocMatImp);
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
		idMateria = helper.getAllIdMatArea();
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idMateria);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnConsultarMatArea.setAdapter(adapter);
		
		spnConsultarMatArea.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				idMatArea = parentView.getItemAtPosition(position).toString();
				Docente Docente = new Docente();
				AreaMateria matarea= new AreaMateria();
				helper.abrir();
				Docente = helper.ConsultarDocente2(docente);
				matarea = helper.ConsultarAreaMat(idMatArea);
				helper.cerrar();
				
				nombre.setText(Docente.getNombre());
				apellido.setText(Docente.getApellido());
				materia.setText(matarea.getIdareamat());
			}

			public void onNothingSelected(AdapterView<?> parentView) {
				
			}
		});
	}
	
	protected void consulta() {
		// TODO Auto-generated method stub
		Docente Docente = new Docente();
		AreaMateria matarea= new AreaMateria();
		helper.abrir();
		Docente = helper.ConsultarDocente2(docente);
		matarea = helper.ConsultarAreaMat(idMatArea);
		helper.cerrar();
		
		nombre.setText(Docente.getNombre());
		apellido.setText(Docente.getApellido());
		materia.setText(matarea.getIdareamat());
	}

	public void insertDocMatImp(View v) {
		String regInsertados;
		MateriasImpartir docente = new MateriasImpartir();
		docente.setIdAreaMat(spnConsultarMatArea.getSelectedItem().toString());
		docente.setIdDocente(spnConsultarDocente.getSelectedItem().toString());
		helper.abrir();
		regInsertados = helper.InsertarMatImpart(docente);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
	}
	
	public void limpiarDocenteDepto(View v) {
		nombre.setText("");
		apellido.setText("");
		materia.setText("");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insertar_materias_impartir, menu);
		return true;
	}

}
