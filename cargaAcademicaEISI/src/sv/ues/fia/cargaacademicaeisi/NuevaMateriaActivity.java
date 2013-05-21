package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NuevaMateriaActivity extends Activity {
	private ControlDB helper;
	private EditText edtCodMateria;
	private EditText edtNomMateria;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nueva_materia);
		helper = new ControlDB(this);
		edtCodMateria = (EditText) findViewById(R.id.edtCodMateria);
		edtNomMateria = (EditText) findViewById(R.id.edtNomMateria);
	}

	public void guardarMateria(View v) {
		if (!edtCodMateria.getText().toString().trim().equals("")
				&& !edtNomMateria.getText().toString().trim().equals("")) {
			Materia materia = new Materia();
			materia.setCodigomateria(edtCodMateria.getText().toString());
			materia.setNom_materia(edtNomMateria.getText().toString());
			helper.abrir();
			String regInsertados = helper.insertar(materia);
			helper.cerrar();
			Toast.makeText(
					this,
					regInsertados + " : " + materia.getCodigomateria() + " : "
							+ materia.getNom_materia(), Toast.LENGTH_LONG)
					.show();
		} else {
			Toast.makeText(this, "Los campos son Obligatorios.",
					Toast.LENGTH_LONG).show();
		}

	}

	public void limpiarEntradas(View v) {
		edtCodMateria.setText("");
		edtNomMateria.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nueva_materia, menu);
		return true;
	}

}
