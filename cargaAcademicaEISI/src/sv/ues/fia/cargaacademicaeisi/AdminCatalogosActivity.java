package sv.ues.fia.cargaacademicaeisi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminCatalogosActivity extends Activity implements
		android.view.View.OnClickListener {
	private ControlDB helper;
	private Button btnAdmonDepto;
	private Button btnAdmonMateria;
	private Button btnMostrarTablas;
	private Button btnAdmonAreaMateria;
	private Button btncontrato;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_catalogos);
		helper = new ControlDB(this);
		btnAdmonDepto = (Button) findViewById(R.id.btnAdmonDepto);
		btnAdmonDepto.setOnClickListener(this);
		btnAdmonMateria = (Button) findViewById(R.id.btnAdmonMateria);
		btnAdmonMateria.setOnClickListener(this);
		btnMostrarTablas = (Button) findViewById(R.id.btnMostrarTablas);
		btnMostrarTablas.setOnClickListener(this);
		btnAdmonAreaMateria = (Button) findViewById(R.id.btnAdmonAreaMateria);
		btnAdmonAreaMateria.setOnClickListener(this);
		btncontrato = (Button) findViewById(R.id.BtnTipoCont);
		btncontrato.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.admin_catalogos, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAdmonDepto:
			startActivity(new Intent(getApplicationContext(),
					AdmonDeptoActivity.class));
			break;
		case R.id.btnAdmonMateria:
			startActivity(new Intent(getApplicationContext(),
					AdmonMateriaActivity.class));
			break;
		case R.id.btnAdmonAreaMateria:
			startActivity(new Intent(getApplicationContext(),
					AdmonAreaMatActivity.class));
			break;
		case R.id.btnMostrarTablas:
			helper.abrir();
			Toast.makeText(this, helper.consultarTablas(), Toast.LENGTH_LONG)
					.show();
			helper.cerrar();
			break;
		case R.id.BtnTipoCont:
			startActivity(new Intent(getApplicationContext(), 
					AdminTipoContratoActivity.class));
			break;
					
		default:
			break;
		}
	}

}
