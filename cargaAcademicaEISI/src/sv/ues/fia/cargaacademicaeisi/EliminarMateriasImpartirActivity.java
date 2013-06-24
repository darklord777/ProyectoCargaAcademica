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

public class EliminarMateriasImpartirActivity extends Activity {
	private ControlDB helper;
	private Spinner spnConsultarDocente;
	private Spinner spnConsultarArea;
	private EditText nombre;
	private EditText apellido;
	private EditText Area;
	private String docente;
	private String idarea;
	private List<String> idareamat;
	private ArrayAdapter<String> adapter;
	private List<String> iddocente;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eliminar_materias_impartir);
		helper = new ControlDB(this);
		nombre = (EditText) findViewById(R.id.editTxtEliminarNomMatImp);
		apellido = (EditText) findViewById(R.id.editTxtEliminarApellidoMatImp);
		Area = (EditText) findViewById(R.id.editTxtEliminarAreaMatImp);
		spnConsultarArea = (Spinner) findViewById(R.id.spnEliminarAreaMatImp);
		spnConsultarDocente = (Spinner) findViewById(R.id.spnEliminarDoceMatImp);
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
		idareamat = helper.getAllIdAreaMatImp4(docente);
		helper.cerrar();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, idareamat);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnConsultarArea.setAdapter(adapter);
		
		spnConsultarArea.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				idarea = parentView.getItemAtPosition(position).toString();
				AreaMateria matarea = new AreaMateria();
				Docente Docente = new Docente();
				helper.abrir();
				matarea = helper.ConsultarAreaMat(idarea);
				Docente = helper.ConsultarDocente2(docente);
				helper.cerrar();
				nombre.setText(Docente.getNombre());
				apellido.setText(Docente.getApellido());
				Area.setText(matarea.getIdareamat());
			}

			public void onNothingSelected(AdapterView<?> parentView) {
				
			}
		});
	}
	
	public void EliminarMatImpar(View v) {
		MateriasImpartir mate = new MateriasImpartir();
		mate.setIdDocente(spnConsultarDocente.getSelectedItem().toString());
		mate.setIdAreaMat(spnConsultarArea.getSelectedItem().toString());
		helper.abrir();
		String estado = helper.EliminarMateriaImpartir(mate);
		helper.cerrar();
		nombre.setText("");
		apellido.setText("");
		Area.setText("");
		Toast.makeText(this, estado, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.eliminar_materias_impartir, menu);
		return true;
	}

}
