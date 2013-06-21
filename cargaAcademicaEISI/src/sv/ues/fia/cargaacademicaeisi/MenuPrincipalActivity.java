package sv.ues.fia.cargaacademicaeisi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MenuPrincipalActivity extends Activity implements OnClickListener {
	private Button btnAdmonCat;
	private Button btnA_CargaAcademica;
	private Button btnDocentes;
	private Button btnAdmonActAcad;
	private Button btnMaterias;
	private ControlDB helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);
		helper = new ControlDB(this);
		btnAdmonCat = (Button) findViewById(R.id.btnAdmonCat);
		btnAdmonCat.setOnClickListener(this);
		btnA_CargaAcademica = (Button) findViewById(R.id.btn5A_CargaAcademica);
		btnA_CargaAcademica.setOnClickListener(this);
		btnDocentes = (Button) findViewById(R.id.btnDocentes);
		btnDocentes.setOnClickListener(this);
		btnAdmonActAcad = (Button) findViewById(R.id.btnActividadAcademica);
		btnAdmonActAcad.setOnClickListener(this);
		btnMaterias = (Button) findViewById(R.id.btnMaterias);
		btnMaterias.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAdmonCat:
			startActivity(new Intent(getApplicationContext(),
					AdminCatalogosActivity.class));
			break;

		case R.id.btn5A_CargaAcademica:
			startActivity(new Intent(getApplicationContext(),
					GestionCargaAcadActivity.class));
			break;

		case R.id.btnDocentes:
			startActivity(new Intent(getApplicationContext(),
					MenuDocenteActivity.class));
			break;

		case R.id.btnActividadAcademica:
			startActivity(new Intent(getApplicationContext(),
					MenuActAcademicasActivity.class));
			break;

		case R.id.btnMaterias:
			startActivity(new Intent(getApplicationContext(),
					MenuMateriaActivity.class));
			break;

		default:
			break;
		}
	}
	
	// menu item ------------------
	@Override
	public boolean onOptionsItemSelected(MenuItem item) { 

		switch (item.getItemId()) {
		case R.id.WS:
			startActivity(new Intent(getApplicationContext(),
					MenuServiciosWebActivity.class));
			return true;
		case R.id.llenarbd:
			helper.abrir();		
			String mensaje = helper.llenarBD();
			helper.cerrar();
			Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	

}//fin class principal
