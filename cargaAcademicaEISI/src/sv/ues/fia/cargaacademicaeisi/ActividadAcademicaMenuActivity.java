package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActividadAcademicaMenuActivity extends Activity implements OnClickListener {
	
	private Button ActividadAcad_insertar;
	private Button ActividadAcad_consultar;
	private Button ActividadAcad_eliminar;
	private Button ActividadAcad_actualizar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actividad_academica_menu);
		
		 ActividadAcad_insertar = (Button) findViewById(R.id.btn_MenuActividadAcad_Ins);
		 ActividadAcad_insertar.setOnClickListener(this);
		 ActividadAcad_consultar = (Button) findViewById(R.id.btn_MenuActividadAcad_Cons);
		 ActividadAcad_consultar.setOnClickListener(this);
		 ActividadAcad_eliminar = (Button) findViewById(R.id.btn_MenuActividadAcad_Elim);
		 ActividadAcad_eliminar.setOnClickListener(this);
		 ActividadAcad_actualizar = (Button) findViewById(R.id.btn_MenuActividadAcad_Act);
		 ActividadAcad_actualizar.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_MenuActividadAcad_Ins:
			startActivity(new Intent(getApplicationContext(),
					InsertarActividadAcademicaActivity.class));			
			break;
		case R.id.btn_MenuActividadAcad_Cons:
			break;
		case R.id.btn_MenuActividadAcad_Elim:
			break;
		case R.id.btn_MenuActividadAcad_Act:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actividad_academica_menu, menu);
		return true;
	}

	

}
