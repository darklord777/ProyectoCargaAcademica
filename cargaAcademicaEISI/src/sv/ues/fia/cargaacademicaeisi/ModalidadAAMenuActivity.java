package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ModalidadAAMenuActivity extends Activity implements OnClickListener{

	private Button modalidad_insertar;
	private Button modalidad_consultar;
	private Button modalidad_eliminar;
	private Button modalidad_actualizar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modalidad_aamenu);
		
		modalidad_insertar = (Button) findViewById(R.id.btn_MenuModalidad_Ins);
		modalidad_insertar.setOnClickListener(this);
		modalidad_consultar = (Button) findViewById(R.id.btn_MenuModalidad_Cons);;
		modalidad_consultar.setOnClickListener(this);
		modalidad_eliminar = (Button) findViewById(R.id.btn_MenuModalidad_Elim);;
		modalidad_eliminar.setOnClickListener(this);
		modalidad_actualizar = (Button) findViewById(R.id.btn_MenuModalidad_Act);;
		modalidad_actualizar.setOnClickListener(this);		
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_MenuModalidad_Ins:
			startActivity(new Intent(getApplicationContext(),
					 InsertarModalidadActAcadActivity.class));
			break;
		case R.id.btn_MenuModalidad_Cons:
			startActivity(new Intent(getApplicationContext(),
					ModActAcadConsultarMainActivity.class));
			break;
		case R.id.btn_MenuModalidad_Elim:
			startActivity(new Intent(getApplicationContext(),
					ModActAcadEliminarActivity.class));
			break;
		case R.id.btn_MenuModalidad_Act:
			startActivity(new Intent(getApplicationContext(),
					ModActAcadActualizarActivity.class));
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modalidad_aamenu, menu);
		return true;
	}

	

}
