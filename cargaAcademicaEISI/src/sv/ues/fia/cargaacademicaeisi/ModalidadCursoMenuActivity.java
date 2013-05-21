package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ModalidadCursoMenuActivity extends Activity implements OnClickListener{

	private Button modalidadC_insertar;
	private Button modalidadC_consultar;
	private Button modalidadC_eliminar;
	private Button modalidadC_actualizar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modalidad_curso_menu);
		
		modalidadC_insertar = (Button) findViewById(R.id.btn_MenuModalidadCurso_Ins);
		modalidadC_insertar.setOnClickListener(this);
		modalidadC_consultar = (Button) findViewById(R.id.btn_MenuModalidadCurso_Cons);
		modalidadC_consultar.setOnClickListener(this);
		modalidadC_eliminar = (Button) findViewById(R.id.btn_MenuModalidadCurso_Elim);
		modalidadC_eliminar.setOnClickListener(this);
		modalidadC_actualizar = (Button) findViewById(R.id.btn_MenuModalidadCurso_Act);
		modalidadC_actualizar.setOnClickListener(this);		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_MenuModalidadCurso_Ins:
			startActivity(new Intent(getApplicationContext(), InsertarModalidadCursoActivity.class));  
			break;
		case R.id.btn_MenuModalidadCurso_Cons:
			startActivity(new Intent(getApplicationContext(), ModalidadCursoConsultarActivity.class));			
			break;
		case R.id.btn_MenuModalidadCurso_Elim:
			startActivity(new Intent(getApplicationContext(), ModalidadCursoEliminarActivity.class));
			break;
		case R.id.btn_MenuModalidadCurso_Act:
			startActivity(new Intent(getApplicationContext(), ModalidadCursoActualizarActivity.class));
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modalidad_curso_menu, menu);
		return true;
	}

	

}
