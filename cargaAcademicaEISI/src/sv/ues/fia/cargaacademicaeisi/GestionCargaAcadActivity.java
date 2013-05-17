package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GestionCargaAcadActivity extends Activity implements
		OnClickListener {
	private Button btnA_tabla_ciclo;
	private Button btnA_tabla_cargaacademica;
	private Button btnA_tabla_cargamaterias;
	private Button btnA_tabla_cargaactividades;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gestion_carga_acad);
		btnA_tabla_ciclo = (Button) findViewById(R.id.btn1TablaCiclo);
		btnA_tabla_ciclo.setOnClickListener(this);
		btnA_tabla_cargaacademica = (Button) findViewById(R.id.btn2TablaCargaAcademica);
		btnA_tabla_cargaacademica.setOnClickListener(this);
		btnA_tabla_cargamaterias = (Button) findViewById(R.id.btn3DetCargaMateria);
		btnA_tabla_cargamaterias.setOnClickListener(this);
		btnA_tabla_cargaactividades = (Button) findViewById(R.id.btn4DetCargaActAcademica);
		btnA_tabla_cargaactividades.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1TablaCiclo:
			 startActivity(new Intent(getApplicationContext(),
			 CicloInsertarActivity.class));
			break;
		case R.id.btn2TablaCargaAcademica:
			// startActivity(new Intent(getApplicationContext(),
			// GestionCargaAcadActivity.class));
			break;
		case R.id.btn3DetCargaMateria:
			// startActivity(new Intent(getApplicationContext(),
			// GestionCargaAcadActivity.class));
			break;
		case R.id.btn4DetCargaActAcademica:
			// startActivity(new Intent(getApplicationContext(),
			// GestionCargaAcadActivity.class));
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gestion_carga_acad, menu);
		return true;
	}

}
