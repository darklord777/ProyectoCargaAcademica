package sv.ues.fia.cargaacademicaeisi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CargoMenuActivity extends Activity implements OnClickListener {
	Button boton_insertar;
    Button boton_eliminar;
	Button boton_actualizar;
	Button boton_consultar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cargo_menu);
		boton_insertar = (Button) findViewById(R.id.btnNuevoDocente_Cargo);
		boton_insertar.setOnClickListener(this);
		boton_eliminar = (Button) findViewById(R.id.btnEliminarNvoDocente_Cargo);
		boton_eliminar.setOnClickListener(this);
		boton_actualizar= (Button) findViewById(R.id.btnActualizarNvoDocente_Cargo);
		boton_actualizar.setOnClickListener(this);
		boton_consultar= (Button) findViewById(R.id.btnConsultarNvoDocente_Cargo);
		boton_consultar.setOnClickListener(this);
		}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnNuevoDocente_Cargo:
			 startActivity(new Intent(getApplicationContext(),CargoInsertarActivity.class));
			break;
		case R.id.btnEliminarNvoDocente_Cargo:
			startActivity(new Intent(getApplicationContext(),CargoEliminarActivity.class));
			break;
		case R.id.btnActualizarNvoDocente_Cargo:
			startActivity(new Intent(getApplicationContext(),CargoActualizarActivity.class));
			break;
		case R.id.btnConsultarNvoDocente_Cargo:
			startActivity(new Intent(getApplicationContext(),CargoConsultarActivity.class));
			break;
		
		}
	}
}