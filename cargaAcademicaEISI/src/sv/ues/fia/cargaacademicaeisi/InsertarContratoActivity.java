package sv.ues.fia.cargaacademicaeisi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertarContratoActivity extends Activity {
	private ControlDB helper;
	private EditText codigo;
	private EditText tipo;
	private EditText horas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insertar_contrato);
		helper = new ControlDB(this);
		codigo = (EditText) findViewById(R.id.editTxtInsertCodioTipoCont);
		tipo = (EditText) findViewById(R.id.editTxtInsertTipoTipoCont);
		horas = (EditText) findViewById(R.id.editTxtInsertHorasTipoCont);
	}

	public void insertContrato(View v) {
		String regInsertados;
		TipoContrato tipocontrato = new TipoContrato();
		tipocontrato.setIdContrato(codigo.getText().toString());
		tipocontrato.setTipo(tipo.getText().toString());
		tipocontrato.setHoras(Integer.valueOf(horas.getText().toString()));
		helper.abrir();
		regInsertados = helper.InsertarContrato(tipocontrato);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
	}
	
	public void limpiarContrato(View v) {
		codigo.setText("");
		tipo.setText("");
		horas.setText("");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insertar_contrato, menu);
		return true;
	}

}
