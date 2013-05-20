package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CargaAcademicaMenuActivity extends Activity implements OnClickListener {
	private Button btnA_cargaacademica_insertar;
	private Button btnA_cargaacademica_eliminar;
	private Button btnA_cargaacademica_consultar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carga_academica_menu);
		btnA_cargaacademica_insertar = (Button) findViewById(R.id.button1_MenuCARGAACADEMICA_Insert);
		btnA_cargaacademica_insertar.setOnClickListener(this);
		btnA_cargaacademica_eliminar = (Button) findViewById(R.id.button2_MenuCARGAACADEMICA_Delete);
		btnA_cargaacademica_eliminar.setOnClickListener(this);
		btnA_cargaacademica_consultar = (Button) findViewById(R.id.button3_MenuCARGAACADEMICA_Consult);
		btnA_cargaacademica_consultar.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1_MenuCARGAACADEMICA_Insert:
			 startActivity(new Intent(getApplicationContext(),
			 CargaAcademicaInsertarActivity.class));
			break;
		case R.id.button2_MenuCARGAACADEMICA_Delete:
			 startActivity(new Intent(getApplicationContext(),
			 CargaAcademicaEliminarActivity.class));
			break;
		case R.id.button3_MenuCARGAACADEMICA_Consult:
			startActivity(new Intent(getApplicationContext(),
			CargaAcademicaConsultarActivity.class));
			break;

		default:
			break;
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.carga_academica_menu, menu);
		return true;
	}

}
