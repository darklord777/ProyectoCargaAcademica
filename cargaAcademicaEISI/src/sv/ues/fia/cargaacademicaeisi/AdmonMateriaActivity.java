package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;

public class AdmonMateriaActivity extends Activity implements OnClickListener {
	private Button btnNuevaMateria;
	private Button btnConsultarMateria;
	private Button btnActMateria;
	private Button btnEliminarMateria;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admon_materia);
		btnNuevaMateria = (Button) findViewById(R.id.btnNuevaMateria);
		btnConsultarMateria = (Button) findViewById(R.id.btnConsultarMateria);
		btnActMateria = (Button) findViewById(R.id.btnActMateria);
		btnEliminarMateria = (Button) findViewById(R.id.btnEliminarMateria);
		btnNuevaMateria.setOnClickListener(this);
		btnConsultarMateria.setOnClickListener(this);
		btnActMateria.setOnClickListener(this);
		btnEliminarMateria.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.admon_materia, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnNuevaMateria:
			startActivity(new Intent(getApplicationContext(),
					NuevaMateriaActivity.class));
			break;
		case R.id.btnConsultarMateria:
			startActivity(new Intent(getApplicationContext(),
					ConsultarMateriaActivity.class));
			break;
		case R.id.btnActMateria:
			startActivity(new Intent(getApplicationContext(),
					ActualizarMateriaActivity.class));
			break;
		case R.id.btnEliminarMateria:
			startActivity(new Intent(getApplicationContext(),
					EliminarMateriaActivity.class));
			break;
		default:
			break;
		}
	}

}
