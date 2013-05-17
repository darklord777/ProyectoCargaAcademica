package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertarLocalesActivity extends Activity {
	private ControlDB helper;
	private EditText idLocal;
	private EditText capLocal;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insertar_locales);
		helper = new ControlDB(this);
		idLocal = (EditText) findViewById(R.id.idLocal);
		capLocal = (EditText) findViewById(R.id.capLocal);
	}
	
	public void insertarLocal(View v) {
		String regInsertados;
		Locales local = new Locales();
		local.setIdlocal(idLocal.getText().toString());
		local.setCapacidad(capLocal.getText().toString());
		helper.abrir();
		regInsertados = helper.insertar(local);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insertar_locales, menu);
		return true;
	}

}
