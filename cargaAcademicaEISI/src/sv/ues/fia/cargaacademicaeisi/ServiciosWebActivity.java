package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServiciosWebActivity extends Activity implements OnClickListener {

	private Button btnIngresarMateria;
	private Button btnActualizarMateria;
	private Button btnEliminarMateria;
	private ControlDB helper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_servicios_web);
		helper = new ControlDB(this);
		btnIngresarMateria = (Button) findViewById(R.id.btnIngresarMateria);
		btnIngresarMateria.setOnClickListener(this);
		btnActualizarMateria = (Button) findViewById(R.id.btnActualizarMateria);
		btnActualizarMateria.setOnClickListener(this);
		btnEliminarMateria = (Button) findViewById(R.id.btnEliminarMateria);
		btnEliminarMateria.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.servicios_web, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnIngresarMateria:
			startActivity(new Intent(getApplicationContext(),
					MateriaInsertarWebServiceActivity.class));
			break;
		case R.id.btnActualizarMateria:
			startActivity(new Intent(getApplicationContext(),
					MateriaActualizarWebServiceActivity.class));
			break;
		case R.id.btnEliminarMateria:
			startActivity(new Intent(getApplicationContext(),
					MateriaEliminarWebServiceActivity.class));
			break;
		}
	}

}
