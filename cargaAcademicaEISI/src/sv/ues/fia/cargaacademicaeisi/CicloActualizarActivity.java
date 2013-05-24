package sv.ues.fia.cargaacademicaeisi;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CicloActualizarActivity extends Activity {
	ControlDB helper;
	private Spinner spinner_anio;
	private Spinner spinner_ciclo;
	private String año;
	private String ciclo1;
	private Button boton1_fechainicio;
	private Button boton1_fechafin;
	private TextView fechaini;
	private TextView fechafin;
	private String fecha_ini;
	private String fecha_fin;
	private int year;
	private int month;
	private int day;
	private int year2;
	private int month2;
	private int day2;
	static final int DATE_DIALOG_ID = 999;
	private int var;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ciclo_actualizar);
		helper = new ControlDB(this);
		this.spinner_anio = (Spinner) findViewById(R.id.spinnera1_cicloanio_consultar);
		this.spinner_ciclo = (Spinner) findViewById(R.id.spinnera2_ciclociclo_consultar);
		loadSpinnerData(); // 1 HACE CONSULTA Y CARGA LOS LABELS EN EL SPINNER
							// AÑOS
		this.boton1_fechainicio =(Button) findViewById(R.id.button1_fechainicio);
		this.boton1_fechafin =(Button) findViewById(R.id.button2_fechafin);
		this.fechaini= (TextView) findViewById(R.id.textView1_ini);
		this.fechafin= (TextView) findViewById(R.id.textView2_fin);
		addListenerOnButton();
	}

	private void loadSpinnerData() {
		// COPIAN A SU ACTIVITY SOLO CAMBIAN LA CONSULTA !!!!!
		String selectQuery = "SELECT distinct ANIO FROM CICLO";
		// LLAMA A getAllLabels en ControlDB !!! importante
		List<String> lables = helper.getAllLabels(selectQuery, 0);

		// creando ADAPTER para el Spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);

		// vista de lista con el botón
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// adaptador de datos para spinner
		spinner_anio.setAdapter(dataAdapter);

		// Guardando valor selecionado en variable para consulta posterior
		spinner_anio.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				// guardo en el string año el valor selecionado del spinner
				año = parentView.getItemAtPosition(position).toString();
				loadSpinnerData2();
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
	}

	/**
	 * Función para cargar los datos de la base de datos SQLite spinner
	 * */
	private void loadSpinnerData2() {
		String selectQuery = "SELECT distinct NUMERO FROM CICLO WHERE ANIO='"
				+ año + "'";
		List<String> lables = helper.getAllLabels(selectQuery, 0);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner_ciclo.setAdapter(dataAdapter);

		spinner_ciclo.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				ciclo1 = parentView.getItemAtPosition(position).toString();
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
	}

	public void consultarCiclo(View v) {

		helper.abrir();
		Ciclo ciclo = helper.consultarCiclo(año, ciclo1);
		helper.cerrar();
		if (ciclo == null)
			Toast.makeText(this, "Ciclo No Encontrado", Toast.LENGTH_LONG)
					.show();
		else {
			fechaini.setText(String.valueOf(ciclo.getFechaini()));
			fechafin.setText(String.valueOf(ciclo.getFechafin()));
		}
	}

	public void actualizarCiclo(View v) {
		fecha_ini=fechaini.getText().toString();
		fecha_fin=fechafin.getText().toString();


		if (fecha_ini.equalsIgnoreCase("") || fecha_fin.equalsIgnoreCase("")) {
			String msj = "Importante: Todos los campos son obligatorios!";
			Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
		} else {
			if(year<=year2 && month<month2){
				if(year==Integer.parseInt(año) && year2==Integer.parseInt(año)){
					//******************************
					Ciclo ciclo = new Ciclo();
					ciclo.setAnio(año);
					ciclo.setNumero(ciclo1);
					ciclo.setFechaini(fechaini.getText().toString());
					ciclo.setFechafin(fechafin.getText().toString());
					helper.abrir();
					String estado = helper.actualizar(ciclo);
					helper.cerrar();
					Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
					fechaini.setText("");
					fechafin.setText("");
					//valores por defecto spinner
					spinner_anio.setSelection(0);
					spinner_ciclo.setSelection(0);
					//*******************************
				}else{
					String msj3 = "Importante: El año de fecha inicio y fin debe coincidir con el año del ciclo que selecionado anteriormente!";
					Toast.makeText(this, msj3, Toast.LENGTH_SHORT).show();
				}//fin else año correcto segun spinner
			
			}
			else{
				String msj2 = "Importante: La fecha de inicio de Ciclo debe ser Menor que la Fecha Fin!";
				Toast.makeText(this, msj2, Toast.LENGTH_SHORT).show();
		
			}//fin else validacion fecha
	   }//fin else validacion campos vacios
		
		
		
	}

	
	//**********************************************************************
		//METODOS DE AGREGAR FECHA
			public void addListenerOnButton() {
				final Calendar c = Calendar.getInstance();
				year = c.get(Calendar.YEAR);
				month = c.get(Calendar.MONTH);
				day = c.get(Calendar.DAY_OF_MONTH);

				boton1_fechainicio.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						showDialog(DATE_DIALOG_ID);
						var=0;
					}

				});
				boton1_fechafin.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						showDialog(DATE_DIALOG_ID);
						var=1;
					}

				});

			}//fin addListenerOnButton

			@Override
			protected Dialog onCreateDialog(int id) {
				switch (id) {
				case DATE_DIALOG_ID:
					// set date picker as current date
					return new DatePickerDialog(this, datePickerListener, year, month,
							day);	
				}
				return null;
			}//fin onCreateDialog

			private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

				// when dialog box is closed, below method will be called.
				public void onDateSet(DatePicker view, int selectedYear,
						int selectedMonth, int selectedDay) {

					// set selected date into textview
					if(var==0){
						year = selectedYear;
						month = selectedMonth;
						day = selectedDay;
						fechaini.setText(new StringBuilder().append(day)
							.append("/").append(month + 1).append("/").append(year)
							.append(""));
					}
					else{
						year2 = selectedYear;
						month2 = selectedMonth;
						day2 = selectedDay;
						fechafin.setText(new StringBuilder().append(day2)
								.append("/").append(month2 + 1).append("/").append(year2)
								.append(""));		
					}
				}
			};
			
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ciclo_actualizar, menu);
		return true;
	}

}
