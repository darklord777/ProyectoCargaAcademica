package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CicloMenuActivity extends Activity implements OnClickListener {
	private Button btnA_ciclo_insertar;
	private Button btnA_ciclo_eliminar;
	private Button btnA_ciclo_consultar;
	private Button btnA_ciclo_actualizar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ciclo_menu);
		btnA_ciclo_insertar = (Button) findViewById(R.id.button4_MenuCiclo_D);
		btnA_ciclo_insertar.setOnClickListener(this);
		btnA_ciclo_eliminar = (Button) findViewById(R.id.button1_MenuCiclo_A);
		btnA_ciclo_eliminar.setOnClickListener(this);
		btnA_ciclo_consultar = (Button) findViewById(R.id.button2_MenuCiclo_B);
		btnA_ciclo_consultar.setOnClickListener(this);
		btnA_ciclo_actualizar = (Button) findViewById(R.id.button3_MenuCiclo_C);
		btnA_ciclo_actualizar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button4_MenuCiclo_D:
			 startActivity(new Intent(getApplicationContext(),
			 CicloInsertarActivity.class));
			break;
		case R.id.button1_MenuCiclo_A:
			 startActivity(new Intent(getApplicationContext(),
			 CicloEliminarActivity.class));
			break;
		case R.id.button2_MenuCiclo_B:
			startActivity(new Intent(getApplicationContext(),
			CicloConsultarActivity.class));
			break;
		case R.id.button3_MenuCiclo_C:
			 startActivity(new Intent(getApplicationContext(),
			 CicloActualizarActivity.class));
			break;
		default:
			break;
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ciclo_menu, menu);
		return true;
	}

}