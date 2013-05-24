package sv.ues.fia.cargaacademicaeisi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PeriodoMenuActivity extends Activity implements OnClickListener{
	Button boton_insertar;
    Button boton_eliminar;
	Button boton_actualizar;
	Button boton_consultar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_periodo_menu);
		boton_insertar = (Button) findViewById(R.id.btn1NuevoPeriodo);
		boton_insertar.setOnClickListener(this);
		boton_eliminar = (Button) findViewById(R.id.btn4EliminarNvoDocente_Cargo);
		boton_eliminar.setOnClickListener(this);
		boton_actualizar= (Button) findViewById(R.id.btn3ActualizarNvoDocente_Cargo);
		boton_actualizar.setOnClickListener(this);
		boton_consultar= (Button) findViewById(R.id.btn2ConsultarNvoDocente_Cargo);
		boton_consultar.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1NuevoPeriodo:
			 startActivity(new Intent(getApplicationContext(),PeriodoInsertarActivity.class));
			break;
		case R.id.btn4EliminarNvoDocente_Cargo:
			 startActivity(new Intent(getApplicationContext(),PeriodoEliminarActivity.class));
			break;
		case R.id.btn3ActualizarNvoDocente_Cargo:
			startActivity(new Intent(getApplicationContext(),PeriodoActualizarActivity.class));
			break;
		case R.id.btn2ConsultarNvoDocente_Cargo:
			 startActivity(new Intent(getApplicationContext(),PeridoConsultarActivity.class));
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.periodo_menu, menu);
		return true;
	}


}