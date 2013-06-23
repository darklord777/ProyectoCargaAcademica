package sv.ues.fia.cargaacademicaeisi;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MateriaInsertarWebServiceActivity extends Activity {

	EditText codigomateriaTxt;
	EditText nombremateriaTxt;
	EditText fechaTxt;
	
	private String urlExterno = "http://etapa2pdm115.netai.net/ws_db_tarea_materia_insert.php";
	private String urlLocal = "http://127.0.0.1:8080/MateriaWebApplication/webresources/sv.ues.fia.tablamateria.entidad.materia/";

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materia_insertar_web_service);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		codigomateriaTxt = (EditText) findViewById(R.id.editTextCodigo);
		nombremateriaTxt = (EditText) findViewById(R.id.editTextNombre);
		fechaTxt = (EditText) findViewById(R.id.editTextFecha);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.materia_insertar_web, menu);
		return true;
	}

public void insertarMateria(View v) {
		
		String codigomat = codigomateriaTxt.getText().toString();
		String nombremat = nombremateriaTxt.getText().toString();
		String fecha = fechaTxt.getText().toString();
		
		String url = null;
		
		switch (v.getId()) {
		case R.id.btnLocal:
			JSONObject datosMateria = new JSONObject();
			JSONObject materia = new JSONObject(); 
			try {
				datosMateria.put("codigomateria", codigomat);
				datosMateria.put("nommateria", nombremat);
				datosMateria.put("fecha", fecha);
				
				ControladorServicio.insertarMateriaLocal(urlLocal, materia, this);
			} catch (Exception e) {
				Toast.makeText(this, "Error en los datos", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.btnExterno:
			url = urlExterno + "?codigomateria=" + codigomat + "&nommateria=" + nombremat + "&fecha=" + fecha;
			ControladorServicio.insertarMateriaExterno(url, this); 
			break;
		}
	}
}
