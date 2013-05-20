package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import sv.ues.fia.cargaacademicaeisi.R;
import android.content.Intent;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MenuDocenteActivity extends Activity implements OnClickListener {
	private Button botonDocente;
	private Button botonDepartamento;
	private Button botonMateriasImpartir;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_docente);
		botonDocente = (Button) findViewById(R.id.botonDocente);
		botonDocente.setOnClickListener(this);
		botonDepartamento = (Button) findViewById(R.id.botonDepartamento);
		botonDepartamento.setOnClickListener(this);
		botonMateriasImpartir = (Button) findViewById(R.id.botonMateriasImpartir);
		botonMateriasImpartir.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_docente, menu);
		return true;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.botonDocente:
			startActivity(new Intent(getApplicationContext(), 
					AdminDocentesActivity.class));
		break;
		
		case R.id.botonDepartamento:
			startActivity(new Intent(getApplicationContext(), 
					AdminDocenteDeptoActivity.class));
		break;
		
		case R.id.botonMateriasImpartir:
			startActivity(new Intent(getApplicationContext(), 
					AdminMateriasImpartirActivity.class));
		break;
		
		default:
		break;
		}
	}

}
