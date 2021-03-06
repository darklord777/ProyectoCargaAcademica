package sv.ues.fia.cargaacademicaeisi;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NuevoDeptoActivity extends Activity {
	private ControlDB helper;
	private EditText idDepto;
	private EditText nomDepto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		helper = new ControlDB(this);
		setContentView(R.layout.activity_nuevo_depto);
		idDepto = (EditText) findViewById(R.id.idDepto);
		nomDepto = (EditText) findViewById(R.id.nomDepto);
	}

	public void insertarDepto(View v) {
		if (!idDepto.getText().toString().trim().equals("")
				&& !nomDepto.getText().toString().trim().equals("")) {
			String regInsertados;
			Departamento departamento = new Departamento();
			departamento.setIddepartamento(idDepto.getText().toString().trim());
			departamento.setNom_depto(nomDepto.getText().toString().trim());
			helper.abrir();
			regInsertados = helper.insertar(departamento);
			helper.cerrar();
			Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this, "Los campos son obligatorios",
					Toast.LENGTH_LONG).show();
		}
	}

	public void limpiarDepto(View v) {
		idDepto.setText("");
		nomDepto.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nuevo_depto, menu);
		return true;
	}

}
