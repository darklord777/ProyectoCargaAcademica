package sv.ues.fia.cargaacademicaeisi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DocenteCargoMenuActivity extends Activity implements OnClickListener{
	Button boton_insertar;
    Button boton_eliminar;
	Button boton_actualizar;
	Button boton_consultar;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_docente_cargo_menu);
		boton_insertar = (Button) findViewById(R.id.btn1NuevoCargo_docente);
		boton_insertar.setOnClickListener(this);
		boton_eliminar = (Button) findViewById(R.id.btn4EliminarNvoCargo_docente);
		boton_eliminar.setOnClickListener(this);
		boton_consultar= (Button) findViewById(R.id.btn2ConsultarNvoDCargo_docente);
		boton_consultar.setOnClickListener(this);
	}
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1NuevoCargo_docente:
			 startActivity(new Intent(getApplicationContext(),DocenteCargoInsertarActivity.class));
			break;
		case R.id.btn4EliminarNvoCargo_docente:
			startActivity(new Intent(getApplicationContext(),DocenteCargoEliminarActivity.class));
			break;
		case R.id.btn2ConsultarNvoDCargo_docente:
			startActivity(new Intent(getApplicationContext(),DocenteCargoConsultarActivity.class));
			break;
		
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.docente_cargo_menu, menu);
		return true;
	}

}
