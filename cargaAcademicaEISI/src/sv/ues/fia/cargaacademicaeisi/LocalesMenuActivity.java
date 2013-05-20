package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LocalesMenuActivity extends Activity implements OnClickListener{

	private Button local_insertar;
	private Button local_consultar;
	private Button local_eliminar;
	private Button local_actualizar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locales_menu);
		
		local_insertar = (Button) findViewById(R.id.btn_MenuLocal_Ins);
		local_insertar.setOnClickListener(this);
		local_consultar = (Button) findViewById(R.id.btn_MenuLocal_Cons);
		local_consultar.setOnClickListener(this);
		local_eliminar = (Button) findViewById(R.id.btn_MenuLocal_Elim);
		local_eliminar.setOnClickListener(this);
		local_actualizar = (Button) findViewById(R.id.btn_MenuLocal_Act);
		local_actualizar.setOnClickListener(this);		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_MenuLocal_Ins:
			startActivity(new Intent(getApplicationContext(),
					 InsertarLocalesActivity.class));
			break;
		case R.id.btn_MenuLocal_Cons:
			startActivity(new Intent(getApplicationContext(),
					 LocalesConsultarActivity.class));
			break;
		case R.id.btn_MenuLocal_Elim:
			startActivity(new Intent(getApplicationContext(),
					 LocalesEliminarActivity.class));
			break;
		case R.id.btn_MenuLocal_Act:
			break;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.locales_menu, menu);
		return true;
	}

	

}
